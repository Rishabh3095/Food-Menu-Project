package application;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Receipt extends JFrame{
	
    public Receipt(String recep){	
    	
        JFrame frm = new JFrame("SHOW RECEIPT");
	
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(new BorderLayout());
		

        JPanel bar = new JPanel(new BorderLayout());
        
        bar.setSize(1000, 1000);
        bar.add(new JLabel("Your Receipt"), BorderLayout.CENTER);
        
        JScrollPane scrl = new JScrollPane(new JTextArea(recep));
        
        scrl.setBounds(100,180,1600,600);
        scrl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        bar.add(scrl, BorderLayout.NORTH);
        frm.add(bar);
        frm.setSize(2000, 2000);      				
        frm.pack();
        
        boolean vis = true;
        frm.setVisible(vis);
    }
}