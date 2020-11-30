package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import servidor.Server;

public class ControllerServer {
	private HashMap<Integer, Server> listServer;

	public void initAddServer() {
		for (int i = 1; i <= 6; i++) {
			addServer(i, new Server());
		}
	}

	private void addServer(Integer id, Server servidor) {
		try {
			servidor.setIdServer(id);
			servidor.setServerSocket(new ServerSocket(2800 + id));
			listServer.put(id, servidor);
		} catch (IOException e) {
			System.out.println("Não foi possível adicionar o servidor na lista");
			System.out.println(e.getMessage());
		}
	}

	public void initServer() {
		if (this.listServer.isEmpty() || this.listServer == null) {
			throw new IllegalArgumentException(
					"Não foi possível iniciar os servidores pois a lista está vazia ou nula !");
		}
		for (Integer id : listServer.keySet()) {
			Server server = listServer.get(id);
			server.start();
		}
	}

	public HashMap<Integer, Server> getListServer() {
		return listServer;
	}

	public void setListServer(HashMap<Integer, Server> listServer) {
		if (listServer == null) {
			throw new IllegalArgumentException("A lista de servidores não pode ser nula");
		}
		this.listServer = listServer;
	}

}
