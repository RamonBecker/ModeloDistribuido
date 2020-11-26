package cliente;

import java.io.IOException;
import java.net.UnknownHostException;

public class MainCliente {

	public static void main(String args[]) throws UnknownHostException, IOException {

	
			ClienteWorker c1 = new ClienteWorker(); 
			c1.run();

		

	}

}
