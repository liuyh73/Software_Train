import java.awt.Image;
import java.awt.image.BufferedImage;

import imagereader.IImageProcessor;

public final class ImplementImageProcessor implements IImageProcessor{
	//set the red and green to 0
	@Override
	public Image showChanelB(Image arg0) {
		return setColor(arg0, 0xff0000ff);
	}
	//set the blue and red to 0
	@Override
	public Image showChanelG(Image arg0) {
		return setColor(arg0, 0xff00ff00);
	}
	//set the green and blue to 0
	@Override
	public Image showChanelR(Image arg0) {
		return setColor(arg0, 0xffff0000);
	}
	//set the new rgb to I=r*0.299+g*0.587+b*0.114
	@Override
	public Image showGray(Image arg0) {
		int argb,r,g,b,I;
		BufferedImage bufferedImage=ImplementImageIO.toBufferedImage(arg0);
		for(int i=0;i<bufferedImage.getWidth();i++){
			for(int j=0;j<bufferedImage.getHeight();j++) {
				argb=bufferedImage.getRGB(i, j);
				r=(argb & 0x00ff0000) >> 16;
				g=(argb & 0x0000ff00) >> 8;
				b=argb & 0x000000ff;
				I=(int)(r*0.299+g*0.587+b*0.114);
				argb= ((((argb&0xff000000)|I<<16)|I<<8)|I);
				bufferedImage.setRGB(i, j, argb);
			}
		}
		return bufferedImage;
	}
	//use this method to set different color
	private BufferedImage setColor(Image arg0, int num) {
		int argb;
		BufferedImage bufferedImage=ImplementImageIO.toBufferedImage(arg0);
		for(int i=0;i<bufferedImage.getWidth();i++){
			for(int j=0;j<bufferedImage.getHeight();j++) {
				argb=bufferedImage.getRGB(i, j) & num;
				bufferedImage.setRGB(i, j, argb);
			}
		}
		return bufferedImage;
	}
}
