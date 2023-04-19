package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener{
    JTextField tfname,tfaddress,city,state,email,phoneno;
    JButton next;
    JLabel lblmeterno;
    Choice meterLocation,meterType,phasecode,billType;
    String meternumber;
    MeterInfo(String meternumber)
    {
        this.meternumber=meternumber;
        setSize(800,600);
        setLocation(150,200);
        
        JPanel p= new JPanel();
        
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading =new JLabel("Meter Information");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblname =new JLabel("Meter Number");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);
        
        JLabel lblmeternumber =new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber);
        
        JLabel lblmeter =new JLabel("Meter Location");
        lblmeter.setBounds(100,120,100,20);
        p.add(lblmeter);
        
        meterLocation =new Choice();
        meterLocation.setBounds(240,120,100,20);
        meterLocation.add("Outside");
        meterLocation.add("Inside");
        p.add(meterLocation);
        
       
        JLabel lbladdress =new JLabel("Meter Type");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        meterType =new Choice();
        meterType.setBounds(240,160,100,20);
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Smart Meter");
        p.add(meterType);
        
        
        
        JLabel lblcity =new JLabel("Phase code");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);
        
        phasecode =new Choice();
        phasecode.setBounds(240,200,100,20);
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        p.add(phasecode);
        
        JLabel lblState =new JLabel("Bill Type");
        lblState.setBounds(100,240,100,20);
        p.add(lblState);
        
        billType =new Choice();
        billType.setBounds(240,240,100,20);
        billType.add("Normal");
        billType.add("Industrial");
        p.add(billType);
        
        JLabel lblemail =new JLabel("Days");
        lblemail.setBounds(100,280,100,20);
        p.add(lblemail);
        
        JLabel lblphoneno =new JLabel("30 Days");
        lblphoneno.setBounds(240,280,100,20);
        p.add(lblphoneno);
        
       JLabel lblnote =new JLabel("Note");
        lblnote.setBounds(100,320,100,20);
        p.add(lblnote);
        
        JLabel lblnote1 =new JLabel("By Default Bill is Calculated for 30 Days");
        lblnote1.setBounds(240,320,300,20);
        p.add(lblnote1);
        
        next =new JButton("Submit");
        next.setBounds(220,390,100,25);
        next.addActionListener(this);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        p.add(next);
        
       
       
        
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
            String meter=meternumber;
            String location=meterLocation.getSelectedItem();
            String type=meterType.getSelectedItem();
            String code=phasecode.getSelectedItem();
            String billtype=billType.getSelectedItem();
            String days="30";
            
            
            String query1="insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+billtype+"','"+days+"')";
            
            
            try{
                connect c1= new connect();
                c1.s.executeUpdate(query1);
               
                
                JOptionPane.showMessageDialog(null, "Meter Information Added Succesfully");
                setVisible(false);
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else 
        {
            setVisible(false);
        }
    }
    
    public static void main(String[] args)
    {
        new MeterInfo("");
    }
}


