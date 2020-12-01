package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Peer extends Thread {
	private int id_Peer;
	private String message;
	private String ip;
	private int porta;
	private HashMap<Integer, Peer> vizinho;
	private boolean arrivalMessage;

	public Peer(int idPeer, int porta) {
		this.id_Peer = idPeer;
		this.ip = "127.0.0.1";
		if (!(porta > 2800)) {
			throw new IllegalArgumentException("Porta não indisponível");
		}
		this.porta = porta;
		this.vizinho = new HashMap<Integer, Peer>();
	}

	public void run() {
		writeMessage();
	}

	public void writeMessage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite uma mensagem: ");
		this.message = scanner.next();

		for (Integer id : vizinho.keySet()) {
			Peer peer = vizinho.get(id);
			peer.requestClient(peer.getIdPeer(), peer);
		}
	}

	private void requestClient(Integer id, Peer peer) {
		try (Socket socket = new Socket("localhost", this.porta)) {

			System.out.println("Peer: " + this.id_Peer + " enviando mensagem para Peer: " + id);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeUTF(this.message);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			String responseServer = in.readUTF();
			System.out.println("Peer:" + id + " recebeu a seguinte mensagem: " + responseServer);
			peer.setArrivalMessage(true);
			getNeighbor().put(id, peer);
			in.close();
			out.close();

		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
		}
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if (message.isEmpty() || message == null) {
			throw new IllegalArgumentException("A mensagem não pode ser vazia");
		}
		this.message = message;
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
		return vizinho;
	}

	public void setNeighbor(HashMap<Integer, Peer> neighbor) {
		if (neighbor == null) {
			throw new IllegalArgumentException("A lista não pode ser vazia");
		}
		this.vizinho = neighbor;
	}

	public boolean isArrivalMessage() {
		return arrivalMessage;
	}

	public void setArrivalMessage(boolean arrivalMessage) {
		this.arrivalMessage = arrivalMessage;
	}

	@Override
	public String toString() {
		return "Peer [id_Peer=" + id_Peer + " ip=" + ip + ", porta=" + porta + ", neighbor=" + vizinho;
	}
}
