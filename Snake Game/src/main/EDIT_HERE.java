package main;
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
         * Now to change the limes position, you can either
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
    public static int limes = 0;
    
    /**
     * Runs this command at the very start
     * of each game once.
     */
    public static void Game_Start() {
        Lime l = new Lime(100,100);
    
        
        
    }
    static Snake_part head = new Snake_head(500,400);
    static Snake_part body1 = new Snake_body(475,400);
    static Snake_part body2 = new Snake_body(450,400);
    static int speed=25;
    static Snake_part[] array= {head,body1,body2};
      /**
     * Runs many times per second.
     * Feel free to put anything in here.
     * (Runs 24 times per second).
     */
    public static void Game_Clock() { 

       
         
        Lime l = new Lime(200,100);
        
        
        
       
        
        if(head.getX()>=1100||head.getX()<=30||head.getY()>=700||head.getY()<50) {
            Game_Over();
        
        }
    
    }
    
    /**
     * Call this when the snake dies.
     */
    public static void Game_Over() {
        head = new Snake_head(500,400);
        body1 = new Snake_body(450,400);
        sg.Game_Over(); /** DO NOT DELETE THIS */
        
    }
    
    /**
     * Is Automatically called when up arrow or w is pressed.
     */
    public static void Up() {
        
        for(int i=array.length-1;i>0;i--) {      
           array[i].setX(array[i-1].getX());
           array[i].setY(array[i-1].getY());
        }
       head.setY(head.getY()-speed);
    }
    
    /**
     * Is Automatically called when down arrow or s is pressed.
     */
    public static void Down() {
        for(int i=array.length-1;i>0;i--) {
            array[i].setX(array[i-1].getX());
            array[i].setY(array[i-1].getY());
         }
        head.setY(head.getY()+speed);
       
    }
    
    /**
     * Is Automatically called when left arrow or a is pressed.
     */
    public static void Left() {
        for(int i=array.length-1;i>0;i--) {
            array[i].setX(array[i-1].getX());
            array[i].setY(array[i-1].getY());
         }
        head.setX(head.getX()-speed);
    }
    
    /**
     * Is Automatically called when right arrow or d is pressed.
     */
    public static void Right() {
        //when snake is moving up
        if(body1.getY()<=head.getY()-5) {
            body1.setY(body1.getY()+speed);
            head.setX(head.getX()-8);
        }
        else {
         head.setX(head.getX()-speed);
         body1.setX(body1.getX()-speed);
        }
        
        
        //when snake is moving down
        if(body1.getY()<=head.getY()-5) {
            body1.setY(body1.getY()+speed);
            head.setX(head.getX()-8);
        }
        else {
         head.setX(head.getX()-speed);
         body1.setX(body1.getX()-speed);
        }
    }
    
   
}
