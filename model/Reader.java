package model;

import controller.MainController;
import javafx.application.Platform;

public class Reader extends Thread{

  private int id;
  private MainController control;
  private boolean isPaused;

  public Reader(int n, MainController control){
    this.id = n;
    this.control = control;
  }

  @Override
  public void run() {
    while (true){
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
    Platform.runLater(()-> control.setDrTextStudent(this.id, control.getDataBaseText()));
    sleepTime(control.usingDr(this.id));
    pausando();

  }

  private void readDataBase() {
    Platform.runLater(()-> control.setDrTextStudent(this.id,""));
    Platform.runLater(()-> control.changeStudentRead(this.id));
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
