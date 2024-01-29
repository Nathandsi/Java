package Window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener, WindowListener{
	
	private boolean isFullWindow;
	private boolean isDecorated;
	private int h;
	private int w;
	private Color backgroundColor;
	private String closeOperation;
	
	public Window(boolean isFullWindow, int w, int h, Color backColor, boolean isDecorated, String closeOperation) {
		setFullWindow(isFullWindow);
		setWeight(w);
		setHeight(h);
		setBackgroundColor(backColor);
		setDecorated(isDecorated);
		
	}
	
	private boolean isFullWindow() {
		return isFullWindow;
	}

	private void setFullWindow(boolean isFullWindow) {
		this.isFullWindow = isFullWindow;
	}

	private boolean isDecorated() {
		return isDecorated;
	}

	private void setDecorated(boolean isDecorated) {
		this.isDecorated = isDecorated;
	}
	
	private int getH() {
		return h;
	}

	private void setHeight(int h) {
		this.h = h;
	}

	private int getW() {
		return w;
	}

	private void setWeight(int w) {
		this.w = w;
	}

	private Color getBackgroundColor() {
		return backgroundColor;
	}

	private void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	private String getCloseOperation() {
		return closeOperation;
	}

	private void setCloseOperation(String closeOperation) {
		this.closeOperation = closeOperation;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
