package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.gson.Gson;

import cell.Cell;
import element.Element;
import element.Tile;
import element.TileType;
import graph.Graph;
import junit.framework.Assert;
import layout.JsonChargingPoint;
import layout.JsonDoor;
import layout.JsonLayout;
import layout.JsonStair;
import layout.JsonTile;
import layout.JsonWall;
import layout.Layout;
import robot.Cleaner;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    
    @Test
    public void buildWalls() throws IOException {
    	
    	JsonLayout jsonLayout =new JsonLayout();
		
		JsonWall Wall1=new JsonWall(new Point(0,0),new Point(0,3),new Point(3,3),new Point(3,0));
		List<JsonWall> walls =new ArrayList<JsonWall>();
		walls.add(Wall1);
		
		Layout layout=new Layout();
		Cell[][] cell =new Cell[10][10]; // set floor grid
		App.populateFloor(cell);//tile all the grids
		layout.setCells(cell);//add the cells to the layout
		
		
		JsonTile tile=new JsonTile(TileType.Highpile,new Point(0,0),new Point(0,3),new Point(3,3),new Point(3,0));
		List<JsonTile> tiles=new ArrayList<JsonTile>();
		tiles.add(tile);
		
		jsonLayout.setWalls(walls);
		jsonLayout.setTile(tiles);
		
		App.buildWalls(jsonLayout,layout);
		
		
		
		assertEquals(layout.getCells()[3][0].getCellType(),"Wall");
		assertEquals(layout.getCells()[3][1].getCellType(),"Wall");
		assertEquals(layout.getCells()[3][2].getCellType(),"Wall");
		assertEquals(layout.getCells()[3][3].getCellType(),"Wall");
		assertEquals(layout.getCells()[0][3].getCellType(),"Wall");
		assertEquals(layout.getCells()[1][3].getCellType(),"Wall");
		assertEquals(layout.getCells()[3][3].getCellType(),"Wall");
		
		
		
		
    }
    
    @Test
   public void chargingPoints() {
    	
    	
    	JsonLayout jsonLayout=new JsonLayout();
    	List<JsonChargingPoint> JsonChargingPoints=new ArrayList<>();
		JsonChargingPoint jcp=new JsonChargingPoint ();
		jcp.setPoint(new Point(0,7));
		JsonChargingPoints.add(jcp);
		
		JsonChargingPoint jcp2=new JsonChargingPoint ();
		jcp2.setPoint(new Point(9,7));
		JsonChargingPoints.add(jcp2);
		jsonLayout.setChargingPoint(JsonChargingPoints);
		
		Layout layout=new Layout();
		
		Cell[][] cell =new Cell[10][10];
		layout.setCells(cell);
		App.populateFloor(cell);//tile all the grids
		
		Cleaner cleaner=new Cleaner(null);
		
		App.setChargingPoints(jsonLayout,layout,cleaner);
		
		
		HashSet<Integer> set=(HashSet<Integer>) jsonLayout.getChargingPoint().stream().
				map(cp->cp.getPoint().x*10+cp.getPoint().y).collect(Collectors.toSet());
		System.out.println(set);
		System.out.println(cleaner.getChargingpoints());
		
		boolean test=cleaner.getChargingpoints().toString().equals(set.toString());
		
		
		assertEquals(cleaner.getChargingpoints(), set);
		
		
   }
    
    
    @Test
    public  void getNumber() {
        Cell c = new Cell();
        c.setNumber(1);
        assertEquals(1,c.getNumber());
    }


    @Test
    public  void getCellType() {
        Element e = new Tile();
        Cell c = new Cell();
        c.setElement(e);
        assertEquals("Tile", c.getCellType());
    }

    @Test
    public  void passable() {
        Element t = new Tile();
        Cell c = new Cell();
        c.setElement(t);
        assertEquals(true, c.passable());
    }

    @Test
    public  void getTileType() {
        Tile t = new Tile();
        t.setTileType(TileType.Barefloor);
        Cell c = new Cell();
        c.setElement(t);
        assertEquals(TileType.Barefloor, c.getTileType());
    }
    
    @Test
    public void neighbours() {
        Graph g = new Graph();
        g.addedge(1, 2);
        HashSet<Integer> s = g.neighbours(1);
        Object []result = s.toArray();
        assertEquals(2, result[0]);
    }




    @Test
    public void getPath() {
        //path with depth first search
        Graph graph = new Graph();
        graph.addedge(1, 2);
        graph.addedge(3,4);
        graph.addedge(5,6);
        graph.addedge(2,6);
        graph.addedge(6,7);


        System.out.println(graph.getPath(2, 7).getClass().getName());
        //Last-In-First-Out
        Stack<Integer> s = new Stack<>();
        s.push(7);
        s.push(6);
        s.push(2);



        assertEquals(s, graph.getPath(2,7));

    }
    @Test
    public void shortestPathTest(){
        //path with depth first search
        Graph graph = new Graph();

        graph.addedge(3,4);
        graph.addedge(5,6);
        graph.addedge(2,6);
        graph.addedge(7,8);
        graph.addedge(6,8);

        System.out.println(graph.getPath(2, 7).getClass().getName());
        //Last-In-First-Out
        Stack<Integer> s = new Stack<>();
        s.push(8);
        s.push(6);
        s.push(2);



        assertEquals(s, graph.getPath(2,8));
    }
    
    
    @Test 
    public void dontFindpathtoCellWithCloseddoor(){
    	JsonLayout jsonLayout =new JsonLayout();
		
		JsonWall Wall1=new JsonWall(new Point(0,0),new Point(0,3),new Point(3,3),new Point(3,0));
		
		JsonWall Wall2=new JsonWall(new Point(5,5),new Point(5,9),new Point(9,9),new Point(9,5));
		
		List<JsonWall> walls =new ArrayList<JsonWall>();
		walls.add(Wall1);
		walls.add(Wall2);
		
		
		JsonDoor door=new JsonDoor (new Point(3,1));
		door.setOpen(false);
		JsonDoor door2=new JsonDoor (new Point(6,5));
		door2.setOpen(true);
		
		
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
		
		jsonLayout.setDoor(doors);
		jsonLayout.setWalls(walls);
		jsonLayout.setStair(stairs);
		jsonLayout.setTile(tiles);
		
		Layout layout=new Layout();
		Cell[][] cell =new Cell[10][10]; // set floor grid
		App.populateFloor(cell);//tile all the grids
		
		layout.setCells(cell);//add the cells to the layout
		
		
		App.buildWalls(jsonLayout,layout);//set up walls
		App.setupDoors(jsonLayout,layout);//set up doors
		App.setupStairs(jsonLayout,layout);//set up stairs
		App.TileFloor(jsonLayout,layout);
		
		
		Graph graph=new Graph();
		
		boolean [][] path=layout.passAbleMatrix();// matrix of coonected paths
		App.addEdge(graph,path);//add connected vertexes to graph
		
		
		Queue<Integer> queue=graph.dfs(graph, 4);
		
		
		boolean doesNotContainCellwithdoorClosed=queue.contains(12);
		
		
		
		boolean containsCellwithDoorOpen=queue.contains(78);
		
		assertFalse(doesNotContainCellwithdoorClosed);
		assertTrue(containsCellwithDoorOpen);
		
		
    }
    
}
