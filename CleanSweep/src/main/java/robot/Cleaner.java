package robot;

import java.awt.Point;
import java.util.Queue;

import cell.Cell;
import element.Tile;
import layout.Layout;

public class Cleaner {
	Layout layout;
	Point point;
	boolean[][] visited;
	
	
	public Cleaner() {
		
		visited =new boolean[10][10];
		
		for(int i=0;i<visited.length;i++) {
			for(int j=0;j<visited.length;j++) {
				visited[i][j]=false;
				}
		}
		
		
	}
	
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
	
	public void move(Queue<Integer> queue) {
		
		 while(!queue.isEmpty()) {
			// System.out.println(queue.poll());
			 
			 int cellNumber=queue.poll();
			 
			 int y=cellNumber/10;
			 int x=cellNumber%10;
			 
			 point.move(x, y);
			 
			 System.out.println(getPosition() +" on cell "+cellNumber);
			 
		 }
		
		
		
		
	}
	
	boolean passable(int x,int y,int l) {
		Cell[][] cells=layout.getCells();
		if(x<0 || x>l || y<0 || y>l) {
			return false;
		}
		return cells[x][y].passable()?true:false;
	}
	
	
	String getPosition() {
		
		return "Cleaner on x:"+point.x+" y:"+point.y;
	}
	
	
	
	

}
