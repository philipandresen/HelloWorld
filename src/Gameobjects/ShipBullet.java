package Gameobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.LinkedList;

import main.Handler;
import main.HelloWorld;
import main.KeyInput;


public class ShipBullet extends GameObject{
	
	public ShipBullet(double x, double y, ID id) {
		super(x, y, id);
		bBox = new Rectangle(-2, -2, 4, 4);
		hitBox = new Area(myTform.createTransformedShape(bBox));
		maxVel=10;
		Handler.addObject(this);
		//bBox_T = new Rectangle();
		//bBox_T.setBounds(bBox.getBounds());
	}
	
	public void tick(KeyInput keyinput) {
		super.tick(keyinput);
		MuzzleFlash mf = new MuzzleFlash(x,y,ID.Particle);
		mf.setProperties(angle, Color.cyan, 10);
		//x += velX;
		//y += velY;
		
		if(x<0 || x>HelloWorld.currentLevel.Width || y<0 || y>HelloWorld.currentLevel.Height){
			Handler.removeObject(this);
		}
		
		hitBox = new Area(myTform.createTransformedShape(bBox));
		
		LinkedList<GameObject> hitList = Handler.checkCollision(hitBox, ID.Enemy);
		if(!hitList.isEmpty()){
			hitList.getFirst().destroy();
			this.destroy();
		}
	}
	
	public void destroy(){
		Exploder.explode(bBox.getPathIterator(null), x, y, scaleX, scaleY, angle, Color.cyan);
		super.destroy();
	}
	
	
	public void render(Graphics2D g2){
		g2.setColor(Color.cyan);
		g2.draw(hitBox);
		
		
	}


}
