package Controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.HashMap;

import servidor.Server;

public class ControllerServer {
	private HashMap<Integer, Server> listServer;

	public void addServers() {
		for (int i = 1; i <= 6; i++) {
			addServer(i, new Server());
		}
	}

	private void addServer(Integer id, Server servidor) {
		try {
			servidor.setIdServer(id);
			servidor.setServerSocket(new ServerSocket(2800 + id));
			getListServer().put(id, servidor);
		} catch (IOException e) {
			System.out.println("Não foi possível adicionar o servidor na lista");
			System.out.println(e.getMessage());
		}
	}

	public void initServers() {
		if (this.listServer.isEmpty() || this.listServer == null) {
			throw new IllegalArgumentException(
					"Não foi possível iniciar os servidores pois a lista está vazia ou nula !");
		}
		for (Integer id : listServer.keySet()) {
			Server server = listServer.get(id);
			System.out.println("Iniciando Peer: "+id+"  Server");
			try {
				System.out.println("Peer: "+id+" Server rodando na porta: "+server.getServerSocket().getLocalPort()+" Hostname:"+InetAddress.getLocalHost().getHostName()+" IP:"+InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			System.out.println();
			server.start();
		}
	}

	public HashMap<Integer, Server> getListServer() {
		if(this.listServer == null) {
			this.listServer = new HashMap<Integer, Server>();
		}
		return listServer;
	}

	public void setListServer(HashMap<Integer, Server> listServer) {
		if (listServer == null) {
			throw new IllegalArgumentException("A lista de servidores não pode ser nula");
		}
		this.listServer = listServer;
	}

}
