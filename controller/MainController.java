/* ***************************************************************
* Autor............: Joao Victor Gomes Macedo
* Matricula........: 202210166
* Inicio...........: 20/10/2023
* Ultima alteracao.: 27/10/2023
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
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Car;
import model.Semaforo;


public class MainController implements Initializable {
  //Elementos de imagem
  @FXML
  ImageView redCar;
  @FXML
  ImageView greenCar;
  @FXML
  ImageView pinkCar;
  @FXML
  ImageView orangeCar;
  @FXML
  ImageView blueCar;
  @FXML
  ImageView yellowCar;
  @FXML
  ImageView purpleCar;
  @FXML
  Slider speedRed;
  @FXML
  Slider speedGreen;
  @FXML
  Slider speedPink;
  @FXML
  Slider speedBlue;
  @FXML
  Slider speedYellow;
  @FXML
  Slider speedOrange;
  @FXML
  Slider speedPurple;
  @FXML
  Slider speedBrown;
  Car carroVermelho;
  Car carroVerde;
  Car carroRosa;
  Car carroAzul;
  Car carroAmarelo;
  Car carroLaranja;
  Car carroRoxo;
  private Slider []velocidade;

  @Override
  public void initialize(URL url, ResourceBundle rb) {

    carroVermelho = new Car(redCar, 0, 726,-8,this);
    carroVerde = new Car(greenCar, 1,321,196,this);
    carroRosa = new Car(pinkCar, 2, 475, 390, this);
    carroAzul = new Car(blueCar, 3,162,426,this);
    carroAmarelo = new Car(yellowCar, 4,321,573,this);
    carroLaranja = new Car(orangeCar, 5, 592,468, this);
    carroRoxo = new Car(purpleCar,6,519,155,this);
    velocidade = new Slider[]{speedRed, speedGreen, speedPink,speedBlue, speedYellow, speedOrange, speedPurple, speedBrown};
    carroVermelho.start();
    carroVerde.start();
    carroRosa.start();
    carroAzul.start();
    carroAmarelo.start();
    carroLaranja.start();
    carroRoxo.start();
    //carroLaranja.start();
  }

  public double getVelocidadeCar(int id){
    return velocidade[id].getValue();
  }
  @FXML
  public void pauseRedCar(){
    carroVermelho.pausar();
  }
  @FXML
  public void retomarRedCar(){
    carroVermelho.retomar();
  }
  @FXML
  public void showRedPath(){}
  @FXML
  public void pauseGreenCar() {
    carroVerde.pausar();
  }

  @FXML
  public void retomarGreenCar() {
    carroVerde.retomar();
  }

  @FXML
  public void showGreenPath() {
  }

  @FXML
  public void pausePinkCar() {
    carroRosa.pausar();
  }

  @FXML
  public void retomarPinkCar() {
    carroRosa.retomar();
  }

  @FXML
  public void showPinkPath() {
  }

  @FXML
  public void pauseBlueCar() {
    carroAzul.pausar();
  }

  @FXML
  public void retomarBlueCar() {
    carroAzul.retomar();
  }

  @FXML
  public void showBluePath() {
  }

  @FXML
  public void pauseYellowCar() {
    carroAmarelo.pausar();
  }

  @FXML
  public void retomarYellowCar() {
    carroAmarelo.retomar();
  }

  @FXML
  public void showYellowPath() {
  }

  @FXML
  public void pauseOrangeCar() {
    carroLaranja.pausar();
  }

  @FXML
  public void retomarOrangeCar() {
    carroLaranja.retomar();
  }

  @FXML
  public void showOrangePath() {
  }

  @FXML
  public void pausePurpleCar() {
    carroRoxo.pausar();
  }

  @FXML
  public void retomarPurpleCar() {
    carroRoxo.retomar();
  }

  @FXML
  public void showPurplePath() {
  }

  @FXML
  public void pauseBrownCar() {
  }

  @FXML
  public void retomarBrownCar() {
  }

  @FXML
  public void showBrownPath() {
  }




}
