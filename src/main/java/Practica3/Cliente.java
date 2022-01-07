package Practica3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Practica3.Cliente;

public class Cliente {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 8888)) {
        	//lee del servidor
        	new Thread() {
        		public void run() {
        			try {
        				Scanner in = new Scanner(socket.getInputStream());		
        				while(in.hasNextLine()) {
        				    String msg = in.nextLine();
        				    System.out.println(msg);
        				}
					} catch (IOException e) {
						e.printStackTrace();
					}
        		};       		
        	}.start();
        	
        	//lee del teclado y escribe al servidor
        	Scanner kb = new Scanner(System.in);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);			
			while(kb.hasNextLine()) {
			    String msg = kb.nextLine();
			    out.println(msg);
			    if(msg.equalsIgnoreCase("exit")) {
			    	break;
			    }
			}
			kb.close();
			socket.close();
		} catch (Exception ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
    }   
}