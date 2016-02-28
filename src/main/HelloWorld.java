package main;

import Levels.*;
import Gameobjects.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;




public class HelloWorld extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 2127143616039646737L;
	
	public static final int WIDTH = 1024, HEIGHT = (WIDTH / 16) * 9;
	private Thread thread; // what Is a thread?
	private boolean running = false;
	public static Level currentLevel = new Level1();

	
	public HelloWorld(){
		KeyInput keyinput = new KeyInput();
		this.addKeyListener(keyinput);
		new Handler(keyinput);
		new Window(WIDTH, HEIGHT, "I AM A DEVELOPER LOOK AT ME", this);
		this.requestFocusInWindow();
		currentLevel = new Level1();
		currentLevel.startLevel();
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		long now;
		while (running){
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				render();
				frames++;
				//render(); // should be window.render
					//window should be a canvas, not HelloWorld. DUMB
				delta--;
			}
			if(running) {
				
			}
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
		stop();
	}
	
	private void tick(){
		Handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		Handler.render(g2);
		
		g2.dispose(); //what is this
		bs.show(); //what is this
		

	}
	
    public static void main(String[] args) {
    	new HelloWorld();
    }

}