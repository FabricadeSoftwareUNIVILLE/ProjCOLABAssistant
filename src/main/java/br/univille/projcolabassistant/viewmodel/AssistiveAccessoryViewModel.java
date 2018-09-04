package br.univille.projcolabassistant.viewmodel;

import java.util.List;

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.model.AssistiveAccessory;

public class AssistiveAccessoryViewModel {
	private List<AssistiveAccessory> listAssistiveAccessory;
	private AccessoryColor accessoryColor;
	private boolean first;
	
	
	public AssistiveAccessoryViewModel() {
		// TODO Auto-generated constructor stub
	}
	
	public AssistiveAccessoryViewModel(List<AssistiveAccessory> listAssistiveAccessory,AccessoryColor accessoryColor) {
		this.listAssistiveAccessory = listAssistiveAccessory;
		this.accessoryColor = accessoryColor;
	}
	
	public AccessoryColor getAccessoryColor() {
		return accessoryColor;
	}
	public void setAccessoryColor(AccessoryColor accessoryColor) {
		this.accessoryColor = accessoryColor;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
	
	
}
