package main;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Imager {
	
	private BufferedImage img = null;
	
	public Imager() {
		try {
			img= ImageIO.read(new File("C:\\Users\\ICB166\\Desktop\\snap.bmp"));
		} catch (IOException e){
			System.out.println(e.toString());
		}
		
		
	}
	
	public Polygon getPolygon(){
		int rgb;
		ArrayList<Integer> xlist = new ArrayList<Integer>();
		ArrayList<Integer> ylist = new ArrayList<Integer>();
		for(int i = 0; i<img.getWidth(); i++){
			for(int j = 0; j<img.getHeight(); j++){
				rgb=img.getRGB(i, j);
				if(rgb <= -0xDDDDDD){
					System.out.println(rgb);
					xlist.add(i);
					ylist.add(j);
				}
			}
		}
		//Integer xOut[] = xlist.toArray(new Integer[xlist.size()]);
		//Integer yOut[] = ylist.toArray(new Integer[ylist.size()]);
		int xOut[] = toIntArray(xlist);
		int yOut[] = toIntArray(ylist);
		Polygon outGon= new Polygon(xOut, yOut, xlist.size());
		System.out.println(outGon.xpoints.length);
		return outGon;
	}
	
	public BufferedImage getImage() {
		return img;
	}

	private int[] toIntArray(ArrayList<Integer> list){
		  int[] ret = new int[list.size()];
		  for(int i = 0;i < ret.length;i++)
		    ret[i] = list.get(i);
		  return ret;
		}
}
