import java.awt.*;

public class Controller {
    Model model;
    Controller(Model model)
    {
         this.model = model;
    }

    public int getArray(int i , int j)
    {
        return model.arrayResult[i][j];
    }
    public int[][] getAllArray()
    {
        return model.arrayResult;
    }

    public void genArray()
    {
        model.genArray();
    }
    public Point getOldPoint()
    {
        return model.oldPoint;
    }
    public Point getStartPoint()
    {
        return model.startPoint;
    }
    public void setOldPoint(Point oldPoint)
    {
        model.oldPoint=oldPoint;
    }
    public void setStartPoint(Point startPoint)
    {
       model.startPoint=startPoint;
    }
    public int getSumEl(){return model.sumEl;};
    public int getCurrX(){return model.currentX;}
    public int getCurrY(){return model.currentY;}
    public void setCurrX(int currX){model.currentX+=currX;}
    public void setCurrY(int currY){model.currentY+=currY;}
}
