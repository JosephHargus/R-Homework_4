package hw4.vehicles;

import java.util.ArrayList;

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
	
	
}
