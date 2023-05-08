package dk.a2mate.palletizing.api.model;

public class LayeredItem {
	
	private int xCoordinate;
	private int yCoordinate;
	private boolean rotated;
	
	public LayeredItem() {
	}
	public LayeredItem(int xCoordinate, int yCoordinate, boolean rotated) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.rotated = rotated;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public boolean isRotated() {
		return rotated;
	}

	public void setRotated(boolean rotated) {
		this.rotated = rotated;
	}

}
