package main;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import plugin.FRCustom.*;

public class Snake_body extends Snake_part {
    
    private static final Color color = new Color(100,200,100);
    
    public Snake_body(int x, int y) {
        this.x = x;
        this.y = y;
        this.Snake_part_GUI.setShape(new Ellipse2D.Double(this.x, this.y, d.getWidth(), d.getHeight()));
        this.Snake_part_GUI.setColor(color);
        Snake_part.Hierarchy.add(this.Snake_part_GUI);
        appendHierarchy();
    }
    
    private void appendHierarchy() {
        //Snake_part.Snake_GUI = new FRCanvas(Hierarchy.toArray());
        Snake_part.Snake_GUI.setOpaque(false);
        sg.Game_GUI1.setOpaque(false);
        sg.Game_GUI2.setOpaque(false);
    }

    public void setX(int x) {
        this.x = x;
        this.Snake_part_GUI.setShape(new Ellipse2D.Double(this.x, this.y, d.getWidth(), d.getHeight()));
        
    }

    public void setY(int y) {
        this.y = y;
        this.Snake_part_GUI.setShape(new Ellipse2D.Double(this.x, this.y, d.getWidth(), d.getHeight()));
        
    }

    public void setDirection(int direction) {
        this.direction = direction;
        this.Snake_part_GUI.setShape(new Ellipse2D.Double(this.x, this.y, d.getWidth(), d.getHeight()));
        //this.Snake_part_GUI.setRotationZ(direction);
        
    }
}
