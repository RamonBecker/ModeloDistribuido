package cliente;

import java.util.HashMap;

public class Peer {
	private int id;
	private String messageReceived;
	private String ip;
	private int porta;
	private HashMap<Integer, Peer> neighbor;
	private boolean arrivalMessage;

	public Peer(int id, int porta) {
		this.id = id;
		this.ip = "127.0.0.1";
		if (!(porta >= 28001)) {
			throw new IllegalArgumentException("Porta não indisponível");
		}
		this.porta = porta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessageReceived() {
		return messageReceived;
	}

	public void setMessageReceived(String message) {
		this.messageReceived = message;
	}

	public String getIp() {
		return ip;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		if (!(porta >= 28001)) {
			throw new IllegalArgumentException("Porta não indisponível");
		}
		this.porta = porta;
	}

	public HashMap<Integer, Peer> getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(HashMap<Integer, Peer> neighbor) {
		if (neighbor == null) {
			throw new IllegalArgumentException("A lista não pode ser vazia");
		}
		this.neighbor = neighbor;
	}

	public boolean isArrivalMessage() {
		return arrivalMessage;
	}

	public void setArrivalMessage(boolean arrivalMessage) {
		this.arrivalMessage = arrivalMessage;
	}

}
