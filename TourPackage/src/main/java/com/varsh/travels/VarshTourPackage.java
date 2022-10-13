package com.varsh.travels;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class VarshTourPackage {

	public static void main(String[] args) throws FileNotFoundException, InvalidPackageIdException, SQLException {
		TravelAgency t=new TravelAgency();
		//t.generatePackageCost("C:/Users/ipsit/Desktop/sprint/VarshTourPackageDetails.txt");
		
		t.generatePackageCost("C:/Users/ipsit/Desktop/sprint/CorrectPackageDetails.txt");
		
		t.findPackagesWithMinimumNumberOfDays();
		for(Package p:t.findPackagesWithMinimumNumberOfDays()) {
			System.out.println(p.packageId+" | "+p.sourcePlace+"-"+p.destinationPlace+"|"+p.noOfDays+" days|Rs."+p.packageCost);
		}

	}

}
