public class Main {
  public static void main(String[] args) {
    ObjectBuffer buffer = new ObjectBuffer();

    Produtor produtor = new Produtor(buffer);
    Consumidor consumidor = new Consumidor(buffer);

    produtor.start();
    consumidor.start();
  }
}
