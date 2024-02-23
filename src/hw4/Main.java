package hw4;

import java.util.ArrayList;

import hw4.vehicles.Car;
import hw4.vehicles.FuelType;
import hw4.vehicles.StartMechanism;
import hw4.vehicles.Vehicle;
import hw4.vehicles.VehicleColor;
import hw4.vehicles.VehicleManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//instantiate a new vehicleManager
		VehicleManager vm = VehicleManager.getInstance("vehicleList.csv");
		
		//create, add, and save a new car
		Car myCar = new Car("BMW", "328i", 2013, 12000.0, VehicleColor.GRAY, FuelType.GASOLINE, 130000.0, 2.5, 12, 15.0, StartMechanism.PUSHSTART);
		vm.addVehicle(myCar);
		vm.saveVehicleList();
		vm.displayAllVehicleInformation();
		
		//display the vehicle with the worst fuel efficiency
		System.out.println("The vehicle(s) with the worst fuel efficiency:\n");
		ArrayList<Vehicle> vehicles = vm.getVehicleWithLowestFuelEfficiency(300, 3.25);
		for(Vehicle vehicle : vehicles)
		{
			System.out.println(vehicle);
		}
		
		//calculate the average fuel efficiency
		System.out.println("\nAverage fuel efficiency: " + vm.getAverageFuelEfficiencyOfSUVs(300, 3.25) + "\n");
		
		//display information about all the MotorBikes
		vm.displayAllMotorBikeInformation();
		
		//remove and save the new car
		if(vm.removeVehicle(myCar))
			System.out.println("Joe's BMW successfully removed.");
		else
			System.out.println("Joe's BMW was not removed.");
		vm.saveVehicleList();

	}

}
