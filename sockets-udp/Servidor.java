import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.io.IOException;
import java.net.SocketException;

public class Servidor {
  public static void main(String[] args) {
    Servidor c = new Servidor();

    try {
      c.iniciar();
    } catch(SocketException e) {
      System.err.println("Servidor Socket:" + e.getMessage());
    } catch(IOException e) {
      System.err.println("IO Error:" + e.getMessage());
    }
  }

  public void iniciar() throws SocketException, IOException{
    int serverPort = 1234;

    System.out.println("Iniciando servidor porta " + serverPort);

    DatagramSocket socket = new DatagramSocket(serverPort);

    byte[] mensagem = new byte[1024];

    DatagramPacket request = new DatagramPacket(mensagem, mensagem.length);

    while(true) {
      socket.receive(request);

      String msgRecebida = new String(request.getData());
      System.out.println("Servidor recebeu: " + msgRecebida);
    }
  }
}