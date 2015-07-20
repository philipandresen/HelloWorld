package Gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import main.Handler;
import main.KeyInput;

public class MuzzleFlash extends GameObject{
	float alpha = 1.0F;
	Color draw_color = Color.white;
	Line2D myline = new Line2D.Double(-1,0,1,0);
	
	public MuzzleFlash(double newx, double newy, ID newid){
		super(newx,newy,newid);
		Handler.addObject(this);
	}
	
	public void setProperties(double newAngle, Color newColor, double length){
		angle=newAngle;
		draw_color=newColor;
		//double vecX = Math.cos(angle)*length;
		//double vecY = Math.sin(angle)*length;
		myline.setLine(-length,0,0,0);
	}
	
	public void tick(KeyInput keyinput){
		super.tick(keyinput);
		alpha = alpha*0.9F;
		if(alpha<0.1F) this.destroy();
	}
	
	public void render(Graphics2D g2) {
		//super.render(g2);
		g2.setColor(new Color(draw_color.getRed()/255,
				draw_color.getGreen()/255,draw_color.getBlue()/255,alpha));
		g2.draw(myTform.createTransformedShape(myline));
	}
}
