package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;//for importing ResultSet which will store data from sql

public class CalculateBill extends JFrame implements ActionListener{
    JTextField tfname,city,state,email,phoneno;
    JButton next,cancel;
    JLabel lblmeterno,lblname,lblmetern,lbladdress,tfaddress,lbln;
    Choice Meternumber,Month;
    CalculateBill()
    {
        
        setSize(800,600);
        setLocation(150,200);
        
        JPanel p= new JPanel();
        
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading =new JLabel("Calculate Electricity Bill");
        heading.setBounds(100,10,400,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        lblmetern =new JLabel("Meter Number");
        lblmetern.setBounds(100,80,100,20);
        p.add(lblmetern);
        
        Meternumber=new Choice();
        
        try{
            connect c =new connect();
            ResultSet rs=c.s.executeQuery("select* from customer");
            while(rs.next())
            {
                Meternumber.add(rs.getString("meter_no"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        Meternumber.setBounds(240,80,200,20);
        p.add(Meternumber);
        
        lblname =new JLabel("Name");
        lblname.setBounds(100,120,100,20);
        p.add(lblname);
        
        lbln=new JLabel();
        lbln.setBounds(240,120,200,20);
        p.add(lbln);
        
        lbladdress =new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        tfaddress=new JLabel();
        
        
        tfaddress.setBounds(240,160,200,20);
        p.add(tfaddress);
        
        Meternumber.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent a)
        {
            try{
            connect c =new connect();
            ResultSet rs =c.s.executeQuery("select* from customer where meter_no = '"+Meternumber.getSelectedItem()+"'");
            while(rs.next())
            {
                lbln.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
            }
        }catch(Exception ev)
        {
            ev.printStackTrace();
        }
         
        }
    });
        
        JLabel lblcity =new JLabel("Units Consumed");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);
        
        city=new JTextField();
        city.setBounds(240,200,200,20);
        p.add(city);
        
        JLabel lblState =new JLabel("Month");
        lblState.setBounds(100,240,100,20);
        p.add(lblState);
        
        Month=new Choice();
        Month.add("January");
        Month.add("February");
        Month.add("March");
        Month.add("April");
        Month.add("May");
        Month.add("June");
        Month.add("July");
        Month.add("August");
        Month.add("September");
        Month.add("October");
        Month.add("November");
        Month.add("December");
        Month.setBounds(240,240,200,20);
        p.add(Month);
        
        
        next =new JButton("Submit");
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
        
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
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
            
            String meter=Meternumber.getSelectedItem();
            String City=city.getText();
            String State=Month.getSelectedItem();
            
            int totalbill=0;
            int units_consumed=Integer.parseInt(City);
            
            
            
            try{
                String query1="select* from tax";
                connect c1= new connect();
                ResultSet rs=c1.s.executeQuery(query1);
                while(rs.next())
                {
                   totalbill+= units_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                }                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            String query3="insert into bill values('"+meter+"','"+State+"','"+City+"','"+totalbill+"','Not Paid')";
            try{
                connect c2=new connect();
                c2.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null,"Customer Bill generated");
                setVisible(false);
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
        new CalculateBill();
    }
}
