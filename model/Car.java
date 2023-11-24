package model;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import sun.applet.Main;
import model.Semaforo;
import java.lang.management.ManagementFactory;

public class Car extends Thread{
  private ImageView img;
  private int id;
  private double posInicialX;
  private double posInicialY;
  private double anguloInicial;
  private String[] movimentos;
  private double[] limites;
  private MainController control;

  public Car(ImageView img, int id, double posInicialX, double posInicialY, MainController control){
    this.img = img;
    this.id = id;
    this.control = control;
    this.posInicialX = posInicialX;
    this.posInicialY = posInicialY;
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
      case 3:
        moveOrangeCar();
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
      move_left(678);
      Semaforo.vermelho_marrom.acquire();
      Semaforo.vermelho_amarelo.acquire();
      move_left(522);
      Semaforo.vermelho_azul.acquire();
      Semaforo.a3a4.acquire();
      move_left(362);
      Semaforo.vermelho_verde.acquire();
      Semaforo.a2a3.acquire();
      move_left(280);
      Semaforo.a3a4.release();
      Semaforo.vermelho_amarelo.release();
      move_left(205);
      Semaforo.a1c1.acquire();
      move_left(120);
      Semaforo.a2a3.release();
      Semaforo.vermelho_marrom.release();
      Semaforo.vermelho_azul.release();
      move_left(11);
      move_down(254);
      Semaforo.c1d1.acquire();
      move_down(419);
      Semaforo.a1c1.release();
      Semaforo.d1f2.acquire();
      move_down(504);
      Semaforo.c1d1.release();
      move_down(745);
      move_right(118);
      Semaforo.vermelho_marrom_f.acquire();
      Semaforo.vermelho_azul_f.acquire();
      move_right(208);
      Semaforo.d1f2.release();
      move_right(280);
      Semaforo.vermelho_amarelo_f.acquire();
      Semaforo.f3f4.acquire();
      move_right(524);
      Semaforo.vermelho_azul_f.release();
      Semaforo.f3f4.release();
      move_right(435);
      Semaforo.f4f5.acquire();
      move_right(592);
      Semaforo.d6f5.acquire();
      move_right(678);
      Semaforo.f4f5.release();
      Semaforo.vermelho_amarelo_f.release();
      Semaforo.vermelho_marrom_f.release();
      move_right(752);
      move_up(504);
      Semaforo.c6d6.acquire();
      move_up(419);
      Semaforo.d6f5.release();
      Semaforo.vermelho_verde.release();
      move_up(254);
      Semaforo.c6d6.release();
      move_up(-8);
      realoCar();

    } catch (InterruptedException e) {
    }
  }

  private void moveGreenCar(){
    try {
      move_down(430);
      MainController.semaforo2.acquire();
      move_down(455);
      move_right(730);
      MainController.semaforo1.acquire();
      MainController.semaforo2.release();
      move_right(759);
      move_down(748);
      move_left(14);
      move_up(-8);
      move_right(313);
      MainController.semaforo1.release();
      realoCar();
    } catch (InterruptedException e) {}
  }
  private void moveOrangeCar(){
    try{
      move_right(286);
      MainController.semaforo2.acquire();
      move_right(730);
      MainController.semaforo1.acquire();
      MainController.semaforo2.release();
      move_right(759);
      move_down(748);
      move_left(14);
      move_up(478);
      MainController.semaforo1.release();
      realoCar();

    } catch (InterruptedException e){}
  }





}
