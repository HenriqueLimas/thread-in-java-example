import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class Server {
  private Socket conn;

  public static void main(String[] args) {
    Server s = new Server();

    s.init();
  }

  public void init() {
    int serverPort = 9000;

    System.out.println("Iniciando server na porta " + serverPort);

    try {
      ServerSocket server = new ServerSocket(serverPort);

      while(true) {
        conn = server.accept();

        new ResponseClient(conn);
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