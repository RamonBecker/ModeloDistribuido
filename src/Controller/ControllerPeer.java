package Controller;

import java.util.HashMap;
import cliente.Peer;

public class ControllerPeer {
	private int aux_i = 2;
	private int j;
	private int k;
	private HashMap<Integer, Peer> list;

	public ControllerPeer() {
		list = new HashMap<Integer, Peer>();
	}

	private void addPeer(Integer id, Peer peer) {
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

	public void initAddPeer() {

		for (int i = 1; i <= 6; i++) {
			addPeer(i, new Peer(i, 2800 + i));
		}
	}

	private void neighborDistribution() {
		Peer aux_Peer = null;
		Peer aux_Put_Peer = null;
		int aux_calc_before_position = 0;
		int aux_calc_next_position = 0;

		for (int i = 1; i <= list.size(); i++) {

			if (i <= 4) {

				while (true) {
					if (list.containsKey(aux_i)) {
						aux_Peer = list.get(aux_i);
						aux_Put_Peer = list.get(i);
						aux_Put_Peer.getNeighbor().put(aux_Peer.getIdPeer(), aux_Peer);
						list.put(aux_Put_Peer.getIdPeer(), aux_Put_Peer);
					}
					if (aux_Put_Peer.getNeighbor().size() == 2) {
						break;
					}
					aux_i++;
				}
			}

		}

		// System.out.println(peer5.getNeighbor().put(peer4.getIdPeer(),
		// peer4)peer5.getNeighbor().put(peer4.getIdPeer(), peer4)););

	}

	public HashMap<Integer, Peer> getList() {
		return list;
	}

}
