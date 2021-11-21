package element;

import java.io.PrintStream;

import robot.Cleaner;

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
	
	
	public void rechargeElement(Cleaner cleaner,String log) {
		log+="Cleaner Charging....."+"\n";
		System.out.println("Cleaner Charging.....");
		cleaner.setPower(250);
		log+="Cleaner Recharged"+"\n";
		System.out.println("Cleaner Recharged");
		System.out.println("........");
		System.out.println("........");
		System.out.println("........");
		log+="......"+"\n";
		
		
	}
	
}
