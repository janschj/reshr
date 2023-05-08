package dk.a2mate.palletizing.api.model;

public class PalletLayer {
	
	int zCoordinate;
	int weight;
	int noOfItems;

	LayeredItem[][] layeredItems;

	public PalletLayer() {
	}

	public int getzCoordinate() {
		return zCoordinate;
	}

	public void setzCoordinate(int zCoordinate) {
		this.zCoordinate = zCoordinate;
	}

	public LayeredItem[][] getLayeredItems() {
		return layeredItems;
	}

	public void setLayeredItems(LayeredItem[][] layeredItems) {
		this.layeredItems = layeredItems;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
		
	}

	public int getNoOfItems() {
		return noOfItems;
	}
}
