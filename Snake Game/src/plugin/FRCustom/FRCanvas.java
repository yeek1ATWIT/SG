
package plugin.FRCustom;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.accessibility.Accessible;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FRCanvas extends JPanel implements Accessible {

    /**
     * Responsible for the arrangement of components into the java
     * window.
     * @Zeroes
     * <b> The origin of the screen is in the top left </b>
     * <br> (All Coordinates are positive) <br>
     * <br> 00 = The top left corner of each shape is the coordinates
     * (Default Coordinate System that java provides)
     * <br> 01 = The bottom left corner of each shape is the coordinates
     * <br> 02 = The top right corner of each shape is the coordinates
     * <br> 03 = The bottom right corner of each shape is the coordinates
     * <br> 04 = The center of each shape is the coordinates
     * <br>
     * @Tens
     * <b> The origin of the screen is in the bottom left </b>
     * <br> (All Coordinates are positive) <br>
     * <br> 10 = The top left corner of each shape is the coordinates,
     * <br> 11 = The bottom left corner of each shape is the coordinates,
     * <br> 12 = The top right corner of each shape is the coordinates,
     * <br> 13 = The bottom right corner of each shape is the coordinates,
     * <br> 14 = The center of each shape is the coordinates,
     * <br>
     * @Twenties
     * <b> The origin of the screen is in the top right </b>
     * <br> (All Coordinates are positive) <br>
     * <br> 20 = The top left corner of each shape is the coordinates,
     * <br> 21 = The bottom left corner of each shape is the coordinates,
     * <br> 22 = The top right corner of each shape is the coordinates,
     * <br> 23 = The bottom right corner of each shape is the coordinates,
     * <br> 24 = The center of each shape is the coordinates,
     * <br>
     * @Thirties
     * <b> The origin of the screen is in the bottom right </b>
     * <br> (All Coordinates are positive) <br>
     * <br> 30 = The top left corner of each shape is the coordinates,
     * <br> 31 = The bottom left corner of each shape is the coordinates,
     * <br> 32 = The top right corner of each shape is the coordinates,
     * <br> 33 = The bottom right corner of each shape is the coordinates,
     * <br> 34 = The center of each shape is the coordinates,
     * <br>
     * @Fourties
     * <b> The origin of the screen is in the Center </b>
     * <br>
     * <br> 40 = The top left corner of each shape is the coordinates,
     * <br> 41 = The bottom left corner of each shape is the coordinates,
     * <br> 42 = The top right corner of each shape is the coordinates,
     * <br> 43 = The bottom right corner of each shape is the coordinates,
     * <br> 44 = The center of each shape is the coordinates,
     * <br>
     */
    public int Coordinate_System = 00;

    private boolean Resizable = false;

    private int X;

    private int Y;

    private Dimension D = new Dimension(100, 100);

    /**
     * Responsible for keeping track of the type of shape or the file path
     * to accessing images.
     */
    private String[] string = new String[0];

    private int[] shapeX;
    private int[] shapeY;
    private int[] width;
    private int[] height;
    private int[] rotation;
    private int[] origin_x;
    private int[] origin_y;
    private int[] relative_rotation;
    private Shape[] shape;
    private Color[] color;
    private int[][] polygon_xPoints;
    private int[][] polygon_yPoints;

    public Object[] shapes = new Object[1];

    public FRCanvas(Object[] Shapes) {
        this.shapes = Shapes;
    }
    
    public FRCanvas(FRShape2D Shape) {
        this.shapes[0] = Shape;
    }
    
    public FRCanvas(JLabel Label) {
        this.shapes[0] = Label;
    }
    
    /** Generally not used normally... */
    public FRCanvas(String[] file, Shape[] shape, Color[] color, int[][] polygon_xPoints,
            int[][] polygon_yPoints, int[] x, int[] y, int[] width, int[] height, int[] rotation,
            int[] origin_x, int[] origin_y, int[] relative_rotation) {
        this.string = file;
        this.shape = shape;
        this.color = color;
        this.polygon_xPoints = polygon_xPoints;
        this.polygon_yPoints = polygon_yPoints;
        this.shapeX = x;
        this.shapeY = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.origin_x = origin_x;
        this.origin_y = origin_y;
        this.relative_rotation = relative_rotation;

    }

    public FRCanvas(FRCanvasLayer layer) {
        this.shapes = layer.getShapes();

    }

    public FRCanvas(FRCanvasLayers layers) {
        this.shapes = layers.getCombinedLayer().getShapes();
    }

    /**
     * Creates a shape (rectangle/oval) with a color,
     * and places it at a bounds of x and y, with an inputted width and height
     * rotated by a variable in degrees. It also has another source of rotation
     * around another declared origin- this rotation variable is separate and 
     * in degrees.
     * 
     */
    public FRCanvas(String shape, Color color, int x, int y, int width, int height, int rotation,
            int origin_x, int origin_y, int relative_rotation) {
        this.string = new String[1];
        this.shape = new Shape[1];
        this.color = new Color[1];
        this.polygon_xPoints = new int[1][];
        this.polygon_yPoints = new int[1][];
        this.shapeX = new int[1];
        this.shapeY = new int[1];
        this.width = new int[1];
        this.height = new int[1];
        this.rotation = new int[1];
        this.origin_x = new int[1];
        this.origin_y = new int[1];
        this.relative_rotation = new int[1];

        this.string[0] = shape;
        this.shape[0] = new Rectangle(1, 1, 1, 1);
        this.color[0] = color;
        this.shapeX[0] = x;
        this.shapeY[0] = y;
        this.polygon_xPoints[0] = new int[] {
                0
        };
        this.polygon_yPoints[0] = new int[] {
                0
        };
        this.width[0] = width;
        this.height[0] = height;
        this.rotation[0] = rotation;
        this.origin_x[0] = origin_x;
        this.origin_y[0] = origin_y;
        this.relative_rotation[0] = relative_rotation;

    }

    /**
     * Creates a polygon with a color,
     * and places it at a bounds of x and y, and is
     * rotated by a variable in degrees. It also has another source of rotation
     * around another declared origin- this rotation variable is separate and 
     * in degrees.
     * 
     */
    public FRCanvas(String shape, Color color, int[] polygon_xPoints, int[] polygon_yPoints, int x,
            int y, int rotation, int origin_x, int origin_y, int relative_rotation) {
        this.string = new String[1];
        this.shape = new Shape[1];
        this.color = new Color[1];
        this.polygon_xPoints = new int[1][];
        this.polygon_yPoints = new int[1][];
        this.shapeX = new int[1];
        this.shapeY = new int[1];
        this.width = new int[1];
        this.height = new int[1];
        this.rotation = new int[1];
        this.origin_x = new int[1];
        this.origin_y = new int[1];
        this.relative_rotation = new int[1];

        this.string[0] = shape;
        this.shape[0] = new Rectangle(1, 1, 1, 1);
        this.color[0] = color;
        this.shapeX[0] = x;
        this.shapeY[0] = y;
        this.polygon_xPoints[0] = polygon_xPoints;
        this.polygon_yPoints[0] = polygon_yPoints;
        this.width[0] = 0;
        this.height[0] = 0;
        this.rotation[0] = rotation;
        this.origin_x[0] = origin_x;
        this.origin_y[0] = origin_y;
        this.relative_rotation[0] = relative_rotation;

    }

    /**
     * Uses a given a shape (rectangle/oval) with a color,
     * and places it at a bounds of x and y, with an inputted width and height
     * rotated by a variable in degrees. It also has another source of rotation
     * around another declared origin- this rotation variable is separate and 
     * in degrees.
     * (NOT YET FUNCTIONAL!)
     * 
     */
    public FRCanvas(Shape shape, Color color, int x, int y, int width, int height, int rotation,
            int origin_x, int origin_y, int relative_rotation) {
        this.string = new String[1];
        this.shape = new Shape[1];
        this.color = new Color[1];
        this.shapeX = new int[1];
        this.shapeY = new int[1];
        this.width = new int[1];
        this.height = new int[1];
        this.rotation = new int[1];
        this.origin_x = new int[1];
        this.origin_y = new int[1];
        this.relative_rotation = new int[1];

        this.string[0] = "";
        this.shape[0] = shape;
        this.color[0] = color;
        this.shapeX[0] = x;
        this.shapeY[0] = y;
        this.width[0] = width;
        this.height[0] = height;
        this.rotation[0] = rotation;
        this.origin_x[0] = origin_x;
        this.origin_y[0] = origin_y;
        this.relative_rotation[0] = relative_rotation;

    }

    /**
     * Places an image at a bounds of x and y, with an inputted width and height
     * rotated by a variable in degrees. It also has another source of rotation
     * around another declared origin- this rotation variable is separate and 
     * in degrees.
     * 
     */
    public FRCanvas(String file, int x, int y, int width, int height, int rotation, int origin_x,
            int origin_y, int relative_rotation) {
        this.string = new String[1];
        this.shape = new Shape[1];
        this.color = new Color[1];
        this.shapeX = new int[1];
        this.shapeY = new int[1];
        this.polygon_xPoints = new int[1][];
        this.polygon_yPoints = new int[1][];
        this.width = new int[1];
        this.height = new int[1];
        this.rotation = new int[1];
        this.origin_x = new int[1];
        this.origin_y = new int[1];
        this.relative_rotation = new int[1];

        this.string[0] = file;
        this.shape[0] = new Rectangle(1, 1, 1, 1);
        this.color[0] = new Color(0, 0, 0);
        this.polygon_xPoints[0] = new int[] {
                0
        };
        this.polygon_yPoints[0] = new int[] {
                0
        };
        this.shapeX[0] = x;
        this.shapeY[0] = y;
        this.width[0] = width;
        this.height[0] = height;
        this.rotation[0] = rotation;
        this.origin_x[0] = origin_x;
        this.origin_y[0] = origin_y;
        this.relative_rotation[0] = relative_rotation;

    }

    /**
     * Creates a shape (rectangle/oval) with a color,
     * and places it at a bounds of x and y, with an inputted width and height
     * rotated by a variable in degrees. 
     * (NOT YET FUNCTIONAL!)
     * 
     */
    public FRCanvas(Shape shape, Color color, int x, int y, int width, int height, int rotation) {
        this.string = new String[1];
        this.shape = new Shape[1];
        this.color = new Color[1];
        this.polygon_xPoints = new int[1][];
        this.polygon_yPoints = new int[1][];
        this.shapeX = new int[1];
        this.shapeY = new int[1];
        this.width = new int[1];
        this.height = new int[1];
        this.rotation = new int[1];
        this.origin_x = new int[1];
        this.origin_y = new int[1];
        this.relative_rotation = new int[1];

        this.string[0] = "";
        this.shape[0] = shape;
        this.color[0] = color;
        this.polygon_xPoints[0] = new int[] {
                0
        };
        this.polygon_yPoints[0] = new int[] {
                0
        };
        this.shapeX[0] = x;
        this.shapeY[0] = y;
        this.width[0] = width;
        this.height[0] = height;
        this.rotation[0] = rotation;
        this.origin_x[0] = 0;
        this.origin_y[0] = 0;
        this.relative_rotation[0] = 0;

    }

    /**
     * Uses a given shape with a color,
     * and places it at a bounds of x and y, with an inputted width and height
     * rotated by a variable in degrees. 
     * 
     * 
     */
    public FRCanvas(String shape, Color color, int x, int y, int width, int height, int rotation) {
        this.string = new String[1];
        this.shape = new Shape[1];
        this.color = new Color[1];
        this.polygon_xPoints = new int[1][];
        this.polygon_yPoints = new int[1][];
        this.shapeX = new int[1];
        this.shapeY = new int[1];
        this.width = new int[1];
        this.height = new int[1];
        this.rotation = new int[1];
        this.origin_x = new int[1];
        this.origin_y = new int[1];
        this.relative_rotation = new int[1];

        this.string[0] = shape;
        this.shape[0] = new Rectangle(1, 1, 1, 1);
        this.color[0] = color;
        this.polygon_xPoints[0] = new int[] {
                0
        };
        this.polygon_yPoints[0] = new int[] {
                0
        };
        this.shapeX[0] = x;
        this.shapeY[0] = y;
        this.width[0] = width;
        this.height[0] = height;
        this.rotation[0] = rotation;
        this.origin_x[0] = 0;
        this.origin_y[0] = 0;
        this.relative_rotation[0] = 0;

    }

    /**
     * Places an image at a bounds of x and y, with an inputted width and height
     * rotated by a variable in degrees. 
     * 
     */
    public FRCanvas(String file, int x, int y, int width, int height, int rotation) {
        this.string = new String[1];
        this.shape = new Shape[1];
        this.color = new Color[1];
        this.polygon_xPoints = new int[1][];
        this.polygon_yPoints = new int[1][];
        this.shapeX = new int[1];
        this.shapeY = new int[1];
        this.width = new int[1];
        this.height = new int[1];
        this.rotation = new int[1];
        this.origin_x = new int[1];
        this.origin_y = new int[1];
        this.relative_rotation = new int[1];

        this.string[0] = file;
        this.shape[0] = new Rectangle(1, 1, 1, 1);
        this.color[0] = new Color(0, 0, 0);
        this.polygon_xPoints[0] = new int[] {
                0
        };
        this.polygon_yPoints[0] = new int[] {
                0
        };
        this.shapeX[0] = x;
        this.shapeY[0] = y;
        this.width[0] = width;
        this.height[0] = height;
        this.rotation[0] = rotation;
        this.origin_x[0] = 0;
        this.origin_y[0] = 0;
        this.relative_rotation[0] = 0;

    }

    public FRCanvas() {
        //this.setLayout(new BorderLayout());
        //this.setPreferredSize(new Dimension(1200, 825));
        //this.setSize(new Dimension(300, 300));
    }
    
    private void instanceofFRShape2D(Graphics2D g2d, Object shape) {
        FRShape2D s = ((FRShape2D) shape);
        /** If 3D coordinates are invalid, use the 2D coordinates */
        if (s.isCoordinates3DNull()) {
            /** Rotates g2d to prepare to apply shape */
            AffineTransform tx = new AffineTransform();
                if (Coordinate_System == 00) {
                    tx.setToRotation(Math.toRadians(s.getRotationZ()), s.getX2D(), s.getY2D());
                } else if (Coordinate_System == 04) {
                    tx.setToRotation(Math.toRadians(s.getRotationZ()), s.getCenterX2D(), s.getCenterY2D());
                }
            g2d.transform(tx);
            
            if (s.getImage() != null) { 
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) s.getOpacity()/100));
                BufferedImage raw_image = (BufferedImage) s.getImage();   
                ImageIcon imageicon = new ImageIcon(raw_image);
                Image scaled_image = imageicon.getImage().getScaledInstance((int) s.getWidth(), (int) s.getHeight(),
                        Image.SCALE_DEFAULT);
                BufferedImage Final = toBufferedImage(scaled_image);
                g2d.drawImage(Final, null, (int) s.getX2D(), (int) s.getY2D());
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            } else {
                
                for (int i = 0; i < ((FRShape2D) shape).relative_rotation.length; i++) {
                    AffineTransform tx2 = new AffineTransform();
                        tx2.setToRotation(
                                Math.toRadians(((FRShape2D) shape).relative_rotation[i].getFeta()),
                                ((FRShape2D) shape).relative_rotation[i].getX(),
                                ((FRShape2D) shape).relative_rotation[i].getY());
                    g2d.transform(tx2);
                }
                
                
                /** Draws the shape onto the screen */
                if (s.getShape() instanceof Rectangle2D) {
                    g2d.setColor(s.getColor());
                    g2d.fillRect((int) (s.getX2D() - FRPlayer.getX()),
                            (int) (s.getY2D() - FRPlayer.getX()), (int) s.getWidth(),
                            (int) s.getHeight());
                } else if (s.getShape() instanceof Ellipse2D) {
                    g2d.setColor(s.getColor());
                    g2d.fillOval((int) (s.getX2D() - FRPlayer.getX()),
                            (int) (s.getY2D() - FRPlayer.getX()), (int) s.getWidth(),
                            (int) s.getHeight());
                } else if (s.getShape() instanceof Polygon) {
                    g2d.setColor(s.getColor());
                    int[] poly_x = ((Polygon) s.getShape()).xpoints;
                    int[] poly_y = ((Polygon) s.getShape()).ypoints;
                    for (int i = 0; i < poly_x.length; i++) {
                        poly_x[i] -= FRPlayer.getX();
                    }
                    for (int i = 0; i < poly_y.length; i++) {
                        poly_y[i] -= FRPlayer.getY();
                    }
                    g2d.fillPolygon(poly_x, poly_y, poly_x.length);
                } else if (s.getShape() instanceof Image) {
                    BufferedImage raw_image = (BufferedImage) s.getShape();
                    ImageIcon imageicon = new ImageIcon(raw_image);
                    Image scaled_image = imageicon.getImage().getScaledInstance(
                            (int) s.getWidth(), (int) s.getHeight(), Image.SCALE_DEFAULT);
                    BufferedImage Final = toBufferedImage(scaled_image);
                    g2d.drawImage(Final, null, 
                            (int) (s.getX2D() - FRPlayer.getX()),
                            (int) (s.getY2D() - FRPlayer.getY()));
                }
            }
            /** Resets Rotation */
            for (int i = ((FRShape2D) shape).relative_rotation.length - 1; i >= 0; i--) {
                AffineTransform tx3 = new AffineTransform();
                    tx3.setToRotation(
                            -1 * Math.toRadians(((FRShape2D) shape).relative_rotation[i].getFeta()),
                            ((FRShape2D) shape).relative_rotation[i].getX(),
                            ((FRShape2D) shape).relative_rotation[i].getY());
                g2d.transform(tx3);
            }
            AffineTransform tx4 = new AffineTransform();
                if (Coordinate_System == 00) {
                    tx4.setToRotation(
                            -1 * Math.toRadians(s.getRotationZ()), 
                            s.getX2D(),
                            s.getY2D());
                } else if (Coordinate_System == 04) {
                    tx4.setToRotation(
                            -1 * Math.toRadians(s.getRotationZ()), 
                            s.getCenterX2D(),
                            s.getCenterY2D());
                }
                
            g2d.transform(tx4);

        /** If the 3D coordinates are valid, then use it. WIP */
        } else if (!s.isCoordinates3DNull()) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            AffineTransform at = AffineTransform.getTranslateInstance(75, 75);
            g2d.transform(at);
            
            Shape shape5 = new Rectangle2D.Float(300, 300, 200, 200);
            // Draw the shapes in their original locations.
            g2d.setPaint(Color.black);
            g2d.draw(shape5);

            // Transform the Graphics2D.
              AffineTransform sat = AffineTransform.getTranslateInstance(150, 0);
              sat.shear(-.5, 0);
              g2d.transform(sat);

            // Draw the "new" shapes in dashed.
              g2d.transform(AffineTransform.getTranslateInstance(75, 75));
              
            Stroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 0, new float[] { 3, 1 }, 0);
            g2d.setStroke(stroke);
            g2d.draw(shape5);
            
            if (FRPlayer.x <= s.getX3D()) {
                AffineTransform txX = new AffineTransform();
                txX.setToRotation(s.getRotationX());
            }
            if (FRPlayer.x > s.getX3D()) {
                AffineTransform txX = new AffineTransform();
                txX.setToRotation(s.getRotationX());
                txX.shear(X, Y);
                
            }
            
            double scaledx = Math.cos(s.getRotationX());
        }
    }
    
    private void instanceofJLabel(Graphics2D g2d, Object shape) {
        JLabel l = (JLabel) shape;
        g2d.setColor(l.getForeground());
        g2d.setFont(l.getFont());
        char[] raw_text = l.getText().toCharArray();
        StringBuilder line = new StringBuilder();
        int line_num = 0;
        StringBuilder command = new StringBuilder();
        boolean com_condition = false;
        for (int i = 0; i < raw_text.length; i++) {
            if (com_condition == true) {
                if (raw_text[i] != '>') {
                    command.append(raw_text[i]);
                } else if (raw_text[i] == '>') {
                    if (command.toString().contentEquals("br")) { 
                        command.delete(0, command.length()-1);
                        if (line.toString().length() > 0) {
                            g2d.drawChars(line.toString().toCharArray(), 0, line.toString().length()-1, l.getBounds().x, l.getBounds().y + line_num*(l.getFont().getSize()));
                        }
                        line = new StringBuilder();
                        command = new StringBuilder();
                        line_num += 1;
                        com_condition = false;
                    } else if (command.toString().contentEquals("b")) {
                        command.delete(0, command.length()-1);
                        if (line.toString().length() > 0) {
                            g2d.drawChars(line.toString().toCharArray(), 0, line.toString().length()-1, l.getBounds().x, l.getBounds().y + line_num*(l.getFont().getSize()));
                        }
                        line = new StringBuilder();
                        if (l.getFont().getStyle() == 0) {
                            g2d.setFont(new Font(l.getFont().getName(), 1, l.getFont().getSize()));
                        } else if (l.getFont().getStyle() == 2) {
                            g2d.setFont(new Font(l.getFont().getName(), 3, l.getFont().getSize()));
                        }
                        command = new StringBuilder();
                        com_condition = false;
                    } else if (command.toString().contentEquals("/b")) {
                        command.delete(0, command.length()-1);
                        if (line.toString().length() > 0) {
                            g2d.drawChars(line.toString().toCharArray(), 0, line.toString().length()-1, l.getBounds().x, l.getBounds().y + line_num*(l.getFont().getSize()));
                        }
                        line = new StringBuilder();
                        if (l.getFont().getStyle() == 1) {
                            g2d.setFont(new Font(l.getFont().getName(), 0, l.getFont().getSize()));
                        } else if (l.getFont().getStyle() == 3) {
                            g2d.setFont(new Font(l.getFont().getName(), 2, l.getFont().getSize()));
                        }
                        command = new StringBuilder();
                        com_condition = false;
                    } else { 
                        command = new StringBuilder();
                        com_condition = false;
                    }
                }
            } else if (com_condition == false) {
                if (raw_text[i] == '<') {
                    com_condition = true;
                } else {
                    line.append(raw_text[i]);
                }
            }
        }
        g2d.drawChars(line.toString().toCharArray(), 0, line.toString().length(), l.getBounds().x, l.getBounds().y + line_num*(l.getFont().getSize()));
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ;
        for (int i = 0 ; i < shapes.length; i++) {
            Object shape = shapes[i];
            if (shape instanceof FRShape3D) {
                if (shape instanceof FRCube) {

                } else if (shape instanceof FRPrism) {

                }
            } else if (shape instanceof FRShape2D) {
                instanceofFRShape2D(g2d, shape);
            } else if (shape instanceof JLabel) { 
                instanceofJLabel(g2d, shape);
            }  
                 
        }
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        AffineTransform at = AffineTransform.getTranslateInstance(50, 0);
        g2d.transform(at);
        
        Shape shape5 = new Rectangle2D.Float(300, 300, 200, 200);
        // Draw the shapes in their original locations.
        g2d.setPaint(Color.black);
        //g2d.draw(shape5);

        // Transform the Graphics2D.
          AffineTransform sat = AffineTransform.getTranslateInstance(0, 0);
          sat.shear(-.5, 0);
          //sat.scale(2,-1);
          g2d.transform(sat);
          
        // Draw the "new" shapes in dashed.
        g2d.transform(AffineTransform.getTranslateInstance(0, 0));
        
        Stroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
        BasicStroke.JOIN_BEVEL, 0, new float[] { 3, 1 }, 0);
        g2d.setStroke(stroke);
        //g2d.draw(shape5);
        
        /**
        g2d.setColor();
        g2d.setFont(new Font());
        g2d.drawChars(char[], 0, char.length, x, y);
        */
        /** Goes through all the shapes given */
        for (int i = 0; i < string.length; i++) {
            /** Rotates graphics if rotations aren't 0 */
            if (rotation[i] != 0 || relative_rotation[i] != 0) {
                AffineTransform tx = new AffineTransform();
                tx.setToRotation(Math.toRadians(rotation[i]), shapeX[i], shapeY[i]);
                g2d.transform(tx);
                AffineTransform tx2 = new AffineTransform();
                tx2.setToRotation(Math.toRadians(relative_rotation[i]), origin_x[i], origin_y[i]);
                g2d.transform(tx2);
            }
            /** Puts the shape into the graphics */
            if (string[i].contentEquals("rectangle") || string[i].contentEquals("rect") ||
                    string[i].contentEquals("Rectangle") || string[i].contentEquals("Rect")) {
                g2d.setColor(color[i]);
                g2d.fillRect((int) (shapeX[i] - (width[i] / 2)),
                        (int) (shapeY[i] - (height[i] / 2)), width[i], height[i]);
            } else if (string[i].contentEquals("oval") || string[i].contentEquals("circle") ||
                    string[i].contentEquals("Oval") || string[i].contentEquals("Circle")) {
                g2d.setColor(color[i]);
                g2d.fillOval((int) (shapeX[i] - (width[i] / 2)),
                        (int) (shapeY[i] - (height[i] / 2)), width[i], height[i]);
            } else if (string[i].contentEquals("polygon") || string[i].contentEquals("poly") ||
                    string[i].contentEquals("Polygon") || string[i].contentEquals("Poly")) {
                g2d.setColor(color[i]);
                int[] polygon_coordinate_xPoints = new int[polygon_xPoints[i].length];
                int[] polygon_coordinate_yPoints = new int[polygon_yPoints[i].length];
                int center_x = FindCenter(polygon_xPoints[i]);
                int center_y = FindCenter(polygon_yPoints[i]);
                for (int q = 0; q < polygon_xPoints[i].length; q++) {
                    polygon_coordinate_xPoints[q] = polygon_xPoints[i][q] + shapeX[i] - center_x;
                }
                for (int q = 0; q < polygon_yPoints[i].length; q++) {
                    polygon_coordinate_yPoints[q] = polygon_yPoints[i][q] + shapeY[i] - center_y;
                }
                g2d.fillPolygon(polygon_coordinate_xPoints, polygon_coordinate_yPoints,
                        polygon_xPoints[i].length + polygon_yPoints[i].length);
            } else if (string[i].contentEquals("")) {
                g2d.setColor(color[i]);
                g2d.fill(shape[i]);
            } else {
                BufferedImage raw_image;
                try {
                    raw_image = ImageIO.read(new File(string[i]));
                    ImageIcon imageicon = new ImageIcon(raw_image);
                    Image scaled_image = imageicon.getImage().getScaledInstance(width[i], height[i],
                            Image.SCALE_DEFAULT);
                    BufferedImage Final = toBufferedImage(scaled_image);
                    g2d.drawImage(Final, null, (int) (shapeX[i] - (width[i] / 2)),
                            (int) (shapeY[i] - (height[i] / 2)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /** Inverse rotates the graphics if rotation was applied */
            if (rotation[i] != 0 || relative_rotation[i] != 0) {
                AffineTransform tx3 = new AffineTransform();
                tx3.setToRotation(Math.toRadians(-1 * rotation[i]), shapeX[i], shapeY[i]);
                AffineTransform tx4 = new AffineTransform();
                tx4.setToRotation(Math.toRadians(-1 * relative_rotation[i]), origin_x[i],
                        origin_y[i]);
                g2d.transform(tx4);
                g2d.transform(tx3);
            }

        }

    }

    private boolean clicked = false;
    
    private MouseListener ML = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (getX() <= e.getX() && e.getX() <= getX() + D.getWidth() &&
                    getY() <= e.getY() && e.getY() <= getY() + D.getHeight()) {
                //System.out.println(this.hashCode());
                clicked = true;
                Fixed_Cursor_X = (int) (e.getX() - getX());
                Fixed_Cursor_Y = (int) (e.getY() - getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            clicked = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    };

    private MouseMotionListener MML = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            //System.out.println(getX()+":"+getY());
            if (clicked) {
                setX(e.getX() - Fixed_Cursor_X);
                setY(e.getY() - Fixed_Cursor_Y);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

    };

    private int Fixed_Cursor_X;
    private int Fixed_Cursor_Y;

    public void setDraggable(boolean Boolean) {
        int f = MouseEvent.MOUSE_DRAGGED;
        //this.listenerList = 1;
        if (Boolean) {
            this.addMouseListener(ML);
            this.addMouseMotionListener(MML);
        } else {
            this.removeMouseListener(ML);
            this.removeMouseMotionListener(MML);
        }

    }
    public void updateDrag() {
        int f = MouseEvent.MOUSE_DRAGGED;
        //String b = MouseEvent.getModifiersExText(MouseEvent.MOUSE_CLICKED);
        String b = MouseEvent.getMouseModifiersText(16); 
        getMouseListeners();
        MouseListener[] p = getMouseListeners();
        p[0].mouseClicked(null);
        //long j = MouseEvent.MOUSE_EVENT_MASK;
        getMousePosition();
        /*
        for (int i = 40; i < 44; i++) {
            System.out.println(MouseEvent.getMouseModifiersText(i));
        }*/ 
        //System.out.println(MouseEvent.getMaskForButton(MouseEvent.BUTTON1));
       // System.out.println(MouseEvent.getMaskForButton(MouseEvent.BUTTON2));
        //System.out.println(MouseEvent.getMaskForButton(MouseEvent.MOUSE_CLICKED));
        
    }
    
    public void setDimension(Dimension d) {
        this.D = d;
    }
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    private static int FindCenter(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int max = array[0];
        int min = array[0];

        for (int num : array) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        return max - min;
    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    /**
    * METHOD DOESN'T WORK!
    * 
    */
    public FRCanvas addPaint(String file_shape, Color color, int x, int y, int width, int height,
            int rotation, int origin_x, int origin_y, int relative_rotation) {

        int Current_int_length = this.string.length;

        String[] file_copy = this.string;
        this.string = new String[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.string[i] = file_copy[i];
        }
        this.string[Current_int_length] = file_shape;

        Shape[] shape_copy = this.shape;
        this.shape = new Shape[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shape[i] = shape_copy[i];
        }
        this.shape[Current_int_length] = new Rectangle(1, 1, 1, 1);

        Color[] color_copy = this.color;
        this.color = new Color[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.color[i] = color_copy[i];
        }
        this.color[Current_int_length] = color;

        int[] x_copy = this.shapeX;
        this.shapeX = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shapeX[i] = x_copy[i];
        }
        this.shapeX[Current_int_length] = x;

        int[] y_copy = this.shapeY;
        this.shapeY = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shapeY[i] = y_copy[i];
        }
        this.shapeY[Current_int_length] = y;

        int[] width_copy = this.width;
        this.width = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.width[i] = width_copy[i];
        }
        this.width[Current_int_length] = width;

        int[] height_copy = this.height;
        this.height = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.height[i] = height_copy[i];
        }
        this.height[Current_int_length] = height;

        int[] rotation_copy = this.rotation;
        this.rotation = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.rotation[i] = rotation_copy[i];
        }
        this.rotation[Current_int_length] = rotation;

        int[] origin_x_copy = this.origin_x;
        this.origin_x = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.origin_x[i] = origin_x_copy[i];
        }
        this.origin_x[Current_int_length] = origin_x;

        int[] origin_y_copy = this.origin_y;
        this.origin_y = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.origin_y[i] = origin_y_copy[i];
        }
        this.origin_y[Current_int_length] = origin_y;

        int[] relative_rotation_copy = this.relative_rotation;
        this.relative_rotation = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.relative_rotation[i] = relative_rotation_copy[i];
        }
        this.relative_rotation[Current_int_length] = relative_rotation;

        //JCanvas i = new JCanvas(this.string, this.shape, this.color, this.x, this.y, this.width, this.height, this.rotation, this.origin_x, this.origin_y, this.relative_rotation);
        return new FRCanvas();
    }

    /**
    * METHOD DOESN'T WORK!
    * 
    */
    public FRCanvas addPaint(Shape shape, Color color, int x, int y, int width, int height,
            int rotation, int origin_x, int origin_y, int relative_rotation) {

        int Current_int_length = this.string.length;

        String[] file_copy = this.string;
        this.string = new String[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.string[i] = file_copy[i];
        }
        this.string[Current_int_length] = "";

        Shape[] shape_copy = this.shape;
        this.shape = new Shape[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shape[i] = shape_copy[i];
        }
        this.shape[Current_int_length] = shape;

        Color[] color_copy = this.color;
        this.color = new Color[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.color[i] = color_copy[i];
        }
        this.color[Current_int_length] = color;

        int[] x_copy = this.shapeX;
        this.shapeX = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shapeX[i] = x_copy[i];
        }
        this.shapeX[Current_int_length] = x;

        int[] y_copy = this.shapeY;
        this.shapeY = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shapeY[i] = y_copy[i];
        }
        this.shapeY[Current_int_length] = y;

        int[] width_copy = this.width;
        this.width = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.width[i] = width_copy[i];
        }
        this.width[Current_int_length] = width;

        int[] height_copy = this.shapeX;
        this.height = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.height[i] = height_copy[i];
        }
        this.height[Current_int_length] = height;

        int[] rotation_copy = this.rotation;
        this.rotation = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.rotation[i] = rotation_copy[i];
        }
        this.rotation[Current_int_length] = rotation;

        int[] origin_x_copy = this.origin_x;
        this.origin_x = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.origin_x[i] = origin_x_copy[i];
        }
        this.origin_x[Current_int_length] = origin_x;

        int[] origin_y_copy = this.origin_y;
        this.origin_y = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.origin_y[i] = origin_y_copy[i];
        }
        this.origin_y[Current_int_length] = origin_y;

        int[] relative_rotation_copy = this.relative_rotation;
        this.relative_rotation = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.relative_rotation[i] = relative_rotation_copy[i];
        }
        this.relative_rotation[Current_int_length] = relative_rotation;

        //JCanvas i = new JCanvas(this.string, this.shape, this.color, this.x, this.y, this.width, this.height, this.rotation, this.origin_x, this.origin_y, this.relative_rotation);
        return new FRCanvas();
    }

    /**
    * METHOD DOESN'T WORK!
    * 
    */
    public FRCanvas addPaint(String file, int x, int y, int width, int height, int rotation,
            int origin_x, int origin_y, int relative_rotation) {

        int Current_int_length = this.string.length;

        String[] file_copy = this.string;
        this.string = new String[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.string[i] = file_copy[i];
        }
        this.string[Current_int_length] = file;

        Shape[] shape_copy = this.shape;
        this.shape = new Shape[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shape[i] = shape_copy[i];
        }
        this.shape[Current_int_length] = new Rectangle(1, 1, 1, 1);

        Color[] color_copy = this.color;
        this.color = new Color[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.color[i] = color_copy[i];
        }
        this.color[Current_int_length] = new Color(0, 0, 0);

        int[] x_copy = this.shapeX;
        this.shapeX = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shapeX[i] = x_copy[i];
        }
        this.shapeX[Current_int_length] = x;

        int[] y_copy = this.shapeY;
        this.shapeY = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.shapeY[i] = y_copy[i];
        }
        this.shapeY[Current_int_length] = y;

        int[] width_copy = this.width;
        this.width = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.width[i] = width_copy[i];
        }
        this.width[Current_int_length] = width;

        int[] height_copy = this.shapeX;
        this.height = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.height[i] = height_copy[i];
        }
        this.height[Current_int_length] = height;

        int[] rotation_copy = this.rotation;
        this.rotation = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.rotation[i] = rotation_copy[i];
        }
        this.rotation[Current_int_length] = rotation;

        int[] origin_x_copy = this.origin_x;
        this.origin_x = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.origin_x[i] = origin_x_copy[i];
        }
        this.origin_x[Current_int_length] = origin_x;

        int[] origin_y_copy = this.origin_y;
        this.origin_y = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.origin_y[i] = origin_y_copy[i];
        }
        this.origin_y[Current_int_length] = origin_y;

        int[] relative_rotation_copy = this.relative_rotation;
        this.relative_rotation = new int[Current_int_length + 1];
        for (int i = 0; i < Current_int_length; i++) {
            this.relative_rotation[i] = relative_rotation_copy[i];
        }
        this.relative_rotation[Current_int_length] = relative_rotation;

        //JCanvas i = new JCanvas(this.string, this.shape, this.color, this.x, this.y, this.width, this.height, this.rotation, this.origin_x, this.origin_y, this.relative_rotation);
        return new FRCanvas();
    }

    /**
    public Image_Rotation addPaint(String file, Shape shape, Color color, int x, int y, int width, int height, int rotation, int origin_x, int origin_y, int relative_rotation) {
       
       int Current_int_length = this.file.length;
       
       
       String[] file_copy = this.file;
       this.file = new String[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.file[i] = file_copy[i];
       }
       this.file[Current_int_length] = file;
       
       Shape[] shape_copy = this.shape;
       this.shape = new Shape[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.shape[i] = shape_copy[i];
       }
       this.shape[Current_int_length] = shape;
       
       Color[] color_copy = this.color;
       this.color = new Color[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.color[i] = color_copy[i];
       }
       this.color[Current_int_length] = color;
       
       int[] x_copy = this.x;
       this.x = new int[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.x[i] = x_copy[i];
       }
       this.x[Current_int_length] = x;
       
       int[] y_copy = this.y;
       this.y = new int[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.y[i] = y_copy[i];
       }
       this.y[Current_int_length] = y;
       
       int[] width_copy = this.width;
       this.width = new int[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.width[i] = width_copy[i];
       }
       this.width[Current_int_length] = width;
       
       int[] height_copy = this.x;
       this.height = new int[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.height[i] = height_copy[i];
       }
       this.height[Current_int_length] = height;
       
       int[] rotation_copy = this.rotation;
       this.rotation = new int[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.rotation[i] = rotation_copy[i];
       }
       this.rotation[Current_int_length] = rotation;
       
       int[] origin_x_copy = this.origin_x;
       this.origin_x = new int[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.origin_x[i] = origin_x_copy[i];
       }
       this.origin_x[Current_int_length] = origin_x;
       
       int[] origin_y_copy = this.origin_y;
       this.origin_y = new int[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.origin_y[i] = origin_y_copy[i];
       }
       this.origin_y[Current_int_length] = origin_y;
       
       int[] relative_rotation_copy = this.relative_rotation;
       this.relative_rotation = new int[Current_int_length+1];
       for (int i = 0; i < Current_int_length; i++) {
    	   this.relative_rotation[i] = relative_rotation_copy[i];
       }
       this.relative_rotation[Current_int_length] = relative_rotation;
       
       Image_Rotation i = new Image_Rotation(this.file, this.shape, this.color, this.x, this.y, this.width, this.height, this.rotation, this.origin_x, this.origin_y, this.relative_rotation);
       return i;
    }
    
    public static void main(String[] args) {
       // Create a JFrame object
       JFrame window = new JFrame("Adjustable circle");
      
       
      Hitbox_Rotation panel = new Hitbox_Rotation("Data\\Hitbox\\red_rectangle_100.png",600,400,40,40,60);
    
       // This takes the panel component and places it in the
       // JFrame ... it is transparent so can't really be seen.
       window.add(panel);
       
       
       // When the user clicks exit to terminate the window.
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // How big should the window be.
       window.setSize(600, 600);
    
       // Make the windows render to the screen.
       window.setVisible(true);
    } 
    */
}
