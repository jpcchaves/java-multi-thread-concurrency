package br.com.jpcchaves.interrupt;

public class Main {
  public static void main (String[] args) {
    Thread thread = new Thread(new BlockingTask());
    thread.interrupt();

    thread.start();
  }

  private static class BlockingTask implements Runnable {
    @Override
    public void run() {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException ex) {
        System.out.println("Exiting blocking thread");
      }
    }
  }
}
