package view;

import controller.Controller;
import view.listeners.MouseAdapterListener;
import view.listeners.MouseMotionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;

/**
 * Created by user on 21.05.2017.
 */
public  class Graphic extends JPanel {
   private MainWindow mainWindow;
   private int currWidth=500;
   private int currHeight=500;
    private double scale = 1;
    private Point oldPoint = new Point();
    private int currentX=0;
    private int currentY=0;

    public Point getOldPoint() {
        return oldPoint;
    }
    public void setOldPoint(Point oldPoint) {
        this.oldPoint = oldPoint;
    }
    public void scalePlus() {scale+=0.1;}
    public void scaleMinus() {scale-=0.1;}
    public void setCurrentX(int currentX) {
        this.currentX += currentX;
    }
    public void setCurrentY(int currentY) {
        this.currentY += currentY;
    }

    public Graphic(MainWindow mainWindow)
    {

        setSizee(currWidth,currHeight);
        this.mainWindow=mainWindow;

        addMouseListener(new MouseAdapterListener(this));
        addMouseMotionListener(new MouseMotionListener(mainWindow,this));

         wheelListener();

    }

    public void setSizee(int currWidth,int currHeight)
    {
        setPreferredSize(new Dimension(currWidth,currHeight));
        setSize(currWidth,currHeight);
    }

    public void wheelListener()
    {
        addMouseWheelListener(e -> {
            if (e.isControlDown()){
                if (e.getWheelRotation() > 0 && scale>0.2){

                    scaleMinus();

                    if(currWidth>scale*500)
                    {
                        currWidth=(int)(500*scale);
                        if(currWidth<500) currWidth=500;
                    }
                    if(currHeight>scale*500)
                    {
                        currHeight=(int)(500*scale);
                        if(currHeight<500) currHeight=500;
                    }
                    setPreferredSize(new Dimension(currWidth,currHeight));
                    setSize(currWidth,currHeight);
                    mainWindow.getLabelSize().setText(" Масштаб:"+(int)(scale*100)+"%");
                    mainWindow.getFrame().repaint();

                }
                else if (scale < 2 && e.getWheelRotation() < 0){

                    scalePlus();

                    if(currWidth<scale*500)
                        currWidth = (int) (500 * scale);
                    if(currHeight<scale*500)
                        currHeight = (int) (500 * scale);

                    setPreferredSize(new Dimension(currWidth, currHeight));
                    setSize(currWidth, currHeight);

                    mainWindow.getLabelSize().setText(" Масштаб:"+(int)(scale*100)+"%");
                    mainWindow.getFrame().repaint();
                }

            }
        });
    }

