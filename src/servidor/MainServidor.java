package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor {

	private static Socket s; // Cliente
	private static ServerSocket ss; //Servidor

	public static void main(String[] args) throws IOException {

		ss = new ServerSocket(2800);

		while (true) {
			s = ss.accept();
			//ServidorWorker t1 = new ServidorWorker(s);
		///	t1.run();

		}

	}

}
