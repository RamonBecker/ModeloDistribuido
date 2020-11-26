package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteWorker implements Runnable {

	// private Socket socket;
	private int port;
	private String message;
	
	public void initConnection(int port) {
		try (Socket socket = new Socket("localhost", port); Scanner scanner = new Scanner(System.in);) {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeUTF("Teste ola mundo");
			out.flush();

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			String msg = in.readUTF();

			in.close();

			// socket = new Socket("localhost", port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

	}

}
