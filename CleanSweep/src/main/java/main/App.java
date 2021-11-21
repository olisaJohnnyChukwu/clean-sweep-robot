package main;

import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.Point;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import cell.*;
import element.*;
import layout.*;
import robot.*;
import graph.*;





public class App 
{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//load layout files from json
		
		
		String floorplan1="floorplan1.json";
		String floorplan2="floorplan2.json";
		
		Scanner scanner=new Scanner(System.in);
		
		String userInput=floorplan1;
		
		System.out.println("Enter the 1 for for floorplan 1");
		System.out.println("Enter the 2 for for floorplan 2");
		
		int choice=scanner.nextInt();
		
		if(choice==1) {
			userInput=floorplan1;
		}else {
			userInput=floorplan2;
		}
		
		FileInputStream fileInputStream= new FileInputStream(new File(userInput));
		BufferedReader bufferedReader =new BufferedReader (new InputStreamReader(fileInputStream));
		
		
		FileOutputStream fileOutputStream=new FileOutputStream(new File("log.txt"));
		PrintStream printStream=new PrintStream(fileOutputStream);
		
		//PrintStream printStream=null;
		
		String log=" ";
		log+=userInput+"\n";
		//create json string
		String json="";
		String fromInput="";
		while((fromInput=bufferedReader.readLine())!=null) {
			json+=fromInput +"\n";
			log+=fromInput +"\n";
		}
		
		
		System.out.println(json);
		
		Gson gson = new Gson();
		//retrieve the layout
		 
		
		JsonLayout jsonLayout =gson.fromJson(json,JsonLayout.class);//get layout plan
		
		Layout layout=new Layout();
		Cell[][] cell =new Cell[10][10]; // set floor grid
		populateFloor(cell);//tile all the grids
		
		layout.setCells(cell);//add the cells to the layout
		
		Cleaner cleaner=new Cleaner(log);//start robot
		
		cleaner.setPoint(jsonLayout.getStartingPoint());//set the starting point
		
		
		
		
		cleaner.setLayout(layout);//cleaner accepts the floor plans
		
		buildWalls(jsonLayout,layout);//set up walls
		setupDoors(jsonLayout,layout);//set up doors
		setupStairs(jsonLayout,layout);//set up stairs
		TileFloor(jsonLayout,layout);
		
		
		
		setChargingPoints(jsonLayout,layout, cleaner);//
		
		
		Graph graph=new Graph();
		
		boolean [][] path=layout.passAbleMatrix();// matrix of coonected paths
		
		
		
		
			
		Gson gson2 = new Gson();
		String matrix=gson.toJson(layout.passAble());
		System.out.println(matrix);
		
		System.out.println(cleaner.getChargingpoints());
		
		
		/*HashSet<Integer> set=(HashSet<Integer>) jsonLayout.getChargingPoint().stream().
				map(cp->cp.getPoint().y*10+cp.getPoint().x).collect(Collectors.toSet());
		System.out.println(set);*/
		
		addEdge(graph,path);//add connected vertexes to graph
		
		
		Point startPoint=cleaner.getPoint();
		int cellNumber=(startPoint.y*10)+startPoint.x;
		
		
		
		
		
		
		cleaner.move(graph,cellNumber);
		
		
		printStream.println(cleaner.getLog());
		
		

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
			JsonWall jsonWall=walls.get(j);
			Point point1=jsonWall.getPoint1();
			Point point2=jsonWall.getPoint2();
			Point point3=jsonWall.getPoint3();
			Point point4=jsonWall.getPoint4();
			
			
			for(int y=point1.y;y<=point2.y;y++) {
				cell[point1.x][y].setElement(new Wall());
				
			}
			
			for(int x=point2.x;x<=point3.x;x++) {
				cell[x][point2.y].setElement(new Wall());
			}
			
			
			for(int y=point3.y;y>=point4.y;y--) {
				cell[point3.x][y].setElement(new Wall());
				
			}
			
			for(int x=point4.x;x>=point1.x;x--) {
				cell[x][point4.y].setElement(new Wall());
			}
				
				
				
		}
		
		
		
		
		
		
		
	}
	
	
	public static int slope(Point point1,Point point2) {
		System.out.println((point1.y-point2.y)/(point1.x-point2.x));
		return (point1.y-point2.y)/(point1.x-point2.x);
		
	};
	
	public static int intercept(Point point1,int slope) {
		System.out.println(point1.y-(slope*point1.x));
		return point1.y-(slope*point1.x);
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
		int length=path.length;
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
	
	
	
	
	public static void TileFloor(JsonLayout jsonLayout,Layout layout) {
		
		List<JsonTile> jsonTiles=jsonLayout.getTile();
		Cell[][] cell =layout.getCells();
		for(JsonTile t:jsonTiles) {
				TileType type=t.getTileType();
				Point point1=t.getPoint1();
				Point point2=t.getPoint2();
				Point point3=t.getPoint3();
				Point point4=t.getPoint4();
				
				
				if(point1.x==0) {
					Tile tile=new Tile();
					tile.setTileType(type);
					for(int x=point1.x;x<point4.x;x++) {
						for(int y=point1.y;y<point2.y;y++) {
							cell[x][y].setElement(tile);
						}
					}
				}else {
					Tile tile=new Tile();
					tile.setTileType(type);
					for(int x=point1.x+1;x<=point4.x;x++) {
						for(int y=point1.y+1;y<=point2.y;y++) {
							cell[x][y].setElement(tile);
						}
					}
				}
				
				
				
			}
		
	}
	
	
	public static void setChargingPoints(JsonLayout jsonLayout,Layout layout,Cleaner cleaner) {
		int length=layout.cells.length;
		List<JsonChargingPoint> chargingStation=jsonLayout.getChargingPoint();
		HashSet<Integer> chargingPoints=new HashSet<>();
		for(JsonChargingPoint c:chargingStation) {
			Cell[][] cell =layout.getCells();
			int x=c.getPoint().x;
			int y=c.getPoint().y;
			
			//int cellnumber=(10*y)+x;
			
			cell[x][y].setElement(new ChargingStation());
			chargingPoints.add((10*x)+y);
			
			
			
			
		}
		
		cleaner.setChargingpoints(chargingPoints);
	}
}
