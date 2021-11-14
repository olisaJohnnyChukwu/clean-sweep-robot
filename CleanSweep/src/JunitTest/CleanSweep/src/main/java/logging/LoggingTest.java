package CleanSweep.src.main.java.logging;

import element.Tile;
import element.TileType;
import layout.JsonTile;
import logging.Logging;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LoggingTest {


    @Test
    void logInternalMap() throws IOException {

        String data = "";

        Logging i = new Logging();

        Point a = new Point(1,2);
        Point b = new Point(3,4);
        Point c = new Point(5,6);
        JsonTile j = new JsonTile(TileType.Lowpile,a,b,c);

        Map<Integer, Tile> m = new HashMap<Integer, Tile>();
        Tile t = new Tile();

        t.setTileType(TileType.Barefloor);
        m.put(1,t);
        i.logInternalMap(m);


            File file = new File("/Users/mmm/Documents/Fall2021/se359/nowWorkPlace/clean-sweep-robot/tracking/afterCleaning.txt");
           // File file = new File("tracking/afterCleaning.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String sr ="";
        while ((st = br.readLine()) != null){
            sr += st;
            // Print the string
            System.out.println(st); }

        assertEquals("Tile: x=1.0 y=2.0 ss=Barefloor ds=Barefloor",sr);


    }
}