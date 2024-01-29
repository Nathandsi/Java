package Application;

import java.awt.Color;

import Window.Window;

public class StartApplication {
	
	// Background color of the Window
	private static Color backgroundColor = new Color( 18,32,56);

	public static void main(String[] args) {
		//  Window(boolean isFullWindow, int w, int h, Color backColor, boolean isUnDecorated, String closeOperation)
		Window firstWindow = new Window(true, 600, 400, backgroundColor, true, "exit");
		firstWindow.setVisible(true);
		System.out.println("First window created");
	}

}
