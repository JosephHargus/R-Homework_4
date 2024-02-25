package hw4.vehicles;

public class SUV extends Vehicle{

	//constructor with all Vehicle attributes as parameters
	public SUV(String brand, String make, long modelYear, double price, VehicleColor color, FuelType fuelType,
			double mileage, double mass, int cylinders, double gasTankCapacity, StartMechanism startType) {
		super(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
	}
	
	//copy constructor with only an SUV object as parameter
	public SUV(SUV suv) {
		super(suv);
	}

	//overridden methods
	//each of the below three methods are explained in detail in the super class Vehicle
	@Override
	public double calculateMaintenanceCost(double distance) {
		double maintenanceCost = distance * mass  * (2024 - modelYear) * cylinders * 0.001;
		return maintenanceCost;
	}

	@Override
	public double calculateFuelEfficiency(double distance, double fuelPrice) {
		double fuelEfficiency = cylinders *  gasTankCapacity * fuelPrice /  distance * 0.05;
		return fuelEfficiency;
	}

	@Override
	public void startEngine() {
		System.out.println("The SUV engine starts using PUSHSTART");
	}

}
