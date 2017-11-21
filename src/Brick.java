package brickBreakerGame;
import java.awt.*;

/**
 * A brick for the brick breaker game
 * Knows its location, color, and whether it is still visible (and thus in play)
 * @author Sarah Schultz
 *
 */
public class Brick {

//location of the upper right corner of the brick
int x,y;
//the color of the brick
Color color;
//is this brick visible
boolean visible;

Rectangle brickShape;

	/**
	 * Constructor- creates a brick with color color at coordinates x,y
	 * @param x, the x coordinate of this brick
	 * @param y, the y coordinate of this brick
	 * @param color, the color of this brick
	 */
	public Brick(int x, int y, Color color){
		this.x=x;
		this.y=y;
		this.color=color;
		
		//bricks are always visible to start
		visible=true;
		
		//create the shape of the brick
		brickShape=new Rectangle(x,y,Board.brickWidth,Board.brickHeight);
	}
	
	/**
	 * Get the color
	 * @return the color of this brick
	 */
	public Color getColor(){
		return color;
	}
	/**
	 * Get the x coordinate
	 * @return the x coordinate of this brick
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Get the y coordinate
	 * @return the y coordinate of this brick
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Get the rectangle that composes this brick
	 * @return the Rectangle of this brick
	 */
	public Rectangle getBrickShape(){
		return brickShape;
	}
	
	/**
	 * Get whether the brick is visible
	 * @return brick's visibility
	 */
	public boolean getVisible(){
		return visible;
	}
	
	/**
	 * Set whether this brick is visible
	 * @param vis, what we set the brick's visibility to
	 */
	public void setVisible(boolean vis){
		visible=vis;
	}

}
