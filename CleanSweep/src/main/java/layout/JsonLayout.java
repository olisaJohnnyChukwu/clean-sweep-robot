package layout;

import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonLayout {
	List<JsonWall> walls=new ArrayList<JsonWall>();
	List<JsonDoor> door=new ArrayList<JsonDoor>();
	List<JsonStair> stair=new ArrayList<JsonStair>();
	List<JsonTile> tile=new ArrayList<JsonTile>();
	Point startingPoint;
	
	
	
	
	
	public Point getStartingPoint() {
		return startingPoint;
	}




	public void setStartingPoint(Point startingPoint) {
		this.startingPoint = startingPoint;
	}

	


	public List<JsonTile> getTile() {
		return tile;
	}




	public void setTile(List<JsonTile> tile) {
		this.tile = tile;
	}




	public List<JsonDoor> getDoor() {
		return door;
	}




	public void setDoor(List<JsonDoor> door) {
		this.door = door;
	}




	public static void main(String[] args) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Point point1=new Point(3,0);
		
		Point point2=new Point(3,3);
		
		Point point3=new Point(0,3);
		
		Point point4=new Point(6,0);
		Point point5=new Point(6,3);
		Point point6=new Point(9,3);
		
		JsonLayout jsonLayout =new JsonLayout();
		
		JsonWall Wall1=new JsonWall(point1,point2,point3);
		JsonWall Wall2=new JsonWall(point4,point5,point6);
		List<JsonWall> walls =new ArrayList<JsonWall>();
		walls.add(Wall1);
		walls.add(Wall2);
		
		JsonDoor door=new JsonDoor (new Point(3,1));
		JsonDoor door2=new JsonDoor (new Point(8,3));
		
		List<JsonDoor> doors= new ArrayList<JsonDoor>();
		doors.add(door);
		doors.add(door2);
		
		JsonStair stair = new JsonStair (new Point(5,9));
		List<JsonStair> stairs= new ArrayList<JsonStair>();
		stairs.add(stair);
		
		List<JsonTile> tiles=new ArrayList<JsonTile>();
		JsonTile tile=new JsonTile();
		
		
		
		jsonLayout.setDoor(doors);
		jsonLayout.setWalls(walls);
		jsonLayout.setStair(stairs);
		
		jsonLayout.setStartingPoint(new Point(0,5));
		
		
		
		
		
		
		
		
		
		
		 
		 String json = gson.toJson(jsonLayout);
		 
		 
		 File file=new File("layoutElements.json");
		 
		 
		 
		 if(file.isFile()) {
	 			FileOutputStream fileOutputStream =new FileOutputStream (file);
	 			PrintStream printstream=new PrintStream(fileOutputStream);
	 			printstream.println(json);
	 			
	 			
	 		}else {
	 			
	 			file.createNewFile();
	 			FileOutputStream fileOutputStream =new FileOutputStream (file);
	 			PrintStream printstream=new PrintStream(fileOutputStream);
	 			printstream.println(json);
	 			
	 			}
		 
		 
		
		
	}




	public List<JsonStair> getStair() {
		return stair;
	}




	public void setStair(List<JsonStair> stair) {
		this.stair = stair;
	}




	public List<JsonWall> getWalls() {
		return walls;
	}




	public void setWalls(List<JsonWall> walls) {
		this.walls = walls;
	}
	
	
	



	
	
	
	

}
