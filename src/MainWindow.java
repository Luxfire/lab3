import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by user on 21.05.2017.
 */
public class MainWindow {
    JFrame frame;
    JPanel panelTable;
    JPanel panelGraphic;
    MainWindow()
    {
        frame = new JFrame("Uh");
        frame.setSize(600,600);
        frame.setLayout(new BorderLayout());


        panelTable = new JPanel();
        panelTable.setLayout(new BoxLayout(panelTable,BoxLayout.Y_AXIS));
        Table table = new Table(panelTable);

        JButton buttonCreateGraphic = new JButton("Построить");
        panelTable.add(buttonCreateGraphic);

        panelGraphic = new JPanel();
        panelGraphic.setLayout(new BorderLayout());
        panelGraphic.add(new Graphic(table));

        frame.add(panelGraphic,BorderLayout.CENTER);
        frame .add(panelTable,BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

