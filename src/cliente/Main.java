package cliente;

import java.util.HashMap;

import Controller.ControllerPeer;

public class Main {

	private static ControllerPeer controllerPeer = new ControllerPeer();

	public static void main(String args[]) {

		controllerPeer.initAddPeer();
		int j;

		HashMap<Integer, Peer> lista = controllerPeer.getList();
		
		for (Integer id : lista.keySet()) {
			Peer peer = lista.get(id);
			System.out.println(peer);
			
		}
	}

}
