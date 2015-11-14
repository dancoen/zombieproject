package zombie;

import java.util.Random;
import java.awt.Color;

public class Human{
	private ZombieModel model;
	private int x;
	private int y;
	private Direction d = Direction.EAST;
	private Random rand = new Random();
	public Human(ZombieModel modelArg){
		model = modelArg;
		//randomly generate a location on the canvas.
		//loop until the location is qualified; check to make sure the human is placed on black (free) space.
		x = rand.nextInt(model.getWidth());
		y = rand.nextInt(model.getHeight());
		while(model.getColor(x,y) != Color.BLACK){
			x = rand.nextInt(model.getWidth());
			y = rand.nextInt(model.getHeight());
		}
		model.setColor(x,y, Color.WHITE);
	}
	
	public void update(){
		int newx = x;
		int newy = y;
		//update newx and newy based on dir
		//(use switch)
		switch(d){
			case NORTH:
				if(y > 0){
					if(model.getColor(newx,newy-1) == Color.BLACK){
						newy = y-1;
						model.setColor(x, y, Color.BLACK);
						y = newy;
						model.setColor(newx, newy, Color.WHITE);
					}
				}
				return;
			case SOUTH:
				 if(y < model.getHeight() - 1){
					if(model.getColor(newx,newy+1) == Color.BLACK){
						newy = y+1;
						model.setColor(x, y, Color.BLACK);
						y = newy;
						model.setColor(newx, newy, Color.WHITE);
					}
				 }
				 return;
			case WEST:
				if(x > 0){
					if(model.getColor(newx-1,newy) == Color.BLACK){
						newx = x-1;
						model.setColor(x, y, Color.BLACK);
						x = newx;
						model.setColor(newx, newy, Color.WHITE);
					}
				}
				return;
			case EAST:
				if(x < model.getWidth() - 1){
					if(model.getColor(newx+1,newy) == Color.BLACK){
						newx = x+1;
						model.setColor(x, y, Color.BLACK);
						x = newx;
						model.setColor(newx, newy, Color.WHITE);
					}
				}	
				return;
		}
		//check whether the new location is on the canvas and is qualified.
		//if yes, move the human and update the human's location.
	}
	public void setDirection(Direction d){
		this.d = d;
	}
	
	public Direction getDirection(){
		return d;
	}
}