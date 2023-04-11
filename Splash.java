package electricity.billing.system;
import javax.swing.*;
import java.awt.*;//Image class(use hoti hai image ko scan krne ke liye)
public class Splash extends JFrame implements Runnable {
    Splash()
    {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image i2=i1.getImage().getScaledInstance(730, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 =new ImageIcon(i2);
        JLabel Image = new JLabel(i1);//Jlabel ke andar hum Image ka object pass nhi kr sakte par ImageIcon ka kr sekte hai
        add(Image);//add is used add any component to frame, add directly ImageIcon ka object nhi accept krta 
        setVisible(true);
        for(int i =1;i<500;i+=2)
        {
            setSize(i,i);
            setLocation(350,150);
        }
        
        Thread t=new Thread(this);
        t.start();
        setVisible(true);
        
    }
    public void run()
    {
        try
        {
            Thread.sleep(5000);
            setVisible(false);
            //login frame
            new Login();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
      new Splash();  
    }
}
