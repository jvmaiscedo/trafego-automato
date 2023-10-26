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

  public ImageView baqueta1;
  @FXML
  public ImageView baqueta2;
  @FXML
  public ImageView baqueta3;
  @FXML
  public ImageView baqueta4;
  @FXML
  public ImageView baqueta5;

  public static final int N = 5; // define o no. de filosofos
  public static Filosofo[] filosofos = new Filosofo[N];
  public volatile static int[] states = new int[N];
  public  static Semaphore mutex = new Semaphore(1);
  public static Semaphore[] forks = new Semaphore[N];

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    for(int i=0; i<N ;i++){
      forks [i] = new Semaphore(0);
      filosofos[i] = new Filosofo(i, this);
      states[i] = 0;
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
  public void pauseBaterista1() {
    filosofos[0].pausar();
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
    switch (id){
      case 0:
        return (int) velBateristaOuvindo1.getValue();
      case 1:
        return (int) velBateristaOuvindo2.getValue();
      case 2:
        return (int) velBateristaOuvindo3.getValue();
      case 3:
        return (int) velBateristaOuvindo4.getValue();
      case 4:
        return (int) velBateristaOuvindo5.getValue();
    }
    return 0;
  }
  public int getVelocidadeTocando(int id){
    switch (id){
      case 0:
        return (int) velBateristaTocando1.getValue();
      case 1:
        return (int) velBateristaTocando2.getValue();
      case 2:
        return (int) velBateristaTocando3.getValue();
      case 3:
        return (int) velBateristaTocando4.getValue();
      case 4:
        return (int) velBateristaTocando5.getValue();
    }
    return 0;
  }
  public void setEstadoBaterista (int id, String estado){
    switch (id){
      case 0:
        estadoBaterista1.setText(estado);
        break;
      case 1:
        estadoBaterista2.setText(estado);
        break;
      case 2:
        estadoBaterista3.setText(estado);
        break;
      case 3:
        estadoBaterista4.setText(estado);
        break;
      case 4:
        estadoBaterista5.setText(estado);
        break;
    }
  }

  public void changeImageToPlaying(int id){
    if(id==0){
      bateristaEscutando1.setVisible(false);
      bateristaTocando1.setVisible(true);
      bateristaEsperando1.setVisible(false);
    }
    else if(id==1){
      bateristaEscutando2.setVisible(false);
      bateristaTocando2.setVisible(true);
      bateristaEsperando2.setVisible(false);
    }
    else if(id==2){
      bateristaEscutando3.setVisible(false);
      bateristaTocando3.setVisible(true);
      bateristaEsperando3.setVisible(false);
    }
    else if(id==3){
      bateristaEscutando4.setVisible(false);
      bateristaTocando4.setVisible(true);
      bateristaEsperando4.setVisible(false);
    }
    else if(id==4){
      bateristaEscutando5.setVisible(false);
      bateristaTocando5.setVisible(true);
      bateristaEsperando5.setVisible(false);
    }

  }

  public void changeImageToListening(int id){
    if(id==0){
      bateristaTocando1.setVisible(false);
      bateristaEscutando1.setVisible(true);
      bateristaEsperando1.setVisible(false);
    }
    else if(id==1){
      bateristaTocando2.setVisible(false);
      bateristaEscutando2.setVisible(true);
      bateristaEsperando2.setVisible(false);
    }
    else if(id==2){
      bateristaTocando3.setVisible(false);
      bateristaEscutando3.setVisible(true);
      bateristaEsperando3.setVisible(false);
    }
    else if(id==3){
      bateristaTocando4.setVisible(false);
      bateristaEscutando4.setVisible(true);
      bateristaEsperando4.setVisible(false);
    }
    else if(id==4){
      bateristaTocando5.setVisible(false);
      bateristaEscutando5.setVisible(true);
      bateristaEsperando5.setVisible(false);
    }

  }

  public void changeImageToWaiting(int id){
    if(id==0){
      bateristaEsperando1.setVisible(true);
      bateristaTocando1.setVisible(false);
      bateristaEscutando1.setVisible(false);
    }
    else if(id==1){
      bateristaEsperando2.setVisible(true);
      bateristaTocando2.setVisible(false);
      bateristaEscutando2.setVisible(false);
    }
    else if(id==2){
      bateristaEsperando3.setVisible(true);
      bateristaTocando3.setVisible(false);
      bateristaEscutando3.setVisible(false);
    }
    else if(id==3){
      bateristaEsperando4.setVisible(true);
      bateristaTocando4.setVisible(false);
      bateristaEscutando4.setVisible(false);
    }
    else if(id==4){
      bateristaEsperando5.setVisible(true);
      bateristaTocando5.setVisible(false);
      bateristaEscutando5.setVisible(false);
    }

  }

  public void hideSticks(int id){
    if(id==0){
      baqueta1.setVisible(false);
      baqueta2.setVisible(false);
    }
    else if (id==1) {
      baqueta2.setVisible(false);
      baqueta3.setVisible(false);
    }
    else if (id==2) {
      baqueta3.setVisible(false);
      baqueta4.setVisible(false);
    }
    else if (id==3) {
      baqueta4.setVisible(false);
      baqueta5.setVisible(false);
    }
    else if (id==4) {
      baqueta5.setVisible(false);
      baqueta1.setVisible(false);
    }

  }
  public void showSticks(int id){
    if(id==0){
      baqueta1.setVisible(true);
      baqueta2.setVisible(true);
    }
    else if (id==1) {
      baqueta2.setVisible(true);
      baqueta3.setVisible(true);
    }
    else if (id==2) {
      baqueta3.setVisible(true);
      baqueta4.setVisible(true);
    }
    else if (id==3) {
      baqueta4.setVisible(true);
      baqueta5.setVisible(true);
    }
    else if (id==4) {
      baqueta5.setVisible(true);
      baqueta1.setVisible(true);
    }

  }

}
