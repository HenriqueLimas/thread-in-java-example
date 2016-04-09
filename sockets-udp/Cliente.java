import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {
  public static void main(String[] args) {
    Cliente c = new Cliente();

    try {
      c.iniciar();
    } catch(SocketException e) {
      System.err.println("Cliente Socket:" + e.getMessage());
    } catch(UnknownHostException e) {
      System.err.println("Unkown Host:" + e.getMessage());
    } catch(IOException e) {
      System.err.println("IO Error:" + e.getMessage());
    }
  }

  public void iniciar() throws SocketException, UnknownHostException, IOException {
    System.out.println("Iniciando cliente...");

    InetAddress serverAddress = InetAddress.getByName("localhost");

    int serverPort = 1234;

    // Criar um socket udp
    DatagramSocket clientSocket = new DatagramSocket();

    // Criar mensagem para envio
    byte[] mensagem = new byte[1024];
    mensagem = new String("Mensagem enviada pelo Cliente").getBytes();

    // Criar pacote
    DatagramPacket request = new DatagramPacket(mensagem, mensagem.length, serverAddress, serverPort);

    clientSocket.send(request);
  }
}