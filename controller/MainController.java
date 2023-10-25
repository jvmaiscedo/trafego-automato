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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import model.Filosofo;


public class MainController implements Initializable {

  @FXML
  public ImageView bateristaEscutando1;
  @FXML
  public ImageView bateristaEscutando2;
  @FXML
  public ImageView bateristaEscutando3;
  @FXML
  public ImageView bateristaEscutando4;
  @FXML
  public ImageView bateristaEscutando5;
  @FXML
  public ImageView bateristaTocando1;
  @FXML
  public ImageView bateristaTocando2;
  @FXML
  public ImageView bateristaTocando3;
  @FXML
  public ImageView bateristaTocando4;
  @FXML
  public ImageView bateristaTocando5;
  @FXML
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
    filosofos[0].setPriority(1);
    filosofos[2].start();
    filosofos[3].start();
    filosofos[4].start();
  }

  public void changeImageToPlaying(int id){
    if(id==0){
      bateristaEscutando1.setVisible(false);
      bateristaTocando1.setVisible(true);
    }
    else if(id==1){
      bateristaEscutando2.setVisible(false);
      bateristaTocando2.setVisible(true);
    }
    else if(id==2){
      bateristaEscutando3.setVisible(false);
      bateristaTocando3.setVisible(true);
    }
    else if(id==3){
      bateristaEscutando4.setVisible(false);
      bateristaTocando4.setVisible(true);
    }
    else if(id==4){
      bateristaEscutando5.setVisible(false);
      bateristaTocando5.setVisible(true);
    }

  }

  public void changeImageToListening(int id){
    if(id==0){
      bateristaTocando1.setVisible(false);
      bateristaEscutando1.setVisible(true);
    }
    else if(id==1){
      bateristaTocando2.setVisible(false);
      bateristaEscutando2.setVisible(true);
    }
    else if(id==2){
      bateristaTocando3.setVisible(false);
      bateristaEscutando3.setVisible(true);
    }
    else if(id==3){
      bateristaTocando4.setVisible(false);
      bateristaEscutando4.setVisible(true);
    }
    else if(id==4){
      bateristaTocando5.setVisible(false);
      bateristaEscutando5.setVisible(true);
    }

  }

  public void changeImageToWaiting(int id){
    if(id==0){
      bateristaTocando1.setVisible(false);
      bateristaEscutando1.setVisible(false);
    }
    else if(id==1){
      bateristaTocando2.setVisible(false);
      bateristaEscutando2.setVisible(false);
    }
    else if(id==2){
      bateristaTocando3.setVisible(false);
      bateristaEscutando3.setVisible(false);
    }
    else if(id==3){
      bateristaTocando4.setVisible(false);
      bateristaEscutando4.setVisible(false);
    }
    else if(id==4){
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
