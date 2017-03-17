package com.swing.test;
//FrameWithPanel.java
import java.awt.*;
@SuppressWarnings("serial")
public class FrameWithPanel extends Frame{
	
	
	//构造函数
	public FrameWithPanel (String str) {
	super(str);
	}
	
	
	public static void main(String args[]){
		FrameWithPanel fr = new FrameWithPanel("Frame with Panel");
		//将框架命名为"Frame With Panel"
		Panel pan = new Panel( );
		
		
		fr.setSize(400,300);
		//将框架大小设置为（400，300）
		fr.setBackground(Color.red);
		//将框架背景颜色设为黑色
		fr.setLayout(null);
		pan.setSize(200,150);
		//将画板大小设置为（200，150）
		pan.setBackground(Color.white);
		//将画板颜色设为白色
		
		
		fr.add(pan);
		fr.setVisible(true);
	}
}