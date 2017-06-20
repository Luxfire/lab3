package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table {
    private JTable table;
    private Controller controller;
    private DefaultTableModel tableModel;
    private int maxEl=0;

    public int getMaxEl() {
        return maxEl;
    }

    public JTable getTable() {
        return table;
    }

    Table(Controller controller)
    {

        this.controller=controller;
        String[] columnNames= {"Кол-во элементов в массиве", "Время сортировки"};
        tableModel = new DefaultTableModel(columnNames,controller.getSumEl());
        table = new JTable(tableModel);
    }

    Integer getValue(int row, int column)
    {
       return Integer.parseInt(String.valueOf(table.getValueAt(row,column)));
    }

    public void genTable()
    { // changeRow();
       maxEl=0;
        for (int index=0;index<controller.getSumEl();index++) {
            table.setValueAt(Integer.toString(controller.getArray(index,0)), index, 0);
            table.setValueAt(String.valueOf(controller.getArray(index,1)), index, 1);
            if(maxEl<controller.getArray(index,1)) maxEl=controller.getArray(index,1);
        }

    }

    void changeRow()
    {
        if(tableModel.getRowCount()>controller.getSumEl())
        {
            for(int index=tableModel.getRowCount()-1;index>=controller.getSumEl();index--)
                tableModel.removeRow(index);
        }
        if(tableModel.getRowCount()<controller.getSumEl())
        { String[] array ={"",""};
            for(int index=table.getRowCount();index<controller.getSumEl();index++)
                tableModel.addRow(array);
        }
    }
}



