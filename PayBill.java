
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class PayBill extends JFrame implements ActionListener{
    
    Choice Month;
    JButton pay,cancel;
    String meter;
    PayBill(String meter)
    {
        this.meter=meter;
        setLayout(null);
        setBounds(300,150,900,600);
        
        JLabel heading =new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);
        
        JLabel lblmeterno =new JLabel("Meter Number");
        lblmeterno.setBounds(35,80,200,20);
        add(lblmeterno);
        
        JLabel meterno =new JLabel("");
        meterno.setBounds(300,80,200,20);
        add(meterno);
        
        JLabel lblname =new JLabel("Name");
        lblname.setBounds(35,140,200,20);
        add(lblname);
        
        JLabel name =new JLabel("");
        name.setBounds(300,140,200,20);
        add(name);
        
        JLabel lblmonth =new JLabel("Month");
        lblmonth.setBounds(35,200,200,20);
        add(lblmonth);
        
        JLabel month =new JLabel("");
        month.setBounds(300,200,200,20);
        add(month);
        
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
        Month.setBounds(300,200,200,20);
        add(Month);
        
        JLabel lblunits =new JLabel("Units");
        lblunits.setBounds(35,260,200,20);
        add(lblunits);
        
        JLabel units =new JLabel("");
        units.setBounds(300,260,200,20);
        add(units);
        
        JLabel lbltotalbill =new JLabel("Total Bill");
        lbltotalbill.setBounds(35,320,200,20);
        add(lbltotalbill);
        
        JLabel totalbill =new JLabel("");
        totalbill.setBounds(300,320,200,20);
        add(totalbill);
        
        JLabel lblstatus =new JLabel("Status");
        lblstatus.setBounds(35,380,200,20);
        add(lblstatus);
        
        JLabel status =new JLabel("");
        status.setBounds(300,380,200,20);
        status.setForeground(Color.RED);
        add(status);
        add(month);
        
        try{
            connect c =new connect();
            ResultSet rs =c.s.executeQuery("select* from customer where meter_no='"+meter+"'");
            while(rs.next())
            {
                meterno.setText(meter);
                name.setText(rs.getString("name"));
            }
            rs =c.s.executeQuery("select* from bill where meter_no='"+meter+"' AND month='"+Month.getSelectedItem()+"'");
            while(rs.next())
            {
                units.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        Month.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e)
            {
                connect c =new connect();
              try{
            ResultSet rs =c.s.executeQuery("select* from bill where meter_no='"+meter+"' AND month='"+Month.getSelectedItem()+"'");
            while(rs.next())
            {
                units.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
        }catch(Exception ae)
        {
            ae.printStackTrace();
        }  
            }
        });
        
        pay =new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.white);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        cancel =new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setBounds(230,460,100,25);
        cancel.addActionListener(this);
        add(cancel);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l21 = new JLabel(i3);
        l21.setBounds(400, 120, 600, 300);
        add(l21);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==pay)
        {
            try{
                connect c =new connect();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no ='"+meter+"' AND month = '"+Month.getSelectedItem()+"'");
                setVisible(false);
            }catch(Exception ve)
            {
                ve.printStackTrace();
            }
        }else
        {
            setVisible(false);
        }
    }
    
    public static void main(String args[])
    {
        new PayBill("");
    }
}
