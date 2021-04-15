package classproject;

public class DataPoint {
	private Double f1,f2;
	private int label;
	private boolean isTest;
	
	public DataPoint(double data1, double data2, int  label, boolean test) {
		this.f1 = data1;
		this.f2 = data2;
		this.label = label;
		this.isTest = test;
	}
	public DataPoint() {
		this.f1 = 0.0;
		this.f2 = 0.0;
		this.label = 0;
		this.isTest = false;
	}
	
	public DataPoint(double data1, double data2) {
		this.f1 = data1;
		this.f2 = data2;
	}
	
	
	
	//Accessors//////////////////////////////////////////////////////////////////////////////
	public Double getF1() {
		return this.f1;
	}
	public Double getF2() {
		return this.f2;
	}
	
	public int getLabel() {
		return this.label;
	}
	
	public boolean getTest() {
		return this.isTest;
	}
	
	//Mutators/////////////////////////////////////////////////////////////////////
	public void setF1(Double data1) {
		this.f1 = data1;
	}
	

	public void setF2(Double data2) {
		this.f2 = data2;
	}
	
	public void setLabel(int label) {
		this.label = label;
	}
	
	public void setTest(Boolean test) {
		if (test == true || test == false) {
			this.isTest = test;
		}
	}
	
	
}
