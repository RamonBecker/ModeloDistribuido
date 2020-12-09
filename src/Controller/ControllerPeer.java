package Controller;

import java.util.HashMap;
import java.util.Scanner;

import cliente.Peer;

public class ControllerPeer {

	private HashMap<Integer, Peer> list;
	private String mensagem;

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

		while (true) {
			System.out.print("Digite a mensagem:");
			this.mensagem = new Scanner(System.in).next();
			peer.setMessage(this.mensagem);
			peer.run();

			checkPeer(peer);
		}
	}

	public void checkPeer(Peer peer) {
		Peer auxVizinho = null;
		Peer aux_Peer = null;

		for (int i = 1; i <= list.size(); i++) {
			aux_Peer = list.get(i);
			if (checkMessageVizinho(aux_Peer) == 2) {
				for (Integer idVizinho : aux_Peer.getNeighbor().keySet()) {
					auxVizinho = aux_Peer.getNeighbor().get(idVizinho);
					list.get(idVizinho).setMessage(auxVizinho.getMessage());
				}
				// k++;
			} else {
				aux_Peer.setMessage(this.mensagem);
				aux_Peer.run();
				list.put(aux_Peer.getIdPeer(), aux_Peer);
			}
		}

	}

	public int checkMessageVizinho(Peer peer) {
		Peer auxVizinho = null;
		int cont = 0;
		for (Integer id : peer.getNeighbor().keySet()) {
			auxVizinho = peer.getNeighbor().get(id);

			if (auxVizinho.getMessage() != null && peer.getMessage() != null) {
				if (auxVizinho.getMessage().contentEquals(peer.getMessage())) {
					cont++;
				}
			}

		}
		return cont;
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
