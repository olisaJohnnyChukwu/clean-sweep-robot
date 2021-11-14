package CleanSweep.src.main.java.cell;

import cell.Cell;
import element.Element;
import element.Tile;
import element.TileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void getNumber() {
        Cell c = new Cell();
        c.setNumber(1);
        assertEquals(1,c.getNumber());
    }


    @Test
    void getCellType() {
        Element e = new Tile();
        Cell c = new Cell();
        c.setElement(e);
        assertEquals("Tile", c.getCellType());
    }

    @Test
    void passable() {
        Element t = new Tile();
        Cell c = new Cell();
        c.setElement(t);
        assertEquals(true, c.passable());
    }

    @Test
    void getTileType() {
        Tile t = new Tile();
        t.setTileType(TileType.Barefloor);
        Cell c = new Cell();
        c.setElement(t);
        assertEquals(TileType.Barefloor, c.getTileType());
    }
}