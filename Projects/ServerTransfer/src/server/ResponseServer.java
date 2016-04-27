package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ResponseServer extends Thread {
	private Socket socket;
	private OutputStream out;
	private InputStream in;
	private FileInputStream fileIn;
	
	public ResponseServer(Socket socket) {
		this.socket = socket;
		
		try {
			in = this.socket.getInputStream();
			out = this.socket.getOutputStream();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		this.start();
	}
	
	@Override
	public void run() {
		String msgRecebida = "";
		
		DataInputStream msgIn = new DataInputStream(in);
		DataOutputStream msgOut = new DataOutputStream(out);

		try {
			msgOut.writeUTF(this.getListFiles());

			while(!(msgRecebida = msgIn.readUTF()).equalsIgnoreCase("TERMINA")) {
				System.out.println("|--------------- Processing ---------------|");
				switch (msgRecebida) {
					case "1":
					case "foo.txt":
						sendFile(msgOut, "foo.txt");
						break;
					case "2":
					case "bar.txt":
						sendFile(msgOut, "bar.txt");
						break;
					case "3":
					case "bazinga.txt":
						sendFile(msgOut, "bazinga.txt");
						break;
					default:
						System.err.println("404 - Not found");
						msgOut.writeUTF("404 - Not Found");
						break;
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
	    } finally {
			closeConnection();
		}
	}
	
	private String getListFiles() {
		String listString;
		
		listString = "Arquivos disponiveis:\n";
		listString += "1 - foo.txt\n";
		listString += "2 - bar.txt\n";
		listString += "3 - bazinga.txt\n";
		listString += "Escreve o nome ou numero do file desejado.\n";
		
		return listString;
	}
	
	private FileInputStream createFile(String fileName) throws FileNotFoundException {
		File file = new File("/Users/Henrique/Google Drive/UFSC/2016-1/Sistemas Distribuidos/Threads/Projects/ServerTransfer/" + fileName);

		return new FileInputStream(file);
	}
	
	private void sendFile(DataOutputStream out, String fileName) {
		System.out.println("Request file " + fileName);

		try {
			FileInputStream fileIn = createFile(fileName);
			
			byte[] cbuffer = new byte[102400];
			int bytesRead;
			
			System.out.println("Sending file...");
			
			float tamanho = 0.0f;
			
			//Envio de arquivo. Finaliza quando terminar de ler todos os dados do arquivo
			while ((bytesRead = fileIn.read(cbuffer)) != -1) {
				//Copiando o conteudo para o fluxo de dados
				out.write(cbuffer, 0, bytesRead);
				out.flush();
				tamanho += bytesRead;
				System.out.println("Qtd de bytes enviados: " + tamanho);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Arquivo enviado com SUCESSO!");
	}
	
	private void closeConnection() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (fileIn != null) {
			try {
				fileIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
