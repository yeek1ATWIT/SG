package plugin.FRCustom;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class FRCube extends FRShape3D {
    
    public FRCube(FRPoint3D position, double length) {
        this(position, (int) length);
    }
    
    public FRCube(FRPoint3D position, int length) {
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();
        Rectangle2D rect1 = new Rectangle(0,0,0,0);
        rect1.setRect(0, 0, length, length);
        FRShape2D face1 = new FRShape2D(rect1);
        face1.setCoordinates3DNull(false);
        face1.setLocation3D(position);
        
        Rectangle2D rect2 = new Rectangle(0,0,0,0);
        rect2.setRect(0, 0, length, length);
        FRShape2D face2 = new FRShape2D(rect1);
        face2.setCoordinates3DNull(false);
        face2.setLocation3D(position);

        /*
        this.setLocation3D(position);
        this.shapes2D = new FRShape2D[] {
                new FRShape2D()
                position, 
                new FRPoint3D(x+length, y,          z), 
                new FRPoint3D(x,        y+length,   z), 
                new FRPoint3D(x,        y,          z+length), 
                new FRPoint3D(x+length, y+length,   z), 
                new FRPoint3D(x+length, y,          z+length), 
                new FRPoint3D(x,        y+length,   z+length), 
                new FRPoint3D(x+length, y+length,   z+length), }; */
    }

    public FRCube() {
        
    }
}
