import java.net.Socket;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;

public class ResponseClient extends Thread {
  private Socket conn;
  private DataOutputStream out;
  private DataInputStream in;

  public ResponseClient(Socket conn) {
    this.conn = conn;

    this.start();
  }

  @Override
  public void run() {
    try {
      out = new DataOutputStream(conn.getOutputStream());
      in = new DataInputStream(conn.getInputStream());

      String msgRecebida = "";
      while (!(msgRecebida = in.readUTF()).equalsIgnoreCase("TERMINA")) {
        System.out.println("Servidor recebeu: " + msgRecebida);

        out.writeUTF("Servidor recebeu: " + msgRecebida);
        
      }
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