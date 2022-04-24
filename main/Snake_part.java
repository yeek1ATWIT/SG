package main;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import plugin.FRCustom.*;

public abstract class Snake_part {
    public static ArrayList<Object> Hierarchy = new ArrayList<Object>();
    public static FRCanvas Snake_GUI = new FRCanvas(Hierarchy);
    protected int x;
    protected int y;
    protected int direction;
    protected FRShape2D Snake_part_GUI = new FRShape2D();
    protected int length;
    protected Dimension d = new Dimension(25,25);
    
    protected Snake_part() {
        if (Snake_part.Hierarchy == null) {
            Snake_part.Hierarchy = new ArrayList<Object>();
        }
    }

    public int getX() {
        return x;
    }
    public abstract void setX(int x);
    
    public int getY() {
        return y;
    }
    public abstract void setY(int y);
    
    public int getDirection() {
        return direction;
    }
    public abstract void setDirection(int direction);
    
    public int getLength() {
        return length;
    }
    
}
