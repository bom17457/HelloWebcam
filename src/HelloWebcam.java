import javax.swing.JFrame;

import org.opencv.core.Core;

import java.awt.BorderLayout;

public class HelloWebcam extends JFrame{
	
	public HelloWebcam() {
		super("Hello Webcam");
		this.setSize(620,480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		WebCamPanel webCamPanel = new WebCamPanel();
		getContentPane().add(webCamPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public static void main(String args[]) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new HelloWebcam();
	}
}
