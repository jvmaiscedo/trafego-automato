/* ***************************************************************
* Autor............: Joao Victor Gomes Macedo
* Matricula........: 202210166
* Inicio...........: 29/09/2023
* Ultima alteracao.: 05/10/2023
* Nome.............: MainController
* Funcao...........: Manipula os objetos da interface gráfica 
		                 e das classes modelo.
*************************************************************** */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.fxml.Initializable;
import model.Filosofo;


public class MainController implements Initializable {

  public static final int N = 5; // define o no. de filosofos
  public static Filosofo[] filosofos = new Filosofo[N];
  public static int[] states = new int[N];
  public static Semaphore mutex = new Semaphore(1);
  public static Semaphore[] forks = new Semaphore[N];

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    for(int i=0; i<N ;i++){
      forks [i] = new Semaphore(1);
      filosofos[i] = new Filosofo(i, this );
      states[i] = 0;
    }
    for (int j=0; j<N; j++){
      filosofos[j].setNeibhours();
      filosofos[j].start();
    }
  }
  
}
