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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Filosofo;


public class MainController implements Initializable {

  //Elementos FXML do baterista 1
  @FXML
  public ImageView bateristaEscutando1;
  @FXML
  public ImageView bateristaTocando1;
  @FXML
  public ImageView bateristaEsperando1;
  @FXML
  public Slider velBateristaOuvindo1;
  @FXML
  public Slider velBateristaTocando1;
  @FXML
  public Button pauseBaterista1;
  @FXML
  public Button restartBaterista1;
  @FXML
  public Label estadoBaterista1;

  //Elementos FXML do baterista 2
  @FXML
  public ImageView bateristaEscutando2;
  @FXML
  public ImageView bateristaTocando2;
  @FXML
  public ImageView bateristaEsperando2;
  @FXML
  public Slider velBateristaOuvindo2;
  @FXML
  public Slider velBateristaTocando2;
  @FXML
  public Button pauseBaterista2;
  @FXML
  public Button restartBaterista2;
  @FXML
  public Label estadoBaterista2;

  //Elementos FXML do baterista 3
  @FXML
  public ImageView bateristaEscutando3;
  @FXML
  public ImageView bateristaTocando3;
  @FXML
  public ImageView bateristaEsperando3;
  @FXML
  public Slider velBateristaOuvindo3;
  @FXML
  public Slider velBateristaTocando3;
  @FXML
  public Button pauseBaterista3;
  @FXML
  public Button restartBaterista3;
  @FXML
  public Label estadoBaterista3;

  //Elementos FXML do baterista 4

  @FXML
  public ImageView bateristaEscutando4;
  @FXML
  public ImageView bateristaTocando4;
  @FXML
  public ImageView bateristaEsperando4;
  @FXML
  public Slider velBateristaOuvindo4;
  @FXML
  public Slider velBateristaTocando4;
  @FXML
  public Button pauseBaterista4;
  @FXML
  public Button restartBaterista4;
  @FXML
  public Label estadoBaterista4;

  //Elementos FXML do baterista 5

  @FXML
  public ImageView bateristaEscutando5;
  @FXML
  public ImageView bateristaTocando5;
  @FXML
  public ImageView bateristaEsperando5;
  @FXML
  public Slider velBateristaOuvindo5;
  @FXML
  public Slider velBateristaTocando5;
  @FXML
  public Button pauseBaterista5;
  @FXML
  public Button restartBaterista5;
  @FXML
  public Label estadoBaterista5;
  @FXML
  public ImageView baqueta0;
  @FXML
  public ImageView baqueta1;
  @FXML
  public ImageView baqueta2;
  @FXML
  public ImageView baqueta3;
  @FXML
  public ImageView baqueta4;

  private ImageView[] baquetas;
  private ImageView [] bateristaEscutando;
  private ImageView [] bateristaTocando;
  private ImageView [] bateristaEsperando;
  private Slider[] velocidadeTocando;
  private Slider[] velocidadeEscutando;
  private Label [] estadoBaterista;

  public static final int N = 5; // define o no. de filosofos
  public static Filosofo[] filosofos = new Filosofo[N];
  public volatile static int[] states = new int[N];
  public  static Semaphore mutex = new Semaphore(1);
  public static Semaphore[] forks = new Semaphore[N];

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    iniciar();

  }

  public void iniciar(){
    velocidadeEscutando = new Slider[]{velBateristaOuvindo1, velBateristaOuvindo2, velBateristaOuvindo3, velBateristaOuvindo4, velBateristaOuvindo5};
    velocidadeTocando = new Slider[] {velBateristaTocando1, velBateristaTocando2, velBateristaTocando3, velBateristaTocando4, velBateristaTocando5};
    baquetas = new ImageView[]{ baqueta0, baqueta1, baqueta2, baqueta3, baqueta4};
    estadoBaterista = new Label[] {estadoBaterista1, estadoBaterista2, estadoBaterista3, estadoBaterista4, estadoBaterista5};
    bateristaEscutando = new ImageView[]{ bateristaEscutando1, bateristaEscutando2, bateristaEscutando3, bateristaEscutando4, bateristaEscutando5};
    bateristaTocando = new ImageView[]{ bateristaTocando1, bateristaTocando2, bateristaTocando3, bateristaTocando4, bateristaTocando5};
    bateristaEsperando = new ImageView[] {bateristaEsperando1, bateristaEsperando2, bateristaEsperando3, bateristaEsperando4, bateristaEsperando5};

    for(int i=0; i<N ;i++){
      forks [i] = new Semaphore(0);
      filosofos[i] = new Filosofo(i, this);
      states[i] = 0;
      velocidadeEscutando[i].setValue(3);
      velocidadeTocando[i].setValue(3);
    }

    for (int j=0; j<N; j++){
      filosofos[j].setNeighbours();
    }
    filosofos[0].start();
    filosofos[1].start();
    filosofos[2].start();
    filosofos[3].start();
    filosofos[4].start();
  }


  @FXML
  public void resetar(){

    interromperThreads();
    iniciar();
  }

  public void interromperThreads() {
    for (int i = 0; i < N; i++) {
      filosofos[i].interrupt();
      baquetas[i].setVisible(true);
    }
  }

  @FXML
  public void pauseBaterista1() {
    filosofos[0].pausar();
    System.out.println(states[0]);
  }

  @FXML
  public void restartBaterista1() {
    filosofos[0].retomar();
  }

  @FXML
  public void pauseBaterista2() {
    filosofos[1].pausar();
  }

  @FXML
  public void restartBaterista2() {
    filosofos[1].retomar();
  }

  @FXML
  public void pauseBaterista3() {
    filosofos[2].pausar();
  }

  @FXML
  public void restartBaterista3() {
    filosofos[2].retomar();
  }

  @FXML
  public void pauseBaterista4() {
    filosofos[3].pausar();
  }

  @FXML
  public void restartBaterista4() {
    filosofos[3].retomar();
  }

  @FXML
  public void pauseBaterista5() {
    filosofos[4].pausar();
  }

  @FXML
  public void restartBaterista5() {
    filosofos[4].retomar();
  }

  public int getVelocidadeOuvindo(int id){
    return (int) velocidadeEscutando[id].getValue();
  }
  public int getVelocidadeTocando(int id){
    return (int) velocidadeTocando[id].getValue();
  }
  public void setEstadoBaterista (int id, String estado){
    estadoBaterista[id].setText(estado);
  }

  public void changeImageToPlaying(int id){
    bateristaEscutando[id].setVisible(false);
    bateristaEsperando[id].setVisible(false);
    bateristaTocando[id].setVisible(true);
  }

  public void changeImageToListening(int id){
    bateristaEscutando[id].setVisible(true);
    bateristaEsperando[id].setVisible(false);
    bateristaTocando[id].setVisible(false);

  }

  public void changeImageToWaiting(int id){
    bateristaEscutando[id].setVisible(false);
    bateristaEsperando[id].setVisible(true);
    bateristaTocando[id].setVisible(false);
  }

  public void hideSticks(int id, int idRight){
    baquetas[id].setVisible(false);
    baquetas[idRight].setVisible(false);
  }
  public void showSticks(int id, int idLeft, int idRight){
    if (states[id]!=2 && states[idLeft]!=2){
      baquetas[id].setVisible(true);
    }
    if (states[idRight]!=2) {
      baquetas[idRight].setVisible(true);
    }
  }

}
