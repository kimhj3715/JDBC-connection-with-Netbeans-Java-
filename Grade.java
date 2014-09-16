/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package gradedatabase;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author bk
 */
public class Grade extends JFrame{
    
    private JTabbedPane tp;
    private InsertPanel ip;
    private SearchPanel sp;
    
    public Grade() {
        tp = new JTabbedPane();
        ip = new InsertPanel();
        sp = new SearchPanel();
        
        tp.addTab("성적입력", ip );
        tp.addTab("성적조회", sp );
        
        getContentPane().add(tp);
        setTitle("학생 성적 관리(JDBC 버전)");
        
        
    }
    
    public static void main(String args[]) {
        
        Grade g = new Grade();
        
        Dimension d = new Dimension(250, 350);
        g.setSize(d);
        g.setVisible(true);
        g.show(true);
    }
}
