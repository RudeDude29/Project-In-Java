
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Project extends JFrame implements ActionListener{
    String meter,choice;
    Project(String choice,String meter)
    { 
        
        setSize(1925,1085);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2=i1.getImage().getScaledInstance(1925, 1085, Image.SCALE_DEFAULT);
        ImageIcon i3 =new ImageIcon(i2);
        JLabel i4=new JLabel(i3);
        add(i4);
        this.choice=choice;
        this.meter=meter;
        JMenuBar m =new JMenuBar();
        setJMenuBar(m);

        JMenu main =new JMenu("Master");
        
        
        JMenuItem cust =new JMenuItem("New Customer");
        cust.addActionListener(this);
        main.add(cust);
        
        //JMenuItem custDetail =new JMenuItem("Customer Details");
        //custDetail.addActionListener(this);
        //main.add(custDetail);
        
        //JMenuItem deposit =new JMenuItem("Deposit Details");
        //deposit.addActionListener(this);
        //main.add(deposit);
        
        JMenuItem calbill =new JMenuItem("Calculate Bill");
        calbill.addActionListener(this);
        main.add(calbill);
        
        JMenu info =new JMenu("Information");
        
        
        //JMenuItem update =new JMenuItem("Update Information");
        //info.add(update);
        
        JMenuItem view =new JMenuItem("View Information");
        view.addActionListener(this);
        info.add(view);
        
        JMenu user =new JMenu("User");
        
        
        JMenuItem pay =new JMenuItem("Pay Bill");
        pay.addActionListener(this);
        user.add(pay);
        
        JMenuItem bill =new JMenuItem("Bill Details");
        bill.addActionListener(this);
        user.add(bill);
        
        //JMenu report =new JMenu("Report");
        
        
        //JMenuItem generate =new JMenuItem("Generate Bill");
        //report.add(generate);

        //JMenu u =new JMenu("Utility");
        //m.add(u);
        
        //JMenuItem notepad =new JMenuItem("Notepad");
        //u.add(notepad);

        //JMenuItem calculator =new JMenuItem("Calculator");
        //u.add(calculator);
        
        JMenu exit=new JMenu("Exit");
        JMenuItem Exit =new JMenuItem("Close");
        exit.add(Exit);
        Exit.addActionListener(this);
        //JMenuItem Exit =new JMenuItem("Exit");
        //u.add(Exit);
        if(choice.equals("Admin"))
        {
             m.add(main);
        }else
        {
        m.add(info); 
        m.add(user);
        
        }
         m.add(exit);
        
        setLayout(new FlowLayout());
        
        setVisible(true);
        
        
    }
    public void actionPerformed(ActionEvent e)
        {
            String m =e.getActionCommand();
            if(m.equals("New Customer"))
            {
                new NewCustomer();
            }
             
            else if(m.equals("Calculate Bill"))
            {
                new CalculateBill();
            }else if(m.equals("View Information"))
                    {
                new ViewInformation(meter);
            }else if(m.equals("Bill Details"))
            {
                new BillDetails(meter);
            }else if(m.equals("Close"))
            {
                setVisible(false);
                new Login();
            }else if(m.equals("Pay Bill"))
            {
                new PayBill(meter);
            }
        }
    public static void main(String[] args)
    {
        
    new Project("","");
}
}