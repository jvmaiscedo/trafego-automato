package model;

import controller.MainController;
import javafx.application.Platform;
import java.util.Random;

public class Writer extends Thread{
  private int id;
  private MainController control;
  private volatile boolean isPaused = false;

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
    if (this.id>0) {
      Platform.runLater(()-> control.setDataBaseText(control.getDataBaseText()+"\n"+avisoAleatorio()));
    }
    else{
      Platform.runLater(()-> control.setDataBaseText(avisoAleatorio()));
    }
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
  public static String avisoAleatorio() {
    String[] frases = {
      "Leiam cap. 7",
      "Prova 18/11",
      "Seminário 20/11",
      "Prova Cálculo",
      "Trabalho lógica",
      "Paralisação amanhã",
      "Estudem pág. 45",
      "Seminário adiado",
      "Ponto facultativo",
      "Revisem conceitos",
      "Prova Física",
      "Seminário individual",
      "Trabalho dupla",
      "Revisão quinta",
      "Entrega trabalhos",
      "Prova surpresa",
    };
    Random random = new Random();
    int indiceSorteado = random.nextInt(frases.length);

    return frases[indiceSorteado];
  }
}

