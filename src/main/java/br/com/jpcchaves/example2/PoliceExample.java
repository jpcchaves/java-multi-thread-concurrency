package br.com.jpcchaves.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoliceExample {
  public static final int MAX_PASSWORD = 999999;
  public static void main(String[] args) {
    Random random = new Random();
    Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

    List<Thread> threads = new ArrayList<>();

    threads.add(new AscendingHackerThread(vault));
    threads.add(new DescendingHacker(vault));
    threads.add(new PoliceThread());

    for (Thread thread : threads) {
      thread.start();
    }
  }

  public static class Vault {
    private int password;

    public Vault(int password) {
      this.password = password;
    }

    public boolean isCorrectPassword(int guess) {
      try {
        Thread.sleep(5);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }

      return this.password == guess;
    }
  }

  private static abstract class HackerThread extends Thread {
    protected Vault vault;

    public HackerThread(Vault vault) {
      this.vault = vault;
      this.setName(this.getClass().getSimpleName());
      this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public synchronized void start() {
      System.out.println("Starting thread: " + this.getName());
      super.start();
    }
  }

  private static class AscendingHackerThread extends HackerThread {
    public AscendingHackerThread(Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = 0; guess < MAX_PASSWORD; guess++) {
        if(vault.isCorrectPassword(guess)) {
          System.out.println(this.getName() + " guessed the password " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class DescendingHacker extends HackerThread {
    public DescendingHacker(Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = 0; guess >= 0; guess--) {
        if (vault.isCorrectPassword(guess)) {
          System.out.println(this.getName() + " guessed the password " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class PoliceThread extends Thread {
    @Override
    public void run() {
      for (int i = 10; i > 0; i--) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }

        System.out.println(i);
      }

      System.out.println("Game over for you hackers");
      System.exit(0);
    }
  }
}
