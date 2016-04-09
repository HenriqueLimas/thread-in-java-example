public class Produtor extends Thread {
  private ObjectBuffer buffer;

  public Produtor(ObjectBuffer buffer) {
    super("Produtor");

    this.buffer = buffer;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep((int) (Math.random() * 3000));
      } catch(InterruptedException e) {
        System.err.println(e.toString());
      }

      this.buffer.writeBuffer(i);
    }

  }
}
