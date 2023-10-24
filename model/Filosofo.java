package model;

import controller.MainController;
import javafx.application.Platform;

public class Filosofo extends Thread{

  private final MainController control;
  private final int id;
  private Filosofo left;
  private Filosofo right;
  public Filosofo(int index, MainController control){
    this.id = index;
    this.control = control;
  }

  @Override
  public void run() {
    while(true) {
      think();
      take_forks();
      eat();
      put_forks();

    }
  }

  public void setNeighbours(){
    this.left = MainController.filosofos[(this.id - 1 + MainController.N) % MainController.N];
    this.right = MainController.filosofos[(this.id + 1 + MainController.N) % MainController.N];
    System.out.println("Esquerda: "+left.id + " . Direita" + right.id);
  }

  private void think(){
    try {
      Platform.runLater(() -> control.changeImageToListening(this.id));
      System.out.println("O filosofo " + this.id + " está pensando");
      sleep(1500);
      Platform.runLater(() -> control.changeImageToWaiting(this.id));
      sleep(1500);
      System.out.println("Filosofo " + this.id + " passou por pensar");

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  private void take_forks(){
    try {
      MainController.mutex.acquire();
      MainController.states[this.id] = 1;
      Filosofo.test(this);
      System.out.println("Filosofo " + this.id + " tentando pegar garfos");
      MainController.mutex.release();
      MainController.forks[this.id].acquire();
      Platform.runLater(()->control.hideSticks(this.id));
      System.out.println("Filosofo " + this.id + " pegou os garfos");
    } catch (InterruptedException e) {}

  }
  private void eat() {
    try  {
      System.out.println("O filosofo " + this.id + " está comendo");
      Platform.runLater(() -> control.changeImageToPlaying(this.id));
      sleep(2000);
    }catch (InterruptedException e) {}
  }
  private void put_forks(){
    try {
      Platform.runLater(()->control.showSticks(this.id));
      Platform.runLater(() -> control.changeImageToListening(this.id));
      MainController.mutex.acquire();
      MainController.states[this.id] = 0;
      Filosofo.test(left);
      Filosofo.test(right);
      MainController.mutex.release();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Filosofo " + this.id + " colocou os garfos");
  }
  public static void test(Filosofo filosofo){
    if(MainController.states[filosofo.id]==1 && MainController.states[filosofo.left.id]!=2
        && MainController.states[filosofo.right.id]!=2){
      MainController.states[filosofo.id] = 2;
      MainController.forks[filosofo.id].release();
    }

  }
}
