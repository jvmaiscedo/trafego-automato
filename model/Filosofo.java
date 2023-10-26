package model;

import controller.MainController;
import javafx.application.Platform;
import sun.applet.Main;

import java.util.concurrent.Semaphore;

public class Filosofo extends Thread{

  private static MainController control;
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
    Platform.runLater(() -> control.changeImageToListening(this.id));
    Platform.runLater(()-> control.setEstadoBaterista(this.id, "Escutando Rock"));
    System.out.println("O filosofo " + this.id + " está pensando");
    sleepTime(control.getVelocidadeOuvindo(this.id));
    System.out.println(control.getVelocidadeOuvindo(this.id));
    System.out.println("Filosofo " + this.id + " passou por pensar");

  }
  private void take_forks(){
    try {
      MainController.mutex.acquire();
      MainController.states[this.id] = 1;
      Platform.runLater(() -> control.changeImageToWaiting(this.id));
      Platform.runLater(()-> control.setEstadoBaterista(this.id, "Querendo tocar"));
      System.out.println("Filosofo " + this.id + " tentando pegar garfos");
      test(this);
      MainController.mutex.release();
      MainController.forks[this.id].acquire();
    } catch (InterruptedException e) {}

  }
  private void eat(){
    System.out.println("O filosofo " + this.id + " está comendo");
    Platform.runLater(() -> control.changeImageToPlaying(this.id));
    Platform.runLater(()-> control.setEstadoBaterista(this.id, "Tocando"));
    sleepTime(control.getVelocidadeTocando(this.id));
  }
  private void put_forks(){
    try {
      Platform.runLater(()->control.showSticks(this.id));
      MainController.mutex.acquire();
      MainController.states[this.id] = 0;
      System.out.println("Filosofo " + this.id + " colocou os garfos");
      test(left);
      test(right);
      MainController.mutex.release();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  public void test(Filosofo filosofo){
    if(MainController.states[filosofo.id]==1 && MainController.states[filosofo.left.id]!=2
        && MainController.states[filosofo.right.id]!=2){
      MainController.states[filosofo.id] = 2;
      MainController.forks[filosofo.id].release();
      System.out.println("Filosofo " + filosofo.id + " pegou os garfos"+"\n Este: "+ MainController.forks[filosofo.id].availablePermits()+
        "\n Esquerdo: "+ MainController.forks[filosofo.left.id].availablePermits()+
        "\n Direito: "+ MainController.forks[filosofo.right.id].availablePermits());
      Platform.runLater(()->control.hideSticks(this.id));
    }
  }

  public void sleepTime(int time){
    try {
      sleep((long) time*1000);
    } catch (InterruptedException e) {
    }
  }
}
