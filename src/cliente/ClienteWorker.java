package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteWorker implements Runnable {

	private Socket socket;

	@Override
	public void run() {

		try {
			socket = new Socket("localhost", 2800);

			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeUTF("Teste ola mundo");
			out.flush();

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			String msg = in.readUTF();

			System.out.println("Resposta servidor:" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
