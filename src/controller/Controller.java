package controller;

import model.*;
import java.awt.*;

public class Controller {
    Model model;
   public Controller(Model model)
    {
         this.model = model;
    }

    public int getArray(int massiveSize, int sortTime)
    {
        return model.getArrayResult(massiveSize,sortTime);
    }

    public Point getOldPoint()
    {
        return model.getOldPoint();
    }
    public Point getStartPoint()
    {
        return model.getStartPoint();
    }
    public int getSumEl(){return model.getSumEl();};
    public int getCurrX(){return model.getCurrentX();}
    public int getCurrY(){return model.getCurrentY();}
    public double getScale(){return model.getScale();}

    public void setCurrX(int currX){model.setCurrentX(currX);}
    public void setCurrY(int currY){model.setCurrentY(currY);}
    public void scalePlus(){model.scalePlus();}
    public void scaleMinus(){model.scaleMinus();}
    public void setOldPoint(Point oldPoint)
    {
        model.setOldPoint(oldPoint);
    }
    public void setStartPoint(Point startPoint)
    {
        model.setStartPoint(startPoint);
    }
    public void setSumEl(int sumEl){model.setSumEl(sumEl);}
}
