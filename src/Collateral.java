
public class Collateral {
	String label;
	double[] oriValue = new double[3];
	
	double[] newValue = new double[3];
	
	public Collateral(String label, double oriRMBValue, double oriDollarValue, double oriPoundValue) {
		this.label = label;
		oriValue[0] = oriRMBValue;
		oriValue[1] = oriDollarValue;
		oriValue[2] = oriPoundValue;
		
		for(int i = 0;i < 3;i++) {
			newValue[i] = oriValue[i];
		}
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double[] getOriValue() {
		return oriValue;
	}

	public void setOriValue(double[] oriValue) {
		this.oriValue = oriValue;
	}

	public double[] getNewValue() {
		return newValue;
	}

	public void setNewValue(int pos, double value) {
		this.newValue[pos] = value;
	}

	public void updateValue(double interest) {
		for(int i = 0;i < 3;i++) {
			newValue[i] = newValue[i] * (1 + interest);
		}
	}
	
	public void cleanValue() {
		for(int i = 0;i < 3;i++) {
			newValue[i] = oriValue[i];
		}
	}
	
}
