package br.univille.projcolabassistant.viewmodel;

import java.util.List;

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.model.AssistiveAccessory;

public class AssistiveAccessoryViewModel {
	private AssistiveAccessory assistiveAccessory;
	private AccessoryColor accessoryColor;
	
	
	public AssistiveAccessoryViewModel() {
		// TODO Auto-generated constructor stub
	}
	
	public AssistiveAccessoryViewModel(AssistiveAccessory assistiveAccessory,AccessoryColor accessoryColor) {
		this.assistiveAccessory = assistiveAccessory;
		this.accessoryColor = accessoryColor;
	}
	
	public AccessoryColor getAccessoryColor() {
		return accessoryColor;
	}
	public void setAccessoryColor(AccessoryColor accessoryColor) {
		this.accessoryColor = accessoryColor;
	}

	public AssistiveAccessory getAssistiveAccessory() {
		return assistiveAccessory;
	}

	public void setAssistiveAccessory(AssistiveAccessory assistiveAccessory) {
		this.assistiveAccessory = assistiveAccessory;
	}
}
