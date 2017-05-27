import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Random;

/**
 * Created by user on 21.05.2017.
 */
public class Table {
    JTable table;
    int sumEl;

    Table()
    {
        String[] columnNames= {"Кол-во элементов в массиве", "Время сортировки"};
        sumEl= 19;
        DefaultTableModel tableModel = new DefaultTableModel(columnNames,sumEl);
        table = new JTable(tableModel);
        genTable();
    }

    Integer  getValue(int row, int column)
    {
       return Integer.parseInt(String.valueOf(table.getValueAt(row,column)));
    }

    public void genTable()
    {
        Random random = new Random();
        int array[];
        long sortArray[] = new long[10000];
        long startTime;
        long spendTime;
        for (int index=0;index<sumEl;index++) {

            array = new int[index+2];

            for(int sumArray=0;sumArray<10000;sumArray++) {

                for (int gen = 0; gen < index+2; gen++)
                    array[gen] = random.nextInt(100);

                startTime = System.nanoTime();
                for (int i = 0; i < index+2; i++)
                    for (int j = index+2 - 1; j > i; j--)
                        if (array[j] < array[j - 1])
                        {
                            int r = array[j - 1];
                            array[j - 1] = array[j];
                            array[j] = r;
                        }
                spendTime = System.nanoTime();

                sortArray[sumArray]=spendTime-startTime;

            }
            long sum = 0;
            for(int i=0;i<100;i++)
               sum+=sortArray[i];

            table.setValueAt(Integer.toString(index+2),index,0);
            table.setValueAt(String.valueOf(sum/100),index,1);
        }

    }

}

