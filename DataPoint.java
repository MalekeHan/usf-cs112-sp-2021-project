package classproject;

public class DataPoint {
	private Double f1,f2;
	private String label;
	private Boolean isTest;
	
	public DataPoint(double data1, double data2, String label, Boolean test) {
		this.f1 = data1;
		this.f2 = data2;
		this.label = label;
		this.isTest = test;
	}
	public DataPoint() {
		this.f1 = null;
		this.f2 = null;
		this.label = null;
		this.isTest = null;
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
	
	public String getLabel() {
		return this.label;
	}
	
	public Boolean getTest() {
		return this.isTest;
	}
	
	//Mutators/////////////////////////////////////////////////////////////////////
	public void setF1(Double data1) {
		if (data1 < 0) {
			return;
		}
		this.f1 = data1;
	}
	

	public void setF2(Double data2) {
		if (data2 < 0) {
			return;
		}
		this.f2 = data2;
	}
	
	public void setLabel(String label) {
		if (!(label.equals("Green") || label.equals("Blue"))) {
			return;
		}
		this.label = label;
	}
	
	public void setTest(Boolean test) {
		if (test == true || test == false) {
			this.isTest = test;
		}
	}
	
	
}
