
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame{
    
    BillDetails(String meter)
    {
        setSize(700,650);
        setLocation(400,150);
        
        getContentPane().setBackground(Color.white);
        
        JTable T=new JTable();
        
        try{
            
            connect c =new connect();
            String query ="select* from bill where meter_no ='"+meter+"'";
            
            ResultSet rs=c.s.executeQuery(query);
            T.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        JScrollPane s =new JScrollPane(T);
        s.setBounds(0,0,700,650);
        add(s);
        setVisible(true);
    }
    
    public static void main(String args[])
    {
        new BillDetails("");
    }
}
