package element;

public class Tile implements Element {
	TileType TileType;
	boolean clean=true;
	
	
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
	
	public void cleanFloor() {
		clean=true;
	}
	
	public  void DirtyFloor() {
		clean=false;
	}

	public boolean isClean() {
		return clean;
	}

	@Override
	public boolean cleanable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	

}
