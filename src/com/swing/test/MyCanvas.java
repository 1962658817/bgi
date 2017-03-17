package com.swing.test;

//MyCanvas.java
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyCanvas implements KeyListener, MouseListener {
	// 变量定义
	Canvas c;
	String s = "";
	TextField t;

	// 主函数
	public static void main(String args[]) {
		Frame f = new Frame("Canvas");
		MyCanvas mc = new MyCanvas();
		mc.c = new Canvas();
		mc.t = new TextField();
		f.add("South", mc.t);
		f.add("Center", mc.c);
		f.setSize(300, 150);
		mc.c.addMouseListener(mc);
		mc.c.addKeyListener(mc);
		f.setVisible(true);
	}

	// 响应键盘事件
	public void keyTyped(KeyEvent ev) {
		t.setText("keyTyped");
		s += ev.getKeyChar();
		c.getGraphics().drawString(s, 0, 20);
	}

	// 响应键盘按下
	public void keyPressed(KeyEvent ev) {
	}

	// 响应键盘抬起
	public void keyReleased(KeyEvent ev) {
		t.setText("keyReleased");
	}

	// 响应点击鼠标
	public void mouseClicked(MouseEvent ev) {
		t.setText("mouseClicked");
		c.requestFocus();
	}

	// 响应压下鼠标
	public void mousePressed(MouseEvent ev) {
		t.setText("mousePressed");
	}

	// 响应松开鼠标
	public void mouseReleased(MouseEvent ev) {
		t.setText("mouseReleased");
	}

	// 响应鼠标进入
	public void mouseEntered(MouseEvent ev) {
		t.setText("mouseEntered");
	}

	// 响应鼠标退出
	public void mouseExited(MouseEvent ev) {
		t.setText("mouseExited");
	}
}