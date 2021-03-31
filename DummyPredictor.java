package classproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;


public class DummyPredictor extends Predictor {
	
	//double f1avg = 0.0;
	//double f2avg = 0.0;
	
	double goodavg = 0.0;
	double badavg = 0.0;
	
	
	public Double getPrecision(ArrayList<DataPoint> data) {
		return 100.0;
	}
	
	public Double getAccuracy(ArrayList<DataPoint> data) {
		return 50.0;
	}
	
	
	
	public String test(DataPoint data) {
		//double myf1avg = Math.abs(data.getF1() - this.f1avg);
		//double myf2avg = Math.abs(data.getF2() - this.f2avg);
		
		double mygoodAvg = Math.abs(data.getF1() + data.getF2() - this.goodavg);
		double mybadAvg = (data.getF1() + data.getF2() - this.badavg);
		
		if (mygoodAvg < mybadAvg) {
			return "Good";
		}
		
		if (mybadAvg < mygoodAvg) {
			return "Bad";
		}
		
		return "Something";
			}
	
	
	
	
	public ArrayList<DataPoint>readData(String filename){
		int lineCounter = 0;
		ArrayList<DataPoint> myList = new ArrayList<>(4);
		try {
			Scanner x = new Scanner(new File(filename));
			while (x.hasNext()) {
				//lineCounter++;
				
				double f1 = x.nextDouble();
				double f2 = x.nextDouble();
				String label = x.next();
				boolean isTest = x.nextBoolean();
				DataPoint data1 = new DataPoint(f1,f2, label, isTest);
				myList.add(data1);
				}
			//for (int i = 0; i < lineCounter; i++) {
				//System.out.println("Hi");
			//}
		} catch(FileNotFoundException ex) {
			System.out.println("File not found");
			}
		
		double f1Value = 0.0;
		double f2Value = 0.0;
		double f1Sum = 0.0;
		double f2Sum = 0.0;
		
		int j = 0;
		int k = 0;
		
		double goodAvg = 0.0;
		double badAvg = 0.0;
		
		//double f1avg = 0.0;
		
		for (int i =0; i < myList.size(); i++) {
			String label = myList.get(i).getLabel();
			double f1 = myList.get(i).getF1();
			double f2 = myList.get(i).getF2();


			
			if (label.equals("Good")) {
				goodAvg = f1 + f2;
				j += 1;
			}
			
			if (label.equals("Bad")) {
				badAvg = f1 + f2;
				k +=1;
			}
			f1Sum += f1Value;
			f2Sum += f2Value;	
		}
		goodAvg = goodAvg/j;
		badAvg = badAvg/k;
		
		this.goodavg = goodAvg;
		this.badavg = badAvg;
		

		return myList;
		
	
	}
	}


	


