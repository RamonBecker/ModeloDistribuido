package cliente;

import java.util.HashMap;

public class Peer extends Thread {
	private int id_Peer;
	private String messageReceived;
	private String ip;
	private int porta;
	private HashMap<Integer, Peer> neighbor;
	private boolean arrivalMessage;

	public Peer(int idPeer, int porta) {
		this.id_Peer = idPeer;
		this.ip = "127.0.0.1";
		if (!(porta > 2800)) {
			throw new IllegalArgumentException("Porta não indisponível");
		}
		this.porta = porta;
		this.neighbor = new HashMap<Integer, Peer>();
	}

	public void serverRequestNeighbor() {

	}

	public int getIdPeer() {
		return id_Peer;
	}

	public void setId(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("O id não pode ser zero ou negativo. ID: " + id);
		}
		this.id_Peer = id;
	}

	public String getMessageReceived() {
		return messageReceived;
	}

	public void setMessageReceived(String message) {
		if (message.isEmpty() || message == null) {
			throw new IllegalArgumentException("A mensagem não pode ser vazia");
		}
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

	@Override
	public String toString() {
		return "Peer [id_Peer=" + id_Peer + " ip=" + ip + ", porta=" + porta + ", neighbor=" + neighbor;
	}
}
