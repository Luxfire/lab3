import javax.swing.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Random;

/**
 * Created by user on 21.05.2017.
 */
public class Table {
    JTable table;

    int sumEl;
    String[][] elements;
    Table(JPanel panel)
    {
        sumEl= 10;
        String[] columnNames = {"Кол-во элементов в массиве", "Время генерации"};
        elements = new String[sumEl][sumEl];
        table = new JTable(elements,columnNames);

        JButton button = new JButton("Сгенерировать");
        GenListener genListener = new GenListener(this,sumEl);
        button.addActionListener(genListener);

           panel.add(table);
           panel.add(button);
           genTable();
           sortTable();

    }

    String  getValue(int row, int column)
    {
        return elements[row][column];
    }

   public void sortTable()
    {
        for(int i=0;i<sumEl;i++)
          for(int j=sumEl-1;j>i;j--)
        {
            if( Integer.parseInt(getValue(j,0))<Integer.parseInt(getValue(j-1,0))) {
             String r = getValue(j,0);
             String r1 =getValue(j,1);
             table.setValueAt(getValue(j-1,0),j,0);
                table.setValueAt(getValue(j-1,1),j,1);

             table.setValueAt(r,j-1,0);
                table.setValueAt(r1,j-1,1);

            }
        }
    }


    public void genTable()
    {
        Random random = new Random();
        int a[];
        for (int index=0;index<sumEl;index++) {

            int count = random.nextInt(19)+2;
            a = new int[count];

            for(int gen= 0; gen<count;gen++)
                a[gen]=random.nextInt(100);
            long startTime = System.nanoTime();

            for(int i =0;i<count;i++)
                for (int j = count-1;j>i;j--)
                    if (a[j] < a[j - 1])
                    {
                        int r = a[j-1];
                        a[j-1]=a[j];
                        a[j] = r;
                    }

            long spendTime = System.nanoTime();
            table.setValueAt(Integer.toString(count),index,0);
            table.setValueAt(String.valueOf(spendTime-startTime),index,1);
        }

    }

}



class GenListener implements ActionListener
        {  Table table;
           int sumEl;

           public GenListener(Table table,int sumEl)
           {
               this.table=table;
               this.sumEl=sumEl;
           }


@Override
public void actionPerformed(ActionEvent e) {
    Random random = new Random();
    int a[];
             for (int index=0;index<sumEl;index++) {

                 int count = random.nextInt(19)+2;
                 a = new int[count];

                  for(int gen= 0; gen<count;gen++)
                       a[gen]=random.nextInt(100);
                 long startTime = System.nanoTime();

                  for(int i =0;i<count;i++)
                      for (int j = count-1;j>i;j--)
                          if (a[j] < a[j - 1])
                          {
                            int r = a[j-1];
                            a[j-1]=a[j];
                            a[j] = r;
                          }

                 long spendTime = System.nanoTime();
                 table.table.setValueAt(Integer.toString(count),index,0);
                 table.table.setValueAt(String.valueOf(spendTime-startTime),index,1);
             }
             table.sortTable();
        }


        }