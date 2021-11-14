package CleanSweep.src.main.java.layout;

import element.TileType;
import layout.JsonDoor;
import layout.JsonLayout;
import layout.JsonStair;
import layout.JsonTile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonLayoutTest {

    @Test
    void getStartingPoint() {
        Point x = new Point(1,5);
        JsonLayout j = new JsonLayout();
        j.setStartingPoint(new Point(1,5));
        assertEquals(x,j.getStartingPoint());
    }


    @Test
    void getTile() {
        Point a = new Point(1,2);
        Point b = new Point(3,4);
        Point c = new Point(5,6);
        JsonTile j = new JsonTile(TileType.Lowpile,a,b,c);
        List<JsonTile> l = new ArrayList<>();
        l.add(j);
        JsonLayout layout = new JsonLayout();
        layout.setTile(l);
        System.out.println(layout.getTile().get(0).getPoint1());
        System.out.println(layout.getTile().get(0).getTileType());
        assertEquals(j, layout.getTile().get(0));

    }


    @Test
    void getDoor() {
        Point a = new Point(1,2);

        JsonDoor j = new JsonDoor(a);
        List<JsonDoor> l = new ArrayList<>();
        l.add(j);
        JsonLayout layout = new JsonLayout();
        layout.setDoor(l);

        assertEquals(j, layout.getDoor().get(0));
    }


    @Test
    void getStair() {
        Point a = new Point(1,2);

        JsonStair j = new JsonStair(a);
        List<JsonStair> l = new ArrayList<>();
        l.add(j);
        JsonLayout layout = new JsonLayout();
        layout.setStair(l);

        assertEquals(j, layout.getStair().get(0));
    }


}