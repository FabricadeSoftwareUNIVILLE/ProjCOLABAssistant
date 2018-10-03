package br.univille.projcolabassistant.viewmodel;

public class OrderRequestStatusAPI {
	private long numberNewOrderRequest;
	private long numberOpenOrderRequest;

	public long getNumberOpenOrderRequest() {
		return numberOpenOrderRequest;
	}

	public void setNumberOpenOrderRequest(long numberOpenOrderRequest) {
		this.numberOpenOrderRequest = numberOpenOrderRequest;
	}

	public long getNumberNewOrderRequest() {
		return numberNewOrderRequest;
	}

	public void setNumberNewOrderRequest(long numberNewOrderRequest) {
		this.numberNewOrderRequest = numberNewOrderRequest;
	}
}
