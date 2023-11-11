package model;

import controller.MainController;
import javafx.application.Platform;

public class Writer extends Thread{
  
  private int id;
  private MainController control;
  public Writer(int n, MainController control){
    this.id = n;
    this.control = control;

  }

  @Override
  public void run() {
    while (true){
      try {
        System.out.println("escritor");
        thinkUpData();
        MainController.db.acquire();
        writeDataBase();
        MainController.db.release();
      } catch (InterruptedException e) {}

    }
  }


  private void writeDataBase() {
    System.out.println("escrevi");
    Platform.runLater(()-> control.changeProfessorWritedb(this.id));
    Platform.runLater(()-> control.setDataBaseText("prova dia 13/10"));
    sleepTime(1);
  }

  private void thinkUpData() {
    Platform.runLater(()-> control.changeProfessorThink(this.id));
    sleepTime(4);
  }

  public void sleepTime(int time){
    try {
      sleep((long) time*1000);
    } catch (InterruptedException e) {
    }
  }
}
