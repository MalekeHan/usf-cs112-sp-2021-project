package classproject;

import java.awt.Container;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static double myAccuracy = 0.0;
	private static double myPrecision = 0.0;
	
	public static void main(String[] args) {
		
		System.out.println("Please input an odd integer");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		while(input % 2 == 0) {
			System.out.println("The integer MUST be odd, try again: ");
			input = scanner.nextInt();
		}
		int K = input;
		
		
		Predictor knnPredictor = new KNNPredictor(K);
		
		ArrayList<DataPoint> myList = new ArrayList<DataPoint>();
		myList = knnPredictor.readData("titanic.csv");
		
		myAccuracy = knnPredictor.getAccuracy(myList);
		myPrecision = knnPredictor.getPrecision(myList);
		
		myAccuracy *= 100;
		myPrecision *= 100;
		
		
		// Call function that displays user-interface
		SwingUtilities.invokeLater(
		          new Runnable() { public void run() { 
		        	  initAndShowGUI(); } }
		        );
	}
	
	private static void initAndShowGUI() {
		// Create a JFrame with a set size
		JFrame dataFrame = new JFrame();
		dataFrame.setSize(500, 500);
		
		// Create a content pane with a GridLayout and specified size
		Container contentPane = dataFrame.getContentPane();
		contentPane.setLayout(new GridLayout(1, 1));
		contentPane.setPreferredSize(new Dimension(700, 400));
		
		JButton accuracy = new JButton("Accuracy: " + String.format("%.2f", myAccuracy) + "%");
		JButton precision = new JButton("Precision: " + String.format("%.2f", myPrecision) + "%");
		
		contentPane.add(accuracy);
		contentPane.add(precision);
		
		dataFrame.pack();
		dataFrame.setVisible(true);
		dataFrame.setTitle("KNN Predictor");
		
	}
}
