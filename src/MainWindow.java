import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


/**
 * Created by user on 21.05.2017.
 */
public class MainWindow {
    JFrame frame;
    Table table;
    Point startPoint = new Point();
    Point oldPoint = new Point();
    JButton buttonGenTable;
    JButton buttonCreateGraphic;
    Graphic graphic;
    int currentX=0;
    int currentY=0;
    double scale = 1;
    Box settingsBox;
    JLabel labelSize;
    JScrollPane scrollPane;

    MainWindow()
    {
        frame = new JFrame("Uh");
        frame.setSize(900,600);

        table = new Table();

        graphic = new Graphic(this);

        JScrollPane scrollPane1 = new JScrollPane(table.table);
                    scrollPane = new JScrollPane(graphic);

        buttonGenTable = new JButton("Сгенерировать");
        buttonCreateGraphic = new JButton("Построить");

        frame.add(createSettingsBox(), BorderLayout.SOUTH);
        frame.add(scrollPane,BorderLayout.CENTER);
        frame.add(scrollPane1,BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        graphic.addMouseListener(new MouseListener(this));

        graphic.addMouseMotionListener(new MouseMotion(this));

        buttonCreateGraphic.addActionListener(new Create(this));

        buttonGenTable.addActionListener(new GenListener(this));
        buttonGenTable.addActionListener(new Create(this));

        scrollPane.getViewport().addChangeListener(new ScrollListener(this));
     }


    public Box createSettingsBox(){
        settingsBox = Box.createHorizontalBox();

        labelSize= new JLabel();
        labelSize.setText(" Масштаб:"+scale*100+"%");
        settingsBox.add(buttonGenTable);
        settingsBox.add(buttonCreateGraphic);
        settingsBox.add(Box.createHorizontalStrut(560));
        settingsBox.add(labelSize);
        return settingsBox;
    }

}

    class ScrollListener implements ChangeListener
    { MainWindow mainWindow;

        public ScrollListener(MainWindow mainWindow) {
            this.mainWindow = mainWindow;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            mainWindow.frame.repaint();
            mainWindow.frame.revalidate();
        }
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
            mainWindow.currentX+= (now.x - mainWindow.oldPoint.x);
            mainWindow.currentY+= (now.y - mainWindow.oldPoint.y);
            mainWindow.oldPoint=now;
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
            mainWindow.startPoint=e.getPoint();
            mainWindow.oldPoint = mainWindow.startPoint;
        }
    }

    class Create implements ActionListener
    {   MainWindow mainWindow;

    Create(MainWindow mainWindow)
    {
        this.mainWindow =mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mainWindow.frame.repaint();
    }
    }

    class GenListener implements ActionListener
    {
    MainWindow mainWindow;

    public GenListener(MainWindow mainWindow)
    {
        this.mainWindow=mainWindow;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
      mainWindow.table.genTable();
     // mainWindow.table.sortTable();
    }


}