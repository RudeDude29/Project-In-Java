package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {
    JButton create,back;
    Choice As;
    JTextField meter,username,name,password;
    SignUp()
    {
        super("Sign Up");
        getContentPane().setBackground(Color.GRAY);
        setLayout(null);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png."));
        Image i2=i1.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel i4=new JLabel(i3);
        i4.setBounds(400,100,200,200);
        add(i4);
        
        JLabel LoginAs =new JLabel("Login in as");
        LoginAs.setBounds(50,20,100,20);
        add(LoginAs);
        
        As =new Choice();
        As.setBounds(200,20,100,20);
        As.add("Admin");
        As.add("Customer");
        //As.addFocusListener(l);
        add(As);
        
        JLabel Meter=new JLabel("Meter Number");
        Meter.setBounds(50, 80, 100, 20);
        add(Meter);
        
        meter =new JTextField();
        meter.setBounds(200, 80, 100, 20);
        add(meter);
        
        JLabel Username =new JLabel("Username");
        Username.setBounds(50,120,100,20);
        add(Username);
        
        username =new JTextField();
        username.setBounds(200,120,100,20);
        add(username);
        
        JLabel Name =new JLabel("Name");
        Name.setBounds(50,160,100,20);
        add(Name);
        
        name =new JTextField();
        name.setBounds(200,160,100,20);
        add(name);
        
        meter.addFocusListener(new FocusListener(){
        
            public void focusGained(FocusEvent e)
            {
                
            }
            public void focusLost(FocusEvent d)
            {
                try
                {
                    connect c= new connect();
                    ResultSet rs =c.s.executeQuery("select* from login where meter_no='"+meter.getText()+"'");
                    while(rs.next())
                    {
                        name.setText(rs.getString("name"));
                    }
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
    });
        
        
        
        JLabel Password =new JLabel("Password");
        Password.setBounds(50,200,100,20);
        add(Password);
        
        password =new JTextField();
        password.setBounds(200,200,100,20);
        add(password);
        
        As.addItemListener(new ItemListener(){
        public void itemStateChanged(ItemEvent a)
        {
            String user =As.getSelectedItem();
            if(user.equals("Customer"))
            {
                Meter.setVisible(true);
                meter.setVisible(true);
            }
            else
            {
                Meter.setVisible(false);
                meter.setVisible(false);
            }
        }
    });
        
        create=new JButton("Create");
        create.setBounds(80, 240, 100, 20);
        create.addActionListener(this);
        add(create);
        
        back=new JButton("Back");
        back.setBounds(220, 240, 100, 20);
        back.addActionListener(this);
        add(back);
        
        
        setSize(600,400);
        setLocation(350,150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource()==create)
        {
            String a1 = As.getSelectedItem();
            String b1=username.getText();
            String c1=name.getText();
            String d1=password.getText();
            String e1=meter.getText();
            
            try{
                connect c=new connect();
                String query =null;
                if(a1.equals("Admin"))
                {
                    query="insert into login values('"+e1+"','"+b1+"','"+c1+"','"+d1+"','"+a1+"')";
                }
                else
                {
                    query = "update login set username = '"+b1+"', name = '"+c1+"', password = '"+d1+"', user = '"+a1+"' where meter_no = '"+meter.getText()+"'";
                }
                
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account created Succesfully" );
                setVisible(false);
                new Login();
            }catch(Exception l)
            {
                l.printStackTrace();
            }
        }else if(ev.getSource()==back)
        {
            setVisible(false);
            new Login();       
        }
       
    }
    public static void main (String[] args)
    {
        new SignUp();
    }
}
