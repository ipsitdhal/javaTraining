package com.varsh.travels;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InvalidPackageIdException extends Exception {
	InvalidPackageIdException(String msg) {
		super(msg);
	}
}

public class TravelAgency {
	public boolean validate(String packageId) {
		String regex = "[0-9]{3}[/]{1}[A-Z]{3}";
		return packageId.matches(regex);
	}
	
	public List<Package> generatePackageCost (String filePath) throws InvalidPackageIdException, SQLException, FileNotFoundException{
		
		List<Package> list = new ArrayList<Package>();

				
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			int i = 1;
			while (sc.hasNextLine()) {

				String st = sc.nextLine();
				st = st.trim();
				String arr[] = st.split(",");

				String packageId = arr[0].trim();
				
				
				if (validate(packageId)) {
					
					String sourcePlace = arr[1].trim();
					String destinationPlace = arr[2].trim();
					
					double basicFare = Double.parseDouble(arr[3].trim());
					int noOfDays = Integer.parseInt(arr[4].trim());
					Package P1=new Package(packageId, sourcePlace, destinationPlace,basicFare,noOfDays);;
					
					P1.calculatePackageCost();
					list.add(P1);
					
					Connection c=JdbcUtility.connect();
					PreparedStatement p=c.prepareStatement("insert into Package_Details values(?,?,?,?,?)");
					
					p.setString(1, packageId);
					p.setString(2,sourcePlace);
					p.setString(3,destinationPlace);
					p.setInt(4,noOfDays);
					p.setDouble(5,P1.getPackageCost());
					
					p.executeUpdate();
	
				} 
				else 
				{
					throw new InvalidPackageIdException("Problem in packageId - " + i);
					
				}
				i++;
			}  

		return list;
		
	}
	
	public List<Package> findPackagesWithMinimumNumberOfDays() {

		List<Package> list = new ArrayList<Package>();
		try {
			Connection connection = JdbcUtility.connect();

			PreparedStatement ps = connection.prepareStatement("select * from Package_Details where no_of_days=(select MIN(no_of_days) from Package_Details)");
            
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String id = rs.getString("package_id");
				String source = rs.getString("source_place");
				String destination = rs.getString("destination_place");
				int days = rs.getInt("no_of_days");
				double cost = rs.getDouble("package_cost");

				Package v1 = new Package(id, source, destination, days, cost);
				
				list.add(v1);

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;

	}

}
