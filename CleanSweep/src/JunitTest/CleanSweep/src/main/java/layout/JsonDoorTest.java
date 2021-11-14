package CleanSweep.src.main.java.layout;

import layout.JsonDoor;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonDoorTest {

    @Test
    void getPoint1() {
        Point x = new Point(1,2);
        JsonDoor j = new JsonDoor(x);
        assertEquals(x, j.getPoint1());

    }

    @Test
    void isOpen() {
        Point x = new Point(1,2);
        JsonDoor j = new JsonDoor(x);
        j.setOpen(true);
        boolean b = j.isOpen();
        assertTrue(b);
    }
}