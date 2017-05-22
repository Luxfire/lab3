import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by user on 21.05.2017.
 */
class Graphic extends JPanel {
   MainWindow mainWindow;

    Graphic(MainWindow mainWindow)
    {
        this.mainWindow=mainWindow;

    }


    @Override
    public void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;
        drp.drawLine(20, 320, 20, 20);
        drp.drawLine(20, 320, 320, 320);
        drp.drawLine(20,20,25,25);
        drp.drawLine(20,20,15,25);
        drp.drawLine(320,320,315,325);
        drp.drawLine(320,320,315,315);
        for(int i = 0;i<20;i++) {
            int vs =20-i;
            drp.drawString(vs+"",5, i * 15 + 20);
            drp.drawLine(15, i * 15 + 20, 320, i * 15 + 20);
        }
        for(int i =0;i<10;i++)
        {
            drp.drawLine(20+i*30+30,325,20+i*30+30,20);
        }

          drp.drawString("0",5,320);
        for(int index=0;index<mainWindow.table.sumEl-1;index++ )
        { int y1 = 320-15*Integer.parseInt(mainWindow.table.getValue(index,0));
          int x1 = (int) (20+0.012*Integer.parseInt(mainWindow.table.getValue(index,1)));

            int y2 = 320-15*Integer.parseInt(mainWindow.table.getValue(index+1,0));
            int x2 = (int) (20+0.012*Integer.parseInt(mainWindow.table.getValue(index+1,1)));
            drp.setStroke(new BasicStroke(3));
             drp.drawOval(x1-3,y1-3,6,6);
            drp.drawOval(x2-3,y2-3,6,6);
            drp.drawLine(x1,y1,x2,y2);

        }

        for (int i=0;i<10;i++)
        {
            AffineTransform at = new AffineTransform();
            at.rotate(Math.PI / 2);
            drp.setTransform(at);
            int vs = i*2500+2500;
            drp.drawString(vs+"",325,-250-i*30);
            at.rotate(Math.PI);
            drp.setTransform(at);
        }

    }
    }
