package Gameobjects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import main.Handler;
import main.KeyInput;


public abstract class GameObject {
	
	protected double x=0, y=0;
	protected double angle=0, scaleX=1, scaleY=1;
	protected double accelX=0, accelY=0;
	public ID id;
	protected double velX=0, velY=0, maxVel, friction=0, speed;
	public Rectangle bBox, bBox_T;
	public Area hitBox = new Area();
	
	public AffineTransform myTform = new AffineTransform();
	
	public GameObject(double newx, double newy, ID newid){ //constructor
		x=newx;
		y=newy;
		id=newid;
		angle=0;
		scaleX=1;
		scaleY=1;
		friction=0;
	}
	
	public void tick(KeyInput keyinput){
		velX=velX+accelX;
		velY=velY+accelY;
		
		accelX=-velX;
		accelY=-velY;
		double proposedfriction = Math.sqrt(accelX*accelX+accelY*accelY); 
		if(proposedfriction > friction){
			accelX=(accelX/proposedfriction)*friction;
			accelY=(accelY/proposedfriction)*friction;
		}
		
		speed = Math.sqrt(velX*velX + velY*velY);
		if(speed > maxVel){
			velX=(velX/speed)*maxVel;
			velY=(velY/speed)*maxVel;
		}
		
		x += velX;
		y += velY;
		
		myTform.setToIdentity();
		myTform.rotate(angle, x, y);
		myTform.translate(x, y);
		myTform.scale(scaleX, scaleY);
	}
	
	public void destroy(){
		Handler.removeObject(this);
	}
	
	
	public void render(Graphics2D g2){//render happens on the global canvas.

	};
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
}
