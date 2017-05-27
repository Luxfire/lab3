import javafx.scene.text.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

/**
 * Created by user on 21.05.2017.
 */
class Graphic extends JPanel {
   MainWindow mainWindow;
   int currWH=400;
   Point endGraphic;
   int maxTime;
   int maxEl;
    Graphic(MainWindow mainWindow)
    {
        setPreferredSize(new Dimension(currWH,currWH));
        setSize(currWH,currWH);
        this.mainWindow=mainWindow;

        addMouseWheelListener(e -> {
            if (e.isControlDown()){
                if (e.getWheelRotation() < 0 && mainWindow.scale>0.2){
                    endGraphic = new Point((int)( mainWindow.scale*30),(int)(mainWindow.scale*330));
                    mainWindow.scale -= 0.1;

                    currWH=(int)(400*mainWindow.scale);
                    setPreferredSize(new Dimension(currWH,currWH));
                    setSize(currWH,currWH);
                    if(currWH<400) currWH=400;
                    mainWindow.currentY=mainWindow.scrollPane.getViewport().getViewPosition().y;
                    mainWindow.currentX=mainWindow.scrollPane.getViewport().getViewPosition().x;
                    mainWindow.labelSize.setText(" Масштаб:"+(int)(mainWindow.scale*100)+"%");
                }
                else if (mainWindow.scale < 2 && e.getWheelRotation() > 0){
                    endGraphic = new Point((int)( mainWindow.scale*30),(int)(mainWindow.scale*330));
                    mainWindow.scale+=0.1;

                    currWH=(int)(400*mainWindow.scale);
                    setPreferredSize(new Dimension(currWH,currWH));
                    setSize(currWH,currWH);
                    mainWindow.currentY=mainWindow.scrollPane.getViewport().getViewPosition().y;
                    mainWindow.currentX=mainWindow.scrollPane.getViewport().getViewPosition().x;
                    mainWindow.labelSize.setText(" Масштаб:"+(int)(mainWindow.scale*100)+"%");
                }

            }
        });

    }

    protected void paintComponent(Graphics gh) {

        Graphics2D drp = (Graphics2D)gh;
        maxTime=(((mainWindow.table.getValue(mainWindow.table.sumEl-1,1)/1000)+1)*1000);
        maxEl = mainWindow.table.sumEl+1;

        drp.setFont(new Font(null, Font.PLAIN,  (int)(mainWindow.scale*(drp.getFont().getSize()))));
        drp.drawLine((int)(mainWindow.scale*(mainWindow.currentX+30 )),(int)(mainWindow.scale*(mainWindow.currentY+330)),(int)(mainWindow.scale*(mainWindow.currentX+ 30)) ,(int)(mainWindow.scale*(mainWindow.currentY+30 )));
        drp.drawLine((int)(mainWindow.scale*(mainWindow.currentX+30 )),(int)(mainWindow.scale*(mainWindow.currentY+330)),(int)(mainWindow.scale*(mainWindow.currentX+330)),(int)(mainWindow.scale*(mainWindow.currentY+330)));

        drp.drawLine((int)(mainWindow.scale*(mainWindow.currentX+30 )),(int)(mainWindow.scale*(mainWindow.currentY+30 )),(int)(mainWindow.scale*(mainWindow.currentX+ 35)) ,(int)(mainWindow.scale*(mainWindow.currentY+35 )));
        drp.drawLine((int)(mainWindow.scale*(mainWindow.currentX+30 )),(int)(mainWindow.scale*(mainWindow.currentY+30 )),(int)(mainWindow.scale*(mainWindow.currentX+ 25)) ,(int)(mainWindow.scale*(mainWindow.currentY+35 )));

        drp.drawLine((int)(mainWindow.scale*(mainWindow.currentX+330)),(int)(mainWindow.scale*(mainWindow.currentY+330)),(int)(mainWindow.scale*(mainWindow.currentX+325)),(int)(mainWindow.scale*(mainWindow.currentY+335)));
        drp.drawLine((int)(mainWindow.scale*(mainWindow.currentX+330)),(int)(mainWindow.scale*(mainWindow.currentY+330)),(int)(mainWindow.scale*(mainWindow.currentX+325)),(int)(mainWindow.scale*(mainWindow.currentY+325)));

        for(int yScala = 0; yScala<20;yScala++)
        {
            int vs =20-yScala;
            drp.drawString(vs+"",(int)(mainWindow.scale*(mainWindow.currentX+15)), (int)(mainWindow.scale*(mainWindow.currentY+yScala * 15 + 30)));
            drp.drawLine((int)(mainWindow.scale*(mainWindow.currentX+25)), (int)(mainWindow.scale*(mainWindow.currentY+yScala * 15 + 30)), (int)(mainWindow.scale*(mainWindow.currentX+330)), (int)(mainWindow.scale*(mainWindow.currentY+yScala * 15 + 30)));
        }

        for(int xScale =0;xScale<10;xScale++)
        {
            String vs = xScale*200+200+"";
            for(int shiftEL=0; shiftEL<vs.length();shiftEL++)
                drp.drawString(vs.substring(shiftEL,shiftEL+1),(int)(mainWindow.scale*(mainWindow.currentX+60+xScale*30)),(int)(mainWindow.scale*(mainWindow.currentY+340+shiftEL*10)));
            drp.drawLine((int)(mainWindow.scale*(mainWindow.currentX+xScale*30+60)),(int)(mainWindow.scale*(mainWindow.currentY+335)),(int)(mainWindow.scale*(mainWindow.currentX+xScale*30+60)),(int)(mainWindow.scale*(mainWindow.currentY+30)));
        }

        drp.drawString("0",(int)(mainWindow.scale*(mainWindow.currentX+15)),(int)(mainWindow.scale*(mainWindow.currentY+330)));

        for(int index=0;index<mainWindow.table.sumEl-1;index++ )
        {
            int y1 =  (int)(mainWindow.scale*(mainWindow.currentY+(330-15*mainWindow.table.getValue(index,0))));
            int x1 = (int)(mainWindow.scale*(mainWindow.currentX+(int) (30+0.15*mainWindow.table.getValue(index,1))));

            int y2 =(int)(mainWindow.scale*( mainWindow.currentY+(330-15*mainWindow.table.getValue(index+1,0))));
            int x2 =(int)(mainWindow.scale*( mainWindow.currentX+(int) (30+0.15*mainWindow.table.getValue(index+1,1))));

            drp.setStroke(new BasicStroke(3));
            drp.drawOval(x1-3,y1-3,6,6);
            drp.drawOval(x2-3,y2-3,6,6);
            drp.drawLine(x1,y1,x2,y2);
        }

        drp.drawString("Время сортировки", (int)(mainWindow.scale*(mainWindow.currentX+150)),(int)(mainWindow.scale*(mainWindow.currentY+390)));

        String sumEl = "Количество элементов";
        for(int yScaleName=0; yScaleName<sumEl.length();yScaleName++)
            drp.drawString(sumEl.substring(yScaleName,yScaleName+1),(int)(mainWindow.scale*(mainWindow.currentX)),(int)(mainWindow.scale*(mainWindow.currentY+90+yScaleName*10)));
    }

}

