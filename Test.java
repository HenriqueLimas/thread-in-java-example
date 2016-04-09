public class Test {
  public static void main(String[] args) {
      FirstThread t;

      for (int i = 0; i < 10; i++) {
        t = new FirstThread(i);
        t.start();
      }
  }
}
