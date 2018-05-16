import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import imagereader.IImageIO;

public final class ImplementImageIO implements IImageIO{
	private int width;
	private int height;
	private int[] pix;
	
	private int getIntFromByte(byte bs[]) {
		int sum=0;
		for(int i=3;i>=0;i--) {
			int num=bs[i] & 0xff;
			num <<= i*8;
			sum+=num;
		}
		return sum;
	}
	
	private void readFile(String path) {
		try {
			//obtain the file stream
			FileInputStream fileInputStream=new FileInputStream(path);
			BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
			//skip bitmap head
			//18-21 are the width
			bufferedInputStream.skip(18);
			byte[] bsWidth=new byte[4];
			bufferedInputStream.read(bsWidth);
			
			//22-25 are the height
			byte[] bsHeight=new byte[4];
			bufferedInputStream.read(bsHeight);
			
			//get the int type of width and height
			width=getIntFromByte(bsWidth);
			height=getIntFromByte(bsHeight);
			
			//create the pix array
			pix=new int[width*height];
			
			//get the number of the zero that will supply
			int skipnum=0;
			skipnum=(4-width*3%4)%4;
			
			bufferedInputStream.skip(28);
			
			for(int i=height-1;i>=0;i--) {
				for(int j=0;j<width;j++) {
					//get the RGB, in the bitmap file the order is reversed
					int blue=bufferedInputStream.read() & 0xff;
					int green=bufferedInputStream.read() & 0xff;
					int red=bufferedInputStream.read() & 0xff;
					Color color=new Color(red, green, blue);
					pix[i*width+j]=color.getRGB();
				}
				bufferedInputStream.skip(skipnum);
			}
			bufferedInputStream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Image myRead(String arg0) throws IOException {
		readFile(arg0);
		return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width,height,pix,0,width));
	}
	@Override
	public Image myWrite(Image arg0, String arg1) throws IOException {
		File outFile = new File(arg1+".bmp");
		BufferedImage bufferedImage=toBufferedImage(arg0);
		ImageIO.write(bufferedImage, "bmp" , outFile);
		return bufferedImage;
	}

	public static BufferedImage toBufferedImage(Image image) {
		if(image instanceof BufferedImage){
			return (BufferedImage)image;
		}
		// This code ensures that all the pixels in the image are loaded  
		image = new ImageIcon(image).getImage();  
        // Create a buffered image with a format that's compatible with the screen
		BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	try {
			int transparency = Transparency.OPAQUE;
		    GraphicsDevice gs = ge.getDefaultScreenDevice();
		    GraphicsConfiguration gc = gs.getDefaultConfiguration();
		    bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
    	} catch (HeadlessException e) {
    		e.printStackTrace();
		}
    	if (bimage == null){
    		int type = BufferedImage.TYPE_INT_RGB;  
    		bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);  
    	}
	    // Copy image to buffered image
	    Graphics g = bimage.createGraphics();
	    // Paint the image onto the buffered image
    	g.drawImage(image, 0, 0, null);
	    g.dispose();  
	   return bimage; 
	}
}
