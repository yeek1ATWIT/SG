
package plugin.FRCustom;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FRShape2D {

    /*
     * The more Simple Properties of the FRShape2D class
     * and includes the information of the shape and 
     * the way it's formatted in a 2D axis.
     */
        private Shape shape;
        private double rotationZ;
        /**
         * Relative Rotation allows a shape to rotate
         * around any point in a 2D axis.
         */
        public FRRelativeRotation[] relative_rotation = new FRRelativeRotation[0];

    /*
     * The more Complex Properties of the FRShape2D class
     * and includes the information of the shape's color,
     * and how the shape interacts with other shapes.
     */
        private Color color;
        
        /** 
         * Controls how transparent the 2D shape is,
         * 100 is completely solid, 0 is completely
         * invisible.
         */
        private double opacity;
        /** 
         * The color that the 2D shape glows onto other 2D shapes in a 2D plane. 
         * In other words, when the 2D shapes are arranged on the screen,
         * the 2D shape literally closest to the glowing 2D shape would experience
         * the most glow from the glowing 2D shape.
         */
        private Color glowColor2D;
        /** The power of the glowing color in the 2D plane. */
        private double glowPower2D;
        
        /** Contains the coordinates of the 2D shape in a 3D plane. */
        private FRPoint3D coordinates3D; 
        
        private double rotationX;
        
        private double rotationY;
        /**
         * The color that the 2D shape glows onto other 2D shapes in a 3D plane.
         * In other words, if the glowing 2D shape was in the middle of 
         * a room, it would light up the room with that color.
         */
        private Color glowColor3D;
        /** The power of the glowing color in the 3D plane. */
        private double glowPower3D;
        
        private double reflective3D;
        
        private Image image;
        
        /*
         * More Information about 2D vs. 3D planes:
         * 
         * If the 2D shape takes place in a 2D plane, its
         * 3D properties are neglected. These include coordinates3D,
         * glowColor3D, glowPower3D, reflective3D, rotationX, and
         * rotationY.
         * 
         * If the 2D shape takes place in a 3D plane, SOME
         * of its 3D properties are neglected. These include the
         * 'shape's' x-y location, and relative rotation.
         */
        
    public FRShape2D(Shape shape) { 
        this();
        this.shape = shape;
    }
    public FRShape2D() { //Ellipse2D i = new Ellipse2D.Double(1.0,1.0,1.0,1.0); ;Shape j = i;
        this.shape = new Rectangle();
        this.rotationZ = 0;
        this.color = Color.black;
        this.opacity = 100;
        this.glowColor2D = Color.black;
        this.glowPower2D = 0;
        this.coordinates3D = new FRPoint3D();
        this.rotationX = 0;
        this.rotationY = 0;
        this.glowColor3D = Color.black;
        this.glowPower3D = 0;
        this.reflective3D = 0; 
        this.image = null;
    }

    public Shape getShape() {
        return shape;
    }

    public Rectangle2D getBounds() {
        return shape.getBounds2D();
    }

    public double getX2D() {
        return shape.getBounds2D().getX();
    }

    public double getY2D() {
        return shape.getBounds2D().getY();
    }
    
    public double getWidth() {
        return shape.getBounds2D().getWidth();
    }

    public double getHeight() {
        return shape.getBounds2D().getHeight();
    }
    
    public Point2D getLocation2D() {
        Point2D pt = new Point();
        pt.setLocation(shape.getBounds2D().getX(), shape.getBounds2D().getY());
        return pt;
    }

    public double getCenterX2D() {
        return shape.getBounds2D().getCenterX();
    }

    public double getCenterY2D() {
        return shape.getBounds2D().getCenterY();
    }

    public double getRotationZ() {
        return rotationZ;
    }

    public Color getColor() {
        return color;
    }

    public Color getGlowColor2D() {
        return glowColor2D;
    }

    public double getGlowPower2D() {
        return glowPower2D;
    }
    
    public boolean isCoordinates3DNull() {
        return coordinates3D.isNull();
    }
    
    public double getX3D() {
        return coordinates3D.getX();
    }

    public double getY3D() {
        return coordinates3D.getY();
    }
    
    public double getZ3D() {
        return coordinates3D.getZ();
    }
    
    public FRPoint3D getLocation3D() {
        return coordinates3D;
    }
    
    public double getRotationX() {
        return rotationX;
    }
    
    public double getRotationY() {
        return rotationY;
    }
    
    public Color getGlowColor3D() {
        return glowColor3D;
    }

    public double getGlowPower3D() {
        return glowPower3D;
    }
    public double getReflective3D() {
        return reflective3D;
    }
    public double getOpacity() {
        return opacity;
    }
    public Image getImage() {
        return image;
    }
    
    public void setShape(Shape shape) {
        this.shape = shape;
    }
    
    public void setRotationZ(int rotationZ) {
        this.rotationZ = rotationZ;
    }
    
    public void setRotationZ(double rotationZ) {
        this.rotationZ = rotationZ;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setGlowColor2D(Color color) {
        this.glowColor2D = color;
    }
    
    public void setGlowPower2D(int glowPower2D) {
        this.glowPower2D = glowPower2D;
    }
    
    public void setGlowPower2D(double glowPower2D) {
        this.glowPower2D = glowPower2D;
    }
    
    public void setX3D(int x) {
        this.coordinates3D.setX(x);
    }
    
    public void setX3D(double x) {
        this.coordinates3D.setX(x);
    }

    public void setY3D(int y) {
        this.coordinates3D.setY(y);
    }
    
    public void setCoordinates3DNull(boolean Boolean) {
        this.coordinates3D.setNull(Boolean);
    }
    public void setY3D(double y) {
        this.coordinates3D.setY(y);
    }
    
    public void setZ3D(int z) {
        this.coordinates3D.setZ(z);
    }
    
    public void setZ3D(double z) {
        this.coordinates3D.setZ(z);
    }
    
    public void setLocation3D(FRPoint3D coordinates) {
        this.coordinates3D = coordinates;
    }
    
    public void setRotationX(int RX) {
        this.rotationX = RX;
    }
    
    public void setRotationX(double RX) {
        this.rotationX = RX;
    }
    
    public void setRotationY(int RY) {
        this.rotationY = RY;
    }
    
    public void setRotationY(double RY) {
        this.rotationY = RY;
    }
    
    public void setGlowColor3D(Color color) {
        this.glowColor3D = color;
    }

    public void setGlowPower3D(int glowPower3D) {
        this.glowPower3D = glowPower3D;
    }
    
    public void setGlowPower3D(double glowPower3D) {
        this.glowPower3D = glowPower3D;
    }
    
    public void setReflective3D(int reflectiveness) {
        this.reflective3D = reflectiveness;
    }
    
    public void setReflective3D(double reflectiveness) {
        this.reflective3D = reflectiveness;
    }
    
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setImage(ImageIcon image) {
        this.image = image.getImage();
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public void setImage(File file) { 
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
