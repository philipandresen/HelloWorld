package Levels;

import main.Handler;
import Gameobjects.*;

public class Level1 extends Level {
	
	public Level1(){
		Width = 800;
		Height = 800;
		
	}
	
	public void startLevel(){
		new Player(100, 100, ID.Player);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		new Enemy(0, 0, ID.Enemy);
		//new Debris(400,100,ID.Particle));
	}
}
