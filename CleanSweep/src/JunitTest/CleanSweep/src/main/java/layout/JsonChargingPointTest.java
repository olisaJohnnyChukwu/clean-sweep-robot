package CleanSweep.src.main.java.layout;

import layout.JsonChargingPoint;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonChargingPointTest {

    @Test
    void getPoint() {
        JsonChargingPoint j = new JsonChargingPoint();
        j.setPoint(new Point(1,3));
        Point x = new Point(1,3);
        assertEquals(x, j.getPoint() );

        JsonChargingPoint j1 = new JsonChargingPoint();
        j1.setPoint(new Point(15, 10));
        Point x1 = new Point(15,10);
        assertEquals(x1, j1.getPoint() );

        JsonChargingPoint j2 = new JsonChargingPoint();
        j2.setPoint(new Point(8, 8));
        Point x2 = new Point(8,8);
        assertEquals(x2, j2.getPoint() );
    }

}