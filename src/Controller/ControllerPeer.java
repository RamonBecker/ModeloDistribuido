package Controller;

import java.util.HashMap;
import cliente.Peer;

public class ControllerPeer {

	private HashMap<Integer, Peer> list;

	public ControllerPeer() {
		list = new HashMap<Integer, Peer>();
	}

	private void addPeer(Integer id, Peer peer) {

		// Distribuindo os vizinhos
		Peer auxAnterior = null;
		Peer auxSucessor = null;

		if (id < 0) {
			throw new IllegalArgumentException("O id não pode ser negativo");
		} else if (peer == null) {
			throw new IllegalArgumentException("O Peer não pode ser nulo");
		}

		if (id >= 5) {
			auxAnterior = new Peer(id - 1, peer.getPorta() - 1);
			auxSucessor = new Peer(id + 1, peer.getPorta() + 1);

		} else {
			auxAnterior = new Peer(id + 1, peer.getPorta() + 1);
		}

		if (id == 6) {
			auxAnterior = new Peer(id - 1, peer.getPorta() - 1);
			auxSucessor = new Peer(1, peer.getPorta() - 5);
		} else if (id < 5) {
			auxSucessor = new Peer(id + 2, peer.getPorta() + 2);
		}
		peer.getNeighbor().put(auxAnterior.getIdPeer(), auxAnterior);
		peer.getNeighbor().put(auxSucessor.getIdPeer(), auxSucessor);
		list.put(id, peer);
	}

	public void getPeer(Integer id) {
		if (!list.containsKey(id)) {
			throw new IllegalArgumentException("Este peer não existe");
		}

		Peer peer = list.get(id);
		System.out.println("Peer: " + peer.getIdPeer() + " selecionado!");
		peer.run();

	}

	public void checkMessageAll() {
		Peer auxPeer = null;
		Peer auxVizinho = null;
		for (Integer id : list.keySet()) {
			auxPeer = list.get(id);
			for (Integer idNeighbor : auxPeer.getNeighbor().keySet()) {
				auxVizinho = auxPeer.getNeighbor().get(idNeighbor);
				if (auxVizinho.isArrivalMessage()) {

				}
			}
		}
	}

	public void initPeers() {
		for (Integer id : list.keySet()) {

		}
	}

	public void initAddPeer() {

		for (int i = 1; i <= 6; i++) {
			addPeer(i, new Peer(i, 2800 + i));
		}
	}

	public HashMap<Integer, Peer> getList() {
		return list;
	}

}
