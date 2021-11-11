package element;

public class Door implements Element{
	
	public boolean open=false;
	
	

	public boolean passable() {
		// TODO Auto-generated method stub
		return open;
	}



	public String printElement() {
		// TODO Auto-generated method stub
		return "Door";
	}



	public void openDoor() {
		// TODO Auto-generated method stub
		open=!open;
		
	}



	@Override
	public boolean cleanable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	

}
