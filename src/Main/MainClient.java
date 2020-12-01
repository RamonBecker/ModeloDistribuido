package Main;

import java.util.Scanner;

import Controller.ControllerPeer;

public class MainClient {
	public static void main(String[] args) {
		ControllerPeer controllerPeer = new ControllerPeer();
		controllerPeer.initAddPeer();
		System.out.println("Digite o id do Peer que deseja selecionar");
		Scanner scanner = new Scanner(System.in);
		Integer id = scanner.nextInt();
		controllerPeer.getPeer(id);
	}
}
