package Gameobjects;

import java.awt.Color;
import java.awt.geom.PathIterator;

import main.Handler;

public class Exploder {

	public static void explode(PathIterator pi,double x, double y, double scaleX, double scaleY, double angle, Color c){
		double coords[] = new double[6];
		double xnow,ynow,xlast,ylast;
		
		pi.currentSegment(coords);
		xnow=coords[0];
		ynow=coords[1];
		
		while(pi.isDone() == false){
			xlast=xnow;
			ylast=ynow;
			pi.next();
			if(pi.isDone() == true) break;
			pi.currentSegment(coords);
			xnow=coords[0];
			ynow=coords[1];
			Debris tempdeb = new Debris(0,0,ID.Particle);
			tempdeb.draw_color=c;
			tempdeb.setLine(xlast, ylast, xnow, ynow);
			tempdeb.x = x;
			tempdeb.y = y;
			tempdeb.angle = angle;
			tempdeb.scaleX = scaleX;
			tempdeb.scaleY = scaleY;

		}
	}
	
}
