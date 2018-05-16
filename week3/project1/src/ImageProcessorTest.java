import static org.junit.Assert.assertTrue;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import imagereader.IImageIO;
import imagereader.IImageProcessor;

public final class ImageProcessorTest{
	private String image1="bmptest/1.bmp", image2="bmptest/2.bmp";
	//function Compare the imageTest with the ImageTarget
	private boolean compare(BufferedImage imageTest, BufferedImage imageTarget) {
		// obtain the width and height of the two images
		int imageTestWidth=imageTest.getWidth(), imageTestHeight=imageTest.getHeight();
		int imageTargetWidth=imageTarget.getWidth(), imageTargetHeight=imageTarget.getHeight();
		//compare the width and height
		if(imageTestWidth!=imageTargetWidth || imageTestHeight!=imageTargetHeight) {
			return false;
		}
		//compare the rgb
		for(int i=0;i<imageTestWidth;i++) {
			for(int j=0;j<imageTestHeight;j++) {
				if(imageTest.getRGB(i, j) != imageTarget.getRGB(i, j)) {
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * function testmyRead()
	 * Cse the java API ImageIO.read and my implement imageioer.myRead to get the image 
	 * and compare them.
	 */
	@Test
	public void testmyRead() throws IOException {
		IImageIO imageioer = new ImplementImageIO();
		BufferedImage imageTest=ImplementImageIO.toBufferedImage(imageioer.myRead(image1));
		BufferedImage imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream(image1)));
		assertTrue(compare(imageTest, imageTarget));
		
		imageTest=ImplementImageIO.toBufferedImage(imageioer.myRead(image2));
		imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream(image2)));
		assertTrue(compare(imageTest, imageTarget));
	}
	/*
	 * function testmyWrite()
	 * Call the imageioer.myWrite method to write the image and the return value is also 
	 * the same BufferedImage. We can use this return value to compare with the target image.
	 */
	@Test
	public void testmyWrite() throws FileNotFoundException, IOException {
		IImageIO imageioer = new ImplementImageIO();
		BufferedImage imageTest=(BufferedImage) imageioer.myWrite(ImageIO.read(new FileInputStream(image1)), "bmptest/1_myWrite");
		BufferedImage imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream(image1)));
		assertTrue(compare(imageTest, imageTarget));
		
		imageTest=(BufferedImage) imageioer.myWrite(ImageIO.read(new FileInputStream(image2)), "bmptest/2_myWrite");
		imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream(image2)));
		assertTrue(compare(imageTest, imageTarget));
	}
	
	/*
	 * function testshowChanelB()
	 * Call the method processor.showChanelB() to get the imageTest and call the method 
	 * ImplementImageIO.toBufferedImage() to get the imageTarget. Then compare them.
	 */
	@Test
	public void testshowChanelB() throws FileNotFoundException, IOException {
		IImageProcessor processor = new ImplementImageProcessor();
		BufferedImage imageTest=(BufferedImage) processor.showChanelB(ImageIO.read(new FileInputStream(image1)));
		BufferedImage imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream("bmptest/goal/1_blue_goal.bmp")));
		assertTrue(compare(imageTest, imageTarget));
		
		imageTest=(BufferedImage) processor.showChanelB(ImageIO.read(new FileInputStream(image2)));
		imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream("bmptest/goal/2_blue_goal.bmp")));
		assertTrue(compare(imageTest, imageTarget));
	}
	/*
	 * function testshowChanelG()
	 * Call the method processor.showChanelG() to get the imageTest and call the method 
	 * ImplementImageIO.toBufferedImage() to get the imageTarget. Then compare them.
	 */
	@Test
	public void testshowChanelG() throws FileNotFoundException, IOException {
		IImageProcessor processor = new ImplementImageProcessor();
		BufferedImage imageTest=(BufferedImage) processor.showChanelG(ImageIO.read(new FileInputStream(image1)));
		BufferedImage imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream("bmptest/goal/1_green_goal.bmp")));
		assertTrue(compare(imageTest, imageTarget));
		
		imageTest=(BufferedImage) processor.showChanelG(ImageIO.read(new FileInputStream(image2)));
		imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream("bmptest/goal/2_green_goal.bmp")));
		assertTrue(compare(imageTest, imageTarget));
	}
	/*
	 * function testshowChanelR()
	 * Call the method processor.showChanelR() to get the imageTest and call the method 
	 * ImplementImageIO.toBufferedImage() to get the imageTarget. Then compare them.
	 */
	@Test
	public void testshowChanelR() throws FileNotFoundException, IOException {
		IImageProcessor processor = new ImplementImageProcessor();
		BufferedImage imageTest=(BufferedImage) processor.showChanelR(ImageIO.read(new FileInputStream(image1)));
		BufferedImage imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream("bmptest/goal/1_red_goal.bmp")));
		assertTrue(compare(imageTest, imageTarget));
		
		imageTest=(BufferedImage) processor.showChanelR(ImageIO.read(new FileInputStream(image2)));
		imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream("bmptest/goal/2_red_goal.bmp")));
		assertTrue(compare(imageTest, imageTarget));
	}
	/*
	 * function testshowGray()
	 * Call the method processor.showGray() to get the imageTest and call the method 
	 * ImplementImageIO.toBufferedImage() to get the imageTarget. Then compare them.
	 */
	@Test
	public void testshowGray() throws FileNotFoundException, IOException {
		IImageProcessor processor = new ImplementImageProcessor();
		BufferedImage imageTest=(BufferedImage) processor.showGray(ImageIO.read(new FileInputStream(image1)));
		BufferedImage imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream("bmptest/goal/1_gray_goal.bmp")));
		assertTrue(compare(imageTest, imageTarget));
		
		imageTest=(BufferedImage) processor.showGray(ImageIO.read(new FileInputStream(image2)));
		imageTarget=ImplementImageIO.toBufferedImage(ImageIO.read(new FileInputStream("bmptest/goal/2_gray_goal.bmp")));
		assertTrue(compare(imageTest, imageTarget));
	}
}
