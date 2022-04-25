package plugin.FRCustom;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class FRCanvasLayer {
    
	private FRShape2D[] shapes = new FRShape2D[0];
	
	public FRShape2D[] getShapes() {
	    return shapes;
	}
	
	public void resetLayer() {
	    shapes = new FRShape2D[0];	
	}
	
	public void addtoTop(FRShape2D shape) {
		   FRShape2D[] shapes_copy = new FRShape2D[shapes.length+1];
		   for (int i = 0; i < shapes.length; i++) {
		       shapes_copy[i] = shapes[i];
		   }
		   shapes_copy[shapes.length] = shape;
		   this.shapes = shapes_copy;
		   
	}
	
	public void addtoTop(FRShape2D[] shapes) {
        FRShape2D[] shapes_copy = new FRShape2D[this.shapes.length+shapes.length];
        for (int i = 0; i < this.shapes.length; i++) {
            shapes_copy[i] = this.shapes[i];
        }
        for (int i = 0; i < shapes.length; i++) {
            shapes_copy[i+this.shapes.length] = shapes[i];
        }
        this.shapes = shapes_copy;    
	}
	
	public void addtoBottom(FRShape2D shape) {
        FRShape2D[] shapes_copy = new FRShape2D[shapes.length+1];
        shapes_copy[0] = shape;
        for (int i = 0; i < shapes.length; i++) {
            shapes_copy[i+1] = shapes[i];
        }
        this.shapes = shapes_copy;       
	}
	
	public void addtoBottom(FRShape2D[] shapes) {
        FRShape2D[] shapes_copy = new FRShape2D[this.shapes.length+shapes.length];
        for (int i = 0; i < shapes.length; i++) {
            shapes_copy[i] = shapes[i];
        }
        for (int i = 0; i < this.shapes.length; i++) {
            shapes_copy[i+shapes.length] = this.shapes[i];
        }
        this.shapes = shapes_copy;    
    }
    
}
