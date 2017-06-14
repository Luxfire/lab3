import java.awt.*;
import java.util.Random;

public class Model {
    int[][] arrayResult;
    Point startPoint = new Point();
    Point oldPoint = new Point();
    int sumEl = 19;
    int currentX=0;
    int currentY=0;

    Model() {
        arrayResult = new int[sumEl][2];
    }

    public void genArray() {
        Random random = new Random();
        int array[];
        long sortArray[] = new long[10000];
        long startTime;
        long spendTime;
        for (int index = 0; index < 19; index++) {

            array = new int[index + 2];

            for (int sumArray = 0; sumArray < 10000; sumArray++) {

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
            long sum = 0;
            for (int i = 0; i < 100; i++)
                sum += sortArray[i];
               arrayResult[index][0]=index+2;
               arrayResult[index][1]=(int)sum/100;

        }
    }
}