package CleanSweep.src.main.java.robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import CleanSweep.src.main.java.cell.Cell;
import CleanSweep.src.main.java.element.ChargingStation;
import CleanSweep.src.main.java.element.Tile;
import CleanSweep.src.main.java.element.TileType;
import CleanSweep.src.main.java.graph.Graph;
import CleanSweep.src.main.java.layout.Layout;

public class Cleaner {
	Layout layout;
	Point point;
	boolean[][] visited;
	Integer power=250;
	HashSet<Integer> chargingpoints;
	
	
	
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
	
	
	public void move(Graph graph,int startingPoint) {
		Queue<Integer> queue=graph.dfs(graph, startingPoint); //find all available paths from the starting point
		
		Cell[][] cells=layout.getCells();
		
		int prev=queue.poll();//get the initial position of cleaner
		
		//get the x and y coordinates 
		int x=prev%10;
		int y=prev/10;
		
		point.move(x, y);
		System.out.println(getPosition()+" "+prev);
		
		/*
		 * in this loop the cleaner is going to traverse  through the valid paths by popping them of the queue.
		 * */
		while(!queue.isEmpty()) {
			int curr=queue.poll();
			
			if(validPath(curr,prev)) {// check if the path is valid 
				 
				setPoint(curr);//set the current  position of the cleaner
				System.out.println(getPosition()+" "+curr);
				
				
				
				Cell cell=cells[(curr%10)][curr/10];//retrieve the cell the cleaner is on
				
				if(power>90) {//check if the cleaner power is more than 50 units
					if(cell.getCellType().equals("Tile")) {
						System.out.println(cell.getTileType());//print the cell type
						energy(cell.getTileType());//for tiles determine the energy units required and deduct
						Tile tile =(Tile)cell.getElement();
						if(!tile.isClean()) {
							clean(cell.getTileType());
							tile.cleanFloor();
							
						System.out.println("Cleaning Tile on cell "+curr);
						}else {
							System.out.println(" Tile on cell "+curr+" is already clean");
						}

						
						printObstacles(curr);
						
					}else {
						System.out.println(cell.getCellType());
					}
				}else {
					
					gotoChargingStation(graph,curr);// if the power is too low find a charging station
				}
				
				
				prev=curr;//set the previous value to current
				
			}else {//if the next path off the quue is not valid
				Stack<Integer> stack=(Stack<Integer>) graph.getPath(prev, curr);//find the the path the current path and next using dfs
				stack.pop(); 
				while(!stack.isEmpty()) {
					
					if(power>90) {//check if the power on the cleaner is sufficient 
						curr=stack.pop();//get the next cell 
						Cell cell=cells[(curr%10)][curr/10];
						setPoint(curr);//set the current position
						System.out.println(getPosition()+" "+curr);
						if(cell.getCellType().equals("Tile")) {
							System.out.println(cell.getTileType());
							energy(cell.getTileType());
							Tile tile =(Tile)cell.getElement();
							if(!tile.isClean()) {
								clean(cell.getTileType());
								tile.cleanFloor();
								
							System.out.println("Cleaning Tile on cell "+curr);
							}else {
								System.out.println(" Tile on cell "+curr+" is already clean");
							}
						}
					}else {
						System.out.println("Battery needs to charge .....");
						gotoChargingStation(graph,curr);// if power not sufficient  find the charging point
					}
					
					prev=curr;//change the previous value to the cureent
					System.out.println();
					System.out.println();
					
				}
			}
			
			System.out.println();
			System.out.println();
			
			
		}
		
			 
		
	}
	
	
	void gotoChargingStation(Graph graph,int current) {
		Cell[][] cells=layout.getCells();
		int chargePointIndex=findCharging(graph,current);// find the closest chargingStation index in the hashset
		
		int i=0;
		int chargePoint=0;
		
		//find the cell number that contains the charging point 
		for(int cp:chargingpoints) {
			if(i==chargePointIndex) {
				chargePoint=cp;
			}
			i=i+1;
		}
		
		
		System.out.println("Closest Charging point on cell "+chargePoint);
		
		Stack<Integer> stack=(Stack<Integer>) graph.getPath(current, chargePoint);//find the path to the charging point
		Stack<Integer> retracingStack=new Stack<>();
		
		
		//navigate to the charging point by popping of the stack
		while(!stack.isEmpty()) {
			current=stack.pop();
			Cell cell=cells[(current%10)][current/10];
			setPoint(current);//set the current position
			if(cell.getCellType().equals("Tile")) {
				System.out.println(cell.getTileType());
				
				energy(cell.getTileType());
				Tile tile =(Tile)cell.getElement();
				if(!tile.isClean()) {
					clean(cell.getTileType());
					tile.cleanFloor();
					
				System.out.println("Cleaning Tile on cell "+current);
				}else {
					System.out.println(" Tile on cell "+current+" is already clean");
				}
			}
			System.out.println(getPosition()+" "+current);
			retracingStack.add(current);//and to stack to retrace back to the cureent position
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
		//get the charging station  using the chargePoint
		ChargingStation chargingStation=(ChargingStation) cells[chargePoint%10][chargePoint/10].getElement();
		System.out.println("Cleaner on charge point at " + chargePoint);
		chargingStation.rechargeElement(this);// recharge the cleaner
		
		
		//retrace back to continue cleaning
		while(!retracingStack.isEmpty()) {
			current=retracingStack.pop();
			
			Cell cell=cells[(current%10)][current/10];
			setPoint(current);
			if(cell.getCellType().equals("Tile")) {
				System.out.println(cell.getTileType());
				energy(cell.getTileType());
				Tile tile =(Tile)cell.getElement();
				if(!tile.isClean()) {
					clean(cell.getTileType());
					tile.cleanFloor();
					
				System.out.println("Cleaning Tile on cell "+current);
				}else {
					System.out.println(" Tile on cell "+current+" is already clean");
				}
				
			}
			System.out.println(getPosition()+" "+current);
			System.out.println();
			System.out.println();
			System.out.println();
			
		}
		
	}
	
	void setPoint(int poistion) {
		int x=poistion%10;
		int y=poistion/10;
		
		point.move(x, y);
	}
	
	
	// check if the current path and the next recommended path is valid to move to
	//it must be either to the left right 
	boolean validPath(int cellNumber,int prev) {
		return (cellNumber-10)==prev || (cellNumber+10)==prev||(cellNumber+1)==prev||(cellNumber-1)==prev;
	}
	

	
	
	String getPosition() {
		
		return "Cleaner on x:"+point.x+" y:"+point.y;
	}
	
	
	
	void energy(TileType tileType) {
		if(tileType.equals(TileType.Barefloor)) {
			power-=1;
		}else if(tileType.equals(TileType.Highpile)){
			power-=3;
		}else if(tileType.equals(TileType.Lowpile)){
			power-=2;
		}
		
		System.out.println("Energy Units remaining after moving : "+power);
	}
	
	void clean(TileType tileType) {
		if(tileType.equals(TileType.Barefloor)) {
			power-=1;
		}else if(tileType.equals(TileType.Highpile)){
			power-=3;
		}else if(tileType.equals(TileType.Lowpile)){
			power-=2;
		}
		
		System.out.println("Energy Units remaining after cleaning : "+power);
	}


	public Integer getPower() {
		return power;
	}

	
	public void setPower(Integer power) {
		this.power = power;
	}

	
	
	
	public HashSet<Integer> getChargingpoints() {
		return chargingpoints;
	}


	public void setChargingpoints(HashSet<Integer> chargingpoints) {
		this.chargingpoints = chargingpoints;
	}

	/*
	 * find the path of all the charging point and return the index  
	 * of the chargingStation in the set with the shortest distance
	 * */
	public int findCharging(Graph graph,int currentPoint) {
		
		List<Integer> distance=new ArrayList<>();
		chargingpoints.stream().forEach(point->{
			Stack<Integer> Stack= (Stack<Integer>) graph.getPath(currentPoint, point);
			distance.add(Stack.size());
		});
		
		int i=0;
		int min=distance.get(0);
		for(int dis:distance) {
			if(min>dis) {
				min=dis;
				i=i+1;
			}
		}
		
		return i;
		
		
	}
	
	void printObstacles(int position){
		Cell[][] cells=layout.getCells();
		
		try {
			int[] positions=new int[4];
			int left =position-1;
			positions[0]=left;
			int right=position+1;
			positions[1]=right;
			int up=position-10;
			positions[2]=up;
			int down=position+10;
			positions[3]=down;
			
			for(int p:positions) {
				if(cells[p%10][p/10].getCellType()!="Tile") {
					System.out.println(cells[p%10][p/10].getCellType() + " Found at "+p);
				}
				
			}
			
			
			
			
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
	}
	
	

}
