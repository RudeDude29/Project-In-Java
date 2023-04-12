package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener {
    JButton login2,cancel,signup;
    JTextField username1,id1;
    Choice login1;
    Login()
    {
        super("Login Page");
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel username=new JLabel("Username");
        username.setBounds(200,20,100,20);
        add(username);
        
        username1 =new JTextField();
        username1.setBounds(400,20,120,20);
        add(username1);
        
        JLabel id=new JLabel("Password");
        id.setBounds(200,60, 100, 20);
        add(id);
        
        id1 =new JTextField();
        id1.setBounds(400, 60, 120, 20);
        add(id1);
        
        JLabel login=new JLabel("Login in as");
        login.setBounds(200,100,100,20);
        add(login);
        
        login1=new Choice();
        login1.setBounds(400, 100, 120, 20);
        login1.add("Admin");
        login1.add("Customer");
        add(login1);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2=i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        login2=new JButton("Login",new ImageIcon(i2));
        login2.setBounds(220, 140, 100, 30);
        login2.addActionListener(this);
        add(login2);
        
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4=i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel =new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(420, 140, 100, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i5=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6=i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup =new JButton("Signup",new ImageIcon(i6));
        signup.setBounds(320, 140, 100, 30);
        signup.addActionListener(this);
        add(signup);
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8=i7.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel i10=new JLabel(i9);
        i10.setBounds(0, 0, 150, 150);
        add(i10);
        
        setSize(600,450);
        setLocation(350,150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource()==login2)
        {
            String susername=username1.getText();
            String password=id1.getText();
            String choice=login1.getSelectedItem();
            
            try
            {
                connect c = new connect();
                String query="Select * from Login where username ='"+susername+"'and password = '"+password+"'";
                
                ResultSet r=c.s.executeQuery(query);
                
                if(r.next())
                {
                    setVisible(false);
                    new Project();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Login Credentials");
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }else if(ev.getSource()==signup)
        {
            setVisible(false);
            
            new SignUp();
            
        }else if(ev.getSource()==cancel)
        {
            setVisible(false);
        }
    }
    public static void main(String [] args)
    {
        new Login();
    }
}
