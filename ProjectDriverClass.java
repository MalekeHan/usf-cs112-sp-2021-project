package classproject;

import javax.swing.*;    // all of the Swing objects
import java.awt.*;       // more windowing components, including Container
import java.io.*;
import java.util.ArrayList;


public class ProjectDriverClass {
	
	private static void initAndShowGUI(DummyPredictor object, ArrayList<DataPoint> data) { 
		
		
		
		JFrame myFrame = new JFrame();
		myFrame.setTitle("My Creative Title");
		
		Container contentPane = myFrame.getContentPane();
		contentPane.setLayout(new GridLayout(1,1));
		
		double total = object.getAccuracy(data);
		String stringTotal = String.valueOf(total);
		double total2 = object.getPrecision(data);
		String stringTotal2 = String.valueOf(total2);
		
		//double total = pred.getAccuracy(myList2);
		//String total2 = String.valueOf(total);
		
		contentPane.add(new JButton(stringTotal));
		contentPane.add(new JButton(stringTotal2));
		
		
		myFrame.pack();
		myFrame.setVisible(true);
			    		
	}
	

	public static void main(String[] args) {
		
		
		DummyPredictor predictor = new DummyPredictor();
		ArrayList<DataPoint> myList = new ArrayList<>(4);
		
		
		predictor.readData("dataSets");
		
		DataPoint test1 = new DataPoint(7.0, 4.0);
		
		System.out.println(predictor.test(test1));
		
		SwingUtilities.invokeLater(
				new Runnable() {
			public void run() {
				initAndShowGUI(predictor, myList);
			}
		});
		

		

}
}
