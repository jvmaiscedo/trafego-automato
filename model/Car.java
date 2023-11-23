package model;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import sun.applet.Main;

public class Car extends Thread{
  private ImageView img;
  private int id;
  private double posInicialX;
  private double posInicialY;
  private double anguloInicial;
  private String[] movimentos;
  private double[] limites;
  private MainController control;

  public Car(ImageView img, int id, double posInicialX, double posInicialY, MainController control, String[] movimentos, double[] limites){
    this.img = img;
    this.id = id;
    this.control = control;
    this.posInicialX = posInicialX;
    this.posInicialY = posInicialY;
    this.movimentos = movimentos;
    this.limites = limites;
    this.anguloInicial = img.getRotate();
  }

  @Override
  public void run() {
    while (true){
      movendo(this.id);
    }
  }

  private void movendo(int id){
    switch (id){
      case 1:
        moveRedCar();
        break;
      case 2:
        moveGreenCar();
        break;
    }
  }

  private void move_down(double lim){
    this.img.setRotate(180);
    while(img.getLayoutY()<lim){
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY()+2));
      try {
        sleep(20);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
  private void move_up(double lim){
    this.img.setRotate(0);
    while(img.getLayoutY()>lim){
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY()-2));
      try {
        sleep(20);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
  private void move_right(double lim){
    this.img.setRotate(90);
    while(img.getLayoutX()<lim){
      Platform.runLater(() -> img.setLayoutX(img.getLayoutX()+2));
      try {
        sleep(20);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
  private void move_left(double lim){
    this.img.setRotate(270);
    while(img.getLayoutX()>lim){
      Platform.runLater(() -> img.setLayoutX(img.getLayoutX()-2));
      try {
        sleep(20);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
  private void realoCar(){

    Platform.runLater(()->{
      this.img.setRotate(anguloInicial);
      this.img.setLayoutX(this.posInicialX);
      this.img.setLayoutY(this.posInicialY);}
      );
  }

  private void moveRedCar(){
    try {
      move_right(313);
      MainController.semaforo1.release();
      move_right(759);
      move_down(416);
      MainController.semaforo1.acquire();
      move_down(748);
      move_left(15);
      move_up(-8);
      realoCar();
    } catch (InterruptedException e) {
    }
  }

  private void moveGreenCar(){
    try {
      move_down(455);
      move_right(730);
      MainController.semaforo1.acquire();
      move_right(759);
      move_down(748);
      move_left(14);
      move_up(-8);
      move_right(313);
      MainController.semaforo1.release();
      realoCar();
    } catch (InterruptedException e) {}

  }





}
