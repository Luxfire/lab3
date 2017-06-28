package controller;

import model.Model;


public class Controller {
   private Model model;

   public Controller(Model model)
    {
         this.model = model;
    }
    public int getOldSumEl() {
        return model.getOldSumEl();
    }
    public int getSortTime(int index) {
        return model.getListElement(index).getSortTime();
    }
    public int getSumEl(int index) {
        return model.getListElement(index).getSumElInMassive();
    }
    public void setOldSumEl(int oldSumEl) {
        model.setOldSumEl(oldSumEl);
    }
    public int getSumEl(){return model.getSumEl();}
    public void setSumEl(int sumEl){model.setSumEl(sumEl);}
    public void genArray() {model.genArray();}
}
