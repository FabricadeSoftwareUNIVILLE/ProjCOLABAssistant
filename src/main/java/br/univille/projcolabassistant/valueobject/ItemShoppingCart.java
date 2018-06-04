package br.univille.projcolabassistant.valueobject;

import br.univille.projcolabassistant.model.AssistiveAccessory;

public class ItemShoppingCart {
	private long sequence;
	private int quantity;
	private AssistiveAccessory accessory = new AssistiveAccessory();
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public AssistiveAccessory getAccessory() {
		return accessory;
	}
	public void setAccessory(AssistiveAccessory accessory) {
		this.accessory = accessory;
	}
}
