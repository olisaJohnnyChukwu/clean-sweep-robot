package layout;
import java.awt.Point;

import element.*;

public class JsonTile {
	TileType  TileType ;
	Point point1;
	Point point2;
	Point point3;
	
	
	
	
	
	public JsonTile(TileType tileType, Point point1, Point point2, Point point3) {
		super();
		TileType = tileType;
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
	}
	public TileType getTileType() {
		return TileType;
	}
	public void setTileType(TileType tileType) {
		TileType = tileType;
	}
	public Point getPoint1() {
		return point1;
	}
	public void setPoint1(Point point) {
		this.point1 = point;
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
