package main;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

import plugin.FRCustom.*;

public class Lime {
    public static FRCanvas Spawned_Lime_GUI;
    public static int x;
    public static int y;
    protected static Dimension d = new Dimension(100,100);
    
    public Lime(int x, int y) {
        Lime.x = x;
        Lime.y = y;
        FRShape2D lime_up = new FRShape2D();
        lime_up.setImage(new File("images\\lime_up.png"));
        lime_up.setShape(new Rectangle2D.Double(x-24,y-20,d.getWidth(),d.getHeight()));
        Spawned_Lime_GUI = new FRCanvas(new Object[] {lime_up});
        Spawned_Lime_GUI.setOpaque(false);
        Snake_part.Snake_GUI.setOpaque(false);
        sg.Game_GUI1.setOpaque(false);
        sg.Game_GUI2.setOpaque(false);

    }
    
}
