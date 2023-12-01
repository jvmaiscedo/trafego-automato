package model;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Car extends Thread {
  private ImageView img;
  private int id;
  private volatile boolean isPaused = false;
  private double posInicialX;
  private double posInicialY;
  private double anguloInicial;
  private MainController control;

  public Car(ImageView img, int id, double posInicialX, double posInicialY, double anguloInicial, MainController control) {
    this.img = img;
    this.id = id;
    this.control = control;
    this.posInicialX = posInicialX;
    this.posInicialY = posInicialY;
    this.anguloInicial = anguloInicial;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      movendo(this.id);
    }
  }

  private void movendo(int id) {
    switch (id) {
      case 0:
        moveRedCar();
        break;
      case 1:
        moveGreenCar();
        break;
      case 2:
        movePinkCar();
        break;
      case 3:
        moveBlueCar();
        break;
      case 4:
        moveYellowCar();
        break;
      case 5:
        moveOrangeCar();
        break;
      case 6:
        movePurpleCar();
        break;
      case 7:
        moveBrownCar();
        break;
    }
  }


  public double getPosInicialX() {
    return posInicialX;
  }

  public double getPosInicialY() {
    return posInicialY;
  }

  public double getAnguloInicial() {
    return anguloInicial;
  }

  private void move_down(double lim) {
    Platform.runLater(() -> this.img.setRotate(180));
    while (img.getLayoutY() < lim && !Thread.interrupted()) {
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY() + 1));
      sleepTime((int) (control.getVelocidadeCar(this.id)));
      pausando();
    }
  }

  private void move_up(double lim) {
    Platform.runLater(() -> this.img.setRotate(0));
    while (img.getLayoutY() > lim && !Thread.interrupted()) {
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY() - 1));
      sleepTime((int) (control.getVelocidadeCar(this.id)));
      pausando();
    }
  }

  private void move_right(double lim) {
    Platform.runLater(() -> this.img.setRotate(90));
    while (img.getLayoutX() < lim && !Thread.interrupted()) {
      Platform.runLater(() -> img.setLayoutX(img.getLayoutX() + 1));
      sleepTime((int) (control.getVelocidadeCar(this.id)));
      pausando();
    }
  }

  private void move_left(double lim) {
    Platform.runLater(() -> this.img.setRotate(270));
    while (img.getLayoutX() > lim && !Thread.interrupted()) {
      Platform.runLater(() -> img.setLayoutX(img.getLayoutX() - 1));
      sleepTime((int) (control.getVelocidadeCar(this.id)));
      pausando();
    }
  }

  public void posicionar() {
    Platform.runLater(() -> {
        this.img.setRotate(anguloInicial);
        this.img.setLayoutX(this.posInicialX);
        this.img.setLayoutY(this.posInicialY);
      }
    );
  }

  /* ***************************************************************
   * Metodo: sleepTime
   * Funcao: Faz o processo dormir.
   * Parametros: Valor numerico do tipo inteiro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void sleepTime(int time) {
    try {
      sleep((long) time);
    } catch (InterruptedException e) {
    }
  }

  private void moveRedCar() {
    try {
      move_left(678);
      MainController.rc[0].acquire();//Rc vermelho e marrom
      MainController.rc[1].acquire();//Rc vermelho e amarelo
      move_left(522);
      MainController.rc[2].acquire();//Rc vermelho e o azul
      MainController.rc[24].acquire();//Rc a3a4
      move_left(362);
      MainController.rc[3].acquire();//Rc vermelho e verde
      MainController.rc[23].acquire();//Rc a2a3
      move_left(280);
      MainController.rc[24].release();//Rc a3a4
      MainController.rc[1].release();//Rc vermelho e amarelo
      move_left(205);
      MainController.rc[30].acquire();// Rc a1c1
      move_left(120);
      MainController.rc[23].release();//Rc a2a3
      MainController.rc[0].release();//Rc vermelho e marrom
      MainController.rc[2].release();//Rc vermelho e azul
      move_left(11);
      move_down(254);
      MainController.rc[35].acquire();//Rc c1d1
      move_down(419);
      MainController.rc[30].release();//Rc a1c1
      MainController.rc[40].acquire();//Rc d1f2
      move_down(504);
      MainController.rc[35].release();//Rc c1d1
      move_down(760);
      move_right(118);
      MainController.rc[4].acquire();//Rc vermelho e marrom 2
      MainController.rc[5].acquire();//Rc vemelho e azul 2
      move_right(208);
      MainController.rc[40].release();//Rc d1f2
      move_right(280);
      MainController.rc[6].acquire();//Rc Vermelho e amarelo 2
      MainController.rc[49].acquire();//Rc f3f4
      move_right(524);
      MainController.rc[5].release();//Rc vermelho e azul 2
      MainController.rc[49].release();//Rc f3f4
      move_right(435);
      MainController.rc[50].acquire();//Rc f4f5
      move_right(592);
      MainController.rc[43].acquire();//Rc d6f5
      move_right(678);
      MainController.rc[50].release();//Rc f4f5
      MainController.rc[6].release();//Rc vermelho e amarelo 2
      MainController.rc[4].release();//Rc vermelho e marrom 2
      move_right(770);
      move_up(504);
      MainController.rc[36].acquire();//Rc c6d6
      move_up(419);
      MainController.rc[43].release();//Rc d6f5
      MainController.rc[3].release();//Rc vermelho e verde
      move_up(254);
      MainController.rc[36].release();//Semaforo c6d6
      move_up(-8);
      move_right(726);
    } catch (InterruptedException e) {
    }
  }

  private void moveGreenCar() {
    try {
      move_down(419);
      MainController.rc[8].acquire();//Rc verde e laranja
      MainController.rc[38].acquire();//Rc d3d4
      move_down(460);
      move_right(365);
      MainController.rc[7].release();//Rc verde e amarelo
      move_right(435);
      MainController.rc[21].acquire();//Rc cd4
      move_right(524);
      MainController.rc[38].release();//Rc d3d4
      MainController.rc[21].release();//Rc cd4
      move_right(592);
      MainController.rc[22].acquire();//Rc cd5
      MainController.rc[39].acquire();//Rc d5d6
      move_right(678);
      MainController.rc[22].release();//Rc cd5
      move_right(718);
      MainController.rc[3].acquire();//Rc vermelho e verde
      move_right(768);
      move_down(504);
      MainController.rc[39].release();//Rc d5d6
      move_down(752);
      move_left(678);
      MainController.rc[50].acquire();//Rc f4f5
      move_left(524);
      MainController.rc[49].acquire();//Rc f3f4
      move_left(435);
      MainController.rc[50].release();//Rc f4f5
      move_left(365);
      MainController.rc[48].acquire();//Rc f2f3
      move_left(280);
      MainController.rc[49].release();//Rc f3f4
      move_left(118);
      MainController.rc[48].release();//Rc f2f3
      move_left(11);
      move_up(504);
      MainController.rc[9].acquire();//Rc verde e rosa
      MainController.rc[35].acquire();//Rc c1d1
      move_up(419);
      MainController.rc[8].release();//Rc verde e laranja
      move_up(254);
      MainController.rc[35].release();//Rc c1d1
      move_up(-8);
      move_right(120);
      MainController.rc[23].acquire();//Rc a2a3
      move_right(280);
      MainController.rc[7].acquire();//Rc verde e amarelo
      MainController.rc[27].acquire();//Rc a3b3
      move_right(321);
      move_down(36);
      MainController.rc[23].release();//Rc a2a3
      MainController.rc[9].release();//Rc verde e rosa
      MainController.rc[3].release();//Rc vermelho e verde
      move_down(196);
      MainController.rc[27].release();//Rc a3b3
    } catch (InterruptedException e) {
    }
  }

  private void movePinkCar() {
    try {
      move_up(196);
      MainController.rc[11].acquire();//Rc rosa e roxo
      move_up(36);
      MainController.rc[12].acquire();//Rc rosa e marrom
      MainController.rc[51].acquire();//Rc amarelo e rosa especial
      MainController.rc[24].acquire();//Rc a3a4
      move_up(-8);
      move_left(365);
      MainController.rc[15].acquire();//Rc rosa e laranja
      MainController.rc[9].acquire();//Rc verde e rosa
      MainController.rc[23].acquire();//Rc a2a3
      move_left(280);
      MainController.rc[24].release();//Rc a3a4
      MainController.rc[51].release();//Rc amarelo e rosa especial
      MainController.rc[11].release();//Rc rosa e roxo
      move_left(205);
      MainController.rc[30].acquire();//Rc a1c1
      move_left(120);
      MainController.rc[23].release();//Rc a2a3
      MainController.rc[10].release();//Rc rosa e azul
      MainController.rc[12].release();//Rc rosa e marrom
      move_left(10);
      move_down(254);
      MainController.rc[14].acquire();//Rc rosa e roxo 2
      MainController.rc[13].acquire();//Rc rosa e marrom 2
      move_down(335);
      MainController.rc[30].release();//Rc a1c1
      move_down(470);
      move_right(51);
      MainController.rc[9].release();//Rc verde e rosa
      move_right(118);
      MainController.rc[19].acquire();//Rc cd2
      move_right(208);
      MainController.rc[19].release();//Rc cd2
      MainController.rc[14].release();//Rc rosa e roxo 2
      MainController.rc[13].release();//Rc rosa e marrom 2
      move_right(280);
      MainController.rc[20].acquire();//Rc cd3
      MainController.rc[38].acquire();//Rc d3d4
      move_right(365);
      MainController.rc[20].release();//Rc cd3
      move_right(435);
      MainController.rc[10].acquire();//Rc rosa e azul
      move_right(475);
      move_up(419);
      MainController.rc[38].release();//Rc d3d4
      MainController.rc[15].release();//Rc rosa e laranja
      move_up(390);
    } catch (InterruptedException e) {
    }
  }

  private void moveBlueCar() {
    try {
      move_up(336);
      MainController.rc[31].acquire();//Rc b2c2
      move_up(196);
      MainController.rc[26].acquire();//Rc a2b2
      move_up(109);
      MainController.rc[31].release();//Rc b2c2
      move_up(35);
      MainController.rc[2].acquire();//Rc vermelho e azul
      MainController.rc[10].acquire();//Rc rosa e azul
      MainController.rc[23].acquire();//Rc a2a3
      move_up(-8);
      move_right(205);
      MainController.rc[26].release();//Rc a2b2
      move_right(280);
      MainController.rc[24].acquire();//Rc a3a4
      move_right(362);
      MainController.rc[23].release();//Rc a2a3
      move_right(435);
      MainController.rc[28].acquire();//Rc a4b4
      move_right(475);
      move_down(35);
      MainController.rc[2].release();//Rc vermelho e azul
      MainController.rc[24].release();//Rc a3a4
      move_down(196);
      MainController.rc[28].release();//Rc a4b4
      move_down(419);
      MainController.rc[21].acquire();//Rc cd4
      move_down(504);
      MainController.rc[10].release();//Rc rosa e azul
      MainController.rc[21].release();//Rc cd4
      move_down(573);
      MainController.rc[46].acquire();//Rc e4f4
      move_down(710);
      MainController.rc[16].acquire();//Rc azul e laranja
      MainController.rc[5].acquire();//Rc vermelho e azul 2
      MainController.rc[49].acquire();//Rc f3f4
      move_down(756);
      move_left(435);
      MainController.rc[46].release();//Rc e4f4
      move_left(365);
      MainController.rc[48].acquire();//Rc f2f3
      move_left(280);
      MainController.rc[49].release();//Rc f3f4
      move_left(208);
      MainController.rc[44].acquire();//Rc e2f2
      move_left(160);
      move_up(710);
      MainController.rc[5].release();//Rc vermelho e azul 2
      MainController.rc[16].release();//Rc azul e laranja
      MainController.rc[48].release();//Rc f2f3
      move_up(659);
      MainController.rc[41].acquire();//Rc d2e2
      move_up(573);
      MainController.rc[44].release();//Rc e2f2
      move_up(504);
      MainController.rc[19].acquire();//Rc cd2
      move_up(419);
      MainController.rc[19].release();//Rc cd2
      MainController.rc[41].release();//Rc d2e2
      move_up(426);
    } catch (InterruptedException e) {
    }
  }

  private void moveYellowCar() {
    try {
      move_up(504);
      MainController.rc[51].acquire();//Rc amarelo e rosa especial
      MainController.rc[1].acquire();//Rc vermelho e amarelo
      MainController.rc[7].acquire();//Rc verde e amarelo
      MainController.rc[20].acquire();//Rc cd3
      move_up(419);
      MainController.rc[20].release();//Rc cd3
      move_up(196);
      MainController.rc[27].acquire();//Rc a3b3
      move_up(36);
      MainController.rc[24].acquire();//Rc a3a4
      move_up(-8);
      move_right(362);
      MainController.rc[27].release();//Rc a3b3
      MainController.rc[7].release();//Rc verde e amarelo
      move_right(435);
      MainController.rc[25].acquire();//Rc a4a5
      move_right(522);
      MainController.rc[24].release();//Rc a3a4
      MainController.rc[51].release();//Rc amarelo e rosa especial
      move_right(595);
      MainController.rc[29].acquire();//Rc a5b5
      move_right(630);
      move_down(36);
      MainController.rc[25].release();//Rc a4a5
      MainController.rc[1].release();//Rc vermelho e amarelo
      move_down(109);
      MainController.rc[32].acquire();//Rc b5c5
      move_down(196);
      MainController.rc[29].release();//Rc a5b5
      move_down(337);
      MainController.rc[32].release();//Rc b5c5
      move_down(419);
      MainController.rc[42].acquire();//Rc d5e5
      MainController.rc[22].acquire();//Rc cd5
      move_down(504);
      MainController.rc[22].release();//Rc cd5
      MainController.rc[42].release();//Rc d5e5
      move_down(573);
      MainController.rc[47].acquire();//Rc e5f5
      move_down(710);
      MainController.rc[6].acquire();//Rc vermelho e amarelo 2
      MainController.rc[17].acquire();//Rc amarelo e laranja
      MainController.rc[50].acquire();//Rc f4f5
      move_down(745);
      move_left(592);
      MainController.rc[47].release();//Rc e5f5
      move_left(524);
      MainController.rc[49].acquire();//Rc f3f4
      move_left(435);
      MainController.rc[50].release();//Rc f4f5
      move_left(365);
      MainController.rc[45].acquire();//Rc e3f3
      move_left(321);
      move_left(710);
      MainController.rc[49].release();//Rc f3f4
      MainController.rc[6].release();//Rc vermelho e amarelo 2
      MainController.rc[17].release();//Rc amarelo e laranja
      move_up(573);
      MainController.rc[45].release();//Rc e3f3
    } catch (InterruptedException e) {
    }
  }

  private void moveOrangeCar() {
    try {
      move_left(527);
      MainController.rc[15].acquire();//Rc rosa e laranja
      MainController.rc[21].acquire();//Rc cd4
      move_left(435);
      MainController.rc[21].release();//Rc cd4
      move_left(365);
      MainController.rc[20].acquire();//Rc cd3
      move_left(280);
      MainController.rc[20].release();//Rc cd3
      MainController.rc[8].release();//Rc verde e laranja
      move_left(208);
      MainController.rc[37].acquire();//Rc d1d2
      MainController.rc[19].acquire();//Rc cd2
      move_left(118);
      MainController.rc[19].release();//Rc cd2
      move_left(51);
      MainController.rc[8].acquire();//Rc verde e laranja
      MainController.rc[40].acquire();//Rc d1f2
      move_left(11);
      move_down(504);
      MainController.rc[37].release();//Rc d1d2
      MainController.rc[15].release();//Rc rosa e laranja
      move_down(752);
      move_right(118);
      MainController.rc[18].acquire();//Rc laranja e marrom
      MainController.rc[16].acquire();//Rc azul e laranja
      MainController.rc[48].acquire();//Rc f2f3
      move_right(208);
      MainController.rc[40].release();//Rc d1f2
      move_right(280);
      MainController.rc[17].acquire();//Rc amarelo e laranja
      MainController.rc[49].acquire();//Rc f3f4
      move_right(365);
      MainController.rc[48].release();//Rc f2f3
      move_right(435);
      MainController.rc[50].acquire();//Rc f4f5
      move_right(524);
      MainController.rc[49].release();//Rc f3f4
      MainController.rc[16].release();//Rc azul e laranja
      move_right(592);
      MainController.rc[43].acquire();//Rc d6f5
      move_right(678);
      MainController.rc[50].release();//Rc f4f5
      MainController.rc[17].release();//Rc amarelo e laranja
      MainController.rc[18].release();//Rc laranja e marrom
      move_right(745);
      move_up(504);
      MainController.rc[39].acquire();//Rc d5d6
      move_up(460);
      move_left(718);
      MainController.rc[43].release();//Rc d6f5
      move_left(678);
      MainController.rc[22].acquire();//Rc cd5
      move_left(592);
      MainController.rc[22].release();//Rc cd5
      MainController.rc[39].release();//Rc d5d6
    } catch (InterruptedException e) {
    }
  }

  private void movePurpleCar() {
    try {
      move_right(592);
      MainController.rc[32].acquire();//Rc b5c5
      move_right(636);
      move_down(254);
      MainController.rc[34].acquire();//Rc c5c6
      move_down(303);
      move_right(678);
      MainController.rc[32].release();//Rc b5c5
      move_right(718);
      MainController.rc[39].acquire();//Rc d5d6
      MainController.rc[36].acquire();//Rc c6d6
      move_right(760);
      move_down(337);
      MainController.rc[34].release();//Rc c5c6
      move_down(464);
      move_left(718);
      MainController.rc[36].release();//Rc c6d6
      move_left(678);
      MainController.rc[42].acquire();//Rc d5e5
      move_left(636);
      move_down(504);
      MainController.rc[39].release();//Rc d5d6
      move_down(619);
      move_left(592);
      MainController.rc[42].release();//Rc d5e5
      move_left(524);
      MainController.rc[46].acquire();//Rc e4f4
      move_left(485);
      move_down(710);
      MainController.rc[49].acquire();//Rc f3f4
      move_down(756);
      move_left(435);
      MainController.rc[46].release();//Rc e4f4
      move_left(365);
      MainController.rc[45].acquire();//Rc e3f3
      move_left(321);
      move_up(710);
      MainController.rc[49].release();//Rc f3f4
      move_up(619);
      move_left(280);
      MainController.rc[45].release();//Rc e3f3
      move_left(208);
      MainController.rc[41].acquire();//Rc d2e2
      move_left(162);
      move_up(504);
      MainController.rc[14].acquire();//Rc rosa e roxo 2
      MainController.rc[37].acquire();//Rc d1d2
      move_up(469);
      move_left(118);
      MainController.rc[41].release();//Rc d2e2
      move_left(51);
      MainController.rc[35].acquire();//Rc c1d1
      move_left(11);
      move_up(419);
      MainController.rc[37].release();//Rc d1d2
      move_up(336);
      MainController.rc[33].acquire();//Rc c1c2
      move_up(298);
      move_right(51);
      MainController.rc[35].release();//Rc c1d1
      MainController.rc[14].release();//Rc rosa e roxo 2
      move_right(118);
      MainController.rc[31].acquire();//Rc b2c2
      move_right(162);
      move_up(254);
      MainController.rc[33].release();//Rc c1c2
      move_up(155);
      move_right(208);
      MainController.rc[31].release();//Rc b2c2
      move_right(280);
      MainController.rc[11].acquire();//Rc rosa e roxo
      MainController.rc[27].acquire();//Rc a3b3
      move_right(320);
      move_up(35);
      MainController.rc[24].acquire();//Rc a3a4
      move_up(-8);
      move_right(362);
      MainController.rc[27].release();//Rc a3b3
      move_right(440);
      MainController.rc[28].acquire();//Rc a4b4
      move_right(480);
      move_down(35);
      MainController.rc[24].release();//Rc a3a4
      move_down(155);
      move_right(524);
      MainController.rc[28].release();//Rc a4b4
      MainController.rc[11].release();//Rc rosa e roxo
    } catch (InterruptedException e) {
    }
  }

  private void moveBrownCar() {
    try {
      move_up(659);
      MainController.rc[41].acquire();//Rc d2e2
      move_up(573);
      MainController.rc[44].release();//Rc e2f2
      move_up(504);
      MainController.rc[13].acquire();//Rc rosa e marrom 2
      MainController.rc[37].acquire();//Rc d1d2
      move_up(469);
      move_left(118);
      MainController.rc[41].release();//Rc d2e2
      move_left(51);
      MainController.rc[35].acquire();//Rc c1d1
      move_left(11);
      move_up(419);
      MainController.rc[37].release();//Rc d1d2
      move_up(336);
      MainController.rc[33].acquire();//Rc c1c2
      move_up(298);
      move_right(51);
      MainController.rc[35].release();//Rc c1d1
      MainController.rc[13].release();//Rc rosa e marrom 2
      move_right(118);
      MainController.rc[31].acquire();//Rc b2c2
      move_right(162);
      move_up(254);
      MainController.rc[33].release();//Rc c1c2
      move_up(196);
      MainController.rc[26].acquire();//Rc a2b2
      move_up(109);
      MainController.rc[31].release();//Rc b2c2
      move_up(36);
      MainController.rc[0].acquire();//Rc vermelho e marrom
      MainController.rc[12].acquire();//Rc rosa e marrom
      MainController.rc[23].acquire();//Rc a2a3
      move_up(-8);
      move_right(208);
      MainController.rc[26].release();//Rc a2b2
      move_right(280);
      MainController.rc[24].acquire();//Rc a3a4
      move_right(362);
      MainController.rc[23].release();//Rc a2a3
      move_right(435);
      MainController.rc[25].acquire();//Rc a4a5
      move_right(524);
      MainController.rc[24].release();//Rc a3a4
      MainController.rc[12].release();//Rc rosa e marrom
      move_right(592);
      MainController.rc[29].acquire();//Rc a5b5
      move_right(638);
      move_down(36);
      MainController.rc[25].release();//Rc a4a5
      MainController.rc[0].release();//Rc vermelho e marrom
      move_down(109);
      MainController.rc[32].acquire();//Rc b5c5
      move_down(196);
      MainController.rc[29].release();//Rc a5b5
      move_down(254);
      MainController.rc[34].acquire();//Rc c5c6
      move_down(303);
      move_right(678);
      MainController.rc[32].release();//Rc b5c5
      move_right(718);
      MainController.rc[39].acquire();//Rc d5d6
      MainController.rc[36].acquire();//Rc c6d6
      move_right(760);
      move_down(337);
      MainController.rc[34].release();//Rc c5c6
      move_down(464);
      move_left(718);
      MainController.rc[36].release();//Rc c6d6
      move_left(678);
      MainController.rc[42].acquire();//Rc d5e5
      move_left(636);
      move_down(504);
      MainController.rc[39].release();//Rc d5d6
      move_down(573);
      MainController.rc[47].acquire();//Rc e5f5
      move_down(659);
      MainController.rc[42].release();//Rc d5e5
      move_down(710);
      MainController.rc[4].acquire();//Rc vermelho e marrom 2
      MainController.rc[18].acquire();//Rc laranja e marrom
      MainController.rc[50].acquire();//Rc f4f5
      move_down(756);
      move_left(592);
      MainController.rc[47].release();//Rc e5f5
      move_left(524);
      MainController.rc[49].acquire();//Rc f3f4
      move_left(435);
      MainController.rc[50].release();//Rc f4f5
      move_left(366);
      MainController.rc[48].acquire();//Rc f2f3
      move_left(280);
      MainController.rc[49].release();//Rc f3f4
      move_left(208);
      MainController.rc[44].acquire();//Rc e2f2
      move_left(162);
      move_up(710);
      MainController.rc[48].release();//Rc f2f3
      MainController.rc[18].release();//Rc laranja e marrom
      MainController.rc[4].release();//Rc vermelho e marrom 2
    } catch (InterruptedException e) {
    }
  }

  private void pausando() {
    while (isPaused && !Thread.interrupted()) {
      try {
        sleep(10);
      } catch (InterruptedException e) {
      }
    }
  }

  /* ***************************************************************
   * Metodo: pausar
   * Funcao: Modifica a flag responsavel por pausar o processo para
   *         que este seja pausado.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void pausar() {
    isPaused = true;
  }

  /* ***************************************************************
   * Metodo: retomar
   * Funcao: Modifica a flag responsavel por pausar o processo para
   *         que este seja retomado.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void retomar() {
    isPaused = false;
  }

}
