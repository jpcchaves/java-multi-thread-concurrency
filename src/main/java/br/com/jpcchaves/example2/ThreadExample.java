package br.com.jpcchaves.example2;

public class ThreadExample implements Runnable {

  @Override
  public void run() {
    for (int i= 1; i <= 10; i++) {
      System.out.println("I'm a class example that implements runnable and it's the " + i);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

    }
  }

  public Thread getThread() {
    return new Thread(this);
  }
}
