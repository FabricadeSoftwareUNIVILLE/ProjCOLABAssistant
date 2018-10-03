package br.univille.projcolabassistant.viewmodel;

import java.util.List;

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.model.AccessoryPhoto;
import br.univille.projcolabassistant.model.AssistiveAccessory;

public class AssistiveAccessoryViewModel {
	private AssistiveAccessory assistiveAccessory;
	private AccessoryPhoto accessoryPhoto;
	
	
	public AssistiveAccessoryViewModel() {
		// TODO Auto-generated constructor stub
	}
	
	public AssistiveAccessoryViewModel(AssistiveAccessory assistiveAccessory,AccessoryPhoto accessoryPhoto) {
		this.assistiveAccessory = assistiveAccessory;
		this.accessoryPhoto = accessoryPhoto;
	}
	

	public AccessoryPhoto getAccessoryPhoto() {
		return accessoryPhoto;
	}

	public void setAccessoryPhoto(AccessoryPhoto accessoryPhoto) {
		this.accessoryPhoto = accessoryPhoto;
	}

	public AssistiveAccessory getAssistiveAccessory() {
		return assistiveAccessory;
	}

	public void setAssistiveAccessory(AssistiveAccessory assistiveAccessory) {
		this.assistiveAccessory = assistiveAccessory;
	}
}
