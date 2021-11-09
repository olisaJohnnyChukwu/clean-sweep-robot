package CleanSweep.src.main.java.cell;

import CleanSweep.src.main.java.element.Element;
import CleanSweep.src.main.java.element.TileType;
import CleanSweep.src.main.java.element.Tile;

import java.awt.Point;

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
	
	public String getCellType() {
		return element.printElement();
	}
	
	public boolean passable() {
		return element.passable();
	}
	
	
	public TileType getTileType() {
		
		Tile tile=(Tile)element;
		return tile.getTileType();
		
	}
	
	
	
}
