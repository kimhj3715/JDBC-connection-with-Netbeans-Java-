package gradedatabase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.sql.*;
import javax.sql.*;
import java.util.*;
/**
 *
 * @author bk
 */
public class InsertPanel extends JPanel implements ActionListener{

    
    // JDBC members
    private Connection con;
    private Statement stmt;
    
    
    // SWING members
    private JPanel p[];
    private JLabel l[];
    private JTextField tf[];
    private JButton okb;
    private JButton rsb;
    
    private String query;
    
    public InsertPanel() {
        // Set the SWING Components
        setLayout(new GridLayout(7, 1));
        EtchedBorder eb = new EtchedBorder();
        setBorder(eb);
        
        p = new JPanel[7];
        l = new JLabel[6];
        tf = new JTextField[6];
        
        l[0] = new JLabel("Name : ");
        l[1] = new JLabel("Studend # : ");
        l[2] = new JLabel("Subject1 : ");
        l[3] = new JLabel("Subject2 : ");
        l[4] = new JLabel("Subject3 : ");
        l[5] = new JLabel("Subject4 : ");
        
        for(int i=0; i<6; i++){
            tf[i] = new JTextField(15);
            p[i] = new JPanel();
            p[i].add(l[i]);
            p[i].add(tf[i]);
            
            add(p[i]);
        }
        
        p[6] = new JPanel();
        okb = new JButton("SAVE");
        okb.addActionListener(this);
        rsb = new JButton("Insert Again");
        rsb.addActionListener(this);
        p[6].add(okb);
        p[6].add(rsb);
        
        add(p[6]);
    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String ae_type = ae.getActionCommand();
        System.out.println(ae_type);
        if(ae_type.equals(okb.getText())) {
            // When the user click "Save"
            try{
                // Save on JDBC
                try {
                    Class.forName("com.mysql.jdbc.Driver");     // see ClassNot..Exception
                } catch (Exception e) {
                    System.out.println("Driver failed to load." + e);
                    return;
                }
                    
                Connection con = null;
                try {
                    con = DriverManager.getConnection(
                            "jdbc:mysql://db4free.net:3306/chatprogram7081", "chatprogram70815", "70815chatprogram");
                    System.out.println("Connected");
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("1");

                //con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/chatprogram7081?zeroDateTimeBehavior=convertToNull");
                //con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/chatprogram7081?user=chatprogram70915&password=");
                //con = DriverManager.getConnection("chatprogramdb");
                if(con.createStatement().equals(null) ) {
                    System.out.println("con.createStatement is Null");
                } else {
                    System.out.println(con.createStatement());
                }
                
                stmt = con.createStatement();
                
                int sum = Integer.parseInt(tf[2].getText())
                        + Integer.parseInt(tf[3].getText())
                        + Integer.parseInt(tf[4].getText())
                        + Integer.parseInt(tf[5].getText());
                
                int avg = sum / 4;
                
                System.out.println("2");
                query = "INSERT INTO grade VALUES(" ;
                query = query + "'" + tf[0].getText() + "',";
                query = query + "'" + tf[1].getText() + "',";
                query = query + tf[2].getText() + ", ";
                query = query + tf[3].getText() + ", ";
                query = query + tf[4].getText() + ", ";
                query = query + tf[5].getText() + ", ";
                query = query + sum + ", ";
                query = query + avg + ")";
                
                System.out.println(query);
                
                stmt.execute(query);
                stmt.close();
                
            }catch(Exception e) {
                
            }
            
        } else if(ae_type.equals(rsb.getText())) {
            for(int i=0; i<6; i++) {
                tf[i].setText("");
            }
        }
    }
    
}
