package brickBreakerGame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
/**
 * The ball for the brick breaker game
 * Breaks bricks and bounces, also bounces off paddle
 * If it falls below the paddle, game over
 * @author Sarah Schultz
 *
 */
public class Ball {

//location, shape, and change in location
int x,y,dx,dy;
Ellipse2D.Float ballShape;
//whether the ball is still in play
boolean ballInPlay;

//ball never changes size
public static final int height=10;
public static final int width=10;

	/**
	 * Constructor, creates the ball
	 * @param startX, ball's starting x coordinate
	 * @param startY, ball's starting y coordinate
	 */
	public Ball(int startX, int startY){
		x=startX;
		y=startY;
		//ball starts out of play
		ballInPlay=false;
		//create the shape of the ball
		ballShape = new Ellipse2D.Float(startX, startY, width, height);
	}
	
	/**
	 * Moves the ball
	 */
	public void move(){
		//update ball's position
		x=x+dx;
		y=y+dy;
		//if the ball hits the "wall," bounce off
		if (x > 790){
			dx=-1;
		}
		if (x < 0){
			dx=1;
		}
		//if the ball misses the paddle on the bottom...
		if (y > 710){
			//it goes out of play
			ballInPlay=false;
		}
		//if the ball hits the "ceiling," bounce off
		if (y < 0){
			dy=1;
		}
	}

	/**
	 * Get the x coordinate of the ball
	 * @return the x coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Get the y coordinate of the ball
	 * @return the y coordinate
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Get whether the ball is still in play
	 * @return whether ball is in play
	 */
	public boolean getBallInPlay(){
		return ballInPlay;
	}
	
	/**
	 * Set x coordinate of ball
	 * Used when reseting after ball goes out
	 * @param x, new x coordinate of ball
	 */
	public void setX(int x){
		this.x=x;
	}
	
	/**
	 * Set the y coordinate of the ball
	 * Used when reseting after ball goes out
	 * @param y, new y coordinate of ball
	 */
	public void setY(int y){
		this.y=y;
	}
	
	/**
	 * Get the bounds of the ball
	 * Used for collision checking
	 * @return bounding box of the ball
	 */
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
	
	/**
	 * When the ball bounces off of something, reverse y movement
	 * If it bounces of an edge, x movement changes appropriately
	 */
	public void bounce(int intersectionWidth, Rectangle other){
		//reverse y movement
		if(dy==1) dy=-1;
		else if(dy==-1) dy=1;
		//check if bouncing off left edge
		if(this.x < other.getX()+width){
			//if so, move left
			dx=-1;
		}
		//check if bouncing off right edge
		else if(this.x > other.getX()+other.getWidth()-width){
			//if so, move right
			dx=1;
		}
		//otherwise, it will keep moving in same x as before
	}
	
	
	/**
	 * If the space key is pressed, ball goes into play and moves up
	 * @param e, the key being pressed
	 */
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		if (key==KeyEvent.VK_SPACE){
			//ball is now in play
			ballInPlay=true;
			//ball begins moving up and right
			dy=-1;
			dx=1;
		}
	}
	
}
