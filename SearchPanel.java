/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradedatabase;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author bk
 */
public class SearchPanel extends JPanel implements ActionListener {

    // JDBC members
    private Connection con;
    private Statement stmt;

    // SWING members
    private JPanel p[];
    private JLabel l[];
    private JTextField tf[];
    private JButton okb;
    private JButton rsb;

    public SearchPanel() {

        setLayout(new GridLayout(9, 1));
        EtchedBorder eb = new EtchedBorder();
        setBorder(eb);

        p = new JPanel[9];
        l = new JLabel[8];
        tf = new JTextField[8];

        l[0] = new JLabel("Name : ");
        l[1] = new JLabel("Studend # : ");
        l[2] = new JLabel("Subject1 : ");
        l[3] = new JLabel("Subject2 : ");
        l[4] = new JLabel("Subject3 : ");
        l[5] = new JLabel("Subject4 : ");
        l[6] = new JLabel("Sum : ");
        l[7] = new JLabel("Avg : ");

        for (int i = 0; i < 8; i++) {
            tf[i] = new JTextField(15);
            p[i] = new JPanel();
            p[i].add(l[i]);
            p[i].add(tf[i]);

            add(p[i]);
        }

        tf[2].setEditable(false);
        tf[3].setEditable(false);
        tf[4].setEditable(false);
        tf[5].setEditable(false);
        tf[6].setEditable(false);
        tf[7].setEditable(false);

        p[8] = new JPanel();
        okb = new JButton("INVOKE");
        okb.addActionListener(this);
        rsb = new JButton("Insert Again");
        rsb.addActionListener(this);
        p[8].add(okb);
        p[8].add(rsb);
        add(p[8]);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String ae_type = ae.getActionCommand();
        System.out.println(ae_type);
        if (ae_type.equals(okb.getText())) {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (Exception e) {
                    System.out.println("Driver failed to load."  + e);
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
                //con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/chatprogram7081?zeroDateTimeBehavior=convertToNull");
                //con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/chatprogram7081?user=chatprogram70915&password=");
                //con = DriverManager.getConnection("chatprogramdb");
                stmt = con.createStatement();

                // Compose SELECT query
                String query = "SELECT * FROM grade WHERE name=";
                query = query + "'" + tf[0].getText() + "'";
                query = query + "and snum='" + tf[1].getText() + "'";

                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    tf[0].setText(rs.getString("name"));
                    tf[1].setText(rs.getString("snum"));
                    tf[2].setText(rs.getString("com"));
                    tf[3].setText(rs.getString("kor"));
                    tf[4].setText(rs.getString("eng"));
                    tf[5].setText(rs.getString("math"));
                    tf[6].setText(rs.getString("sum"));
                    tf[7].setText(rs.getString("avg"));

                }

                stmt.close();
                con.close();
            } catch (Exception e) {

            }

        } else if (ae_type.equals(rsb.getText())) {
            for (int i = 0; i < 8; i++) {
                tf[i].setText("");
            }
        }

    }

}
