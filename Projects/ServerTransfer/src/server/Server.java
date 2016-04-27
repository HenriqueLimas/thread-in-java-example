package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	Socket conn;
	ServerSocket socket;
	
	public void init() {
		final int SERVER_PORT = 9000;
		
		System.out.println("Listening server port " + SERVER_PORT);
		
		try {
			socket = new ServerSocket(SERVER_PORT);
			
			while(true) {
				conn = socket.accept();
				new ResponseServer(conn);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
