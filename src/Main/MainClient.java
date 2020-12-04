package Main;

import java.util.Scanner;

import Controller.ControllerPeer;
import cliente.Peer;

public class MainClient {
	public static void main(String[] args) {
		ControllerPeer controllerPeer = new ControllerPeer();
		controllerPeer.initAddPeer();
		System.out.println("Digite o id do Peer que deseja selecionar");
		Scanner scanner = new Scanner(System.in);
		Integer id = scanner.nextInt();
		controllerPeer.getPeer(id);
		
		System.out.println("--------- Depois -------");
		
		for (Integer key : controllerPeer.getList().keySet()) {
			Peer peer = controllerPeer.getList().get(key);
			System.out.println("Peer id:"+peer.getIdPeer()+" mensagem:"+peer.getMessage());
			for (Integer keyVizinho : peer.getNeighbor().keySet()) {
				Peer auxVizinho = peer.getNeighbor().get(keyVizinho);
				System.out.println("Peer id vizinho:"+auxVizinho.getIdPeer()+ " Mensagem:"+auxVizinho.getMessage());
				
			}
			
		}
	}
}
