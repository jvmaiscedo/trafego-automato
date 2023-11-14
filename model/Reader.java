package model;

import controller.MainController;
import javafx.application.Platform;

public class Reader extends Thread{

  private int id;
  private MainController control;
  private String text;
  private volatile boolean isPaused = false;

  public Reader(int n, MainController control){
    this.id = n;
    this.control = control;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()){
      try {
        MainController.mutex.acquire();
        MainController.rc+=1;
        if(MainController.rc == 1){
          MainController.db.acquire();
        }
        MainController.mutex.release();
        readDataBase();
        MainController.mutex.acquire();
        MainController.rc-=1;
        if(MainController.rc == 0){
          MainController.db.release();
        }
        MainController.mutex.release();
        useDataRead();


      } catch (InterruptedException e) {}

    }
  }


  private void useDataRead() {
    Platform.runLater(()-> control.changeStudentUdr(this.id));
    Platform.runLater(()-> control.setDrTextStudent(this.id, this.text));
    sleepTime(control.usingDr(this.id));
    pausando();
    Platform.runLater(()-> control.setDrTextStudent(this.id,""));
    Platform.runLater(()-> control.changeStudent(this.id));
  }

  private void readDataBase() {
    Platform.runLater(()-> control.changeStudentRead(this.id));
    text = control.getDataBaseText();
    sleepTime(control.readingData(this.id));
    pausando();
  }
  public void sleepTime(int time){
    try {
      sleep((long) time*1000);
    } catch (InterruptedException e) {
    }
  }
  private void pausando(){
    while (isPaused && !Thread.interrupted()){
      sleepTime(1);
    }
  }

  /* ***************************************************************
   * Metodo: pausar
   * Funcao: Modifica a flag responsavel por pausar o processo para
   *         que este seja pausado.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void pausar(){
    isPaused = true;

  }

  /* ***************************************************************
   * Metodo: retomar
   * Funcao: Modifica a flag responsavel por pausar o processo para
   *         que este seja retomado.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void retomar(){
    isPaused = false;
  }

}
