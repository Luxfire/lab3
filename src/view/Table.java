package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table {
    private JTable table;
    private Controller controller;
    private DefaultTableModel tableModel;
    private int maxEl;
    private MainWindow mainWindow;


    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public int getMaxEl() {
        return maxEl;
    }

    public void setMaxEl(int maxEl) {
        this.maxEl = maxEl;
    }

    public JTable getTable() {
        return table;
    }

    Table(MainWindow mainWindow)
    {

        this.mainWindow=mainWindow;
        this.controller = mainWindow.getController();
        String[] columnNames= {"Кол-во элементов в массиве", "Время сортировки"};
        tableModel = new DefaultTableModel(columnNames,0);
        table = new JTable(tableModel);
    }

    Integer getValue(int row, int column)
    {
       return Integer.parseInt(String.valueOf(table.getValueAt(row,column)));
    }

    public void genTable()
    {
       MyThread myThread = new MyThread(controller,mainWindow);
    }

}

