package zombie;

import java.awt.Color;
import java.util.Random;

public class ZombieModel {
	private Human human;
	private int tempx;
	private int tempy;
	
	private final Color[][] matrix;
	private final int width;
	private final int height;
	private final int dotSize;
	private final Random rand = new Random();
	
	public ZombieModel(int widthArg, int heightArg, int dotSizeArg) {
		width = widthArg;
		height = heightArg;
		dotSize = dotSizeArg;
		matrix = new Color[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = Color.BLACK;
			}
		}
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDotSize() { return dotSize; }
	public Color getColor(int x, int y) { return matrix[x][y]; }
	public void setColor(int x, int y, Color color) { matrix[x][y] = color; }
	
	public void initialize() {
		initializeRocks();
		initializeTrees();
		initializeRiver();
		human = new Human(this);
	}
	
	public Human getHuman(){
		return human;
	}
	
	private void initializeRiver(){
		int x = rand.nextInt(width-5);
		for(int i = 0; i < 5; i++){		//river is 5 dots wide
			for(int n = 0; n < height; n++){
				setColor(x,n,Color.BLUE);
			}
			x++;
		}
	}
	
	private void initializeTrees(){
		int x;
		int y;
		for(int i = 0; i < 40; i++){	//40 trees
			x = rand.nextInt(width-2) + 1;
			y = rand.nextInt(height-2) + 1;
			while(this.getColor(x,y) != Color.BLACK){			//see if center point is free
				if(getColor(x, y) == Color.BLACK 	//check each point of the plus sign shape
				&& getColor(x, tempy+1) == Color.BLACK  //too see if free
				&& getColor(x, y-1) == Color.BLACK 
				&& getColor(x+1, y) == Color.BLACK 
				&& getColor(x-1, y) == Color.BLACK) break;
				x = rand.nextInt(width-2) + 1;
				y = rand.nextInt(height-2) + 1;
			}
			setColor(x,y,Color.GREEN);
			setColor(x+1,y,Color.GREEN);
			setColor(x-1,y,Color.GREEN);
			setColor(x,y+1,Color.GREEN);
			setColor(x,y-1,Color.GREEN);
		}
	}
	
	private void initializeRocks(){
		int x;
		int y;
		int diameter;
		double distance;
		boolean free = true;
		for(int i = 0; i < 6; ){				//six rocks
			diameter = rand.nextInt(4)+4;
			x = rand.nextInt(width-5);
			y = rand.nextInt(height-5);
			free = true;
			for(int n = 0; n < width ; n++){
				for(int j = 0; j < height; j++){
					distance = Math.sqrt(Math.pow(n-x, 2) + Math.pow(j-y, 2));
					if(distance<diameter && matrix[n][j] == Color.GRAY){	// check every point to see if theres a rock there
						free = false;								//if theres a rock, we need to start over
						break;
					}
				}
			}
			if(free){								//if every dot was rock-free, we'll make a rock here
				for(int n = 0; n < width ; n++){
					for(int j = 0; j < height; j++){
						distance = Math.sqrt(Math.pow(n-x, 2) + Math.pow(j-y, 2));
						if(distance<diameter && matrix[n][j] == Color.BLACK){
							matrix[n][j] = Color.GRAY;
						}
					}
				}
				i++;		//on to the next rock!
			}
		}
	}
	
	
	public void update() {
		human.update();
	}
}
