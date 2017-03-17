package com.swing.test;
//Checkers.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Checkers类
@SuppressWarnings("serial")
public class Checkers extends JFrame implements ActionListener {
	//变量定义
	CheckersPanel checkers = new CheckersPanel();
	JButton startButton = new JButton("start");
	JButton stopButton = new JButton("stop");
	//构造函数
	public Checkers(){
		super("Checkers");
		setSize(210,170);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = new JPanel();
		BorderLayout border = new BorderLayout();
		pane.setLayout(border);
		pane.add(checkers,"Center");
		
		JPanel buttonPanel = new JPanel();
		startButton.addActionListener(this);
		buttonPanel.add(startButton);
		stopButton.addActionListener(this);
		stopButton.setEnabled(false);
		buttonPanel.add(stopButton);
		
		pane.add(buttonPanel,"South");
		setContentPane(pane);
		show();
	}
	//响应用户动作
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == startButton){
			checkers.playAnimation();
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
		}else{
		    checkers.stopAnimation();
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
		}
	}
	//主函数
	public static void main(String[] arguments){
		Checkers ck = new Checkers();
	}
}

//CheckersPanel类
class CheckersPanel extends JPanel implements Runnable{
	//变量定义
	private Thread runner;
	int xPos = 5;
	int xMove = 4;
	//播放动画
	void playAnimation(){
		if (runner ==null);{
			runner = new Thread(this);
			runner.start();
		}
	}
	//停止动画
	void stopAnimation(){
		if (runner !=null);{
			runner = null;
		}
	}
	//运行
	public void run(){
	Thread thisThread = Thread.currentThread();
	while(runner ==thisThread){
		xPos += xMove;
		if ((xPos > 105)|(xPos < 5))
		    xMove *= -1;
		    repaint();
		    try{
		    	Thread.sleep(100);
		    }catch(InterruptedException e){}
		}
	}
	//画图形
	public void paintComponent(Graphics comp){
		Graphics2D comp2D = (Graphics2D)comp;
		comp2D.setColor(Color.blue);
		comp2D.fillRect(0,0,100,100);
		comp2D.setColor(Color.white);
		comp2D.fillRect(100,0,100,100);
		comp2D.setColor(Color.black);
		comp2D.fillOval(xPos,5,90,90);
	}
}