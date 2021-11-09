package CleanSweep.src.main.java.main;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

import CleanSweep.src.main.java.cell.Cell;
import CleanSweep.src.main.java.element.Tile;
import CleanSweep.src.main.java.layout.JsonChargingPoint;
import com.google.gson.Gson;

import CleanSweep.src.main.java.cell.Cell;
import CleanSweep.src.main.java.element.ChargingStation;
import CleanSweep.src.main.java.element.Door;
import CleanSweep.src.main.java.element.Element;
import CleanSweep.src.main.java.element.Stairs;
import CleanSweep.src.main.java.element.Tile;
import CleanSweep.src.main.java.element.TileType;
import CleanSweep.src.main.java.element.Wall;
import CleanSweep.src.main.java.graph.Graph;
import CleanSweep.src.main.java.layout.JsonDoor;
import CleanSweep.src.main.java.layout.JsonLayout;
import CleanSweep.src.main.java.layout.JsonStair;
import CleanSweep.src.main.java.layout.JsonTile;
import CleanSweep.src.main.java.layout.JsonWall;
import CleanSweep.src.main.java.layout.Layout;
import CleanSweep.src.main.java.robot.Cleaner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//load layout files from json
		FileInputStream fileInputStream= new FileInputStream(new File("CleanSweep/layoutElements.json"));
		BufferedReader bufferedReader =new BufferedReader (new InputStreamReader(fileInputStream));
		
		
	
		
		//create json string
		String json="";
		String fromInput="";
		while((fromInput=bufferedReader.readLine())!=null) {
			json+=fromInput +"\n";
		}
		
		
		System.out.println(json);
		Gson gson = new Gson();
		//retrieve the layout
		JsonLayout jsonLayout =gson.fromJson(json,JsonLayout.class);//get layout plan
		
		Layout layout=new Layout();
		Cell[][] cell =new Cell[10][10]; // set floor grid
		populateFloor(cell);//tile all the grids
		
		layout.setCells(cell);//add the cells to the layout
		
		Cleaner cleaner=new Cleaner();//start robot
		
		cleaner.setPoint(jsonLayout.getStartingPoint());//set the starting point
		
		
		
		cleaner.setLayout(layout);//cleaner accepts the floor plans
		
		buildWalls(jsonLayout,layout);//set up walls
		setupDoors(jsonLayout,layout);//set up doors
		setupStairs(jsonLayout,layout);//set up stairs
		TileFloor(jsonLayout,layout);
		setChargingPoints(jsonLayout,layout, cleaner);//
		
		
		Graph graph=new Graph();
		
		boolean [][] path=layout.passAbleMatrix();// matrix of coonected paths
			
		
		
		
		
		
		addEdge(graph,path);//add connected vertexes to graph
		
		Point startPoint=cleaner.getPoint();
		int cellNumber=(startPoint.y*10)+startPoint.x;
	
		
		
		
		
		cleaner.move(graph,cellNumber);
		
		
		
		

	}
	
	
	public static void populateFloor(Cell[][] cell) {
		
		int x=0;
		for(int i=0;i<cell.length;i++) {
			for(int j=0;j<cell.length;j++) {
				cell[i][j]=new Cell();
				cell[i][j].setPoint(new Point(i,j));
				if(cell[i][j]!=null) {
					Tile tile =new Tile();
					int random=getRandomNumber(1,2);
					if(random==1) {
						tile.DirtyFloor();
						
					}
					
					tile.setTileType(TileType.Barefloor);
					cell[i][j].setElement(tile);
					cell[i][j].setNumber(x);
					x=x+1;
				}
				
				
				
			}
		}
	}
	
	
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	public static void buildWalls(JsonLayout jsonLayout,Layout layout) {
		Cell[][] cell =layout.getCells();
		List<JsonWall> walls=jsonLayout.getWalls();
		
		
		for(int j=0;j<walls.size();j++) {
			JsonWall JsonWall=walls.get(j);
			Point point1=JsonWall.getPoint1();
			Point point2=JsonWall.getPoint2();
			Point point3=JsonWall.getPoint3();
			
			List<Integer> maxValues=max(point1,point2,point3);
			List<Integer> minValues=min(point1,point2,point3);
			
			
			int xMax=maxValues.get(0);
			int xMin=minValues.get(0);
			
			int yMax=maxValues.get(1);
			int yMin=minValues.get(1);
			
			
			
				for(int x=xMin; x<=xMax;x++) {
					cell[x][yMax].setElement(new Wall());
				}
				
				for(int y=yMin; y<=yMax;y++) {
					cell[xMax][y].setElement(new Wall());
				}
				
				
				
		}
		
		
		
		
		
	}
	
	
	public static void setupDoors(JsonLayout jsonLayout,Layout layout) {
		Cell[][] cell =layout.getCells();
		List<JsonDoor> doors=jsonLayout.getDoor();
		
		
		for(int j=0;j< doors.size();j++) {
			JsonDoor jsonDoor=doors.get(j);
			Point point=jsonDoor.getPoint1();
			Door door =new Door();
			if(jsonDoor.isOpen()) {
				door.openDoor();
			}
			cell[point.x][point.y].setElement(door);
			
		}
		
		
	}
	
	
	public static void setupStairs(JsonLayout jsonLayout,Layout layout) {
		Cell[][] cell =layout.getCells();
		List<JsonStair> stair=jsonLayout.getStair();
		
		
		for(int j=0;j< stair.size();j++) {
			JsonStair jsonStair =stair.get(j);
			Point point=jsonStair.getPoint();
			cell[point.x][point.y].setElement(new Stairs());
		
		}
		
		
		
	}
	
	
	public static void addEdge(Graph graph,boolean[][] path) {
		for(int i=0;i<path.length;i++) {
			for(int j=0;j<path.length;j++) {
				if(path[i][j]) {
				 try 
				 {
					if(path[i][j-1]) graph.addedge((j*10)+i, ((j*10)+i)-10);
					if(path[i][j+1]) graph.addedge((j*10)+i, ((j*10)+i)+10);
					if(path[i-1][j]) graph.addedge((j*10)+i,((j*10)+i)-1);
					if(path[i+1][j]) graph.addedge((j*10)+i,((j*10)+i)+1);
					
				 }catch(ArrayIndexOutOfBoundsException e) {
					 
				 }
				}
				
			}
			
		}
		
	}
	
	
	public static ArrayList<Integer> max(Point point1,Point point2,Point point3) {
			
		ArrayList<Integer> x=new ArrayList<>();
		x.add(point1.x);
		x.add(point2.x);
		x.add(point3.x);
		
		ArrayList<Integer> y=new ArrayList<>();
		y.add(point1.y);
		y.add(point2.y);
		y.add(point3.y);
		
		int maxX=x.get(0);
		for(int i:x) {
			if(maxX<i) {
				maxX=i;
			}
		}
		
		int maxY=y.get(0);
		for(int i:y) {
			if(maxY<i) {
				maxY=i;
			}
		}
		
		ArrayList<Integer>list=new ArrayList<>();
		list.add(maxX);
		list.add(maxY);
		
		return list;
		
	}
	
	
	public static ArrayList<Integer>  min(Point point1,Point point2,Point point3) {
		
		ArrayList<Integer> x=new ArrayList<>();
		x.add(point1.x);
		x.add(point2.x);
		x.add(point3.x);
		
		ArrayList<Integer> y=new ArrayList<>();
		y.add(point1.y);
		y.add(point2.y);
		y.add(point3.y);
		
		int minX=x.get(0);
		for(int i:x) {
			if(minX>i) {
				minX=i;
			}
		}
		
		int minY=y.get(0);
		for(int i:y) {
			if(minY>i) {
				minY=i;
			}
		}
		
		ArrayList<Integer>list=new ArrayList<>();
		list.add(minX);
		list.add(minY);
		
		return list;

		
	}
	
	public static void TileFloor(JsonLayout jsonLayout,Layout layout) {
		
		List<JsonTile> jsonTiles=jsonLayout.getTile();
		Cell[][] cell =layout.getCells();
		for(JsonTile t:jsonTiles) {
			Point point1=t.getPoint1();
			Point point2=t.getPoint2();
			Point point3=t.getPoint3();
			
			List<Integer> maxValues=max(point1,point2,point3);
			List<Integer> minValues=min(point1,point2,point3);
			
			int xMax=maxValues.get(0);
			int xMin=minValues.get(0);
			
			int yMax=maxValues.get(1);
			int yMin=minValues.get(1);
			
			TileType tileType=t.getTileType();
			
			for(int x=xMin;x<xMax;x++) {
				for(int y=yMin;y<yMax;y++) {
					Tile tile =new Tile();
					tile.setTileType(tileType);
					cell[x][y].setElement(tile);
					
					
				}
			}
			
			
		}
		
	}
	
	
	public static void setChargingPoints(JsonLayout jsonLayout,Layout layout,Cleaner cleaner) {
		List<JsonChargingPoint> chargingStation=jsonLayout.getChargingPoint();
		HashSet<Integer> chargingPoints=new HashSet<>();
		for(JsonChargingPoint i:chargingStation) {
			Cell[][] cell =layout.getCells();
			int x=i.getPoint().x;
			int y=i.getPoint().y;
			
			//int cellnumber=(10*y)+x;
			
			cell[x][y].setElement(new ChargingStation());
			chargingPoints.add((10*y)+x);
			
			
		}
		
		cleaner.setChargingpoints(chargingPoints);
	}

}
