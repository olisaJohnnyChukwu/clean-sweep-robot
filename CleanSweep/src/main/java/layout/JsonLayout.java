package layout;

import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import element.TileType;

public class JsonLayout {
	List<JsonWall> walls=new ArrayList<JsonWall>();
	List<JsonDoor> door=new ArrayList<JsonDoor>();
	List<JsonStair> stair=new ArrayList<JsonStair>();
	List<JsonTile> tile=new ArrayList<JsonTile>();
	List<JsonChargingPoint> chargingPoint =new ArrayList<>();
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
		
		
		
		
		
		JsonLayout jsonLayout =new JsonLayout();
		
		JsonWall Wall1=new JsonWall(new Point(0,0),new Point(0,3),new Point(3,3),new Point(3,0));
		
		JsonWall Wall2=new JsonWall(new Point(5,5),new Point(5,9),new Point(9,9),new Point(9,5));
		
		
		
		
		List<JsonWall> walls =new ArrayList<JsonWall>();
		walls.add(Wall1);
		walls.add(Wall2);
		
		
		JsonDoor door=new JsonDoor (new Point(3,1));
		door.setOpen(true);
		JsonDoor door2=new JsonDoor (new Point(6,5));
		door2.setOpen(false);
		
		
		List<JsonDoor> doors= new ArrayList<JsonDoor>();
		doors.add(door);
		doors.add(door2);
		
		
		JsonStair stair = new JsonStair (new Point(5,9));
		List<JsonStair> stairs= new ArrayList<JsonStair>();
		stairs.add(stair);
		
		JsonTile tile=new JsonTile(TileType.Highpile,new Point(0,0),new Point(0,3),new Point(3,3),new Point(3,0));
		JsonTile tile2=new JsonTile(TileType.Lowpile,new Point(5,5),new Point(5,9),new Point(9,9),new Point(9,5));
		List<JsonTile> tiles=new ArrayList<JsonTile>();
		tiles.add(tile);
		tiles.add(tile2);
		
		
		
		
		
		
		
		List<JsonChargingPoint> JsonChargingPoints=new ArrayList<>();
		JsonChargingPoint jcp=new JsonChargingPoint ();
		jcp.setPoint(new Point(0,7));
		JsonChargingPoints.add(jcp);
		
		JsonChargingPoint jcp2=new JsonChargingPoint ();
		jcp2.setPoint(new Point(9,7));
		JsonChargingPoints.add(jcp2);
		
		
		
		
		
		jsonLayout.setDoor(doors);
		jsonLayout.setWalls(walls);
		jsonLayout.setStair(stairs);
		jsonLayout.setTile(tiles);
		jsonLayout.setChargingPoint(JsonChargingPoints);
		
		jsonLayout.setStartingPoint(new Point(0,5));
		
		
		
		
		
		
		
		
		
		
		 
		 String json = gson.toJson(jsonLayout);
		 
		 
		 File file=new File("floorplan2.json");
		 
		 
		 
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




	public List<JsonChargingPoint> getChargingPoint() {
		return chargingPoint;
	}




	public void setChargingPoint(List<JsonChargingPoint> chargingPoint) {
		this.chargingPoint = chargingPoint;
	}
	
	
	

	

	
	
	
	

}
