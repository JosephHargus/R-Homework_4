package hw4.vehicles;

public class MotorBike extends Vehicle{

	public MotorBike(String brand, String make, long modelYear, double price, VehicleColor color, FuelType fuelType,
			double mileage, double mass, int cylinders, double gasTankCapacity, StartMechanism startType) {
		super(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
	}
	
	//copy constructor
	public MotorBike(MotorBike motorBike) {
		super(motorBike);
	}

	//overridden methods
	@Override
	public double calculateMaintenanceCost(double distance) {
		double maintenanceCost = distance * mass  * (2024 - modelYear) *  cylinders * 0.0002;
		return maintenanceCost;
	}

	@Override
	public double calculateFuelEfficiency(double distance, double fuelPrice) {
		double fuelEfficiency = cylinders *  gasTankCapacity * fuelPrice /  distance * 0.001;
		return fuelEfficiency;
	}

	@Override
	public void startEngine() {
		System.out.println("The MotorBike engine starts using KICKSTART");
	}

}
