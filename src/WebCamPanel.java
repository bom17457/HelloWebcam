import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class WebCamPanel extends JPanel {

	private Image img;
	Mat mat = new Mat();
	private VideoCapture webcam = new VideoCapture(0);

	private CascadeClassifier detector = new CascadeClassifier("data/haarcascade_frontalface_alt.xml");
	private MatOfRect matofrect = new MatOfRect();

	
	WebCamPanel() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					repaint();
					try {
						Thread.sleep((long) 34f);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		System.out.println(detector);
		this.setSize(mat.width(), mat.height());
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// g.drawImage(img, 0, 0, null);
		// g.drawImage(img, 0, 0,Color.red, null);
		super.paint(g);
		webcam.read(mat);		
		int type = 0;		
		if (mat.channels() == 1) {
			type = BufferedImage.TYPE_BYTE_GRAY;
		} else if (mat.channels() == 3) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		
		BufferedImage image = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_3BYTE_BGR);		
		WritableRaster raster = image.getRaster();
		DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
		byte[] data = dataBuffer.getData();
		mat.get(0, 0, data);
		
		g.drawImage(image, 0, 0, null);				
		g.setColor(Color.blue); 
		detector.detectMultiScale(mat, matofrect);
		
		for(Rect rect:matofrect.toArray()) {
			g.drawRect(rect.x, rect.y, rect.width, rect.height);	
			//g.drawString("This is eye", rect.x+(rect.width/2), rect.y+(rect.height/2));
		}
	}

	public Image Capture() {

		return Capture();
	}
}
