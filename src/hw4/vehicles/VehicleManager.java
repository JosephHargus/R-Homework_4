package hw4.vehicles;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

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
				if(line[1].isBlank()) break;
				
				//read attributes of the vehicle
				String type = line[0];
				String brand = line[1];
				String make = line[2];
				long modelYear = Long.parseLong(line[3]);
				double price = Double.parseDouble(line[4]);
				VehicleColor color = VehicleColor.valueOf(line[5]);
				FuelType fuelType = FuelType.valueOf(line[6]);
				double mileage = Double.parseDouble(line[7]);
				double mass = Double.parseDouble(line[8]);
				int cylinders = Integer.parseInt(line[9]);
				double gasTankCap = Double.parseDouble(line[10]);
				StartMechanism startType = StartMechanism.valueOf(line[11]);
				
				
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
		return false;
	}
	
	//Adds the given behivle into vehicleList
	//Returns true if successfully added
	//Returns false otherwise
	public boolean addVehicle(Vehicle vehicle)
	{
		return false;
	}
	
	//Saves the updated vehicleList back to the CSV file located at vehicleFilePath
	//Overwrites the existing file with the updated data
	//Returns true if successfully saved
	//Returns false otherwise
	public boolean saveVehicleList()
	{
		return false;
	}
	
	//Returns the ArrayList stored at vehicleList
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	
	
}
