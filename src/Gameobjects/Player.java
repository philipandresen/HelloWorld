package Gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

import main.Handler;
import main.KeyInput;


public class Player extends GameObject {
	
	Polygon ship;
	int score = 0;
	int shake=0;
	int maxShake = 30;
	public static int health = 100;
	private double shotangle = 90;
	
	public Player(double x, double y, ID id) {
		super(x, y, id); // perform the inherited constructor?
		angle=0;
		maxVel=2;
		friction=0.1;
		int ypoints[] = {2,3,3,6,6,8,8,3,3,-3,-3,-8,-8,-6,-6,-3,-3,-2};
		int xpoints[] = {5,3,-1,-3,1,1,-5,-6,-8,-8,-6,-5,1,1,-3,-1,3,5};
		int npoints = 18;
		ship = new Polygon(xpoints, ypoints, npoints);
		hitBox = new Area(myTform.createTransformedShape(ship));
		Handler.addObject(this);
	}
	

	@Override
	public void tick(KeyInput keyinput) {
		super.tick(keyinput);
		hitBox = new Area(myTform.createTransformedShape(ship));
		double accelRate=friction+0.1;
		
		if(keyinput.isPressed(KeyEvent.VK_LEFT)){
			accelX += -accelRate;
		}
		if(keyinput.isPressed(KeyEvent.VK_RIGHT)){
			accelX += accelRate;
		}
		if(keyinput.isPressed(KeyEvent.VK_UP)){
			accelY += -accelRate;
		}
		if(keyinput.isPressed(KeyEvent.VK_DOWN)){
			accelY += accelRate;
		}
		
		if(keyinput.justPressed(KeyEvent.VK_SPACE)){
			shoot();
		}
		
		maxVel=2;
		if(keyinput.isPressed(KeyEvent.VK_SHIFT)){
			maxVel=4;
		}
		
		if(speed>0){
			angle = Math.atan2(velY, velX);
		}
		
		scaleX=2;
		scaleY=2;
		if(!Handler.checkCollision(hitBox, ID.Enemy).isEmpty()) {
			if(shake<=1) {
				shake=maxShake;
				addHealth(-10);
			}
		}
		if(shake>0) {
			shake--;
			scaleX=1.8+Math.random()*(0.4*shake/maxShake);
			scaleY=1.8+Math.random()*(0.4*shake/maxShake);
		}
		
	}
	
	private void shoot(){
		shotangle = -shotangle;
		double shotX=x + Math.cos(angle+Math.toRadians(shotangle))*14;
		double shotY=y + Math.sin(angle+Math.toRadians(shotangle))*14;
		ShipBullet shot = new ShipBullet(shotX, shotY , ID.ShipBullet);
		shot.velX = (10*Math.cos(angle));
		shot.velY = (10*Math.sin(angle));
		shot.angle=angle;
		
	}
	
	private void addHealth(int ammount){
		health = health + ammount;
		if(ammount<0){
			Exploder.explode(ship.getPathIterator(null),x,y,scaleX,scaleY,angle,Color.white);
		}
		if(health>100) health=100;
		if(health<=0) {
			health=0;
			destroy();
		}
	}
	
	public void destroy(){
		Exploder.explode(ship.getPathIterator(null), x, y, scaleX, scaleY, angle, Color.white);
		super.destroy();
	}
	
	public void render(Graphics2D g2){
		super.render(g2);
		
		float col = (float)health/100;
		g2.setColor(Color.black);
		g2.fill(myTform.createTransformedShape(ship));
		g2.setColor(new Color(1F,col,col,1F));
		g2.draw(myTform.createTransformedShape(ship));
		
	}

	
}
