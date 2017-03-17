package com.swing.test;
//MyFrame.java
import java.awt.*;
@SuppressWarnings("serial")
public class MyFrame extends Frame {
	//主函数
	public static void main(String args[])  {
		MyFrame fr = new MyFrame("Hello,This is my frame !");
		//调用来自Component类的setSize()方法
		fr.setSize(400,200);
		//设置背景颜色
		fr.setBackground(Color.blue);
		//设置窗口可见
		fr.setVisible(true);
	}
	//构造函数
	public MyFrame(String str){
		super(str);
	}
}