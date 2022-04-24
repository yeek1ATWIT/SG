package plugin.FRCustom;

import java.awt.Point;
import java.io.Serializable;

/**
 * This class holds the information that allows FRShape class
 * to rotate around any point on a 2D axis.
 * <br>
 * This holds the points x and y the shape rotates around,
 * <br>
 * and the angle itself 'theta'.
 *
 */
public class FRRelativeRotation {
    private double x;
    private double y;
    private double theta;
    
    public FRRelativeRotation(Point coordinates, double feta) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
        this.theta = feta; 
    }
    
    public FRRelativeRotation(Point coordinates, int feta) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
        this.theta = feta; 
    }
    
    public FRRelativeRotation(double x, double y, double feta) {
        this.x = x;
        this.y = y;
        this.theta = feta; 
    }
    
    public FRRelativeRotation(int x, double y, double feta) {
        this.x = x;
        this.y = y;
        this.theta = feta; 
    }
    
    public FRRelativeRotation(double x, int y, double feta) {
        this.x = x;
        this.y = y;
        this.theta = feta; 
    }
    
    public FRRelativeRotation(double x, double y, int feta) {
        this.x = x;
        this.y = y;
        this.theta = feta; 
    }
    
    public FRRelativeRotation(int x, int y, double feta) {
        this.x = x;
        this.y = y;
        this.theta = feta; 
    }
    
    public FRRelativeRotation(double x, int y, int feta) {
        this.x = x;
        this.y = y;
        this.theta = feta; 
    }
    
    public FRRelativeRotation(int x, int y, int feta) {
        this.x = x;
        this.y = y;
        this.theta = feta; 
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getFeta() {
        return theta;
    }

    public void setFeta(double feta) {
        this.theta = feta;
    }
    
}
