package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorWorker implements Runnable {

	private ServerSocket serverSocket;

	public ServidorWorker(int port) {
		createdServerSocket(port);
	}

	private void createdServerSocket(int port) {
		try{
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.print(e.getLocalizedMessage());
			System.err.println(e.getMessage());
		}
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

			System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress() + " porta:"
					+ client.getLocalPort());

			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			String msg = in.readUTF();
			System.out.println("Cliente enviou: " + msg);
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

}
