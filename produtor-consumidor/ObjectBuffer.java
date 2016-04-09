public class ObjectBuffer {
  private int memory = -1;
  private boolean disponivel = true;

  public synchronized void writeBuffer(int value) {
    while (!isDisponivel()) {
      try {
        wait();
      } catch(InterruptedException e) {
        System.out.println("Wait Produtor Error");
      }
    }

    System.out.println(Thread.currentThread().getName() + " produzindo o valor " + value);

    this.setDisponivel(false);

    notifyAll();
    this.memory = value;
  }

  public synchronized int readBuffer() {
    while(this.isDisponivel()) {
      try {
        wait();
      } catch(InterruptedException e) {
        System.out.println("Wait Read Error");
      }
    }

    System.out.println(Thread.currentThread().getName() + " consumindo o valor " + this.memory);

    this.setDisponivel(true);

    notifyAll();
    return this.memory;
  }

  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }

  public boolean isDisponivel() {
    return this.disponivel;
  }
}
