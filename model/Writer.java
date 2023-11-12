package model;

import controller.MainController;
import javafx.application.Platform;

public class Writer extends Thread{
  private static final String [] text = new String[]{"Prova AED", "Enforcou a sexta", "Reunião departamento", "Trabalho PC", "M"};
  private int id;
  private MainController control;
  private boolean isPaused;

  public Writer(int n, MainController control){
    this.id = n;
    this.control = control;

  }

  @Override
  public void run() {
    while (true){
      try {
        thinkUpData();
        MainController.db.acquire();
        writeDataBase();
        MainController.db.release();
      } catch (InterruptedException e) {}

    }
  }


  private void writeDataBase() {
    Platform.runLater(()-> control.changeProfessorWritedb(this.id));
    System.out.println(control.getDataBaseText());
    Platform.runLater(()-> control.setDataBaseText(control.getDataBaseText()+"\n"+text[this.id]));
    sleepTime(control.writingData(this.id));
    pausando();
  }

  private void thinkUpData() {
    Platform.runLater(()-> control.changeProfessorThink(this.id));
    sleepTime(control.thinkingData(this.id));
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

