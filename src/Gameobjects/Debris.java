package Gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import main.Handler;
import main.HelloWorld;
import main.KeyInput;

public class Debris extends GameObject{
	
	Color draw_color = Color.white;
	Line2D myline = new Line2D.Double(-1,0,1,0);
	float alpha = 1.0F;
	
	public Debris(double newx, double newy, ID newid) {
		super(newx, newy, newid);
		Handler.addObject(this);
	}
	
	public void setLine(double newx1, double newy1, double newx2, double newy2){
		myline.setLine(newx1,newy1,newx2,newy2);
		velX = (Math.random()*2-1)*2;
		velY = (Math.random()*2-1)*2;
		maxVel = 10;
	}
	
	public void tick(KeyInput keyinput){
		super.tick(keyinput);
		angle=angle+(Math.toRadians(2));
		if(x<0 || x>HelloWorld.currentLevel.Width || y<0 || y>HelloWorld.currentLevel.Height){
			Handler.removeObject(this);
		}
		
	}

	public void render(Graphics2D g2) {
		super.render(g2);
		alpha = alpha*0.99F;
		if(alpha<0.1F) this.destroy();

		g2.setColor(new Color(draw_color.getRed()/255,
				draw_color.getGreen()/255,draw_color.getBlue()/255,alpha));
		g2.draw(myTform.createTransformedShape(myline));
	}

}
