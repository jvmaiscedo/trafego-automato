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
        movePinkCar();
        break;
      case 4:
        moveBlueCar();
        break;
      case 5:
        moveYellowCar();
        break;
      case 6:
        moveOrangeCar();
        break;
      case 7:
        movePurpleCar();
        break;
    }
  }

  private void move_down(double lim){
    this.img.setRotate(180);
    while(img.getLayoutY()<lim){
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY()+2));
      try {
        sleep(14);
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
        sleep(14);
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
        sleep(14);
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
        sleep(14);
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
      move_right(770);
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
      move_down(419);
      Semaforo.verde_laranja.acquire();
      Semaforo.d3d4.acquire();
      move_down(460);
      move_right(365);
      Semaforo.verde_amarelo.release();
      move_right(435);
      Semaforo.cd4.acquire();
      move_right(524);
      Semaforo.d3d4.release();
      Semaforo.cd4.release();
      move_right(592);
      Semaforo.cd5.acquire();
      Semaforo.d5d6.acquire();
      move_right(678);
      Semaforo.cd5.release();
      move_right(718);
      Semaforo.vermelho_verde.acquire();
      move_right(768);
      move_down(504);
      Semaforo.d5d6.release();
      move_down(752);
      move_left(678);
      Semaforo.f4f5.acquire();
      move_left(524);
      Semaforo.f3f4.acquire();
      move_left(435);
      Semaforo.f4f5.release();
      move_left(365);
      Semaforo.f2f3.acquire();
      move_left(280);
      Semaforo.f3f4.release();
      move_left(118);
      Semaforo.f2f3.release();
      move_left(11);
      move_up(504);
      Semaforo.verde_rosa.acquire();
      Semaforo.c1d1.acquire();
      move_up(419);
      Semaforo.verde_laranja.release();
      move_up(254);
      Semaforo.c1d1.release();
      move_up(-8);
      move_right(120);
      Semaforo.a2a3.acquire();
      move_right(280);
      Semaforo.verde_amarelo.acquire();
      Semaforo.a3b3.acquire();
      move_right(321);
      move_down(36);
      Semaforo.a2a3.release();
      Semaforo.verde_rosa.release();
      Semaforo.vermelho_verde.release();
      move_down(196);
      Semaforo.a3b3.release();
      realoCar();

    } catch (InterruptedException e) {}
  }

  private void movePinkCar(){
    try{
     move_up(196);
     Semaforo.rosa_roxo.acquire();
     move_up(36);
     Semaforo.rosa_marrom.acquire();
     Semaforo.rosa_laranja.acquire();
     Semaforo.a3a4.acquire();
     move_up(-8);
     move_left(365);
     Semaforo.verde_rosa.acquire();
     Semaforo.a2a3.acquire();
     move_left(280);
     Semaforo.a3a4.release();
     Semaforo.rosa_roxo.release();
     move_left(205);
     Semaforo.a1c1.acquire();
     move_left(120);
     Semaforo.a2a3.release();
     Semaforo.rosa_azul.release();
     Semaforo.rosa_marrom.release();
     move_left(10);
     move_down(254);
     Semaforo.rosa_roxo_c.acquire();
     Semaforo.rosa_marrom_c.acquire();
     move_down(335);
     Semaforo.a1c1.release();
     move_down(470);
     move_right(51);
     Semaforo.verde_rosa.release();
     move_right(118);
     Semaforo.cd2.acquire();
     move_right(208);
     Semaforo.cd2.release();
     Semaforo.rosa_roxo_c.release();
     Semaforo.rosa_marrom_c.release();
     move_right(280);
     Semaforo.cd3.acquire();
     Semaforo.d3d4.acquire();
     move_right(365);
     Semaforo.cd3.release();
     move_right(435);
     Semaforo.rosa_azul.acquire();
     move_right(475);
     move_up(419);
     Semaforo.d3d4.release();
     Semaforo.rosa_laranja.release();
     move_up(390);
     realoCar();


    } catch (InterruptedException e){}
  }

  private void moveBlueCar(){
    try{
      move_up(336);
      Semaforo.b2c2.acquire();
      move_up(196);
      Semaforo.a2b2.acquire();
      move_up(109);
      Semaforo.b2c2.release();
      move_up(35);
      Semaforo.vermelho_azul.acquire();
      Semaforo.rosa_azul.acquire();
      Semaforo.a2a3.acquire();
      move_up(-8);
      move_right(205);
      Semaforo.a2b2.release();
      move_right(280);
      Semaforo.a3a4.acquire();
      move_right(362);
      Semaforo.a2a3.release();
      move_right(435);
      Semaforo.a4b4.acquire();
      move_right(475);
      move_down(35);
      Semaforo.vermelho_azul.release();
      Semaforo.a3a4.release();
      move_down(196);
      Semaforo.a4b4.release();
      move_down(419);
      Semaforo.cd4.acquire();
      move_down(504);
      Semaforo.rosa_azul.release();
      Semaforo.cd4.release();
      move_down(573);
      Semaforo.e4f4.acquire();
      move_down(710);
      Semaforo.azul_laranja.acquire();
      Semaforo.vermelho_azul_f.acquire();
      Semaforo.f3f4.acquire();
      move_down(745);
      move_left(435);
      Semaforo.e4f4.release();
      move_left(365);
      Semaforo.f2f3.acquire();
      move_left(280);
      Semaforo.f3f4.release();
      move_left(208);
      Semaforo.e2f2.acquire();
      move_left(160);
      move_up(710);
      Semaforo.vermelho_azul_f.release();
      Semaforo.azul_laranja.release();
      Semaforo.f2f3.release();
      move_up(659);
      Semaforo.d2e2.acquire();
      move_up(573);
      Semaforo.e2f2.release();
      move_up(504);
      Semaforo.cd2.acquire();
      move_up(419);
      Semaforo.cd2.release();
      Semaforo.d2e2.release();
      move_up(426);
      realoCar();

    } catch (InterruptedException e){}
  }
  private void moveYellowCar(){
    try{
      move_up(504);
      Semaforo.vermelho_amarelo.acquire();
      Semaforo.verde_amarelo.acquire();
      Semaforo.cd3.acquire();
      move_up(419);
      Semaforo.cd3.release();
      move_up(196);
      Semaforo.a3b3.acquire();
      move_up(36);
      Semaforo.a3a4.acquire();
      move_up(-8);
      move_right(362);
      Semaforo.a3b3.release();
      Semaforo.verde_amarelo.release();
      move_right(435);
      Semaforo.a4a5.acquire();
      move_right(522);
      Semaforo.a3a4.release();
      move_right(595);
      Semaforo.a5b5.acquire();
      move_right(630);
      move_down(36);
      Semaforo.a4a5.release();
      Semaforo.vermelho_amarelo.release();
      move_down(109);
      Semaforo.b5c5.acquire();
      move_down(196);
      Semaforo.a5b5.release();
      move_down(337);
      Semaforo.b5c5.release();
      move_down(419);
      Semaforo.d5e5.acquire();
      Semaforo.cd5.acquire();
      move_down(504);
      Semaforo.cd5.release();
      Semaforo.d5e5.release();
      move_down(573);
      Semaforo.e5f5.acquire();
      move_down(710);
      Semaforo.vermelho_amarelo_f.acquire();
      Semaforo.amarelo_laranja.acquire();
      Semaforo.f4f5.acquire();
      move_down(745);
      move_left(592);
      Semaforo.e5f5.release();
      move_left(524);
      Semaforo.f3f4.acquire();
      move_left(435);
      Semaforo.f4f5.release();
      move_left(365);
      Semaforo.e3f3.acquire();
      move_left(321);
      move_left(710);
      Semaforo.f3f4.release();
      Semaforo.vermelho_amarelo_f.release();
      Semaforo.amarelo_laranja.release();
      move_up(573);
      Semaforo.e3f3.release();
      realoCar();
    } catch (InterruptedException e){}
  }

  private void moveOrangeCar(){
    try{
      move_left(527);
      Semaforo.rosa_laranja.acquire();
      Semaforo.cd4.acquire();
      move_left(435);
      Semaforo.cd4.release();
      move_left(365);
      Semaforo.cd3.acquire();
      move_left(280);
      Semaforo.cd3.release();
      Semaforo.verde_laranja.release();
      move_left(208);
      Semaforo.d1d2.acquire();
      Semaforo.cd2.acquire();
      move_left(118);
      Semaforo.cd2.release();
      move_left(51);
      Semaforo.verde_laranja.acquire();
      Semaforo.d1f2.acquire();
      move_left(11);
      move_down(504);
      Semaforo.d1d2.release();
      Semaforo.rosa_laranja.release();
      move_down(752);
      move_right(118);
      Semaforo.laranja_marrom.acquire();
      Semaforo.amarelo_laranja.acquire();
      Semaforo.azul_laranja.acquire();
      Semaforo.f2f3.acquire();
      move_right(208);
      Semaforo.d1f2.release();
      move_right(280);
      Semaforo.f3f4.acquire();
      move_right(365);
      Semaforo.f2f3.release();
      move_right(435);
      Semaforo.f4f5.acquire();
      move_right(524);
      Semaforo.f3f4.release();
      Semaforo.azul_laranja.release();
      move_right(592);
      Semaforo.d6f5.acquire();
      move_right(678);
      Semaforo.f4f5.release();
      Semaforo.amarelo_laranja.release();
      Semaforo.laranja_marrom.release();
      move_right(745);
      move_up(504);
      Semaforo.d5d6.acquire();
      move_up(460);
      move_left(718);
      Semaforo.d6f5.release();
      move_left(678);
      Semaforo.cd5.acquire();
      move_left(592);
      Semaforo.cd5.release();
      Semaforo.d5d6.release();
      realoCar();
    } catch (InterruptedException e){}
  }
  private void movePurpleCar(){
    try{
      move_right(592);
      Semaforo.b5c5.acquire();
      move_right(620);
      move_down(254);
      Semaforo.c5c6.acquire();
      move_down(300);
      move_right(678);
      Semaforo.b5c5.release();
      move_right(718);
      Semaforo.c6d6.acquire();
      move_right(745);
      move_down(337);
      Semaforo.c5c6.release();
      move_down(419);
      Semaforo.d5d6.acquire();
      move_down(462);
      move_left(718);
      Semaforo.c6d6.release();
      move_left(678);
      Semaforo.d5e5.acquire();
      move_left(621);
      move_down(504);
      Semaforo.d5d6.release();
      move_down(610);
      move_left(592);
      Semaforo.d5e5.release();
      move_left(524);
      Semaforo.e4f4.acquire();
      move_left(480);
      move_down(710);
      Semaforo.f3f4.acquire();
      move_down(745);
      move_left(435);
      Semaforo.e4f4.release();
      move_left(365);
      Semaforo.e3f3.acquire();
      move_left(321);
      move_up(710);
      Semaforo.f3f4.release();
      move_up(610);
      move_left(280);
      Semaforo.e3f3.release();
      move_left(208);
      Semaforo.d2e2.acquire();
      move_left(160);
      move_up(504);
      Semaforo.d1d2.acquire();
      move_up(460);
      move_left(118);
      Semaforo.d2e2.release();
      move_left(51);
      Semaforo.c1d1.acquire();
      move_left(11);
      move_up(419);
      Semaforo.d1d2.release();
      move_up(336);
      Semaforo.c1c2.acquire();
      move_up(278);
      move_right(51);
      Semaforo.c1d1.release();
      move_right(118);
      Semaforo.b2c2.acquire();
      move_right(160);
      move_up(254);
      Semaforo.c1c2.release();
      move_up(155);
      move_right(208);
      Semaforo.b2c2.release();
      move_right(280);
      Semaforo.a3b3.acquire();
      move_right(320);
      move_up(35);
      Semaforo.rosa_roxo.acquire();
      Semaforo.a3a4.acquire();
      move_up(-8);
      move_right(362);
      Semaforo.a3b3.release();
      move_right(440);
      Semaforo.a4b4.acquire();
      move_right(480);
      move_down(35);
      Semaforo.a3a4.release();
      move_down(155);
      move_right(524);
      Semaforo.a4b4.release();
      Semaforo.rosa_roxo.release();
      realoCar();
    } catch (InterruptedException e){}
  }




}
