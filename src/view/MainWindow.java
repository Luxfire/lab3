package view;

import controller.Controller;
import view.listeners.ChangeElListener;
import view.listeners.CreateGraphicListener;
import view.listeners.GenTableListener;

import javax.swing.*;
import java.awt.*;


public class MainWindow {
    private Controller controller;
    private JFrame frame;
    private Table table;
    private JLabel labelSize;
    private JLabel labelSumEl;
    private JTextField textField;

    public JTextField getTextField() {
        return textField;
    }

    public Controller getController() {
        return controller;
    }

    public JLabel getLabelSize() {
        return labelSize;
    }

    public JLabel getLabelSumEl() {
        return labelSumEl;
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

        frame = new JFrame("Uh");
        frame.setSize(900,600);
        table = new Table(this);

        JScrollPane jScrollPane = new JScrollPane(table.getTable());
        jScrollPane.setPreferredSize(new Dimension(350,500));
        JButton buttonGenTable = new JButton("Сгенерировать");
        JButton buttonCreateGraphic = new JButton("Построить");
        JButton buttonChangeEl = new JButton("Изменить кол-во элементов");

        JPanel settingsBox = new JPanel();
        labelSumEl = new JLabel();
        labelSize= new JLabel();
        labelSumEl.setText("Размер таблицы:19");
        settingsBox.add(labelSumEl);
        labelSize.setText(" Масштаб:"+100+"%");
        settingsBox.add(buttonGenTable);
       // settingsBox.add(buttonCreateGraphic);
        textField = new JTextField("19",5);
        textField.setPreferredSize(new Dimension(20,20));
        textField.setSize(20,20);
        settingsBox.add(Box.createHorizontalStrut(10));
        settingsBox.add(buttonChangeEl);
        settingsBox.add(textField);

        settingsBox.add(labelSize);

        frame.add(settingsBox, BorderLayout.SOUTH);
        frame.add(jScrollPane,BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonCreateGraphic.addActionListener(new CreateGraphicListener(this));
        buttonGenTable.addActionListener(new GenTableListener(this));
        buttonGenTable.addActionListener(new CreateGraphicListener(this));
        buttonChangeEl.addActionListener(new ChangeElListener(this));

    }
}


