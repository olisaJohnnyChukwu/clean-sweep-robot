package CleanSweep.src.main.java.element;

import CleanSweep.src.main.java.robot.Cleaner;

public class ChargingStation implements Element{
	
	

	@Override
	public boolean passable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String printElement() {
		// TODO Auto-generated method stub
		return "Charging Point";
	}
	
	
	public void rechargeElement(Cleaner cleaner) {
		System.out.println("Cleaner Charging.....");
		cleaner.setPower(250);
		System.out.println("Cleaner Recharged");
		System.out.println("........");
		System.out.println("........");
		System.out.println("........");
	}
	
}
