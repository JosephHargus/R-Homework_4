package hw4.vehicles;

public class Truck extends Vehicle{

	//constructor with all Vehicle attributes as parameters
	public Truck(String brand, String make, long modelYear, double price, VehicleColor color, FuelType fuelType,
			double mileage, double mass, int cylinders, double gasTankCapacity, StartMechanism startType) {
		super(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
	}
	
	//copy constructor with a Truck object as parameter
	public Truck(Truck truck) {
		super(truck);
	}

	//overridden methods
	//each of the below three methods are explained in detail in the super class Vehicle
	@Override
	public double calculateMaintenanceCost(double distance) {
		double maintenanceCost = distance * mass * (2024 - modelYear) *  cylinders * 0.002;
		return maintenanceCost;
	}

	@Override
	public double calculateFuelEfficiency(double distance, double fuelPrice) {
		double fuelEfficiency = cylinders *  gasTankCapacity * fuelPrice /  distance * 0.1;
		return fuelEfficiency;
	}

	@Override
	public void startEngine() {
		System.out.println("The truck engine starts using KEYSTART");
	}
	
	

}
