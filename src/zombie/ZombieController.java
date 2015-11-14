package zombie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class ZombieController implements MouseListener {

	private final ZombieModel model;
	private final ZombieView view;
	private final int delay;
	
	public ZombieController(ZombieModel modelArg, ZombieView viewArg, int sleepTimeArg) {
		model = modelArg;
		view = viewArg;
		delay = sleepTimeArg;
	}

	public void beginSimulation() {
		model.initialize();
		view.draw();
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.update();
				view.draw();
			}
		};
		new Timer(delay, taskPerformer).start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();//model.getDotSize()
		int y = e.getY();//model.getDotSize()
		int size = model.getDotSize();
		//if the mouse-clicked location is with certain ranges, change the human's direction.
		Human human = model.getHuman();
		int vert = model.getHeight()/2 * size;		//based on max x and y in main
		int horiz = model.getWidth()/4 * size;  
		int boundary = (model.getHeight()/2 + 5) * size;	
		if(x < vert && y > horiz && y < boundary) {
			human.setDirection(Direction.WEST);
		} else if(x > vert && y > horiz && y < boundary) {
			human.setDirection(Direction.EAST);
		} else if(y < boundary) {
			human.setDirection(Direction.NORTH);
		} else if(y > horiz) {
			human.setDirection(Direction.SOUTH);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {


		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
