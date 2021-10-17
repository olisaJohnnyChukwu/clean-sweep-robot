package cell;

import java.awt.Point;

import element.Element;
import element.Tile;
import element.TileType;

public class Cell {
	public Element element;
	Point point;
	int number;
	
	
	
	
	public int getNumber() {
		return number;
		
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	
	
	public boolean passable() {
		return element.passable();
	}
	
	
	public TileType getTileType() {
		
		Tile tile=(Tile)element;
		return tile.getTileType();
		
	}
	
	
	
}
