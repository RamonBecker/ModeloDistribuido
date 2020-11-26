package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor {

	private static Socket s; // Cliente
	private static ServerSocket ss; //Servidor

	public static void main(String[] args) throws IOException {

		ServidorWorker server = new ServidorWorker(2800);

		server.run();

	}

}
