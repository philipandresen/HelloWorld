package main;
import Gameobjects.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.LinkedList;


public class Handler{
	
	static KeyInput keyinput;
	
	public Handler(KeyInput keyinput){
		Handler.keyinput = keyinput;
	}
	
	static LinkedList<GameObject> object = new LinkedList<GameObject>();
	public static AffineTransform windowTform = new AffineTransform();
	public static double viewWScale = 1.5, viewHScale = 1.5;
	public static ID viewTarget = ID.Player;
	public static double windowX = 0, windowY = 0, windowWidth, windowHeight;
	static boolean didtick = false;
	
	public static void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempobject = object.get(i);
			if(tempobject.id == viewTarget){
				windowWidth=HelloWorld.WIDTH/viewWScale;
				windowHeight=HelloWorld.HEIGHT/viewHScale;
				windowX=tempobject.getX()-windowWidth/2;
				windowY=tempobject.getY()-windowHeight/2;
				windowTform.setToIdentity();
				windowTform.scale(viewWScale, viewHScale);
				windowTform.translate(-windowX,-windowY);
			}
			tempobject.tick(keyinput);
			didtick=true;
		}
	}
	
	public static void render(Graphics2D g2){
		g2.setTransform(Handler.windowTform);
		/*g2.setColor(new Color(0.5F, 0.5F, 0.5F, 1F));
		for(int i = 0; i<HelloWorld.currentLevel.Width ; i = i+200){
			g2.drawLine(i,0,i,HelloWorld.currentLevel.Height);
		}
		for(int j = 0; j<HelloWorld.currentLevel.Height ; j = j+200){
			g2.drawLine(0,j,HelloWorld.currentLevel.Width,j);
		}*/
		
		g2.setColor(Color.WHITE);
		String debugstr = Boolean.toString(didtick);
		g2.setFont(new Font("Serif",Font.PLAIN,12));
		g2.drawString(debugstr, 16, 16);
		didtick=false;
		
		for(int i = 0; i < object.size(); i++){
			GameObject tempobject = object.get(i);
			if(!(tempobject.getX()<windowX) && !(tempobject.getX()>windowX+windowWidth)
					&& !(tempobject.getY()<windowY) && !(tempobject.getY()>windowY+windowHeight)){
				tempobject.render(g2);
			}
		}
		g2.setColor(Color.WHITE);
		/*String debugstr = Integer.toString(object.size());
		g2.setFont(new Font("Serif",Font.PLAIN,12));
		g2.drawString(debugstr, 16, 16);*/
		
		g2.draw(new Rectangle(0,0,HelloWorld.currentLevel.Width,HelloWorld.currentLevel.Height));
	}
	
	public static void addObject(GameObject newObject){
		object.add(newObject);
	}
	public static void removeObject(GameObject oldObject){
		object.remove(oldObject);
		oldObject = null;
	}
	
	public static LinkedList<GameObject> checkCollision(Area hitbox1, ID checkID){
		//Check if collision with bBox and return objects colliding there.
		LinkedList<GameObject> objList = new LinkedList<GameObject>();
		for(int i = 0; i<object.size(); i++){
			Area hitbox_temp = (Area) hitbox1.clone();
			GameObject tempObj = object.get(i);
			if(tempObj.id == checkID){ //if correct type
				hitbox_temp.intersect(tempObj.hitBox);
				
				if(!hitbox_temp.isEmpty()){//if intersecting
					objList.add(tempObj);
				}
				
			}
			
		}
		return objList;
	}

	public static LinkedList<GameObject> findObject(ID checkID){
		LinkedList<GameObject> objList = new LinkedList<GameObject>();
		for(GameObject tempObj : object){
			if(tempObj.id == checkID){
				objList.add(tempObj);
			}
		}
		return objList;
	}
}
