package model;

import controller.MainController;
import sun.applet.Main;

import java.util.concurrent.Semaphore;

public class Filosofo extends Thread{

  private MainController control;
  private int id;
  private Filosofo left;
  private Filosofo right;
  public Filosofo(int index, MainController control){
    this.id = index;
    this.control = control;
    this.left = MainController.filosofos[(this.id - 1 +MainController.N) % MainController.N];
    this.right = MainController.filosofos[(this.id + 1 +MainController.N) % MainController.N];

  }
  @Override
  public void run() {
    while(true) {
      try {
        think();
        take_forks();
        eat();
        put_forks();
        sleep(20);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void setNeibhours(){
    this.left = MainController.filosofos[(this.id - 1 +MainController.N) % MainController.N];
    this.right = MainController.filosofos[(this.id + 1 +MainController.N) % MainController.N];
  }

  private void think(){
    try {
      System.out.println("O filosofo "+this.id+" esta ensando");
      sleep(1500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  private void take_forks(){
    try {
      MainController.mutex.acquire();
      MainController.states[id] = 1;
      Filosofo.test(this);
      MainController.mutex.release();
      MainController.forks[id].acquire();
      //MainController.hideForks -> fazer posteriormente.
    } catch (InterruptedException e) {}

  }
  private void eat(){
    System.out.println("O filosofo "+this.id+" esta comendo");
  }
  private void put_forks(){
    try {
      MainController.mutex.acquire();
      MainController.states[id] = 0;
      Filosofo.test(left);
      Filosofo.test(right);
      MainController.mutex.release();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  public static void test(Filosofo filosofo){
    if(MainController.states[filosofo.id]==1 && MainController.states[filosofo.left.id]!=2
        && MainController.states[filosofo.right.id]!=2){
      MainController.states[filosofo.id] = 2;
      MainController.forks[filosofo.id].release();
    }
  }
}
