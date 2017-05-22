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
    Table table;

    MainWindow()
    {
        frame = new JFrame("Uh");
        frame.setSize(600,600);
        frame.setLayout(new BorderLayout());


        panelTable = new JPanel();
        panelTable.setLayout(new BoxLayout(panelTable,BoxLayout.Y_AXIS));
        table = new Table(panelTable);

        Graphic graphic = new Graphic(this);

       JScrollPane scrollPane = new JScrollPane(graphic);


        JButton buttonCreateGraphic = new JButton("Построить");
        buttonCreateGraphic.addActionListener(new Create(this));

       graphic.setPreferredSize(new Dimension(200,200));
        panelTable.add(buttonCreateGraphic);
        frame.add(scrollPane,BorderLayout.CENTER);
        frame .add(panelTable,BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    class Create implements ActionListener
    {   MainWindow mainWindow;

        Create(MainWindow mainWindow){
            this.mainWindow =mainWindow;}

        @Override
        public void actionPerformed(ActionEvent e) {
            Graphic graphic = new Graphic(mainWindow);
            frame.add(graphic,BorderLayout.CENTER);
            frame.repaint();
            frame.revalidate();
        }
    }
}
