package com.swing.test;
//NamePass.java
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class NamePass extends JFrame {
	
	 //建造一个容器
	 void bulidConstraints(GridBagConstraints gbc,int gx,int gy,int gw,int gh,int wx,int wy){
	 	gbc.gridx = gx;
	 	gbc.gridy = gy;
	 	gbc.gridwidth = gw;
	 	gbc.gridheight = gh;
	 	gbc.weightx = wx;
	 	gbc.weighty = wy;
	}
	//构造函数
	public NamePass(){
		super("Username and Password");
		setSize(290,110);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		JPanel pane = new JPanel();
		pane.setLayout(gridbag);
		
		bulidConstraints(constraints,0,0,1,1,10,40);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor =  GridBagConstraints.EAST;
		JLabel l = new JLabel("Name:",JLabel.LEFT);
		gridbag.setConstraints(l,constraints);
		pane.add(l);
		
		bulidConstraints(constraints,1,0,1,1,90,0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		JTextField tfname = new JTextField();
		gridbag.setConstraints(tfname,constraints);
		pane.add(tfname);
		
		bulidConstraints(constraints,0,1,1,1,0,40);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor =  GridBagConstraints.EAST;
		JLabel l2 = new JLabel("Password",JLabel.LEFT);
		gridbag.setConstraints(l2,constraints);
		pane.add(l2);
		
		bulidConstraints(constraints,1,1,1,1,0,0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		JPasswordField tfpass = new JPasswordField();
		tfpass.setEchoChar('*');
		gridbag.setConstraints(tfpass,constraints);
		pane.add(tfpass);
		
		bulidConstraints(constraints,0,2,2,1,0,20);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor =  GridBagConstraints.CENTER;
		JButton okb = new JButton("OK");
		gridbag.setConstraints(okb,constraints);
		pane.add(okb);
		
		setContentPane(pane);
	}
	//主函数
	public static void main(String[] arguments){
		NamePass f = new NamePass();
		ExitWindow exit = new ExitWindow();
		f.addWindowListener(exit);
		f.show();
	}
}

//退出窗口	
class ExitWindow extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}