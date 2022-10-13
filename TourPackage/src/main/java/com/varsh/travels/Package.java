package com.varsh.travels;

public class Package {
	String packageId;
	String sourcePlace;
	String destinationPlace;
	double basicFare;
	int noOfDays;
	double packageCost;
	
	
	
	public Package(String packageId, String sourcePlace, String destinationPlace, double basicFare, int noOfDays) {
		super();
		this.packageId = packageId;
		this.sourcePlace = sourcePlace;
		this.destinationPlace = destinationPlace;
		this.basicFare = basicFare;
		this.noOfDays = noOfDays;
	}
	


	public Package(String packageId, String sourcePlace, String destinationPlace, int noOfDays, double packageCost) {
		super();
		this.packageId = packageId;
		this.sourcePlace = sourcePlace;
		this.destinationPlace = destinationPlace;
		this.noOfDays = noOfDays;
		this.packageCost = packageCost;
	}



	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getSourcePlace() {
		return sourcePlace;
	}
	public void setSourcePlace(String sourcePlace) {
		this.sourcePlace = sourcePlace;
	}
	public String getDestinationPlace() {
		return destinationPlace;
	}
	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}
	public double getBasicFare() {
		return basicFare;
	}
	public void setBasicFare(double basicFare) {
		this.basicFare = basicFare;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public double getPackageCost() {
		return packageCost;
	}
	public void setPackageCost(double packageCost) {
		this.packageCost = packageCost;
	}
	void calculatePackageCost() {
		//Package Cost = ((Basic fare x number of days)-discount)+GST;
		double disc=0;
		if(this.noOfDays<=5) {
			disc=0;
		}
		else if(this.noOfDays>5 && noOfDays<=8) {
			disc=0.03;
		}
		else if(this.noOfDays>8 && noOfDays<=10) {
			disc=0.05;
		}
		else if(this.noOfDays>10) {
			disc=0.07;
		}
		
		this.packageCost=(this.basicFare*this.noOfDays)*(1-disc)*1.12;
		
	}
	

}
