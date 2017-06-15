package view;

import controller.Controller;
import view.listeners.CreateGraphicListener;
import view.listeners.GenTableListener;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private Controller controller;
    private JFrame frame;
    private Table table;
    private JLabel labelSize;

    public Controller getController() {
        return controller;
    }

    public JLabel getLabelSize() {
        return labelSize;
    }

    public JScrollPane scrollPane;

    public JFrame getFrame() {
        return frame;
    }

    public Table getTable() {
        return table;
    }

    public MainWindow(Controller controller)
    {
        this.controller=controller;
        table = new Table(controller);

        frame = new JFrame("Uh");
        frame.setSize(900,600);

        JScrollPane jScrollPane = new JScrollPane(table.getTable());
        JButton buttonGenTable = new JButton("Сгенерировать");
        JButton buttonCreateGraphic = new JButton("Построить");

        Box settingsBox = Box.createHorizontalBox();
        labelSize= new JLabel();
        labelSize.setText(" Масштаб:"+100+"%");
        settingsBox.add(buttonGenTable);
        settingsBox.add(buttonCreateGraphic);
        settingsBox.add(Box.createHorizontalStrut(560));
        settingsBox.add(labelSize);

        frame.add(settingsBox, BorderLayout.SOUTH);
        frame.add(jScrollPane,BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonCreateGraphic.addActionListener(new CreateGraphicListener(this));
        buttonGenTable.addActionListener(new GenTableListener(this));

    }
}

