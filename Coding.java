import javax.swing.*;
import java.awt.*; 
import java.lang.Math;

 class Main{
    public static void main(String[] args){
        //ImageIcon logo = new ImageIcon("./brau.jpeg");
        JFrame DISPLAY = new JFrame("Data");
        DISPLAY.setLocationRelativeTo(null);   
        DISPLAY.setSize(800, 480);  
        DISPLAY.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        JPanel panel = new JPanel(); 
        //panel.setLayout(new GridLayout());
        JTextArea MPH = new JTextArea("MPH:\n " + Integer.toString(MPH()));
        JTextArea Gear = new JTextArea("Gear:\n " + Integer.toString(Gear()));  
        JTextArea RPM = new JTextArea("RPM:\n " + Integer.toString(RPM()));
        JTextArea Temp = new JTextArea("Temp:\n " + Double.toString(Temp()));
   
        MPH.setBounds(new Rectangle(500,50));
        Gear.setBounds(new Rectangle(50,50));
        RPM.setBounds(new Rectangle(50,50));
        Temp.setBounds(new Rectangle(50,50));
        
        panel.add(Gear);
        panel.add(MPH);
        panel.add(RPM);
        panel.add(Temp);
        panel.setBackground(Color.decode("#ADD8E6"));
        DISPLAY.add(panel); 
        DISPLAY.setVisible(true); 
     //DISPLAY.setIconImage(logo.getImage()); 
        
    }   
    public static int MPH(){
        int x= 51;
        return x;
    }
    public static int Gear(){
        int x= 3;
        return x;
    }
    public static int RPM(){
        int x= 100;
        return x;
    }
    public static double Temp(){
        double x= 45.00;
        return x;
    }
}