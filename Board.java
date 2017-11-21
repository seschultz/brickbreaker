package brickBreakerGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Board of the Brick Breaker game
 * Holds the paddle, ball, and bricks
 * Updates display every 5 ms
 */
public class Board extends JPanel implements ActionListener{

//the paddle and ball
Paddle paddle;
Ball ball;
//a timer for updating the display
Timer timer;
//array of bricks
Brick[][] bricks;
//array of different colors for the bricks
Color[] colors;
//font for begin print out on screen
Font f;
//font for scoring
Font f2;
//keep score
int score;

//paddle and ball always start in same place
public static final int paddleStartX=365, paddleStartY=700, ballStartX=390, ballStartY=685;
public static final int brickWidth=95, brickHeight=15;

/**
 * Constructor, initializes all elements
 */
	public Board(){
		//create the paddle
		paddle = new Paddle(paddleStartX, paddleStartY);
		//create the ball
		ball = new Ball(ballStartX, ballStartY);
		//create the bricks
		createBricks();
		//listen for key presses
		addKeyListener(new listener());
		//make the board able to gain focus
		setFocusable(true);
		//set the fonts to be used
		f=new Font("Arial", Font.BOLD, 24);
		f2=new Font("Arial", Font.PLAIN, 12);
		//create and start the timer
		timer = new Timer(5,this);
		timer.start();
		
	}
	
	/**
	 * Create the array of bricks
	 */
	public void createBricks(){
		//create the array of colors for the bricks
		colors=new Color[5];
		colors[0]=Color.BLUE;
		colors[1]=Color.RED;
		colors[2]=Color.GREEN;
		colors[3]=Color.ORANGE;
		//create each brick and put in brick array
		bricks=new Brick[8][20];
			for(int i=0;i<bricks.length;i++){
				for(int j=0; j<bricks[i].length;j++){
					bricks[i][j] = new Brick(i*(brickWidth+5)+2,j*(brickHeight+5)+5,colors[(2*i+j)%4]);
				}
			}
	}
	
	/**
	 * When an action is performed, check for collisions,
	 * move the paddle and ball and repaint
	 */
	public void actionPerformed(ActionEvent e) {
		checkCollisions();
		paddle.move();
		ball.move();
		//when the ball goes out of play, reset
		if(!ball.getBallInPlay()){
			//reset paddle and ball to start position
			paddle.setX(paddleStartX);
			paddle.setY(paddleStartY);
			ball.setX(ballStartX);
			ball.setY(ballStartY);
			//all bricks become visible
			for(int i=0; i<bricks.length;i++){
				for(int j=0; j<bricks[i].length;j++){
					bricks[i][j].setVisible(true);
				}
			}
		}
		repaint();
		
	}
	
	/**
	 * Draws everything
	 */
	public void paint(Graphics graphics){
		super.paint(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		//when the ball is not in play...
		if(!ball.getBallInPlay()){
			//put the instructions up
			g2d.setFont(f);
			g2d.drawString("Press Space to Start", 275, 450);
			g2d.drawString("Use left and right arrows to move paddle", 185, 475);
			//reset score to 0
			score=0;
		}
		//put the score up
		g2d.setFont(f2);
		g2d.drawString("Score: " + score, 725, 750);
		//draw the paddle, ball, and bricks
		g2d.fillRect(paddle.getX(), paddle.getY(), Paddle.width, Paddle.height);
		g2d.fillOval(ball.getX(), ball.getY(), Ball.width, Ball.height);
		for(int i=0; i<bricks.length;i++){
			for(int j=0; j<bricks[i].length;j++){
				//only draw bricks that haven't been hit yet
				if(bricks[i][j].getVisible()){
					g2d.setColor(bricks[i][j].getColor());
					g2d.fillRect(bricks[i][j].getX(), bricks[i][j].getY(), brickWidth, brickHeight);
				}
			}
		}
	}
	/**
	 * Check whether the ball has hit the paddle or a brick
	 * If so, ball bounces
	 */
	public void checkCollisions(){
		Rectangle paddleRect = paddle.getBounds();
		Rectangle ballRect = ball.getBounds();
		if (paddleRect.intersects(ballRect)){
			Rectangle intersection=paddleRect.intersection(ballRect);
			ball.bounce(intersection.width, paddleRect);
		}
		
		//go through bricks and check if ball hit
		for(int i=0; i<bricks.length; i++){
			for(int j=0; j<bricks[i].length;j++){
				//get the current brick
				Brick b=bricks[i][j];
				Rectangle brickRect = b.getBrickShape();
				//if the ball hits the brick and it is still visible...
				if (ballRect.intersects(brickRect) && b.getVisible()){
					Rectangle intersection1=ballRect.intersection(brickRect);
					//make this brick invisible
					b.setVisible(false);
					//ball bounces off brick
					ball.bounce(intersection1.width,brickRect);
					//score a point
					score+=1;
				}
			}
		}
	}

	/**
	 * Event listener class, calls ball and paddle key listeners
	 */
	private class listener extends KeyAdapter{
		public void keyReleased(KeyEvent e){
				paddle.keyReleased(e);
		}
		public void keyPressed(KeyEvent e){
			//if the ball is in play, only the paddle listens
			//for its cue to move
			if(ball.getBallInPlay()){
				paddle.keyPressed(e);
			}
			//if the ball is not in play, it listens for cue to reset
			//paddle stops moving
			else ball.keyPressed(e);

		}
	}
}
