package main;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class Snake_head extends Snake_part {
    
    private static final Color color = new Color(100,200,100);
    
    public Snake_head(int x, int y) {
        Snake_GUI.Coordinate_System = 04;
        this.x = x;
        this.y = y;
        this.Snake_part_GUI.setShape(new Rectangle2D.Double(this.x, this.y, d.getWidth(), d.getHeight()));
        this.Snake_part_GUI.setImage(new File("images//Snake_head.png"));
        this.Snake_part_GUI.setColor(color);
        Snake_part.Hierarchy.add(this.Snake_part_GUI);
        appendHierarchy();
    }
    
    private void appendHierarchy() {
        //Snake_part.Snake_GUI = new FRCanvas(Hierarchy.toArray());
        Snake_part.Snake_GUI.setOpaque(false);
        GUI.Game_GUI1.setOpaque(false);
        GUI.Game_GUI2.setOpaque(false);
        
    }

    public void setX(int x) {
        this.x = x;
        this.Snake_part_GUI.setShape(new Rectangle2D.Double(this.x, this.y, d.getWidth(), d.getHeight()));
        
    }

    public void setY(int y) {
        this.y = y;
        this.Snake_part_GUI.setShape(new Rectangle2D.Double(this.x, this.y, d.getWidth(), d.getHeight()));
        
    }

    public void setDirection(int direction) {
        this.direction = direction;
        this.Snake_part_GUI.setShape(new Rectangle2D.Double(this.x, this.y, d.getWidth(), d.getHeight()));
        this.Snake_part_GUI.setRotationZ(direction);
        
    }
}
