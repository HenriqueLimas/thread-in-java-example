import java.net.InetAddress;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.UnknownHostException;
import java.io.IOException;

public class Client {
  private Socket conn;

  public static void main(String[] args) {
    Client c = new Client();

    c.init();
  }

  public void init(){
    int serverPort = 9000;

    try {
      InetAddress serverAddress = InetAddress.getByName("localhost");

      conn = new Socket(serverAddress, serverPort);
      DataOutputStream out = new DataOutputStream(conn.getOutputStream());
      DataInputStream in = new DataInputStream(conn.getInputStream());

      for (int i = 0; i < 10;i++) {
        String mensagem = new String("Stream enviado pelo cliente..." + i);

        out.writeUTF(mensagem);

        String msgRecebida = "";

        msgRecebida = in.readUTF();

        System.out.println("Cliente recebeu: " + msgRecebida);
      }

      out.writeUTF("TERMINA");

    } catch(UnknownHostException e) {
      System.err.println("Host conn: " + e.getMessage());
    } catch(IOException e) {
      System.err.println("IO: " + e.getMessage());
    } finally {
      try {
        conn.close();
      } catch(IOException e) {
        System.err.println("IO Closing conn: " + e.getMessage());
      }
    }
  }
}