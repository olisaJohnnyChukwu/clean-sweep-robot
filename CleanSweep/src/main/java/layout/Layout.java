package CleanSweep.src.main.java.layout;

import CleanSweep.src.main.java.cell.Cell;

public class Layout {
	public Cell cells[][];

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	
	public boolean[][] passAbleMatrix(){
		
		boolean[][] matrix=new boolean[10][10]; 
		
		for(int i=0;i<cells.length;i++) {
			for(int j=0;j<cells.length;j++) {
				matrix[i][j]=cells[i][j].passable();
				
				
			}
		}
		return matrix;
		
	}
	
	
	public Cell cell(int cellNumber) {
		
		 int y=cellNumber/10;
		 int x=cellNumber%10;
		 
		 
		 return cells[x][y];
		 
		
	}
	
	
	
	
}
