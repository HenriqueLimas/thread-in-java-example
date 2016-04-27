package client;

import java.net.InetAddress;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private Socket conn;

	private int id;

	public Client(int id) {
		this.id = id;
	}

	public void init() {
		System.out.println("Iniciando cliente..." + this.id);
		
		final int SERVER_PORT = 9000;

		try {
			InetAddress serverAddress = InetAddress.getByName("localhost");
			conn = new Socket(serverAddress, SERVER_PORT);
			
			InputStream io = conn.getInputStream();
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			DataInputStream in = new DataInputStream(io);
			
			String serverMessage = in.readUTF();
			
			System.out.println("Message from server:\n" + serverMessage);
			
			String choice;
			
			Scanner reader = new Scanner(System.in);
			choice = reader.nextLine();
			reader.close();

			out.writeUTF(choice);
			out.writeUTF("TERMINA");
			
			writeFile(io);
			
		} catch (UnknownHostException e) {
			System.err.println("Unkown Host:" + e.getMessage());
		} catch (IOException e) {
			System.err.println("IO:" + e.getMessage());
		}
	}
	
	private FileOutputStream createFile() throws FileNotFoundException {
		File file = new File("/Users/Henrique/Desktop/file-recebido.txt");

		return new FileOutputStream(file);
	}
	
	private void writeFile(InputStream io) {
		try {
			FileOutputStream fos = createFile();

			byte[] cbuffer = new byte[1024];
			int bytesRead;

			System.out.println("Recebendo arquivo...");
			float tamanho = 0.0f;

			// Recebendo os dados do arquivo pela stream
			while ((bytesRead = io.read(cbuffer)) != -1) {

				// Escrevendo o arquivo na sua pasta do SO que foi lido no fluxo
				// de dados
				fos.write(cbuffer, 0, bytesRead);
				fos.flush();

				tamanho += bytesRead;
				System.out.println("Qtd de bytes recebidos: " + tamanho);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Arquivo recebido com SUCESSO!");
	}
}