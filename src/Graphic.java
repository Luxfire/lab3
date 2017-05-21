import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 21.05.2017.
 */
class Graphic extends JPanel {
    Table table;

    Graphic(Table table)
    {
        this.table=table;
    }


    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;
        drp.drawLine(20, 320, 20, 20);
        drp.drawLine(20, 320, 320, 320);
        drp.drawLine(20,20,25,25);
        drp.drawLine(20,20,15,25);
        drp.drawLine(320,320,315,325);
        drp.drawLine(320,320,315,315);
        for(int i = 0;i<20;i++)
            drp.drawLine(15, i*15+20, 320, i*15+20);
        for(int index=0;index<table.sumEl-1;index++ )
        { int y1 = 320-15*Integer.parseInt(table.getValue(index,0));
          int x1 = (int) (20+0.015*Integer.parseInt(table.getValue(index,1)));

            int y2 = 320-15*Integer.parseInt(table.getValue(index+1,0));
            int x2 = (int) (20+0.015*Integer.parseInt(table.getValue(index+1,1)));
            drp.setStroke(new BasicStroke(3));
             drp.drawOval(x1-3,y1-3,6,6);
            drp.drawOval(x2-3,y2-3,6,6);
            drp.drawLine(x1,y1,x2,y2);

        }
    }
    }
