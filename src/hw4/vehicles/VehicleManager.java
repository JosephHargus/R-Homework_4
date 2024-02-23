package hw4.vehicles;

import java.util.ArrayList;
import java.util.Random;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class VehicleManager {
	
	private final String vehicleFilePath;
	private ArrayList <Vehicle> vehicleList;
	private static VehicleManager instance;
	
	//Returns the singleton instance for vehicleManager
	//If the instance does not exist, one will be made
	//User must pass in the file path which VehicleManager should be initialized from
	public static VehicleManager getInstance(String filePath)
	{
		//create the instance if it doesn't exist yet
		if(instance == null) {
			instance = new VehicleManager(filePath);
		}
		
		return instance;
	}
	
	//Reads the data from the CSV file stored at vehicleFilePath
	//Initializes each vehicle object in the file
	//Returns true if operation successful
	//Returns false otherwise
	private boolean readFromFile()
	{
		try 
		{
			//open the file
			FileReader fr = new FileReader(this.vehicleFilePath);
			BufferedReader br = new BufferedReader(fr);
			
			//clear the headers from the CSV file
			br.readLine();
			
			//read all lines from the file
			String[] line = new String[12];
			String unsplitLine;
			
			while((unsplitLine = br.readLine()) != null)
			{
				
				line = unsplitLine.split(",");
				
				//check that line is not empty
				//if the second element is empty, line was not split because it was empty
				if(line.length < 12) break;
				
				//read attributes of the vehicle
				String type = line[0].trim();
				String brand = line[1].trim();
				String make = line[2].trim();
				long modelYear = Long.parseLong(line[3].trim());
				double price = Double.parseDouble(line[4].trim());
				VehicleColor color = VehicleColor.valueOf(line[5].trim());
				FuelType fuelType = FuelType.valueOf(line[6].trim());
				double mileage = Double.parseDouble(line[7].trim());
				double mass = Double.parseDouble(line[8].trim());
				int cylinders = Integer.parseInt(line[9].trim());
				double gasTankCap = Double.parseDouble(line[10].trim());
				StartMechanism startType = StartMechanism.valueOf(line[11].trim());
				
				
				//create a vehicle object depending on the type specified in the first column
				switch (type)
				{
				case "Car":
					Car car = new Car(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCap, startType);
					this.vehicleList.add(car);
					break;
				case "Truck":
					Truck truck = new Truck(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCap, startType);
					this.vehicleList.add(truck);
					break;
				case "SUV":
					SUV suv = new SUV(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCap, startType);
					this.vehicleList.add(suv);
					break;
				case "MotorBike":
					MotorBike motorBike = new MotorBike(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCap, startType);
					this.vehicleList.add(motorBike);
					break;
				}
		
			}
			
			//close file and buffered readers
			br.close();
			fr.close();
			
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("The file could not be opened.");
			e.printStackTrace();
			return false;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	//Private constructor for the singleton VehicleManager
	private VehicleManager(String fileName)
	{
		this.vehicleFilePath = fileName;
		this.vehicleList = new ArrayList<Vehicle>();
		if(!this.readFromFile())
		{
			System.out.println("vehicleList could not be initialized. See error message.");
		}
	}
	
	
	//Removes the given vehicle from vehicleList
	//Returns true if successfully removed
	//Returns false otherwise
	public boolean removeVehicle(Vehicle vehicle) 
	{
		if(vehicleList.remove(vehicle))
		{
			//if true, vehicle was successfully removed
			return true;
		}
		return false;
	}
	
	//Adds the given vehicle into vehicleList
	//Returns true if successfully added
	//Returns false otherwise
	public boolean addVehicle(Vehicle vehicle)
	{
		int size = vehicleList.size();
		vehicleList.add(vehicle);
		
		//check that vehicle was successfully added
		if(size == vehicleList.size())
		{
			return false;
		}
		
		return true;
	}
	
	//Saves the updated vehicleList back to the CSV file located at vehicleFilePath
	//Overwrites the existing file with the updated data
	//Returns true if successfully saved
	//Returns false otherwise
	public boolean saveVehicleList()
	{
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(vehicleFilePath));
			
			//write the header line
			bw.write("Type, Model, Make, ModelYear, Price, Color, FuelType, Mileage, Mass, Cylinders, GasTankCapacity, StartType\n");
			
			for (Vehicle vehicle : vehicleList) {
				String line = "";
				
				//get the VehicleType
				if(vehicle instanceof Car)
					line = "Car, ";
				else if(vehicle instanceof SUV)
					line = "SUV, ";
				else if(vehicle instanceof Truck)
					line = "Truck, ";	
				else if(vehicle instanceof MotorBike)
					line = "MotorBike, ";
						
				line = line.concat(String.format("%s, %s, %d, %f, %s, %s, %f, %f, %d, %f, %s\n", vehicle.getBrand(), vehicle.getMake(), vehicle.getModelYear(),
						vehicle.getPrice(), vehicle.getColor().toString(), vehicle.getFuelType().toString(), vehicle.getMileage(), vehicle.getMass(), 
						vehicle.getCylinders(), vehicle.getGasTankCapacity(), vehicle.getStartType().toString()));
				
				bw.write(line); 
			}
			bw.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	//Returns the ArrayList stored at vehicleList
	//this is for testing purposes only. !!we need to delete this from the final submission
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	
	public void displayAllCarInformation()
	{
		   boolean carLocated = false;   //initializes a variable carLocated to false , and checks inventory

		    for (Vehicle vehicle : vehicleList) {
		        if (vehicle instanceof Car) {   
		            carLocated = true;
		            displayVehicleInformation(vehicle);  //calls displayVehicleInformation to display info 
		        }
		    }

		    if (!carLocated) {   //Checks if no cars were found
		        System.out.println("No cars found.");
		    }
		
	}
	
	public void displayAllTruckInformation() {
	    boolean truckLocated = false;   //initializes a variable carLocated to false , and checks inventory


	    for (Vehicle vehicle : vehicleList) {
	        if (vehicle instanceof Truck) { //  // check if the current vehicle is an instance of the Truck class
	            truckLocated = true;
	            displayVehicleInformation(vehicle);
	        }
	    }

	    if (!truckLocated) {
	        System.out.println("No trucks found.");
	    }
	}
	
	public void displayAllSUVInformation() {
	    boolean suvLocated = false;

	    for (Vehicle vehicle : vehicleList) {
	        if (vehicle instanceof SUV) {
	            suvLocated = true;
	            displayVehicleInformation(vehicle);
	        }
	    }

	    if (!suvLocated) {
	        System.out.println("No SUVs found.");
	    }
	}
	

	public void displayAllMotorBikeInformation() {
    boolean motorBikeLocated = false;

    for (Vehicle vehicle : vehicleList) {
        if (vehicle instanceof MotorBike) {
            motorBikeLocated = true;
            displayVehicleInformation(vehicle);
        }
    }

    if (!motorBikeLocated) {
        System.out.println("No motorbikes found in the inventory.");
    }
}

	
	public void displayVehicleInformation(Vehicle v) {
	    if (vehicleList.contains(v)) {
	        System.out.println("Here is the Vehicle Information:");
	        System.out.println(v);
           System.out.printf("Here is the Maintenance Cost: $%.2f%n", v.calculateMaintenanceCost(300)); // inputs given 
	        System.out.printf("Here is the Fuel Efficiency: %.2f%n", v.calculateFuelEfficiency(300, 3.25)); //inputs given
	        v.startEngine();
	        System.out.println();
	    } else {
	        System.out.println("The Vehicle not found.");
	    }
	}
	
	//checks if given vehicle is a specific type of Vehicle subclass
	private boolean isVehicleType(Vehicle v, Class clazz) {
		return (clazz == v.getClass());
	}
	
	//returns number of objects in vehicle list based on the vehicle type of the object
	public int getNumberOfVehiclesByType(Class clazz) {
		int count = 0;
		for (Vehicle v : vehicleList) {
			if (isVehicleType(v, clazz));
			count++;
		}
		return count;
	}
	
	//calculates maintenance cost for each vehicle in vehicle list,
	//returns vehicle with highest maintenance cost. if multiple vehicles
	//cost the same highest maintenance cost, one vehicle is randomly returned.
	public Vehicle getVehicleWithHighestMaintenanceCost(double distance) {
		double max=Double.MIN_VALUE, cost;
		ArrayList<Vehicle> highestMaintenance = new ArrayList<>();
		
		for (Vehicle v : vehicleList) {
			cost = v.calculateMaintenanceCost(distance);
			if (cost > max) {
				max=cost;
				highestMaintenance.clear();
				highestMaintenance.add(v);
			}
			else if (cost == max) {
				highestMaintenance.add(v);
			}
		}
		
		if (highestMaintenance.isEmpty() == false) {
			Random r = new Random();
			int rand_index = r.nextInt(highestMaintenance.size());
			return highestMaintenance.get(rand_index);
		}
		return null;
	}
	
	//calculates maintenance cost for each vehicle in vehicle list,
	//returns vehicle with lowest maintenance cost. If multiple vehicles
	//cost the same lowest maintenance cost, one vehicle is randomly returned.
	public Vehicle getVehicleWithLowestMaintenanceCost(double distance) {
		double min= Double.MAX_VALUE, cost;
		ArrayList<Vehicle> lowestMaintenance = new ArrayList<>();
		
		for (Vehicle v : vehicleList) {
			cost = v.calculateMaintenanceCost(distance);
			if (cost < min) {
				min=cost;
				lowestMaintenance.clear();
				lowestMaintenance.add(v);
			}
			else if (cost == min) {
				lowestMaintenance.add(v);
			}
		}
		
		if (lowestMaintenance.isEmpty() == false) {
			Random r = new Random();
			int rand_index = r.nextInt(lowestMaintenance.size());
			return lowestMaintenance.get(rand_index);
		}
		return null;
	}
	
	//calculates fuel efficiency for each vehicle in the list,
	//returns vehicle with highest fuel efficiency. If multiple vehicles
	//have the same highest fuel efficiency, an ArrayList is returned.
	public ArrayList<Vehicle> getVehicleWithHighestFuelEfficiency(double distance, double fuelPrice) {
		double max=Double.MIN_VALUE, cost;
		ArrayList<Vehicle> highestFuelEfficiency = new ArrayList<>();
		
		for (Vehicle v : vehicleList) {
			cost = v.calculateFuelEfficiency(distance, fuelPrice);
			if (cost > max) {
				max=cost;
				highestFuelEfficiency.clear();
				highestFuelEfficiency.add(v);
			}
			else if (cost == max) {
				highestFuelEfficiency.add(v);
			}
		}
		return highestFuelEfficiency;
	}
	
	//calculates fuel efficiency for each vehicle in the list,
	//returns vehicle with lowest fuel efficiency. If multiple vehicles
	//have the same lowest fuel efficiency, an ArrayList is returned.
	public ArrayList<Vehicle> getVehicleWithLowestFuelEfficiency(double distance, double fuelPrice) {
		double min=Double.MAX_VALUE, cost;
		ArrayList<Vehicle> lowestFuelEfficiency = new ArrayList<>();
		
		for (Vehicle v : vehicleList) {
			cost = v.calculateFuelEfficiency(distance, fuelPrice);
			if (cost < min) {
				min=cost;
				lowestFuelEfficiency.clear();
				lowestFuelEfficiency.add(v);
			}
			else if (cost == min) {
				lowestFuelEfficiency.add(v);
			}
		}
		return lowestFuelEfficiency;
	}
	
	//calculates average fuel efficiency of SUVs in the vehicle list.
	//if no SUVs exist in the list, -1.0 is returned as an error code.
	public double getAverageFuelEfficiencyOfSUVs(double distance, double fuelPrice) {
		double sum=0;
		int count=0;
		
		for (Vehicle v : vehicleList) {
			if (isVehicleType(v, SUV.class)) {
				sum += v.calculateFuelEfficiency(distance, fuelPrice);
				count++;
			}
		}
		if (count > 0) {
			double average = sum/count;
			return average;
		}
		return -1.0;
	}
	
}
