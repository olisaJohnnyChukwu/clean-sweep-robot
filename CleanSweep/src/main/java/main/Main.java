package main;

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
import java.util.List;
import java.util.Queue;

import com.google.gson.Gson;

import cell.Cell;
import element.Door;
import element.Element;
import element.Stairs;
import element.Tile;
import element.TileType;
import element.Wall;
import graph.Graph;
import layout.JsonDoor;
import layout.JsonLayout;
import layout.JsonStair;
import layout.JsonWall;
import layout.Layout;
import robot.Cleaner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//load layout files from json
		FileInputStream fileInputStream= new FileInputStream(new File("layoutElements.json"));
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
		
		
		Graph graph=new Graph();
		
		boolean [][] path=layout.passAbleMatrix();// matrix of coonected paths
			
		
		
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
		
		 

		
		
		
	
		
		Point startPoint=cleaner.getPoint();
		int cellNumber=(startPoint.y*10)+startPoint.x;
	
		
		Queue<Integer> queue=graph.dfs(graph, cellNumber);
		
		
		cleaner.move(queue);
		 
		
		
		

	}
	
	
	public static void populateFloor(Cell[][] cell) {
		
		int x=0;
		for(int i=0;i<cell.length;i++) {
			for(int j=0;j<cell.length;j++) {
				cell[i][j]=new Cell();
				cell[i][j].setPoint(new Point(i,j));
				if(cell[i][j]!=null) {
					Tile tile =new Tile();
					tile.setTileType(TileType.Barefloor);
					cell[i][j].setElement(new Tile());
					cell[i][j].setNumber(x);
					x=x+1;
				}
				
				
				
			}
		}
	}
	
	
	public static void buildWalls(JsonLayout jsonLayout,Layout layout) {
		Cell[][] cell =layout.getCells();
		List<JsonWall> walls=jsonLayout.getWalls();
		
		
		for(int j=0;j<walls.size();j++) {
			JsonWall JsonWall=walls.get(j);
			Point point1=JsonWall.getPoint1();
			Point point2=JsonWall.getPoint2();
			Point point3=JsonWall.getPoint3();
			
			if(point1.x==point2.x && point1.y<point2.y) {
				for(int i=point1.y;i<=point2.y;i++) {
					cell[point2.x][i].setElement(new Wall());
				}
			}
			
			if(point2.x>point3.x && point2.y==point3.y) {
				for(int i=point3.x;i<=point2.x;i++) {
					cell[i][point2.y].setElement(new Wall());
				}
			}
			
			
			if(point2.x<point3.x && point2.y==point3.y) {
				for(int i=point2.x;i<=point3.x;i++) {
					cell[i][point2.y].setElement(new Wall());
				}
			}
			
			if(point1.x<point2.x && point1.y==point2.y) {
				for(int i=point1.x;i<=point2.x;i++) {
					cell[i][point1.y].setElement(new Wall());
				}
			}
			
			
			if(point2.x==point3.x && point2.y<point3.y) {
				for(int i=point1.y;i<=point2.y;i++) {
					cell[point2.x][i].setElement(new Wall());
				}
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
			//door.openDoor();
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

}
