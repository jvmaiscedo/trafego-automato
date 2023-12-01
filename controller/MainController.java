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
import model.Positions;


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
  private Slider[] velocidade;
  private ImageView[] carImage;
  private ImageView[] percursos;
  private Positions[] posicaoInicial = new Positions[8];
  private Car[] carros = new Car[8];
  public static final int N = 53;
  public static Semaphore[] rc = new Semaphore[N];
  public boolean[] booleansIsVisible = new boolean[8];
  private volatile boolean reinicializando = false;


  @Override
  public void initialize(URL url, ResourceBundle rb) {
    posicaoInicial[0] = new Positions(726, -8, 270);
    posicaoInicial[1] = new Positions(321, 196, 180);
    posicaoInicial[2] = new Positions(475, 390, 0);
    posicaoInicial[3] = new Positions(162, 426, 0);
    posicaoInicial[4] = new Positions(321, 573, 0);
    posicaoInicial[5] = new Positions(592, 468, 270);
    posicaoInicial[6] = new Positions(519, 155, 90);
    posicaoInicial[7] = new Positions(160, 710, 0);
    percursos = new ImageView[]{redPath, greenPath, pinkPath, bluePath, yellowPath, orangePath, purplePath, brownPath};
    velocidade = new Slider[]{speedRed, speedGreen, speedPink, speedBlue, speedYellow, speedOrange, speedPurple, speedBrown};
    carImage = new ImageView[]{redCar, greenCar, pinkCar, blueCar, yellowCar, orangeCar, purpleCar, brownCar};
    /*
    //carroVermelho = new Car(redCar, 0, 726,-8,this);
    //carroVerde = new Car(greenCar, 1,321,196,this);
    //carroRosa = new Car(pinkCar, 2, 475, 390, this);
    //carroAzul = new Car(blueCar, 3,162,426,this);
    //carroAmarelo = new Car(yellowCar, 4,321,573,this);
    //carroLaranja = new Car(orangeCar, 5, 592,468, this);
    //carroRoxo = new Car(purpleCar,6,519,155,this);
    //carroMarrom = new Car(brownCar, 7,160,710, this);
    iniciarCarros();

    iniciarSemaforos();
    for(int i = 0; i<7;i++){
      booleansIsVisible[i] = false;
    }


    //carroVermelho.start();
    //carroVerde.start();
    //carroRosa.start();
   //carroAzul.start();
    //carroAmarelo.start();
    //carroLaranja.start();
    //carroRoxo.start();
    //carroMarrom.start();*/
    iniciar();


  }

  public void iniciar() {
    for (int i = 0; i < 8; i++) {
      velocidade[i].setValue(5);
      percursos[i].setVisible(booleansIsVisible[i]);
    }
    iniciarSemaforos();
    iniciarCarros();
    for (int j = 0; j < 8; j++) {
      carros[j].start();
    }
  }

  /* ***************************************************************
   * Metodo: resetar
   * Funcao: Chama as subrotinas que configuram o reset da aplicacao
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void reset() {
    interromperThreads();
    iniciarSemaforos();
    iniciar();
  }

  /* ***************************************************************
   * Metodo: interromperThreads
   * Funcao: Interrompe as threads.
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  public void interromperThreads() {
    for (int j = 0; j < 8; j++) {//pausando as threads para interromper sem haver conflito.
      carros[j].pausar();
    }
    for (int i = 0; i < 8; i++) {//interrompendo as threads e setando as variaveis booleanas.
      carros[i].interrupt();
      booleansIsVisible[i] = false;
      carros[i].posicionar();
    }
  }

  public void iniciarSemaforos() {
    for (int i = 0; i < N; i++) {
      if (i == 7 || i == 8 || i == 10 || i == 44) {
        rc[i] = new Semaphore(0);
      } else {
        rc[i] = new Semaphore(1);
      }
    }
  }

  public void iniciarCarros() {
    for (int i = 0; i < 8; i++) {
      carros[i] = new Car(carImage[i], i, posicaoInicial[i].getCoordenadaX(), posicaoInicial[i].getCoordenadaY(), posicaoInicial[i].getAngulo(), this);
      carros[i].posicionar();
    }
  }

  public double getVelocidadeCar(int id) {
    return velocidade[id].getValue();
  }

  @FXML
  public void pauseRedCar() {
    carros[0].pausar();
  }

  @FXML
  public void retomarRedCar() {
    carros[0].retomar();
  }

  @FXML
  public void showRedPath() {
    booleansIsVisible[0] = !booleansIsVisible[0];
    percursos[0].setVisible(booleansIsVisible[0]);
  }

  @FXML
  public void pauseGreenCar() {
    carros[1].pausar();
  }

  @FXML
  public void retomarGreenCar() {
    carros[1].retomar();
  }

  @FXML
  public void showGreenPath() {
    booleansIsVisible[1] = !booleansIsVisible[1];
    percursos[1].setVisible(booleansIsVisible[1]);
  }

  @FXML
  public void pausePinkCar() {
    carros[2].pausar();
  }

  @FXML
  public void retomarPinkCar() {
    carros[2].retomar();
  }

  @FXML
  public void showPinkPath() {
    booleansIsVisible[2] = !booleansIsVisible[2];
    percursos[2].setVisible(booleansIsVisible[2]);
  }

  @FXML
  public void pauseBlueCar() {
    carros[3].pausar();
  }

  @FXML
  public void retomarBlueCar() {
    carros[3].retomar();
  }

  @FXML
  public void showBluePath() {
    booleansIsVisible[3] = !booleansIsVisible[3];
    percursos[3].setVisible(booleansIsVisible[3]);
  }

  @FXML
  public void pauseYellowCar() {
    carros[4].pausar();
  }

  @FXML
  public void retomarYellowCar() {
    carros[4].retomar();
  }

  @FXML
  public void showYellowPath() {
    booleansIsVisible[4] = !booleansIsVisible[4];
    percursos[4].setVisible(booleansIsVisible[4]);
  }

  @FXML
  public void pauseOrangeCar() {
    carros[5].pausar();
  }

  @FXML
  public void retomarOrangeCar() {
    carros[5].retomar();
  }

  @FXML
  public void showOrangePath() {
    booleansIsVisible[5] = !booleansIsVisible[5];
    percursos[5].setVisible(booleansIsVisible[5]);
  }

  @FXML
  public void pausePurpleCar() {
    carros[6].pausar();
  }

  @FXML
  public void retomarPurpleCar() {
    carros[6].retomar();
  }

  @FXML
  public void showPurplePath() {
    booleansIsVisible[6] = !booleansIsVisible[6];
    percursos[6].setVisible(booleansIsVisible[6]);
  }

  @FXML
  public void pauseBrownCar() {
    carros[7].pausar();
  }

  @FXML
  public void retomarBrownCar() {
    carros[7].retomar();
  }

  @FXML
  public void showBrownPath() {
    booleansIsVisible[7] = !booleansIsVisible[7];
    percursos[7].setVisible(booleansIsVisible[7]);
  }


}
