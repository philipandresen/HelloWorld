package Gameobjects;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.LinkedList;

import main.Handler;
import main.HelloWorld;
import main.KeyInput;

public class Enemy extends GameObject{
	
	Polygon myPoly;
	
	public Enemy(double newx, double newy, ID newid) {
		super(newx, newy, newid);
		x = HelloWorld.currentLevel.Width*Math.random();
		y = HelloWorld.currentLevel.Height*Math.random();
		bBox = new Rectangle(-8, -8, 16, 16);
		//bBox_T = new Rectangle();
		//bBox_T.setBounds(bBox.getBounds());
		scaleX = 1;
		scaleY = 1;
		friction=0.015;
		
		int ypoints[] = {-14,-5,-4,-4,0,4,4,5,14,5,4,4,0,-4,-4,-5,-14};
		int xpoints[] = {0,4,4,5,14,5,4,4,0,-4,-4,-5,-14,-5,-4,-4,0};
		int npoints = 17;
		myPoly = new Polygon(xpoints, ypoints, npoints);
		hitBox = new Area(myTform.createTransformedShape(myPoly));
		angle = Math.random()*2*Math.PI;
		Handler.addObject(this);
	}


	public void tick(KeyInput keyinput) {
		super.tick(keyinput);
		hitBox = new Area(myTform.createTransformedShape(myPoly));
		angle=angle+Math.toRadians(2);
		scaleX=1+Math.sin(8*angle)*0.05;
		scaleY=scaleX;
		
		LinkedList<GameObject> playerList = Handler.findObject(ID.Player);
		if(!playerList.isEmpty()){
			GameObject player = playerList.getFirst();
			double length, XTo, YTo;
			XTo = (player.x-x);
			YTo = (player.y-y);
			length = Math.sqrt(XTo*XTo + YTo*YTo);
			if(length>1 && length<400){
				accelX = (XTo/length)*0.04;
				accelY = (YTo/length)*0.04;
			}
		}
		
		maxVel = 2;
		
		super.tick(keyinput);
		
	}

	public void destroy(){
		Exploder.explode(myPoly.getPathIterator(null), x, y, scaleX, scaleY, angle, Color.red);
		super.destroy();
	}
	
	public void render(Graphics2D g2){
		super.render(g2);
		g2.setColor(Color.black);
		g2.fill(hitBox);
		g2.setColor(Color.red);
		g2.draw(hitBox);
		
	}

}
