package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteWorker implements Runnable {

	// private Socket socket;
	// private int port;
	private String message;

	public void initConnection(int port) {
		try (Socket socket = new Socket("localhost", port); Scanner scanner = new Scanner(System.in);) {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

			this.message = scanner.next();
			out.writeUTF(this.message);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			String responseServer = in.readUTF();
			System.out.println("Mensagem recebida do servidor:" + responseServer);
			in.close();

		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		initConnection(2800);
	}

}
