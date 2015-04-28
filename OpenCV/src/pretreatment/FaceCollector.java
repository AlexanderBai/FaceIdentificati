package pretreatment;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.TrainMain;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import com.atul.JavaOpenCV.Imshow;

/**
 * ͨ������ͷ�������ͼ�񲢽���������100*100��ͼƬ��������
 * 
 * @author Administrator
 *
 */
public class FaceCollector {
	
	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * step1:
	 * @throws IOException 
	 */
	public void run() throws IOException {
			
		VideoCapture camera = new VideoCapture(0);
		camera.open(0);
		Imshow imshow = new Imshow("test");
		if (!camera.isOpened()) {
			System.out.println("Camera Error");
		} else {
			System.out.println("Camera OK?");
		}

		String xmlfilePath ="cascade/haarcascade_frontalface_alt.xml";

		CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);
		MatOfRect faceDetections = new MatOfRect();

		Mat frame = new Mat();
		Mat cropped = null;
		
		// ���г������������ͷһֱ�ڻ��������ʵʱ���䱣�浽����
		int index = 0;
		while (true) {
			camera.read(frame);
			faceDetector.detectMultiScale(frame, faceDetections);
			for (Rect rect : faceDetections.toArray()) {
				Core.rectangle(frame, new Point(rect.x, rect.y), new Point(
						rect.x + rect.width, rect.y + rect.height), new Scalar(
						0, 255, 0));

				Rect roi = new Rect(rect.x, rect.y, rect.width, rect.height);
				cropped = new Mat(frame, roi);
				// imshow.showImage(cropped);
			}
			if (cropped != null) {//��ʾ����ͷ��׽����ͼ��תΪһά��������
				imshow.showImage(cropped);
				Mat resizeimage = new Mat();
				Size sz = new Size(100, 100);
				Imgproc.resize(cropped, resizeimage, sz);
				Highgui.imwrite("out/" + index + ".png", resizeimage);
				index = index + 1;
			}

		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Hello OpenCV");
		new FaceCollector().run();
	}

}
