/* ***************************************************************
* Autor............: Joao Victor Gomes Macedo
* Matricula........: 202210166
* Inicio...........: 22/11/2023
* Ultima alteracao.: 03/12/2023
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
  //Imagens dos carros
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
  //Imagens dos percursos
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
  //Elementos de controle
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
  private Slider[] velocidade;//Vetor para organizar os sliders e acessa-los diretamente via id.
  private ImageView[] carImage;//Vetor para organizar as imagens e acessa-las diretamente via id.
  private ImageView[] percursos;//Vetor para organizar as imagens e acessa-las diretamente via id.
  private final Positions[] posicaoInicial = new Positions[8];//Vetor para guardar os objetos contendo a posicao inicial de cada carro
  private Car[] carros = new Car[8];//Vetor para guardar os carros e tornar mais pratico a instancia e manipulacao de objetos Car
  private static final int N = 53;//Variavel estatica para o numero de semaforos total
  private static final int M = 8;//Variavel estatica para o numero de carros
  public static Semaphore[] rc = new Semaphore[N];//Vetor para armazenar os semaforos e facilitar a manipulacao destes
  public boolean[] booleansIsVisible = new boolean[8];//Vetor para armazenar as variaveis que controlam a visibilidade dos percursos

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //instanciando as posicoes iniciais de cada carro
    posicaoInicial[0] = new Positions(726, -8, 270);
    posicaoInicial[1] = new Positions(321, 196, 180);
    posicaoInicial[2] = new Positions(475, 390, 0);
    posicaoInicial[3] = new Positions(162, 426, 0);
    posicaoInicial[4] = new Positions(321, 573, 0);
    posicaoInicial[5] = new Positions(592, 468, 270);
    posicaoInicial[6] = new Positions(519, 155, 90);
    posicaoInicial[7] = new Positions(160, 710, 0);
    //agrupando os elementos em vetores
    percursos = new ImageView[]{redPath, greenPath, pinkPath, bluePath, yellowPath, orangePath, purplePath, brownPath};
    velocidade = new Slider[]{speedRed, speedGreen, speedPink, speedBlue, speedYellow, speedOrange, speedPurple, speedBrown};
    carImage = new ImageView[]{redCar, greenCar, pinkCar, blueCar, yellowCar, orangeCar, purpleCar, brownCar};
    iniciar();//chamando o metodo que da inicio a aplicacao
  }

  /* ***************************************************************
   * Metodo: iniciar
   * Funcao: iniciar os elementos, instanciar os objetos Car e semaforos
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  public void iniciar() {
    iniciarSemaforos();//instanciando todos os semaforos
    for (int i = 0; i < M; i++) {
      velocidade[i].setValue(6);//setando a velocidade inicial
      percursos[i].setVisible(false);//setando a visibilidade dos percursos
    }
    iniciarCarros();//instanciando os carros
    for (int j = 0; j < M; j++) {
      carros[j].start();//dando start nas threads
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
    iniciar();
  }

  /* ***************************************************************
   * Metodo: interromperThreads
   * Funcao: Interrompe as threads.
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  public void interromperThreads() {
    for (int j = 0; j < M; j++) {//pausando as threads para interromper sem haver conflito.
      carros[j].pausar();
    }
    for (int i = 0; i < M; i++) {//interrompendo as threads e setando as variaveis booleanas.
      carros[i].interrupt();
      booleansIsVisible[i] = false;
    }
  }

  /* ***************************************************************
   * Metodo: iniciarSemaforos
   * Funcao: Instancia os semaforos
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  public void iniciarSemaforos() {
    for (int i = 0; i < N; i++) {
      if (i == 7 || i == 8 || i == 10 || i == 44) {
        rc[i] = new Semaphore(0);//semaforos iniciados em 0 para os carros que iniciam o movimento dentro da RC
      } else {
        rc[i] = new Semaphore(1);//semaforos iniciados em 1 para aquisicao das RC`s
      }
    }
  }

  /* ***************************************************************
   * Metodo: iniciarCarros
   * Funcao: Instancia os carros
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  public void iniciarCarros() {
    for (int i = 0; i < M; i++) {
      carros[i] = new Car(carImage[i], i, posicaoInicial[i].getCoordenadaX(), posicaoInicial[i].getCoordenadaY(), posicaoInicial[i].getAngulo(), this);//Criando os objetos Car
    }
  }

  /* ***************************************************************
   * Metodo: getVelocidadeCar
   * Funcao: Verifica o valor atual do slider de velocidade no
   *         carro baseado em seu Id.
   * Parametros: id do tipo inteiro
   * Retorno: valor numerico de tipo double
   *************************************************************** */
  public double getVelocidadeCar(int id) {
    return velocidade[id].getValue();
  }

  /* ***************************************************************
   * Metodo: pauseRedCar
   * Funcao: pausa o carro vermelhor
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void pauseRedCar() {
    carros[0].pausar();
  }

  /* ***************************************************************
   * Metodo: retormarRedCar
   * Funcao: retoma o carro vermelhor
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void retomarRedCar() {
    carros[0].retomar();
  }

  /* ***************************************************************
   * Metodo: showRedPath
   * Funcao: mostra ou oculta o percurso do carro
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void showRedPath() {
    booleansIsVisible[0] = !booleansIsVisible[0];
    percursos[0].setVisible(booleansIsVisible[0]);
  }

  //========= OS METODOS ABAIXO SEGUEM O MESMO PADRAO DOS TRES ANTERIORES =========//
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
