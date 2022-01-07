package Practica3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(8888)) {
			while (true) {
				System.out.println("Esperando cliente...");
				Socket socket = server.accept();
				System.out.println("Cliente conectado: "+ socket.getRemoteSocketAddress());
				new Manejo(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
