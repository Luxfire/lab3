
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;

/**
 * Created by user on 21.05.2017.
 */
class Graphic extends JPanel {
   MainWindow mainWindow;
   int currWH=400;
   Point endGraphic;
   Controller controller;
    Graphic(MainWindow mainWindow)
    {
        controller = mainWindow.controller;
        setPreferredSize(new Dimension(currWH,currWH));
        setSize(currWH,currWH);
        this.mainWindow=mainWindow;

        addMouseListener(new MouseListener(mainWindow));
        addMouseMotionListener(new MouseMotion(mainWindow));

        addMouseWheelListener(e -> {
            if (e.isControlDown()){
                if (e.getWheelRotation() < 0 && mainWindow.scale>0.2){
                    endGraphic = new Point((int)( mainWindow.scale*30),(int)(mainWindow.scale*330));
                    mainWindow.scale -= 0.1;

                    currWH=(int)(400*mainWindow.scale);
                    setPreferredSize(new Dimension(currWH,currWH));
                    setSize(currWH,currWH);
                    if(currWH<400) currWH=400;
                    mainWindow.labelSize.setText(" Масштаб:"+(int)(mainWindow.scale*100)+"%");
                    mainWindow.frame.repaint();
                }
                else if (mainWindow.scale < 2 && e.getWheelRotation() > 0){
                    endGraphic = new Point((int)( mainWindow.scale*30),(int)(mainWindow.scale*330));
                    mainWindow.scale+=0.1;
                    currWH=(int)(400*mainWindow.scale);
                    setPreferredSize(new Dimension(currWH,currWH));
                    setSize(currWH,currWH);
                    mainWindow.labelSize.setText(" Масштаб:"+(int)(mainWindow.scale*100)+"%");
                    mainWindow.frame.repaint();
                }

            }
        });

    }

    protected void paintComponent(Graphics gh) {

        Graphics2D drp = (Graphics2D)gh;
        int currentX = controller.getCurrX();
        int currentY = controller.getCurrY();
        drp.setFont(new Font(null, Font.PLAIN,  (int)(mainWindow.scale*(drp.getFont().getSize()))));
        drp.drawLine(currentX+(int)(mainWindow.scale*(30 )),currentY+(int)(mainWindow.scale*(330)),currentX+(int)(mainWindow.scale*( 30)),currentY+(int)(mainWindow.scale*(30 )));
        drp.drawLine(currentX+(int)(mainWindow.scale*(30 )),currentY+(int)(mainWindow.scale*(330)),currentX+(int)(mainWindow.scale*(330)),currentY+(int)(mainWindow.scale*(330)));

        drp.drawLine(currentX+(int)(mainWindow.scale*(30 )),currentY+(int)(mainWindow.scale*(30 )),currentX+(int)(mainWindow.scale*( 35)),currentY+(int)(mainWindow.scale*(35 )));
        drp.drawLine(currentX+(int)(mainWindow.scale*(30 )),currentY+(int)(mainWindow.scale*(30 )),currentX+(int)(mainWindow.scale*( 25)),currentY+(int)(mainWindow.scale*(35 )));

        drp.drawLine(currentX+(int)(mainWindow.scale*(330)),currentY+(int)(mainWindow.scale*(330)),currentX+(int)(mainWindow.scale*(325)),currentY+(int)(mainWindow.scale*(335)));
        drp.drawLine(currentX+(int)(mainWindow.scale*(330)),currentY+(int)(mainWindow.scale*(330)),currentX+(int)(mainWindow.scale*(325)),currentY+(int)(mainWindow.scale*(325)));

        for(int yScala = 0; yScala<20;yScala++)
        {
            int vs =20-yScala;
            drp.drawString(vs+"",currentX+(int)(mainWindow.scale*(15)),currentY+(int)(mainWindow.scale*(yScala * 15 + 30)));
                    drp.drawLine(currentX+(int)(mainWindow.scale*(25)),currentY+(int)(mainWindow.scale*(yScala * 15 + 30)),currentX+ (int)(mainWindow.scale*(330)), currentY+(int)(mainWindow.scale*(yScala * 15 + 30)));
        }

        for(int xScale =0;xScale<10;xScale++)
        {
            String vs = xScale*200+200+"";
            for(int shiftEL=0; shiftEL<vs.length();shiftEL++)
                drp.drawString(vs.substring(shiftEL,shiftEL+1),currentX+(int)(mainWindow.scale*(60+xScale*30)),currentY+(int)(mainWindow.scale*(340+shiftEL*10)));
            drp.drawLine(currentX+(int)(mainWindow.scale*(xScale*30+60)),currentY+(int)(mainWindow.scale*(335)),currentX+(int)(mainWindow.scale*(xScale*30+60)),currentY+(int)(mainWindow.scale*(30)));
        }

        drp.drawString("0",currentX+(int)(mainWindow.scale*(15)),currentY+(int)(mainWindow.scale*(330)));

        for(int index=0;index<mainWindow.table.controller.getSumEl()-1;index++ )
        {
            int y1 = currentY+ (int)(mainWindow.scale*((330-15*mainWindow.table.getValue(index,0))));
            int x1 = currentX+ (int)(mainWindow.scale*((int) (30+0.15*mainWindow.table.getValue(index,1))));

            int y2 = currentY+ (int)(mainWindow.scale*((330-15*mainWindow.table.getValue(index+1,0))));
            int x2 = currentX+ (int)(mainWindow.scale*((int) (30+0.15*mainWindow.table.getValue(index+1,1))));

            drp.setStroke(new BasicStroke(3));
            drp.drawOval(x1-3,y1-3,6,6);
            drp.drawOval(x2-3,y2-3,6,6);
            drp.drawLine(x1,y1,x2,y2);
        }

        drp.drawString("Время сортировки",currentX+ (int)(mainWindow.scale*(150)),currentY+(int)(mainWindow.scale*(390)));

        String sumEl = "Количество элементов";
        for(int yScaleName=0; yScaleName<sumEl.length();yScaleName++)
            drp.drawString(sumEl.substring(yScaleName,yScaleName+1),currentX,currentY+(int)(mainWindow.scale*(90+yScaleName*10)));
    }

    class MouseMotion extends MouseMotionAdapter
    {MainWindow mainWindow;
        MouseMotion(MainWindow mainWindow)
        {
            this.mainWindow=mainWindow;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point now = e.getPoint();
           controller.setCurrX(now.x - controller.getOldPoint().x);
           controller.setCurrY(now.y - controller.getOldPoint().y);
            controller.setOldPoint(now);
            mainWindow.frame.repaint();
        }
    }

    class MouseListener extends MouseAdapter
    {
        MainWindow mainWindow;
        MouseListener(MainWindow mainWindow)
        {
            this.mainWindow=mainWindow;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            controller.setStartPoint(e.getPoint());
            controller.setOldPoint(controller.getStartPoint());
        }
    }

}

