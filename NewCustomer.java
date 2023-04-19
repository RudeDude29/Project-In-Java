package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class NewCustomer extends JFrame implements ActionListener{
    JTextField tfname,tfaddress,city,state,email,phoneno;
    JButton next,cancel;
    JLabel lblmeterno;
    NewCustomer()
    {
        
        setSize(800,600);
        setLocation(150,200);
        
        JPanel p= new JPanel();
        
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading =new JLabel("New Customer");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblname =new JLabel("Customer Name");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);
        
        tfname=new JTextField();
        tfname.setBounds(240,80,200,20);
        p.add(tfname);
        
        JLabel lblmeter =new JLabel("Meter No ");
        lblmeter.setBounds(100,120,100,20);
        p.add(lblmeter);
        
         lblmeterno =new JLabel("");   
        Random num=new Random();
        long number=num.nextLong() %100000;
        lblmeterno.setText("" + Math.abs(number));
        lblmeterno.setBounds(240,120,100,20);
        p.add(lblmeterno);
        
        JLabel lbladdress =new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        tfaddress=new JTextField();
        tfaddress.setBounds(240,160,200,20);
        p.add(tfaddress);
        
        JLabel lblcity =new JLabel("City");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);
        
        city=new JTextField();
        city.setBounds(240,200,200,20);
        p.add(city);
        
        JLabel lblState =new JLabel("State");
        lblState.setBounds(100,240,100,20);
        p.add(lblState);
        
        state=new JTextField();
        state.setBounds(240,240,200,20);
        p.add(state);
        
        JLabel lblemail =new JLabel(" Email Address");
        lblemail.setBounds(100,280,100,20);
        p.add(lblemail);
        
        email=new JTextField();
        email.setBounds(240,280,200,20);
        p.add(email);
        
        JLabel lblphoneno =new JLabel("Phone no.");
        lblphoneno.setBounds(100,320,100,20);
        p.add(lblphoneno);
        
        phoneno=new JTextField();
        phoneno.setBounds(240,320,200,20);
        p.add(phoneno);
        
        next =new JButton("Next");
        next.setBounds(120,390,100,25);
        next.addActionListener(this);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        p.add(next);
        
        cancel =new JButton("Cancel");
        cancel.setBounds(280,390,100,25);
        cancel.addActionListener(this);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        p.add(cancel);
        
        setLayout(new BorderLayout());
        add(p,"Center");
        
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 =i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel i4=new JLabel(i3);
        add(i4,"West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==next)
        {
            String name=tfname.getText();
            String meter=lblmeterno.getText();
            String address=tfaddress.getText();
            String City=city.getText();
            String State=state.getText();
            String Email=email.getText();
            String Phone=phoneno.getText();
            
            String query1="insert into customer values('"+name+"','"+meter+"','"+address+"','"+City+"','"+State+"','"+Email+"','"+Phone+"')";
            String query2="insert into login values('"+meter+"','','"+name+"','','')";
            
            try{
                connect c1= new connect();
                c1.s.executeUpdate(query1);
                c1.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Details Added Succesfully");
                setVisible(false);
                
                new MeterInfo(meter);
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(a.getSource()==cancel)
        {
            setVisible(false);
        }
    }
    
    public static void main(String[] args)
    {
        new NewCustomer();
    }
}
