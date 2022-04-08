package main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import plugin.FRCustom.*;

public class sg {
    public static final String ERR_FNF = "FILE NOT FOUND!";
    
    public static FRCanvasFrame Window = new FRCanvasFrame();
    
    public static FRCanvas Menu_GUI = new FRCanvas();
    
    public static FRCanvas LeaderBoard_GUI = new FRCanvas();
    
    public static FRCanvas Game_GUI1 = new FRCanvas();
    
    public static FRCanvas Game_GUI2 = new FRCanvas();
    
    public static final int Frame_width = 1200;
    
    public static final int Frame_height = 825;
    
    public static final int FPS = 24;
    
    private static EDIT_HERE EH = new EDIT_HERE();
    
    private static int limes = EH.score;
    
    public static boolean Game_switch = false;
    /**
     * Unit is in Frames.
     */
    public static int SW = 0;
    
    public static void main(String[] args) {
        Window_Setup();
        Show_Menu_with_Buttons();
        Clock();
    }
    public static void Window_Setup() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (Game_switch == true) {
                    if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                        EH.Up();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                        EH.Down();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                        EH.Left();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        EH.Right();
                    }
                }
                return false;
            }
            
        });
        //Window.setBounds(150, 0, Frame_width, Frame_height);
        Window.setSize(Frame_width, Frame_height);
        Window.setResizable(false);
        Window.setTitle("SG");
        Window.setVisible(true);
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void Show_Menu() { 
        Window.getContentPane().removeAll(); 
        Object[] Hierarchy = new Object[100];
        FRShape2D bg = new FRShape2D();
        bg.setColor(new Color(240,255,240));
        bg.setShape(new Rectangle(0,0,Frame_width, Frame_height));
        Hierarchy[0] = bg;
        
        FRShape2D shp1 = new FRShape2D();
        shp1.setColor(new Color(160,240,255));
        shp1.setShape(new Ellipse2D.Double(110,110,300, 300));
        Hierarchy[1] = shp1;
        FRShape2D shp2 = new FRShape2D();
        shp2.setColor(new Color(160,240,255,255));
        shp2.setShape(new Ellipse2D.Double(500,400,500, 500));
        Hierarchy[2] = shp2;
        FRShape2D shp3 = new FRShape2D();
        shp3.setColor(new Color(160,240,255,45));
        shp3.setShape(new Ellipse2D.Double(200,100,600, 600));
        Hierarchy[3] = shp3;
        FRShape2D shp4 = new FRShape2D();
        shp4.setColor(new Color(160,255,235,200));
        shp4.setShape(new Ellipse2D.Double(800,0,300, 300));
        Hierarchy[4] = shp4;
        FRShape2D shp5 = new FRShape2D();
        shp5.setColor(new Color(160,255,225,200));
        shp5.setShape(new Ellipse2D.Double(20,600,200, 200));
        Hierarchy[5] = shp5;
        
        FRShape2D leaf_image = new FRShape2D();
        leaf_image.setOpacity(85);
        leaf_image.setImage(new File("images\\leaf.png"));
        leaf_image.setShape(new Rectangle(150,-150,900,500));
        leaf_image.setRotationZ(10);
        Hierarchy[6] = leaf_image;  
        

        FRShape2D snake_image = new FRShape2D();
        snake_image.setOpacity(65);
        snake_image.setImage(new File("images\\snake_drawing.png"));
        snake_image.setShape(new Rectangle(100,350,500,500));
        Hierarchy[7] = snake_image;  
        
        FRShape2D eye1 = new FRShape2D();
        eye1.setColor(new Color(255,255,145,250));
        eye1.setShape(new Ellipse2D.Double(183,403,25, 25));
        Hierarchy[8] = eye1;
        
        FRShape2D eye2 = new FRShape2D();
        eye2.setColor(new Color(255,255,145,250));
        eye2.setShape(new Ellipse2D.Double(208,443,25, 25));
        Hierarchy[9] = eye2;
        
        FRShape2D side_bar1 = new FRShape2D();
        side_bar1.setColor(new Color(160,240,165));
        side_bar1.setShape(new Rectangle(Frame_width-100,0,100,Frame_height));
        Hierarchy[10] = side_bar1;
        
        FRShape2D side_bar2 = new FRShape2D();
        side_bar2.setColor(new Color(160,240,165));
        side_bar2.setShape(new Rectangle(0,0,100,Frame_height));
        Hierarchy[11] = side_bar2;
         
        JLabel Label = new JLabel();
        Label.setBounds(270,200,300,300);
        Label.setText("Snake Game");
        Label.setForeground(Color.white); 
        Label.setFont(new Font("Verdana",3,80));
        Hierarchy[99] = Label;
        Menu_GUI = new FRCanvas(Hierarchy);
        Menu_GUI.setOpaque(false);
        Window.add(Menu_GUI);
    }
    
    public static void Show_Menu_with_Buttons() {
        Show_Menu();
        
        JPanel Menu_b1 = new JPanel();
        Menu_b1.setOpaque(false);
        JLabel BLabel1 = new JLabel();
        BLabel1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        BLabel1.setBounds(270,200,300,300);
        BLabel1.setText(" Play ");
        BLabel1.setForeground(Color.white); 
        BLabel1.setFont(new Font("Verdana",3,80));
        
        JButton Button1 = new JButton();
        Button1.setBorderPainted(false);
        Button1.add(BLabel1);
        Button1.setPreferredSize(new Dimension(300,150));
        Button1.setBackground(new Color(100,190,250));
        Menu_b1.add(Button1);
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Snake_part body1 = new Snake_body(2000,4000);
                Lime l = new Lime(1000,10000);
                SW = 0;
                Show_Game();
                EH.Game_Start();
                EH.score = 0;
                Game_switch = true;
                
            }
            
        });
        Window.add(Menu_b1,660,370,300,150);
        
        JPanel Menu_b2 = new JPanel();
        Menu_b2.setOpaque(false);
        JLabel BLabel2 = new JLabel();
        BLabel2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        BLabel2.setBounds(270,200,300,300);
        BLabel2.setText("LeaderBoard");
        BLabel2.setForeground(Color.white); 
        BLabel2.setFont(new Font("Verdana",3,33));
        
        JButton Button2 = new JButton();
        Button2.setBorderPainted(false);
        Button2.add(BLabel2);
        Button2.setPreferredSize(new Dimension(300,150));
        Button2.setBackground(new Color(100,190,250));
        Menu_b2.add(Button2);
        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Show_Menu();
                Object[] Hierarchy = new Object[10];
                try (Scanner LB = new Scanner(new File("data\\LeaderBoard"))) {
                    boolean placement = true;
                    for (int i = 0; i < 5; i++) {
                        if (LB.hasNext()) {
                            int l = LB.nextInt();
                            int t = LB.nextInt();
                            Hierarchy = Load_LeaderBoard(Hierarchy, i, l, t, Color.white);
                            
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.err.println("FILE NOT FOUND!");
                    e.printStackTrace();
                }
                FRCanvas scores = new FRCanvas(Hierarchy);
                scores.setY(-30);
                scores.setOpaque(false);
                Window.add(scores);
                Show_LeaderBoard_with_Close();
            }
            
        });
        Window.add(Menu_b2,660,570,300,150);
        Window.add(Menu_GUI);
        Window.refresh();
    }
    
    public static void Show_LeaderBoard() {  
        Object[] Hierarchy = new Object[100];
        
        FRShape2D dim = new FRShape2D();
        dim.setColor(new Color(0,0,0,55));
        dim.setShape(new Rectangle(0,0,Frame_width, Frame_height));
        Hierarchy[0] = dim;
        
        FRShape2D bg = new FRShape2D();
        bg.setColor(new Color(140,220,255));
        bg.setShape(new Rectangle(400,150,400, 500));
        Hierarchy[1] = bg;
        
        FRShape2D border1 = new FRShape2D();
        border1.setColor(new Color(250,250,250));
        border1.setShape(new Rectangle(370,120,30, 560));
        Hierarchy[2] = border1;
        
        FRShape2D border2 = new FRShape2D();
        border2.setColor(new Color(250,250,250));
        border2.setShape(new Rectangle(770,120,30, 560));
        Hierarchy[3] = border2;
        
        FRShape2D border3 = new FRShape2D();
        border3.setColor(new Color(250,250,250));
        border3.setShape(new Rectangle(370,120,430, 30));
        Hierarchy[4] = border3;
        
        FRShape2D border4 = new FRShape2D();
        border4.setColor(new Color(250,250,250));
        border4.setShape(new Rectangle(370,650,430, 30));
        Hierarchy[5] = border4;
        
        FRShape2D shp1 = new FRShape2D();
        shp1.setColor(new Color(160,255,185));
        shp1.setShape(new Ellipse2D.Double(330,90,100, 100));
        Hierarchy[6] = shp1;
        
        FRShape2D shp2 = new FRShape2D();
        shp2.setColor(new Color(160,255,185));
        shp2.setShape(new Ellipse2D.Double(730,90,100, 100));
        Hierarchy[7] = shp2;

        FRShape2D shp3 = new FRShape2D();
        shp3.setColor(new Color(160,255,185));
        shp3.setShape(new Ellipse2D.Double(330,620,100, 100));
        Hierarchy[8] = shp3;
        
        FRShape2D shp4 = new FRShape2D();
        shp4.setColor(new Color(160,255,185));
        shp4.setShape(new Ellipse2D.Double(730,620,100, 100));
        Hierarchy[9] = shp4;
        

        JLabel Label = new JLabel();
        Label.setBounds(450,200,300,300);
        Label.setText("LeaderBoards:");
        Label.setForeground(Color.white); 
        Label.setFont(new Font("Verdana",3,30));
        Hierarchy[99] = Label;
        
        LeaderBoard_GUI = new FRCanvas(Hierarchy);
        LeaderBoard_GUI.setOpaque(false);
        
        Window.add(LeaderBoard_GUI);
        Component[] components = Window.getContentPane().getComponents();
        for (Component component : components) {
            if (component.equals(Menu_GUI)) {
                Window.remove(Menu_GUI); 
                Window.add(Menu_GUI);
            }
            if (component.equals(Game_GUI1)) {
                Window.remove(Game_GUI1); 
                Window.add(Game_GUI1);
            }
            if (component.equals(Game_GUI2)) {
                Window.remove(Game_GUI2); 
                Window.add(Game_GUI2);
            }
        }

        Window.refresh();
    }
    
    public static void Show_LeaderBoard_with_Close() {
        Show_LeaderBoard();

        JPanel LB_b1 = new JPanel();
        LB_b1.setOpaque(false);
        JLabel BLabel1 = new JLabel();
        BLabel1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        BLabel1.setBounds(170,200,400,400);
        BLabel1.setText(" close ");
        BLabel1.setForeground(Color.white); 
        BLabel1.setFont(new Font("Verdana",3,40));
        
        JButton Button1 = new JButton();
        Button1.setBorderPainted(false);
        Button1.add(BLabel1);
        Button1.setPreferredSize(new Dimension(200,60));
        Button1.setBackground(new Color(100,190,250));
        LB_b1.add(Button1);
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Show_Menu_with_Buttons();
            }
            
        });
        Window.add(LB_b1,480,570,200,180);
        
        
        Window.add(LeaderBoard_GUI);
        Component[] components = Window.getContentPane().getComponents();
        for (Component component : components) {
            if (component.equals(Menu_GUI)) {
                Window.remove(Menu_GUI); 
                Window.add(Menu_GUI);
            }
        }

        Window.refresh();
    }
    
    public static void Show_Game() { //System.out.println("ds");
        Window.getContentPane().removeAll(); 
        Object[] Hierarchy2 = new Object[5];

        FRShape2D bar = new FRShape2D();
        bar.setColor(new Color(75,82,90));
        bar.setShape(new Rectangle(0,720,1200,300));
        Hierarchy2[0] = bar; 
        
        FRShape2D lime = new FRShape2D();
        lime.setImage(new File("images\\lime.png"));
        lime.setShape(new Rectangle(120,650,150,125));
        lime.setRotationZ(10);
        Hierarchy2[1] = lime; 
        
        JLabel limes_label = new JLabel();
        limes_label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        limes_label.setBounds(280,750,300,300);
        limes_label.setText(String.valueOf(limes));
        limes_label.setForeground(Color.white); 
        limes_label.setFont(new Font("Verdana",3,100));
        Hierarchy2[2] = limes_label;

        
        JLabel timer_label = new JLabel();
        timer_label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        timer_label.setBounds(800,750,300,300);
        timer_label.setText(String.valueOf(SW / FPS)+":"+String.valueOf(Math.round((SW % FPS)*100.0 / FPS)));
        timer_label.setForeground(Color.white); 
        timer_label.setFont(new Font("Verdana",3,100));
        Hierarchy2[3] = timer_label;

        Game_GUI2 = new FRCanvas(Hierarchy2);
        Game_GUI2.setOpaque(false);
        Window.add(Game_GUI2);
        
        //Show Snake HERE
        
        
        //Snake_part.Snake_GUI = new FRCanvas();
        //Lime.Spawned_Lime_GUI = new FRCanvas();
        
        
        Snake_part.Snake_GUI.setOpaque(false);
        Lime.Spawned_Lime_GUI.setOpaque(false);
        Window.add(Snake_part.Snake_GUI);
        Window.add(Lime.Spawned_Lime_GUI);
        
        Object[] Hierarchy1 = new Object[1];
        FRShape2D sg_bg = new FRShape2D();
        sg_bg.setImage(new File("images\\sg_bg.png"));
        sg_bg.setShape(new Rectangle(0,0,Frame_width,Frame_height-50));
        Hierarchy1[0] = sg_bg;
        
        Game_GUI1 = new FRCanvas(Hierarchy1);
        Game_GUI1.setOpaque(false);
        Window.add(Game_GUI1);
        
        
    }

    public static void Game_Over() {
        Game_switch = false;
        
        try {
            Thread.sleep(1000/FPS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        //Show_Game();
        //Window.removeAll();
        Window.getContentPane().remove(0);
        Window.getContentPane().remove(0);
        Window.getContentPane().remove(0);
        scoreToLeaderBoard();
        Show_LeaderBoard_with_Close();
        Window.add(Game_GUI2);
        Window.remove(Snake_part.Snake_GUI);
        Window.remove(Lime.Spawned_Lime_GUI);
        Window.add(Game_GUI1);
        
        Object[] Hierarchy = new Object[100];
        
        JLabel Label = new JLabel();
        Label.setBounds(460,240,300,300);
        Label.setText("Limes: "+limes+" | Time: "+SW_toString());
        Label.setForeground(Color.yellow); 
        Label.setFont(new Font("Verdana",3,20));
        Hierarchy[10] = Label;
        
        try (Scanner LB = new Scanner(new File("data\\LeaderBoard"))) {
            boolean placement = true;
            for (int i = 0; i < 5; i++) {
                if (LB.hasNext()) {
                    int l = LB.nextInt();
                    int t = LB.nextInt();
                    if (limes == l && SW == t && placement == true) {
                        Hierarchy = Load_LeaderBoard(Hierarchy, i, l, t, Color.yellow);
                        placement = false;
                    } else {
                        Hierarchy = Load_LeaderBoard(Hierarchy, i, l, t, Color.white);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(ERR_FNF);
            e.printStackTrace();
        }

        FRCanvas GameOver = new FRCanvas(Hierarchy);
        GameOver.setOpaque(false);
        Window.add(GameOver);
        
        Component[] components = Window.getContentPane().getComponents();
        for (Component component : components) {
            if (component.equals(Menu_GUI)) {
                Window.remove(Menu_GUI); 
                Window.add(Menu_GUI);
            }
            if (component.equals(Game_GUI1)) {
                Window.remove(Game_GUI1); 
                Window.add(Game_GUI1);
            }
            if (component.equals(Game_GUI2)) {
                Window.remove(Game_GUI2); 
                Window.add(Game_GUI2);
            }
            if (component.equals(LeaderBoard_GUI)) {
                Window.remove(LeaderBoard_GUI); 
                Window.add(LeaderBoard_GUI);
            }
        } 

        for (int i = 0; i < Snake_part.Hierarchy.size(); i++) {
            Snake_part.Hierarchy.remove(0);
        }
    }
    private static void scoreToLeaderBoard() {
        ArrayList<Integer> buffered_lb = new ArrayList<Integer>();
        try (Scanner lb = new Scanner(new File("data\\LeaderBoard"))) {
            while (lb.hasNextInt()) {
                buffered_lb.add(lb.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.err.println(ERR_FNF);
        }
        boolean placement_condition = true;
        try (PrintWriter lb = new PrintWriter(new File("data\\LeaderBoard"))) {
            for (int i = 0; i < buffered_lb.size(); i += 2) {
                if (buffered_lb.get(i) < limes && placement_condition == true) {
                    lb.println(limes+" "+SW);
                    placement_condition = false;
                } else if (buffered_lb.get(i) == limes && buffered_lb.get(i+1) > SW && placement_condition == true) {
                    lb.println(limes+" "+SW);
                    placement_condition = false;
                }
                lb.println(buffered_lb.get(i)+" "+buffered_lb.get(i+1));
            }
        } catch (FileNotFoundException e) {
            System.err.println(ERR_FNF);
        }
    }
    
    private static Object[] Load_LeaderBoard(Object[] Hierarchy, int index, int Limes, int Time, Color color) {
        JLabel Label = new JLabel();
        Label.setBounds(420,300 + (index*60),300,300);
        Label.setText((index+1) + ". Limes: "+Limes+" | Time: "+Time);
        Label.setForeground(color); 
        Label.setFont(new Font("Verdana",3,22));
        Hierarchy[index] = Label;
        
        return Hierarchy;
        
    }
    
    private static String SW_toString() {
        StringBuilder sb = new StringBuilder();
        if (SW >= (FPS*60*60)) {
            sb.append(String.valueOf(String.format("%.2f", (double) (SW / FPS / 3600 % 24)*1.0 / 100).toCharArray(), 2, 2));
            sb.append(":");
        }
        if (SW >= (FPS*60)) {
            sb.append(String.valueOf(String.format("%.2f", (double) (SW / FPS / 60 % 60)*1.0 / 100).toCharArray(), 2, 2));
            sb.append(":");
        }
        sb.append(String.valueOf(String.format("%.2f", (double) (SW / FPS % 60)*1.0 / 100).toCharArray(), 2, 2));
        sb.append(":");
        sb.append(String.valueOf(String.format("%.2f", (double) (SW % FPS)*1.0 / FPS).toCharArray(), 2, 2));
        
        
        return sb.toString();
        
    }
    
    
    
    
    public static void Clock() { 
        while (true) { 
            try { 
                Thread.sleep(1000/FPS);
                if (Game_switch == true) {
                    
                    EH.Game_Clock(); 
                    limes = EH.score;
                    
                }
                
                Window.refresh();
                if (Game_switch) {
                    SW += 1; 
                    ((JLabel) Game_GUI2.shapes[2]).setText(String.valueOf(limes));
                    ((JLabel) Game_GUI2.shapes[3]).setText(SW_toString());
                    if (SW == (FPS*60)) {
                        ((JLabel) Game_GUI2.shapes[3]).setBounds(
                                ((JLabel) Game_GUI2.shapes[3]).getBounds().x-160, 
                                ((JLabel) Game_GUI2.shapes[3]).getBounds().y,
                                ((JLabel) Game_GUI2.shapes[3]).getBounds().width,
                                ((JLabel) Game_GUI2.shapes[3]).getBounds().height);
                    }
                    if (SW == (FPS*60*60)) {
                        ((JLabel) Game_GUI2.shapes[3]).setBounds(
                                ((JLabel) Game_GUI2.shapes[3]).getBounds().x-180, 
                                ((JLabel) Game_GUI2.shapes[3]).getBounds().y,
                                ((JLabel) Game_GUI2.shapes[3]).getBounds().width,
                                ((JLabel) Game_GUI2.shapes[3]).getBounds().height);
                    } 
                }
                /*
                if (SW == 40) {
                    SW += 1;
                    Game_Over();
                } */
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
}
