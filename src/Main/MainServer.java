package Main;


import Controller.ControllerServer;

public class MainServer {

	public static void main(String[] args) {

		ControllerServer controllerServer = new ControllerServer();
		controllerServer.addServers();
		controllerServer.initServers();

	}

}
