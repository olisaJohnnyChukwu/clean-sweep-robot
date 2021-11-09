package CleanSweep.src.main.java.layout;

import java.awt.Point;

public class JsonDoor {
	Point point1;
	boolean open;
	
	
	

	public JsonDoor(Point point1) {
		super();
		this.point1 = point1;
	}

	

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}



	public boolean isOpen() {
		return open;
	}



	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
	
	
}
