package layout;

import java.awt.Point;

public class JsonWall {
	Point point1;
	
	Point point2;
	
	Point point3;
	
	
	

	public JsonWall() {
		
		super();
	}



	public JsonWall(Point point1, Point point2, Point point3) {
		super();
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
	}



	public Point getPoint1() {
		return point1;
	}



	public void setPoint1(Point point1) {
		this.point1 = point1;
	}



	public Point getPoint2() {
		return point2;
	}



	public void setPoint2(Point point2) {
		this.point2 = point2;
	}



	public Point getPoint3() {
		return point3;
	}



	public void setPoint3(Point point3) {
		this.point3 = point3;
	}

}
