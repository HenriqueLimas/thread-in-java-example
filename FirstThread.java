public class FirstThread extends Thread {
  private int identificador;

  public FirstThread(int id) {
      this.identificador = id;
  }

  @Override
  public void run () {
    System.out.println("My first thread" + this.identificador);
  }
}
