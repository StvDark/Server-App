package Practica3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Practica3.Manejo;

public class Manejo extends Thread{
	private Socket client;
    private Scanner in;
    private PrintWriter out;    

    public Manejo(Socket client) {
        try {
            this.client = client;
            this.in = new Scanner(client.getInputStream());
            this.out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(Manejo.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }

    @Override
    public void run() {
        String msg;
        try {
        	
        	//asignamos un numero aleatorio a cada servidor que se conecte
        	
        	double number = Math.floor(Math.random()*100);
        	String snumber=String.valueOf(number);  
        	System.out.println("Numero Asignado: "+number);
        	
        	
        	out.println("Encuentre el numero entre 0 y 100");
            while(in.hasNextLine()) {
                msg = in.nextLine();
                System.out.println("Numero recibido: " + msg);
                try {
                	double usrnum = Double.parseDouble(msg);
                	 if(usrnum>number) {
                		 System.out.println("--Numero incorrecto");
                     	out.println("Su numero es mayor al numero que adivinar!");
                     }else if(usrnum<number) {
                    	 System.out.println("--Numero incorrecto");
                     	out.println("Su numero es menor al numero que adivinar!");
                     }else if(usrnum==number) {
                    	 System.out.println("--Numero adivinado! Se cerrara la conexion!");
                    	 out.println("Numero correcto! Juego Finalizado! El numero era: "+snumber );
                    	 out.println("Su cliente a sido desconectado! Escriba exit para salir!");
                         break;
                     }
            		 
        	}catch(Exception e) {
            	    System.out.println("ERROR: el valor ingresado no es un numero!");
            	    out.println("Por favor ingrese solo numeros!");
                	}
            }
            client.close();
            System.out.println("Cliente desconectado: " + client.getRemoteSocketAddress());
        } catch (IOException ex) {
            Logger.getLogger(Manejo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
