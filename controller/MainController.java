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
  Car carroVermelho;
  Car carroVerde;
  Car carroRosa;
  Car carroAzul;
  Car carroAmarelo;
  Car carroLaranja;
  Car carroRoxo;
  Semaforo mover;
  String [] moverRedCar;
  double [] limRedCar;
  String [] moverGreenCar;
  double [] limGreenCar;
  public static Semaphore semaforo1 = new Semaphore(0);
  public static Semaphore semaforo2 = new Semaphore(1);

  @Override
  public void initialize(URL url, ResourceBundle rb) {


    moverRedCar = new String[] {"r","d","l","u"};
    limRedCar = new double[]{759,748,15,-8};
    moverGreenCar = new String[] {"d","r","d","l","u","r"};
    limGreenCar = new double[]{455,759,748,14,-8,313};
    carroVermelho = new Car(redCar, 1, 726,-8,this);
    carroVerde = new Car(greenCar, 2,321,196,this);
    carroRosa = new Car(pinkCar, 3, 475, 390, this);
    carroAzul = new Car(blueCar, 4,162,426,this);
    carroAmarelo = new Car(yellowCar, 5,321,573,this);
    carroLaranja = new Car(orangeCar, 6, 592,468, this);
    carroRoxo = new Car(purpleCar,7,519,155,this);
    carroVermelho.start();
    carroVerde.start();
    carroRosa.start();
    carroAzul.start();
    carroAmarelo.start();
    carroLaranja.start();
    carroRoxo.start();

    //carroLaranja.start();
  }



}
