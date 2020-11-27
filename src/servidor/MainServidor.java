package servidor;

import java.io.IOException;


public class MainServidor {


	public static void main(String[] args) throws IOException {

		ServidorWorker server = new ServidorWorker(2800);

		server.run();
		

	}

}
