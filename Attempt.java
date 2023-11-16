import javax.swing.*;
import java.awt.*;  
import java.lang.Math;

 class Main{
    public static void main(String[] args){
        JFrame DISPLAY = new JFrame("Data"); 
        JPanel panel = new JPanel();  
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        JLabel MPH = new JLabel("MPH:\n " + Integer.toString(MPH()));
        JLabel Gear = new JLabel("Gear:\n " + Integer.toString(Gear()));  
        JLabel RPM = new JLabel("RPM:\n " + Integer.toString(RPM()));
        JLabel Temp = new JLabel("Temp:\n " + Double.toString(Temp()));
   
        MPH.setMinimumSize(new Dimension(50,50));
        Gear.setMinimumSize(new Dimension(100,100));
        RPM.setMinimumSize(new Dimension(50,50));
        Temp.setMinimumSize(new Dimension(50,50));
        
       /* gbc.gridx = 0;
        gbc.gridy = 0; */
        panel.add(MPH, gbc);
 
      /*  gbc.gridx = 1;
        gbc.gridy = 0;*/
        panel.add(Gear, gbc);
 
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panel.add(RPM, gbc);

      /*panel.add(MPH);  
        panel.add(Gear);
        panel.add(RPM);
        panel.add(Temp); */

        DISPLAY.add(panel); 
        DISPLAY.pack();
        DISPLAY.setSize(800, 480);  
        DISPLAY.setLocationRelativeTo(null);  
        DISPLAY.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        DISPLAY.setVisible(true); 
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