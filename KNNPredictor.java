package classproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class KNNPredictor extends Predictor {
	
	private int K;
	//Instance Variables
	private int amountSurvived = 0;
	private int amountDied = 0;
	
	//Constructor
	public KNNPredictor(int kValue) {
		this.K = kValue;	
	}
	
	
	private List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
	 return values;
	}
	public boolean RandomPer() {
		Random rand = new Random();
		double randNum = rand.nextDouble();
		Boolean isTest;
		// 90% of the data is reserved for training
		if (randNum < 0.9) {
			 isTest = true;
		// Set the type of DataPoint as “train” and put into the Collection
		} else {
		 isTest = false;
		// Set the type of DataPoint as “test” and put into the Collection
		}
		return isTest;
	}
	
	public ArrayList<DataPoint>readData(String filename){
		int lineCounter = 0;
		ArrayList<DataPoint> myList2 = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(filename));){
			while (scanner.hasNextLine()) {
				lineCounter++;
				List<String> records = getRecordFromLine(scanner.nextLine());
				//Need a way to skip the lines that dont contain all the information
				if(records.size() < 7) {
					continue;
				}
				
				String strF1 = records.get(5);
				String strF2 = records.get(6);
				String strSurvived = records.get(1);
				
				
				//Kept getting NumberFormatException error
				double F1;
				try {
					F1 = Double.parseDouble(strF1);
				} catch(NumberFormatException ex) {
					continue;
				}
				double F2;
				try {
					F2 = Double.parseDouble(strF2);
				} catch(NumberFormatException ex) {
					continue;
				}
				int intSurvivedValue;
				try {
					intSurvivedValue = Integer.parseInt(strSurvived);
				} catch(NumberFormatException ex) {
					continue;
				}
	
				if(RandomPer()) {
					if(intSurvivedValue == 1) {
						amountSurvived++;
					}
					else if (intSurvivedValue == 0) {
						amountDied++;
					}
				}
				DataPoint data1 = new DataPoint(F1,F2, intSurvivedValue, RandomPer() ); 
				myList2.add(data1);
			}
			scanner.close();
			

			
			
		} catch(FileNotFoundException ex) {
			System.out.println("File not found");
			}
		System.out.println("Titanic Data Set: " + "\nTotal records: " + lineCounter + "\nPassengers Survived: " + amountSurvived + "\nPassengers Killed: " + amountDied);
		return myList2;
	}
	
	private ArrayList<DataPoint> myList = new ArrayList<>();
	
	public String test(DataPoint data) {
		double totalDistance;
		int totalData;
		double myLabel;
		
		totalData = amountSurvived + amountDied;
		Double[][] arr = new Double[totalData][2];
		
		for (int r = 0; r < totalData; r++) {
			DataPoint numTrainingD = myList.get(r);
			totalDistance = getDistance(data, numTrainingD);
			myLabel = numTrainingD.getLabel();
			arr[r][1] = myLabel;
			arr[r][0] = totalDistance;
		}
		
		
	
		java.util.Arrays.sort(arr, new java.util.Comparator<Double[]>() {
			public int compare(Double[] a, Double[] b) {
				return a[0].compareTo(b[0]);
			}
		});
		int mySurvived = 0;
		int myDied = 0;
		
		for(int r = 0; r < K; r++) {
			if(arr[r][1] == 1) {
				mySurvived++;
			}
			else if (survivalTracker == 0 ) {
				myDied++;
			}
		}
		if(mySurvived > myDied) {
			return "1";
		}
												//Cannot use else if?////////
		else {
			return "0";
		}
	}
	
	public double getDistance(DataPoint p1, DataPoint p2) {
		double distance;
		double firstVal = Math.pow(p2.getF2()-p1.getF2(),2);
		double secondVal = Math.pow(p2.getF1()-p1.getF1(), 2);
		distance = Math.sqrt(firstVal + secondVal);
		return distance;	
	}
	
	public Double getAccuracy(ArrayList<DataPoint> data) {
		int truePositive = 0;
		int falsePositive = 0;
		int falseNegative = 0;
		int trueNegative = 0;
		double totalCalculations = 0;
		double accuracy = 0;
		for( DataPoint dataPoint : data) {
			double myLabelDataPoints = dataPoint.getLabel();
			myList = data;
			double accuracyPrediction = Double.parseDouble(test(dataPoint));
			
			if(myLabelDataPoints == 1.0) {
				if(accuracyPrediction ==1.0) {
					truePositive++;
				}
				else if (accuracyPrediction ==0.0) {
					falsePositive++;
				}
			}
			if (myLabelDataPoints == 0.0) {
				if(accuracyPrediction == 1.0) {
					falseNegative++;
				}
				else if (accuracyPrediction == 0.0) {
					trueNegative++;
				}
			}
		}
		totalCalculations = truePositive + falsePositive + falseNegative + trueNegative;
		
		accuracy = (truePositive + trueNegative) / (totalCalculations);
		
		return accuracy;	
		
	}
	
	public Double getPrecision(ArrayList<DataPoint> data) {
		int truePositive = 0;
		int falsePositive = 0;
		int falseNegative = 0;
		int trueNegative = 0;
		double totalCalculations =0;
		double precision = 0;
		for( DataPoint dataPoint : data) {
			//if(!dataPoint.getTest()) {
				//continue;
			//}
			double myLabelDataPoints = dataPoint.getLabel();
			myList = data;
			double precisionPrediction = Double.parseDouble(test(dataPoint));
			
			if(myLabelDataPoints == 1.0) {
				if(precisionPrediction ==1.0) {
					truePositive++;
				}
				else if (precisionPrediction ==0.0) {
					falsePositive++;
				}
			}
			if (myLabelDataPoints == 0.0) {
				if(precisionPrediction == 1.0) {
					falseNegative++;
				}
				else if (precisionPrediction == 0.0) {
					trueNegative++;
				}
			}
		}
		
		totalCalculations = truePositive + falseNegative;
		
		precision = truePositive / totalCalculations;
		
		return precision;
	}
	
	
	
}
	
	
	
	
	
