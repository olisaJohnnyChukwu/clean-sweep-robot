package element;

public class Tile implements Element {
	TileType TileType;
	
	
	public boolean passable() {
		// TODO Auto-generated method stub
		return true;
	}

	public String printElement() {
		// TODO Auto-generated method stub
		return "Tile";
	}

	public TileType getTileType() {
		return TileType;
	}

	public void setTileType(TileType tileType) {
		TileType = tileType;
	}
	
	
	

}
