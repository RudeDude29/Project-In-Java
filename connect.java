package electricity.billing.system;

import java.sql.*;
public class connect {
    
    Connection c;
    Statement s;
    connect()
    {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        
        try{
            c=DriverManager.getConnection("jdbc:mysql:///ebs","root","88Ag@@@@");
            s=c.createStatement();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