    protected void paintComponent(Graphics gh) {

        Graphics2D drp = (Graphics2D) gh;
        int sumEl = mainWindow.getTable().getTable().getRowCount();

        int maxEl = mainWindow.getTable().getMaxEl();

        Point endGraphic = new Point();
        endGraphic.x = (int) (scale * (0.15 * maxEl + 120));
        endGraphic.y = (int) (scale * (80 + 15 * (sumEl + 1)));

        if (currWidth != currentX + endGraphic.x + 100)
        {
            currWidth = currentX + endGraphic.x + 100;
            setSizee(currWidth,currHeight);
        }
        if(currHeight != currentY+endGraphic.y+100)
        {
            currHeight=currentY+endGraphic.y+100;
            setSizee(currWidth,currHeight);
        }

        if(currentX<0) currentX=0;
        if(currentY<0) currentY=0;

        drp.setFont(new Font(null, Font.PLAIN,  (int)(scale*(drp.getFont().getSize()))));
        drp.drawLine(currentX+(int)(scale*(30 )),currentY+(int)(scale*(30)),currentX+(int)(scale*( 30)),currentY+(int)(scale*(30+15*(sumEl+1) )));//---------ок
        drp.drawLine(currentX+(int)(scale*(30 )),currentY+(int)(scale*(30+15*(sumEl+1))),currentX+(int)(scale*(0.15*maxEl+70)),currentY+(int)(scale*(30+15*(sumEl+1))));//---------ок

        drp.drawLine(currentX+(int)(scale*(30 )),currentY+(int)(scale*(30 )),currentX+(int)(scale*( 35)),currentY+(int)(scale*(35 )));//---------ок
        drp.drawLine(currentX+(int)(scale*(30 )),currentY+(int)(scale*(30 )),currentX+(int)(scale*( 25)),currentY+(int)(scale*(35 )));//---------ок

        drp.drawLine(currentX+(int)(scale*(0.15*maxEl+70)),currentY+(int)(scale*(30+15*(sumEl+1))),currentX+(int)(scale*(0.15*maxEl+70-5)),currentY+(int)(scale*(35+15*(sumEl+1))));//---------ок
        drp.drawLine(currentX+(int)(scale*(0.15*maxEl+70)),currentY+(int)(scale*(30+15*(sumEl+1))),currentX+(int)(scale*(0.15*maxEl+70-5)),currentY+(int)(scale*(25+15*(sumEl+1))));//---------ок

        for(int yScala = 0; yScala<sumEl+1;yScala++)//---------ок
        {
            int vs =sumEl+1-yScala;
            drp.drawString(vs+"",currentX+(int)(scale*(15)),currentY+(int)(scale*(yScala * 15 + 30)));
                    drp.drawLine(currentX+(int)(scale*(25)),currentY+(int)(scale*(yScala * 15 + 30)),currentX+ (int)(scale*(0.15*maxEl+70)), currentY+(int)(scale*(yScala * 15 + 30)));
        }

        int xScale=0;

        do{
            String vs = xScale*200+200+"";
            for(int shiftEL=0; shiftEL<vs.length();shiftEL++)
                drp.drawString(vs.substring(shiftEL,shiftEL+1),currentX+(int)(scale*(60+xScale*30)),currentY+(int)(scale*(32+15*(sumEl+1)+10+shiftEL*10)));
            drp.drawLine(currentX+(int)(scale*(xScale*30+60)),currentY+(int)(scale*(30+15*(sumEl+1)+5)),currentX+(int)(scale*(xScale*30+60)),currentY+(int)(scale*(30)));
            xScale++;
        }while(xScale*200<maxEl+70);

        drp.drawString("0",currentX+(int)(scale*(15)),currentY+(int)(scale*(30+15*(sumEl+1) )));

         Table jTable = mainWindow.getTable();
        for(int index=0;index<sumEl-1;index++ )
        {
          if(index<mainWindow.getTable().getTableModel().getRowCount()){
            int y1 = currentY+ (int)(scale*(((30+15*(sumEl+1)) -15*jTable.getValue(index,0))));//----------------
            int x1 = currentX+ (int)(scale*((int) (30+0.15*jTable.getValue(index,1))));

            int y2 = currentY+ (int)(scale*(((30+15*(sumEl+1))-15*jTable.getValue(index+1,0))));//-------------
            int x2 = currentX+ (int)(scale*((int) (30+0.15*jTable.getValue(index+1,1))));

            drp.setStroke(new BasicStroke(3));
            drp.drawOval(x1-3,y1-3,6,6);
            drp.drawOval(x2-3,y2-3,6,6);
            drp.drawLine(x1,y1,x2,y2);}

        }

        drp.drawString("Время сортировки",currentX+ (int)(scale*(60)),currentY+(int)(scale*(80+15*(sumEl+1))));

        String sumEl1 = "Количество элементов";
        for(int yScaleName=0; yScaleName<sumEl1.length();yScaleName++)
            drp.drawString(sumEl1.substring(yScaleName,yScaleName+1),currentX,currentY+(int)(scale*((30+15*(sumEl+1)-200)+yScaleName*10)));
    }

}


