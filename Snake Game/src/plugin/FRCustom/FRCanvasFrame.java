package plugin.FRCustom;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.SpringLayout;

/**
 * This functions as a frame but as its own layout. 
 * Incorporating the aspects of SpringLayout, this class
 * uses a JFrame with a layout of SpringLayout to include a
 * breed of Paint and Components. And thus overall, serves
 * as an easier method of having a frame interact/include the
 * paint method and the components.
 * 
 * @Details 
 * This puts any sort of paint at the very bottom of the frame
 * Hierarchy (of course with each paint having their own order amongst each other),
 * and prioritizes components to be just above at all times. The Format acts similar
 * to FlowLayout in that regard, and also in 'easy to use commands' as well.
 * 
 * @author yeek1
 *
 */
@SuppressWarnings("serial")
public class FRCanvasFrame extends JFrame {
    
    private String Mode = "";
    private SpringLayout WindowLayout = new SpringLayout();
    
    public FRCanvasFrame(String title) {
        super();
        this.setLayout(WindowLayout);
        this.setTitle(title);
    } 
    
    public FRCanvasFrame() {
        super();
        this.setLayout(WindowLayout);
    } 
    /**
     * Supposed to be used just after initializing the JCanvasFrame and is
     * responsible for how the JCanvasFrame methods function. It is highly suggested not
     * to change modes once set though, as changing modes might invalidate a lot of the frame.
     * 
     * Typing in 'Default' sets the JCanvasFrame in default mode.
     * Typing in 'Auto-Default' sets the JCanvasFrame in auto-default mode.
     * Typing in 'Paint' sets the JCanvasFrame in paint mode.
     * 
     * @Default
     * Allows the JCanvasFrame to be in its most simplistic form and allows the functions of
     * adding and removing paint directly along with easy to integrate components.
     * @Auto-Default 
     * Functions like Default mode, but every time a component or paint is updated, it updates
     * the frame automatically. This mode, while usable, isn't recommended as always updating
     * a frame every single time the frame is changed takes more computation than ideally necessary.
     * This is only recommended if the frame is only updated/changed so often.
     * @Paint
     * Allows for the Integration of JCanvas and its following classes. This is used if 
     * the user wants to better control the paint properties. This still allows for
     * Components, (in case the title 'Paint' seemed unclear).
     * 
     * @param Mode to be set.
     */
    public void setMode(String Mode) {
        
    }
    /**
     * Adds a Component with set bounds of x position, y position, width and height.
     * This extends off of the following methods to simplify: add, putConstraint.
     * This method interacts with JCanvas Window directly.
     * 
     * @param Component
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void add(Component C, int x, int y, int width, int height) {
        WindowLayout.putConstraint(SpringLayout.WEST, C, +x, SpringLayout.WEST, this.getContentPane());
        WindowLayout.putConstraint(SpringLayout.NORTH, C, +y, SpringLayout.NORTH, this.getContentPane());
        C.setPreferredSize(new Dimension(width,height));
        this.add(C);
    }
    
    /**
     * Draws the Canvas upon the Frame.
     * @param FRCanvas
     */
    public void add(FRCanvas FRCanvas) {
        WindowLayout.putConstraint(SpringLayout.WEST, FRCanvas, +FRCanvas.getX(), SpringLayout.WEST, this.getContentPane());
        WindowLayout.putConstraint(SpringLayout.NORTH, FRCanvas, +FRCanvas.getY(), SpringLayout.NORTH, this.getContentPane());
        int width = this.getContentPane().getWidth();
        int height = this.getContentPane().getHeight();
        FRCanvas.setPreferredSize(new Dimension(width, height));
        this.getContentPane().add(FRCanvas);
    }
    
    /**
     * Draws the Canvas upon the Frame.
     * @param FRCanvas
     */
    public void add(FRCanvas FRCanvas, int x, int y) {
        int width = this.getContentPane().getWidth();
        int height = this.getContentPane().getHeight();
        FRCanvas.setPreferredSize(new Dimension(width, height));
        this.add(FRCanvas,x,y,this.getContentPane().getWidth()-x,this.getContentPane().getHeight()-y);
    }
    /**
     * Draws the Canvas upon the Frame.
     * @param FRCanvas
     */
    public void add(FRCanvas FRCanvas, int x, int y, int width, int height) {
        WindowLayout.putConstraint(SpringLayout.WEST, FRCanvas, +x, SpringLayout.WEST, this.getContentPane());
        WindowLayout.putConstraint(SpringLayout.NORTH, FRCanvas, +y, SpringLayout.NORTH, this.getContentPane());
        FRCanvas.setPreferredSize(new Dimension(width,height));
        super.add(FRCanvas);
    }
    
    /**
     * Removes the FRCanvas.
     * This method is primarily used to refresh the Canvas or Frame.
     * And much like invalidate or removeAll function, it should be done
     * first before modifying and adding a new Canvas. If this format isn't followed, the Canvas
     * doesn't function properly and will result in showing only one Canvas despite the frame
     * possibly having multiple Canvases that basically do nothing but slow CPU.
     * @param FRCanvas
     */
    public void remove(FRCanvas FRCanvas) {
        this.getContentPane().remove(FRCanvas);
    }
    
    /**
     * Once all changes have been applied to the frame, this 
     * method updates the frame completely. This includes the paint/Canvas
     * along with the updated components like Buttons. This function uses
     * methods validate, revalidate and repaint in that order.
     */
    public void refresh() {
        this.validate();
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Gives an error message for mode if mode isn't set or isn't a valid mode.
     */
    private void ERR_Mode() {
        System.out.println();
    }
}
