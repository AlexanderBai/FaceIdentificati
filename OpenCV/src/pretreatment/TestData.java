package pretreatment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ������ͼƬת����libsvm�����ʽ�浽��Ŀ·���µ��ļ�
 * @author Administrator
 *
 */
public class TestData {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		FileWriter fw1 = new FileWriter("OpenCVtest.txt");

		File[] subFiles = new File("li").listFiles();
		for (int i = 0; i < subFiles.length; i++) {
			File file = subFiles[i];
			System.out.println(file.getPath());
			BufferedImage img = ImageIO
					.read(new FileInputStream(file.getPath()));
			int[] temp = new int[10000];
			int index = 0;
			for (int ii = 0; ii < 100; ii++) {
				for (int jj = 0; jj < 100; jj++) {
					temp[index++] = img.getRGB(ii, jj);
				}
			}
			StringBuilder sbBuilder = new StringBuilder();
			sbBuilder.append("3");
			for (int k = 0; k < 10000; k++) {
				sbBuilder.append(" " + k + ":" + temp[k]);
			}
			sbBuilder.append("\r\n");
			fw1.write(sbBuilder.toString());

		}
		fw1.close();
	}
}
