package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private Integer idServer;
	private ServerSocket serverSocket;
	
	public Server() {
	}

	public Server(ServerSocket serverSocket) {
		if (serverSocket == null) {
			throw new IllegalArgumentException("O server socket não pode ser nulo");
		}
		this.serverSocket = serverSocket;
	}

	public Server(Integer idServer, ServerSocket serverSocket) {
		if (serverSocket == null) {
			throw new IllegalArgumentException("O server socket não pode ser nulo");
		}
		if (idServer <= 0) {
			throw new IllegalArgumentException("O id não pode ser zero ou negativo. ID: " + idServer);
		}
		this.idServer = idServer;
		this.serverSocket = serverSocket;
	}

	private void acceptRequestClient() {
		try (Socket client = serverSocket.accept()) {
			processRequestClient(client);
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			System.err.println(e.getMessage());
		}
	}

	private void processRequestClient(Socket client) {

		try {
			System.out.println(
					"Peer conectado: " + client.getInetAddress().getHostAddress() + " porta:" + client.getLocalPort());

			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			String msg = in.readUTF();
			System.out.println("Peer enviou: " + msg);
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

			out.writeUTF("OK");
			out.flush();
			in.close();
			out.close();

		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			System.err.println(e.getMessage());

		}
	}

	@Override
	public void run() {

		while (true) {
			acceptRequestClient();
		}
	}

	public ServerSocket getServerSocket() {
		if (serverSocket == null) {
			throw new IllegalArgumentException("O server socket não pode ser nulo");
		}
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		if (serverSocket == null) {
			throw new IllegalArgumentException("O server socket não pode ser nulo");
		}
		this.serverSocket = serverSocket;
	}

	public Integer getIdServer() {
		return idServer;
	}

	public void setIdServer(Integer idServer) {
		if (idServer <= 0) {
			throw new IllegalArgumentException("O id não pode ser zero ou negativo. ID: " + idServer);
		}
		this.idServer = idServer;
	}

}
