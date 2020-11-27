package cliente;

public class Main {

	public static void main(String args[]){

		int id = 1;//Integer.parseInt(args[0]);1
		//Peer peer1 = new Peer(, porta);
		processID(id);
		
	}
	
	
	private static void processID(int id) {
		
		for (int i = 1; i < 6; i++) {
			 System.out.println(id+i);
		}
	}

}
