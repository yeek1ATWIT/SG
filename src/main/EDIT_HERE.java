package main;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * @apiNote
 * Hello, this is where you can write your code!
 * Feel free to creates classes etc., just don't
 * delete the following commands, and don't 
 * edit any of my code and don't rename the files.
 * I'll revise my code to look neater once you
 * do your work- that way I can organize based
 * on your code.
 * For now, you can work on the bare bones of
 * snake game.
 * I left hints here if you don't understand stuff,
 * and if you have any questions, leave a comment-
 * just use: //
 * That will make your text green and it will also not
 * affect your code.
 * 
 * @author yeek1
 *
 */
public class EDIT_HERE {
    
    Snake_part head = new Snake_head(500,400);
    Snake_part body1 = new Snake_body(475,400);  
    static int speed=20;
    static ArrayList<Snake_part> array = new ArrayList<Snake_part>();
    
    /**
     * TODO: 
     * This command isn't to be <i> used </i>, <b> it's meant to be read! </b>
     */
    public static void GUIDE_README() {
        int x = 100;
        int y = 100;
        
        /** 
         * In order to generate a lime with a position,
         * You need to initialize a Lime like so:
         * This puts the lime on screen immediately at
         * coordinate x,y
         */
        Lime l = new Lime(x,y);
        
        /**
         * Now to change the score position, you can either
         * do this:
         */
        Lime.x = x;
        Lime.y = y;
        /** Or this: */
        l = new Lime(x,y);
        /** 
         * This works because lime class is static, and
         * there can only be one lime at a time spawned.
         */
        
        
        /** 
         * To make snake parts, you need to initialize them.
         * Much like the lime, the moment they are spawned they
         * immediately show on screen.
         */
        Snake_part head = new Snake_head(x,y); /** For the head */
        Snake_part body1 = new Snake_body(x,y); /** For the body */
        
        /** 
         * However, to change their coordinates, you have to
         * call these commands:
         */
        head.setX(x); /** For the head */
        head.setY(y);
        body1.setX(x); /** For the body */
        body1.setY(y);
      
        /** That should be it. The rest of the code is on you. */
    }
    
    
    
    
    
    
    
    
    
    /**
     * Limes counter.
     */
    public static int score = 0;
    
    /**
     * Runs this command at the very start
     * of each game once.
     */
    public void Game_Start() {
        Snake_part.Hierarchy.clear();
        array.clear();
        head = new Snake_head(500,400);
        body1 = new Snake_body(475,400);
        array.add(0, head);
        array.add(1, body1);
        Lime l= new Lime((int)(Math.random()*1040+80),(int)(Math.random()*720+80));
        
    }
    
    
    
      /**
     * Runs many times per second.
     * Feel free to put anything in here.
     * (Runs 24 times per second).
     */
    public void Game_Clock() { 
        //Lime newlime = new Lime((int)Math.random() * ( 50 )+950,(int)Math.random() * ( 50 )+950) ;
        Rectangle a= new Rectangle(head.getX(),head.getY(),25,25);
        Rectangle b= new Rectangle(Lime.x-10,Lime.y-5,25,25);
        
        
        if(head.getDirection()==0) {
        if(a.intersects(b)){
            score++;
            Lime l= new Lime((int)(Math.random()*1040+80),(int)(Math.random()*640+50));
            array.add(new Snake_body(array.get(array.size()-1).getX(),array.get(array.size()-1).getY()));
            
        }
        Up();
        }
        if(head.getDirection()==90) {
            if(a.intersects(b)){
                score++;
                Lime l= new Lime((int)(Math.random()*1040+80),(int)(Math.random()*640+50));
                array.add(new Snake_body(array.get(array.size()-1).getX(),array.get(array.size()-1).getY()));
            }
            Right();
            }
        if(head.getDirection()==180) {
            if(a.intersects(b)){
                score++;
                Lime l= new Lime((int)(Math.random()*1040+80),(int)(Math.random()*640+50));
                array.add(new Snake_body(array.get(array.size()-1).getX(),array.get(array.size()-1).getY()));
            }
            Down();
            }
        if(head.getDirection()==270) {
            if(a.intersects(b)){
                score++;
                Lime l= new Lime((int)(Math.random()*1040+80),(int)(Math.random()*640+50));
                array.add(new Snake_body(array.get(array.size()-1).getX(),array.get(array.size()-1).getY()));
                
            }
            Left();
            }
       
         

        
        if(head.getX()>=1120||head.getX()<=50||head.getY()>=690||head.getY()<40) {
            Game_Over();
           
        
        }
    
    }
    
    /**
     * Call this when the snake dies.
     */
    public void Game_Over() {
        array.removeAll(array);
        head = new Snake_head(500,400);
        body1 = new Snake_body(450,400);
        sg.Game_Over(); /** DO NOT DELETE THIS */
        array.add(head);
        array.add(body1);
        
    }
    
    /**
     * Is Automatically called when up arrow or w is pressed.
     */
    public void Up() {
     
        if(head.getDirection()!=180) {
            head.setDirection(0);
        for(int i=array.size()-1;i>0;i--) {      
           array.get(i).setX(array.get(i-1).getX());
           array.get(i).setY(array.get(i-1).getY());
        }
        
       head.setY(head.getY()-speed);
        }
        
    }
    
    
    /**
     * Is Automatically called when down arrow or s is pressed.
     */
    public void Down() {
        if(head.getDirection()!=0) {
            head.setDirection(180);
        for(int i=array.size()-1;i>0;i--) {
            array.get(i).setX(array.get(i-1).getX());
            array.get(i).setY(array.get(i-1).getY());
         }
        head.setY(head.getY()+speed);
        }
    }
    
    /**
     * Is Automatically called when left arrow or a is pressed.
     */
    public void Left() {
        if(head.getDirection()!=90) {
            head.setDirection(270);
            for(int i=array.size()-1;i>0;i--) {
                array.get(i).setX(array.get(i-1).getX());
                array.get(i).setY(array.get(i-1).getY());
             }
        head.setX(head.getX()-speed);
    }
    }
    
    /**
     * Is Automatically called when right arrow or d is pressed.
     */
    public void Right() {
        //when snake is moving up
        if(head.getDirection()!=270) {
            head.setDirection(90);
            for(int i=array.size()-1;i>0;i--) {
                array.get(i).setX(array.get(i-1).getX());
                array.get(i).setY(array.get(i-1).getY());
             }
        head.setX(head.getX()+speed);
        }
    
    }
    
   
}
