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
  ImageView brownCar;
  //percursos
  @FXML
  ImageView redPath;
  @FXML
  ImageView greenPath;
  @FXML
  ImageView pinkPath;
  @FXML
  ImageView bluePath;
  @FXML
  ImageView yellowPath;
  @FXML
  ImageView orangePath;
  @FXML
  ImageView purplePath;
  @FXML
  ImageView brownPath;
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
  Car carroMarrom;
  private Slider [] velocidade;
  private ImageView [] carros;
  private ImageView [] percursos;
  public static final int N = 53;
  public static Semaphore[] rc = new Semaphore[N];
  public boolean [] booleansIsVisible = new boolean[8];
  public boolean redPathIsVisible;
  public boolean greenPathIsVisible;
  public boolean pinkPathIsVisible;
  public boolean bluePathIsVisible;
  public boolean yellowPathIsVisible;
  public boolean orangePathIsVisible;
  public boolean purplePathIsVisible;
  public boolean brownPathIsVisible;



  @Override
  public void initialize(URL url, ResourceBundle rb) {

    carroVermelho = new Car(redCar, 0, 726,-8,this);
    carroVerde = new Car(greenCar, 1,321,196,this);
    carroRosa = new Car(pinkCar, 2, 475, 390, this);
    carroAzul = new Car(blueCar, 3,162,426,this);
    carroAmarelo = new Car(yellowCar, 4,321,573,this);
    carroLaranja = new Car(orangeCar, 5, 592,468, this);
    carroRoxo = new Car(purpleCar,6,519,155,this);
    carroMarrom = new Car(brownCar, 7,160,710, this);
    iniciarSemaforos();
    for(int i = 0; i<7;i++){
      booleansIsVisible[i] = false;
    }
    percursos = new ImageView[] {redPath, greenPath, pinkPath, bluePath, yellowPath, orangePath, purplePath, brownPath};
    velocidade = new Slider[]{speedRed, speedGreen, speedPink,speedBlue, speedYellow, speedOrange, speedPurple, speedBrown};

    carroVermelho.start();
    carroVerde.start();
    carroRosa.start();
    carroAzul.start();
    carroAmarelo.start();
    carroLaranja.start();
    carroRoxo.start();
    carroMarrom.start();

  }

  @FXML
  public void reset(){

  }
  public void iniciarSemaforos(){
    for(int i=0; i<N;i++){
      if(i==7||i==8||i==10||i==44){
        rc[i]=new Semaphore(0);
      }
      else{
        rc[i]=new Semaphore(1);
      }


    }
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
  public void showRedPath(){
    booleansIsVisible[0] = !booleansIsVisible[0];
    percursos[0].setVisible(booleansIsVisible[0]);
  }
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
    booleansIsVisible[1] = !booleansIsVisible[1];
    percursos[1].setVisible(booleansIsVisible[1]);
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
    booleansIsVisible[2] = !booleansIsVisible[2];
    percursos[2].setVisible(booleansIsVisible[2]);
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
    booleansIsVisible[3] = !booleansIsVisible[3];
    percursos[3].setVisible(booleansIsVisible[3]);
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
    booleansIsVisible[4] = !booleansIsVisible[4];
    percursos[4].setVisible(booleansIsVisible[4]);
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
    booleansIsVisible[5] = !booleansIsVisible[5];
    percursos[5].setVisible(booleansIsVisible[5]);
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
    booleansIsVisible[6] = !booleansIsVisible[6];
    percursos[6].setVisible(booleansIsVisible[6]);
  }

  @FXML
  public void pauseBrownCar() {
    carroMarrom.pausar();
  }

  @FXML
  public void retomarBrownCar() {
    carroMarrom.retomar();
  }

  @FXML
  public void showBrownPath() {
    booleansIsVisible[7] = !booleansIsVisible[7];
    percursos[7].setVisible(booleansIsVisible[7]);
  }




}
