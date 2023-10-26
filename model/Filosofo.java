package model;

import controller.MainController;
import javafx.application.Platform;



public class Filosofo extends Thread{
  private volatile boolean pausada = false;
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
  }

  private void think() {
    Platform.runLater(() -> control.changeImageToListening(this.id));
    Platform.runLater(() -> control.setEstadoBaterista(this.id, "Escutando Rock"));
    sleepTime(control.getVelocidadeOuvindo(this.id));
    pausando();
  }
  private void take_forks(){
    try {
      MainController.mutex.acquire();
      MainController.states[this.id] = 1;
      entrandoNaFila();
      test(this);
      MainController.mutex.release();
      MainController.forks[this.id].acquire();
    } catch (InterruptedException e) {
    }

  }
  private void eat() {
    Platform.runLater(() -> control.changeImageToPlaying(this.id));
    Platform.runLater(() -> control.setEstadoBaterista(this.id, "Tocando"));
    sleepTime(control.getVelocidadeTocando(this.id));
    pausando();
  }
  private void put_forks(){
    try {
      pausando();
      Platform.runLater(() -> control.showSticks(this.id, right.id));
      MainController.mutex.acquire();
      MainController.states[this.id] = 0;
      test(left);
      test(right);
      MainController.mutex.release();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }
  public void test(Filosofo filosofo){
    pausando();
    if (MainController.states[filosofo.id] == 1 && MainController.states[filosofo.left.id] != 2
      && MainController.states[filosofo.right.id] != 2) {
      MainController.states[filosofo.id] = 2;
      MainController.forks[filosofo.id].release();
      Platform.runLater(() -> control.hideSticks(this.id, right.id));
    }
  }

  public void sleepTime(int time){
    try {
      sleep((long) time*1000);
    } catch (InterruptedException e) {
    }
  }
  private void entrandoNaFila(){
    Platform.runLater(() -> control.changeImageToWaiting(this.id));
    Platform.runLater(() -> control.setEstadoBaterista(this.id, "Querendo tocar"));
    pausando();
  }
  private void pausando(){
    while (pausada){
      sleepTime(1);
    }
  }
  public void pausar(){
    pausada = true;

  }
  public void retomar(){
    pausada = false;
  }
}
