package br.com.jpcchaves.example2;

public class ThreadCreation {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new ThreadExample().getThread();

    thread.start();
    thread.join();

    System.out.println("After the thread");
  }

}

