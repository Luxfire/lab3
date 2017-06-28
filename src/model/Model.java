package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Model {

    private List<ModelElement> modelList;
    private int sumEl = 19;
    private int oldSumEl = 19;

    public int getOldSumEl() {
        return oldSumEl;
    }

    public void setOldSumEl(int oldSumEl) {
        this.oldSumEl = oldSumEl;
    }

    public ModelElement getListElement(int index) {
        return modelList.get(index);
    }


    public int getSumEl() {
        return sumEl;
    }

    public void setSumEl(int sumEl) {
        this.sumEl = sumEl;
    }

    public Model() {
        modelList = new ArrayList<>();
    }

    public void genArray() {
          modelList.clear();
        Random random = new Random();
        int array[];
        long[] sortArray = new long[1000];

        long startTime;
        long spendTime;
        long sum;
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
            ModelElement modelElement = new ModelElement(index+2,(int) sum / 1000);
            modelList.add(modelElement);

        }

    }
}

