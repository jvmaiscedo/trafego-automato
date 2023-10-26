package model;

import controller.MainController;
import javafx.application.Platform;
import sun.applet.Main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

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
      if(!pausada){
        think();
        take_forks();
        eat();
        put_forks();
      }
    }
  }

  public void setNeighbours(){
    this.left = MainController.filosofos[(this.id - 1 + MainController.N) % MainController.N];
    this.right = MainController.filosofos[(this.id + 1 + MainController.N) % MainController.N];
  }

  private void think() {
    if (!pausada) {
      Platform.runLater(() -> control.changeImageToListening(this.id));
      Platform.runLater(() -> control.setEstadoBaterista(this.id, "Escutando Rock"));
      sleepTime(control.getVelocidadeOuvindo(this.id));
    }
  }
  private void take_forks(){
    if(!pausada) {
      try {
        MainController.mutex.acquire();
        MainController.states[this.id] = 1;
        Platform.runLater(() -> control.changeImageToWaiting(this.id));
        Platform.runLater(() -> control.setEstadoBaterista(this.id, "Querendo tocar"));
        test(this);
        MainController.mutex.release();
        MainController.forks[this.id].acquire();
      } catch (InterruptedException e) {
      }
    }
  }
  private void eat() {
    if (!pausada) {
      Platform.runLater(() -> control.changeImageToPlaying(this.id));
      Platform.runLater(() -> control.setEstadoBaterista(this.id, "Tocando"));
      sleepTime(control.getVelocidadeTocando(this.id));
    }
  }
  private void put_forks(){
    if(!pausada) {
      try {
        Platform.runLater(() -> control.showSticks(this.id));
        MainController.mutex.acquire();
        MainController.states[this.id] = 0;
        test(left);
        test(right);
        MainController.mutex.release();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
  public void test(Filosofo filosofo){
    if(!pausada) {
      if (MainController.states[filosofo.id] == 1 && MainController.states[filosofo.left.id] != 2
        && MainController.states[filosofo.right.id] != 2) {
        MainController.states[filosofo.id] = 2;
        MainController.forks[filosofo.id].release();
        Platform.runLater(() -> control.hideSticks(this.id));
      }
    }
  }

  public void sleepTime(int time){
    try {
      sleep((long) time*1000);
    } catch (InterruptedException e) {
    }
  }

  public void pausar(){
    pausada = true;
  }
  public void retomar(){
    pausada = false;
  }
}
