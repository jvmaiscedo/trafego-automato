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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Car;
import model.Move;


public class MainController implements Initializable {
  //Elementos de imagem
  @FXML
  ImageView redCar;
  @FXML
  ImageView greenCar;

  Car carroVermelho;
  Car carroVerde;
  Move mover;
  String [] moverRedCar;
  double [] limRedCar;
  String [] moverGreenCar;
  double [] limGreenCar;
  public static Semaphore semaforo1 = new Semaphore(0);
  public static Semaphore semaforo2 = new Semaphore(1);
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    mover = new Move();
    moverRedCar = new String[] {"r","d","l","u"};
    limRedCar = new double[]{759,748,15,-8};
    moverGreenCar = new String[] {"d","r","d","l","u","r"};
    limGreenCar = new double[]{455,759,748,14,-8,313};
    carroVermelho = new Car(redCar, 1, 20,-8,this, moverRedCar, limRedCar);
    carroVerde = new Car(greenCar, 2,321,33,this, moverGreenCar, limGreenCar);

    carroVermelho.start();
    carroVerde.start();

  }



}
