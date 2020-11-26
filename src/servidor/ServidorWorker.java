package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorWorker implements Runnable {

	private Socket client;
	private ServerSocket serverSocket;
	private int port;

	public ServidorWorker(int port, Socket client) {
		this.port = port;
		this.client = client;
	}

	private void createdServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void processRequestClient() {
		
	}

	@Override
	public void run() {

		try {

			ObjectInputStream in = new ObjectInputStream(client.getInputStream());

			String msg = in.readUTF();
			System.out.println("Cliente enviou: " + msg);

			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

			out.writeUTF("OK");
			out.flush();
			in.close();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
