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
   private int currWH=500;
   private Controller controller;

   public Graphic(MainWindow mainWindow)
    {
        controller = mainWindow.getController();
        setPreferredSize(new Dimension(currWH,currWH));
        setSize(currWH,currWH);
        this.mainWindow=mainWindow;

        addMouseListener(new MouseAdapterListener(mainWindow.getController()));
        addMouseMotionListener(new MouseMotionListener(mainWindow));

        addMouseWheelListener(e -> {
            if (e.isControlDown()){

                double scale = controller.getScale();
                if (e.getWheelRotation() > 0 && scale>0.2){

                    controller.scaleMinus();

                    if(currWH>scale*500)
                    {
                    currWH=(int)(500*scale);
                    setPreferredSize(new Dimension(currWH,currWH));
                    setSize(currWH,currWH);
                    if(currWH<500) currWH=500;
                    }
                    mainWindow.getLabelSize().setText(" Масштаб:"+(int)(scale*100)+"%");
                    mainWindow.getFrame().repaint();
                }
                else if (scale < 2 && e.getWheelRotation() < 0){

                    controller.scalePlus();

                    if(currWH<scale*500) {
                        currWH = (int) (500 * scale);
                        setPreferredSize(new Dimension(currWH, currWH));
                        setSize(currWH, currWH);
                    }

                    mainWindow.getLabelSize().setText(" Масштаб:"+(int)(scale*100)+"%");
                    mainWindow.getFrame().repaint();
                }

            }
        });

    }

    protected void paintComponent(Graphics gh) {

        Graphics2D drp = (Graphics2D)gh;
        int currentX = controller.getCurrX();
        int currentY = controller.getCurrY();
        double scale = controller.getScale();

       Point endGraphic= new Point();
        endGraphic.x= (int)(scale*( 330));
        endGraphic.y= (int)(scale*( 330));

       if(currentX+endGraphic.x>currWH) {currWH=currentX+endGraphic.x+10;
       setPreferredSize(new Dimension(currWH,currWH));
       setSize(currWH,currWH);
     }

        if(currentY+endGraphic.y+100>currWH) {currWH=currentY+endGraphic.y+100;
            setPreferredSize(new Dimension(currWH,currWH));
            setSize(currWH,currWH);
        }



        if(currentX<0) currentX=0;
        if(currentY<0) currentY=0;

        drp.setFont(new Font(null, Font.PLAIN,  (int)(scale*(drp.getFont().getSize()))));
        drp.drawLine(currentX+(int)(scale*(30 )),currentY+(int)(scale*(330)),currentX+(int)(scale*( 30)),currentY+(int)(scale*(30 )));
        drp.drawLine(currentX+(int)(scale*(30 )),currentY+(int)(scale*(330)),currentX+(int)(scale*(330)),currentY+(int)(scale*(330)));

        drp.drawLine(currentX+(int)(scale*(30 )),currentY+(int)(scale*(30 )),currentX+(int)(scale*( 35)),currentY+(int)(scale*(35 )));
        drp.drawLine(currentX+(int)(scale*(30 )),currentY+(int)(scale*(30 )),currentX+(int)(scale*( 25)),currentY+(int)(scale*(35 )));

        drp.drawLine(currentX+(int)(scale*(330)),currentY+(int)(scale*(330)),currentX+(int)(scale*(325)),currentY+(int)(scale*(335)));
        drp.drawLine(currentX+(int)(scale*(330)),currentY+(int)(scale*(330)),currentX+(int)(scale*(325)),currentY+(int)(scale*(325)));

        for(int yScala = 0; yScala<20;yScala++)
        {
            int vs =20-yScala;
            drp.drawString(vs+"",currentX+(int)(scale*(15)),currentY+(int)(scale*(yScala * 15 + 30)));
                    drp.drawLine(currentX+(int)(scale*(25)),currentY+(int)(scale*(yScala * 15 + 30)),currentX+ (int)(scale*(330)), currentY+(int)(scale*(yScala * 15 + 30)));
        }

        for(int xScale =0;xScale<10;xScale++)
        {
            String vs = xScale*200+200+"";
            for(int shiftEL=0; shiftEL<vs.length();shiftEL++)
                drp.drawString(vs.substring(shiftEL,shiftEL+1),currentX+(int)(scale*(60+xScale*30)),currentY+(int)(scale*(340+shiftEL*10)));
            drp.drawLine(currentX+(int)(scale*(xScale*30+60)),currentY+(int)(scale*(335)),currentX+(int)(scale*(xScale*30+60)),currentY+(int)(scale*(30)));
        }

        drp.drawString("0",currentX+(int)(scale*(15)),currentY+(int)(scale*(330)));

         Table jTable = mainWindow.getTable();
        for(int index=0;index<controller.getSumEl()-1;index++ )
        {
            int y1 = currentY+ (int)(scale*((330-15*jTable.getValue(index,0))));
            int x1 = currentX+ (int)(scale*((int) (30+0.15*jTable.getValue(index,1))));

            int y2 = currentY+ (int)(scale*((330-15*jTable.getValue(index+1,0))));
            int x2 = currentX+ (int)(scale*((int) (30+0.15*jTable.getValue(index+1,1))));

            drp.setStroke(new BasicStroke(3));
            drp.drawOval(x1-3,y1-3,6,6);
            drp.drawOval(x2-3,y2-3,6,6);
            drp.drawLine(x1,y1,x2,y2);
        }

        drp.drawString("Время сортировки",currentX+ (int)(scale*(150)),currentY+(int)(scale*(390)));

        String sumEl = "Количество элементов";
        for(int yScaleName=0; yScaleName<sumEl.length();yScaleName++)
            drp.drawString(sumEl.substring(yScaleName,yScaleName+1),currentX,currentY+(int)(scale*(90+yScaleName*10)));
    }


}


