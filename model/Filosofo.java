/* ***************************************************************
* Autor............: Joao Victor Gomes Macedo
* Matricula........: 202210166
* Inicio...........: 20/10/2023
* Ultima alteracao.: 27/10/2023
* Nome.............: Filosofo
* Funcao...........: Modela o comportamento dos filosofos (que
*                    nesta aplicacao sao bateristas)
*************************************************************** */

package model;

import controller.MainController;
import javafx.application.Platform;

public class Filosofo extends Thread{
  private volatile boolean pausada = false;//Flag para pausar ou retomar o processo.
  private static MainController control;//Traz o controle para realizacao de alteracoes graficas.
  private final int id;//Id do filosofo
  private Filosofo left;//Vizinho esquerdo do filosofo
  private Filosofo right;//Vizinho direito do filosofo

  /* ***************************************************************
   * Metodo: Filosofo
   * Funcao: Construtor do objeto Filosofo.
   * Parametros: Id do filosofo e o controlador da interface.
   * Retorno: Sem retorno.
   *************************************************************** */
  public Filosofo(int index, MainController control){
    this.id = index;
    this.control = control;
  }
  /* ***************************************************************
   * Metodo: run
   * Funcao: Executar o ciclo de vida do filosofo.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  @Override
  public void run() {
    while(!Thread.interrupted()) {
      think();
      take_forks();
      eat();
      put_forks();
    }
  }
  /* ***************************************************************
   * Metodo: setNeighbours
   * Funcao: Definir os dois vizinhos do filosofo.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void setNeighbours(){
    this.left = MainController.filosofos[(this.id - 1 + MainController.N) % MainController.N];
    this.right = MainController.filosofos[(this.id + 1 + MainController.N) % MainController.N];
  }

  /* ***************************************************************
   * Metodo: think
   * Funcao: Realiza o estágio de pensamento do filosofo, neste caso
   *         o estágio em que o baterista escuta rock.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void think() {
    Platform.runLater(() -> control.changeImageToListening(this.id));
    Platform.runLater(() -> control.setEstadoBaterista(this.id, "Escutando Rock"));
    sleepTime(control.getVelocidadeOuvindo(this.id));
    pausando();
  }

  /* ***************************************************************
   * Metodo: take_forks
   * Funcao: Realiza o estágio de tentativa de pegar os garfos do filosofo,
   *         neste caso o estágio em que o baterista tenta pegar duas baquetas.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void take_forks(){
    try {
      MainController.mutex.acquire();
      MainController.states[this.id] = 1;
      entrandoNaFila();
      test(this);
      MainController.mutex.release();
      MainController.forks[this.id].acquire();
      Platform.runLater(() -> control.hideSticks(this.id, right.id));
    } catch (InterruptedException e) {
    }
  }

  /* ***************************************************************
   * Metodo: eat
   * Funcao: Realiza o estágio de comer do filosofo, neste caso o estágio
   *         em que o baterista toca rock.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void eat() {
    Platform.runLater(() -> control.changeImageToPlaying(this.id));
    Platform.runLater(() -> control.setEstadoBaterista(this.id, "Tocando Rock!"));
    sleepTime(control.getVelocidadeTocando(this.id));
    pausando();
  }

  /* ***************************************************************
   * Metodo: put_forks
   * Funcao: Realiza o estágio onde o filosofo devolve os garfos, neste
   *         caso o estagio em que o baterista devolve as baquetas.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void put_forks(){
    try {
      pausando();
      Platform.runLater(() -> control.showSticks(this.id, left.id, right.id));
      MainController.mutex.acquire();
      MainController.states[this.id] = 0;
      test(left);
      test(right);
      MainController.mutex.release();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /* ***************************************************************
   * Metodo: test
   * Funcao: Testa se o filosofo pode pegar os dois garfos, neste
   *         caso se o baterista pode pegar as duas baquetas.
   * Parametros: Objeto do tipo filosofo.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void test(Filosofo filosofo){
    pausando();
    if (MainController.states[filosofo.id] == 1 && MainController.states[filosofo.left.id] != 2
      && MainController.states[filosofo.right.id] != 2) {
      MainController.states[filosofo.id] = 2;
      MainController.forks[filosofo.id].release();
    }
  }

  /* ***************************************************************
   * Metodo: sleepTime
   * Funcao: Faz o processo dormir.
   * Parametros: Valor numerico do tipo inteiro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void sleepTime(int time){
    try {
      sleep((long) time*1000);
    } catch (InterruptedException e) {
    }
  }

  /* ***************************************************************
   * Metodo: entrandoNaFila
   * Funcao: Define a mudanca grafica do filosofo que esta tentando
   *         comer, neste caso estagio onde o baterista quer tocar.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void entrandoNaFila(){
    Platform.runLater(() -> control.changeImageToWaiting(this.id));
    Platform.runLater(() -> control.setEstadoBaterista(this.id, "Querendo tocar"));
    pausando();
  }

  /* ***************************************************************
   * Metodo: pausando
   * Funcao: pausa o processo.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void pausando(){
    while (pausada && !Thread.interrupted()){
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
    pausada = true;

  }

  /* ***************************************************************
   * Metodo: retomar
   * Funcao: Modifica a flag responsavel por pausar o processo para
   *         que este seja retomado.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void retomar(){
    pausada = false;
  }
}
