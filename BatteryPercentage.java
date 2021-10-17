

public class BatteryPercentage {
	private double CurrentBatteryPercentage;
	private double LowBatteryPercentage = 15;
	
	public double GetBatteryPercentage;{
		return CurrentBatteryPercentage;
	}
	public double LowBattery;{
		if(CurrentBatteryPercentage <= LowBatteryPercentage) {
			System.out.println("Low Battery Warning: " + CurrentBatteryPercentage);
		}
	}
	

}
