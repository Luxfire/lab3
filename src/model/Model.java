package model;

import java.awt.*;
import java.util.Random;

public class Model {
    private int[][] arrayResult;
    private Point startPoint = new Point();
    private Point oldPoint = new Point();
    private int sumEl = 19;
    private int currentX=0;
    private int currentY=0;
    private double scale = 1;
    private MyThread myThread;

    public int getArrayResult(int massiveSize, int sortTime) {
        return arrayResult[massiveSize][sortTime];
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getOldPoint() {
        return oldPoint;
    }

    public int getSumEl() {
        return sumEl;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public double getScale() {
        return scale;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setOldPoint(Point oldPoint) {
        this.oldPoint = oldPoint;
    }

    public void setCurrentX(int currentX) {
        this.currentX += currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY += currentY;
    }

    public void scalePlus() {scale+=0.1;}

    public void scaleMinus() {scale-=0.1;}

    public void setSumEl(int sumEl) {
        this.sumEl = sumEl;
        myThread.setSumEl(sumEl);
    }

    public Model() {
        arrayResult = new int[100][2];
        myThread = new MyThread(arrayResult,sumEl);
        myThread.start();
    }

}
  class MyThread extends Thread {
    int[][] arrayResult;
    int sumEl;
      Random random = new Random();
      int array[];
      long sortArray[] = new long[10000];
      long startTime;
      long spendTime;
      long sum;

      public void setSumEl(int sumEl) {
          this.sumEl = sumEl;
      }

      MyThread(int[][] arrayResult, int sumEl)
    {    this.sumEl=sumEl;
        this.arrayResult=arrayResult;
    }

    public void run() {
        genArray();
        run();
    }
      public void genArray() {

          for (int index = 0; index < sumEl; index++) {

              array = new int[index + 2];

              for (int sumArray = 0; sumArray < 1000; sumArray++) {

                  for (int gen = 0; gen < index + 2; gen++)
                      array[gen] = random.nextInt(100);

                  startTime = System.nanoTime();
                  for (int i = 0; i < index + 2; i++)
                      for (int j = index + 2 - 1; j > i; j--)
                          if (array[j] < array[j - 1]) {
                              int r = array[j - 1];
                              array[j - 1] = array[j];
                              array[j] = r;
                          }
                  spendTime = System.nanoTime();

                  sortArray[sumArray] = spendTime - startTime;

              }
              sum = 0;
              for (int i = 0; i < 1000; i++)
                  sum += sortArray[i];
              arrayResult[index][0]=index+2;
              arrayResult[index][1]=(int)sum/1000;

          }
      }
    }