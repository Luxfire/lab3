import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainWindow {
    Controller controller;
    JFrame frame;
    Table table;
    JButton buttonGenTable;
    JButton buttonCreateGraphic;
    double scale = 1;
    Box settingsBox;
    JLabel labelSize;
    JScrollPane scrollPane;

    MainWindow(Controller controller)
    {
        this.controller=controller;
        table = new Table(controller);

        frame = new JFrame("Uh");
        frame.setSize(900,600);

        JScrollPane jScrollPane = new JScrollPane(table.table);
        buttonGenTable = new JButton("Сгенерировать");
        buttonCreateGraphic = new JButton("Построить");

        frame.add(createSettingsBox(), BorderLayout.SOUTH);
        frame.add(jScrollPane,BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonCreateGraphic.addActionListener(new Create(this));
        buttonGenTable.addActionListener(new GenListener(this));

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

    class Create implements ActionListener
    {MainWindow mainWindow;

        Create(MainWindow mainWindow)
        {
        this.mainWindow =mainWindow;
        }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Graphic graphic = new Graphic(mainWindow);
        mainWindow.scrollPane = new JScrollPane(graphic);
        mainWindow.scrollPane.getViewport().addChangeListener(new ScrollListener(mainWindow));
        mainWindow.frame.add(mainWindow.scrollPane,BorderLayout.CENTER);
        mainWindow.frame.revalidate();
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

    }


}