package brickBreakerGame;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The paddle for the Brick Breaker game, controlled by the user
 * @author Sarah Schultz
 *
 */
public class Paddle {
	
/**
 * The x and y coordinates of the paddle
 * and the amount the paddle moves on the x-axis
 */
int x, dx, y;

//never changes size
public static final int height=10;
public static final int width=60;
//The paddle is a rectangle
Rectangle paddleShape;

	public Paddle(int startX, int startY){
		x=startX;
		y=startY;
		paddleShape = new Rectangle(x, y, width, height);
	}
	
	/**
	 * Move the paddle by dx
	 */
	public void move(){
		x=x+dx;
		//don't allow the paddle out of frame
		if(x>750){
			dx=-1;
		}
		if(x<0){
			dx=1;
		}
	}
	
	//get the x coordinate of the paddle
	public int getX(){
		return x;
	}
	
	//get the y coordinate of the paddle
	public int getY(){
		return y;
	}
	
	//get the current bounds of the paddle for collision detection
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
	
	//get the bounds of the paddle
	//used for collision checking
//	public Rectangle getBounds(){
//		return new Rectangle(x,y,height,width);
//	}
	
	//set paddle's x
	public void setX(int x){
		this.x=x;
	}
	
	//set paddle's y
	public void setY(int y){
		this.y=y;
	}
	
	/**
	 * If the left or right arrow key is being pressed,
	 * move the paddle in the appropriate direction
	 * @param e, the key being pressed
	 */
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		if (key==KeyEvent.VK_LEFT){
			//continually holding allows us to speed up, but not too much
			if(dx>-2){
				dx-=1;
			}
		}
		if (key==KeyEvent.VK_RIGHT){
			//continually holding allows us to speed up, but not too much
			if(dx<2){
				dx+=1;
			}
		}
	}
	
	/**
	 * If the left or right arrow key is released,
	 * stop the paddle
	 * @param e, the key released
	 */
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_RIGHT){
			dx=0;
		}
	}

}
