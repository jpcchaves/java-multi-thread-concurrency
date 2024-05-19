package br.com.jpcchaves.example2;

public class ThreadInterruption {

  public static void main(String[] args) {
    Thread thread = new ThreadExample().getThread();


    thread.start();
  }
}
