package model;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Car extends Thread{
  private ImageView img;
  private int id;
  private volatile boolean isPaused=false;
  private double posInicialX;
  private double posInicialY;
  private double anguloInicial;
  private MainController control;

  public Car(ImageView img, int id, double posInicialX, double posInicialY, double anguloInicial, MainController control){
    this.img = img;
    this.id = id;
    this.control = control;
    this.posInicialX = posInicialX;
    this.posInicialY = posInicialY;
    this.anguloInicial = anguloInicial;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()){
      movendo(this.id);
    }
  }

  private void movendo(int id){
    switch (id){
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

  private void move_down(double lim){
    Platform.runLater(() ->this.img.setRotate(180));
    while(img.getLayoutY()<lim && !Thread.interrupted()){
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY()+1));
      sleepTime((int)(control.getVelocidadeCar(this.id)));
      pausando();
    }
  }
  private void move_up(double lim){
    Platform.runLater(() ->this.img.setRotate(0));
    while(img.getLayoutY()>lim && !Thread.interrupted()){
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY()-1));
      sleepTime((int)(control.getVelocidadeCar(this.id)));
      pausando();
    }
  }
  private void move_right(double lim){
    Platform.runLater(() ->this.img.setRotate(90));
    while(img.getLayoutX()<lim && !Thread.interrupted()){
      Platform.runLater(() -> img.setLayoutX(img.getLayoutX()+1));
      sleepTime((int)(control.getVelocidadeCar(this.id)));
      pausando();
    }
  }
  private void move_left(double lim){
    Platform.runLater(() ->this.img.setRotate(270));
    while(img.getLayoutX()>lim && !Thread.interrupted()){
      Platform.runLater(() -> img.setLayoutX(img.getLayoutX()-1));
      sleepTime((int)(control.getVelocidadeCar(this.id)));
      pausando();
    }
  }
  public void posicionar(){
    Platform.runLater(()->{
      this.img.setRotate(anguloInicial);
      this.img.setLayoutX(this.posInicialX);
      this.img.setLayoutY(this.posInicialY);}
      );
  }
  /* ***************************************************************
   * Metodo: sleepTime
   * Funcao: Faz o processo dormir.
   * Parametros: Valor numerico do tipo inteiro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void sleepTime(int time){
    try {
      sleep((long) time);
    } catch (InterruptedException e) {
    }
  }

  private void moveRedCar(){
    try {
      move_left(678);
      MainController.rc[0].acquire();
      MainController.rc[1].acquire();
      //Semaforo.vermelho_marrom.acquire();
      //Semaforo.vermelho_amarelo.acquire();
      move_left(522);
      MainController.rc[2].acquire();
      //Semaforo.vermelho_azul.acquire();
      MainController.rc[24].acquire();
      //Semaforo.a3a4.acquire();
      move_left(362);
      MainController.rc[3].acquire();
      //Semaforo.vermelho_verde.acquire();
      MainController.rc[23].acquire();
      //Semaforo.a2a3.acquire();
      move_left(280);
      MainController.rc[24].release();
      //Semaforo.a3a4.release();
      MainController.rc[1].release();
      //Semaforo.vermelho_amarelo.release();
      move_left(205);
      MainController.rc[30].acquire();
      //Semaforo.a1c1.acquire();
      move_left(120);
      MainController.rc[23].release();
      //Semaforo.a2a3.release();
      MainController.rc[0].release();
      //Semaforo.vermelho_marrom.release();
      MainController.rc[2].release();
      //Semaforo.vermelho_azul.release();
      move_left(11);
      move_down(254);
      MainController.rc[35].acquire();
      //Semaforo.c1d1.acquire();
      move_down(419);
      MainController.rc[30].release();
      //Semaforo.a1c1.release();
      MainController.rc[40].acquire();
      //Semaforo.d1f2.acquire();
      move_down(504);
      MainController.rc[35].release();
      //Semaforo.c1d1.release();
      move_down(760);
      move_right(118);
      MainController.rc[4].acquire();
      //Semaforo.vermelho_marrom_f.acquire();
      MainController.rc[5].acquire();
      //Semaforo.vermelho_azul_f.acquire();
      move_right(208);
      MainController.rc[40].release();
      //Semaforo.d1f2.release();
      move_right(280);
      MainController.rc[6].acquire();
      //Semaforo.vermelho_amarelo_f.acquire();
      MainController.rc[49].acquire();
      //Semaforo.f3f4.acquire();
      move_right(524);
      MainController.rc[5].release();
      //Semaforo.vermelho_azul_f.release();
      MainController.rc[49].release();
      //Semaforo.f3f4.release();
      move_right(435);
      MainController.rc[50].acquire();
      //Semaforo.f4f5.acquire();
      move_right(592);
      MainController.rc[43].acquire();
      //Semaforo.d6f5.acquire();
      move_right(678);
      MainController.rc[50].release();
      //Semaforo.f4f5.release();
      MainController.rc[6].release();
      //Semaforo.vermelho_amarelo_f.release();
      MainController.rc[4].release();
      //Semaforo.vermelho_marrom_f.release();
      move_right(770);
      move_up(504);
      MainController.rc[36].acquire();
      //Semaforo.c6d6.acquire();
      move_up(419);
      MainController.rc[43].release();
      //Semaforo.d6f5.release();
      MainController.rc[3].release();
      //Semaforo.vermelho_verde.release();
      move_up(254);
      MainController.rc[36].release();
      //Semaforo.c6d6.release();
      move_up(-8);
      move_right(726);
      //realoCar();
      System.out.println("SOU A ULTIMA LINHA DO RED CAR");

    } catch (InterruptedException e) {
    }
  }

  private void moveGreenCar(){
    try {
      move_down(419);
      MainController.rc[8].acquire();
      //Semaforo.verde_laranja.acquire();
      MainController.rc[38].acquire();
      //Semaforo.d3d4.acquire();
      move_down(460);
      move_right(365);
      MainController.rc[7].release();
      //Semaforo.verde_amarelo.release();
      move_right(435);
      MainController.rc[21].acquire();
      //Semaforo.cd4.acquire();
      move_right(524);
      MainController.rc[38].release();
      //Semaforo.d3d4.release();
      MainController.rc[21].release();
      //Semaforo.cd4.release();
      move_right(592);
      MainController.rc[22].acquire();
      //Semaforo.cd5.acquire();
      MainController.rc[39].acquire();
      //Semaforo.d5d6.acquire();
      move_right(678);
      MainController.rc[22].release();
      //Semaforo.cd5.release();
      move_right(718);
      MainController.rc[3].acquire();
      //Semaforo.vermelho_verde.acquire();
      move_right(768);
      move_down(504);
      MainController.rc[39].release();
      //Semaforo.d5d6.release();
      move_down(752);
      move_left(678);
      MainController.rc[50].acquire();
      //Semaforo.f4f5.acquire();
      move_left(524);
      MainController.rc[49].acquire();
      //Semaforo.f3f4.acquire();
      move_left(435);
      MainController.rc[50].release();
      //Semaforo.f4f5.release();
      move_left(365);
      MainController.rc[48].acquire();
      //Semaforo.f2f3.acquire();
      move_left(280);
      MainController.rc[49].release();
      //Semaforo.f3f4.release();
      move_left(118);
      MainController.rc[48].release();
      //Semaforo.f2f3.release();
      move_left(11);
      move_up(504);
      MainController.rc[9].acquire();
      //Semaforo.verde_rosa.acquire();
      MainController.rc[35].acquire();
      //Semaforo.c1d1.acquire();
      move_up(419);
      MainController.rc[8].release();
      //Semaforo.verde_laranja.release();
      move_up(254);
      MainController.rc[35].release();
      //Semaforo.c1d1.release();
      move_up(-8);
      move_right(120);
      MainController.rc[23].acquire();
      //Semaforo.a2a3.acquire();
      move_right(280);
      MainController.rc[7].acquire();
      //Semaforo.verde_amarelo.acquire();
      MainController.rc[27].acquire();
      //Semaforo.a3b3.acquire();
      move_right(321);
      move_down(36);
      MainController.rc[23].release();
      //Semaforo.a2a3.release();
      MainController.rc[9].release();
      //Semaforo.verde_rosa.release();
      MainController.rc[3].release();
      //Semaforo.vermelho_verde.release();
      move_down(196);
      MainController.rc[27].release();
      //Semaforo.a3b3.release();
      //realoCar();

    } catch (InterruptedException e) {}
  }

  private void movePinkCar(){
    try{
     move_up(196);
     MainController.rc[11].acquire();
     //Semaforo.rosa_roxo.acquire();
     move_up(36);
     MainController.rc[12].acquire();
     //Semaforo.rosa_marrom.acquire();
     //Semaforo.rosa_laranja.acquire();
      MainController.rc[51].acquire();
      //Semaforo.amarelo_rosa_especial.acquire();
      MainController.rc[24].acquire();
      //Semaforo.a3a4.acquire();
     move_up(-8);
     move_left(365);
     MainController.rc[15].acquire();
     //Semaforo.rosa_laranja.acquire();
     MainController.rc[9].acquire();
     //Semaforo.verde_rosa.acquire();
      MainController.rc[23].acquire();
      //Semaforo.a2a3.acquire();
     move_left(280);
     MainController.rc[24].release();
     //Semaforo.a3a4.release();
      MainController.rc[51].release();
      //Semaforo.amarelo_rosa_especial.release();
      MainController.rc[11].release();
      //Semaforo.rosa_roxo.release();
     move_left(205);
     MainController.rc[30].acquire();
     //Semaforo.a1c1.acquire();
     move_left(120);
     MainController.rc[23].release();
     //Semaforo.a2a3.release();
      MainController.rc[10].release();
      //Semaforo.rosa_azul.release();
      MainController.rc[12].release();
      //Semaforo.rosa_marrom.release();
     move_left(10);
     move_down(254);
      MainController.rc[14].acquire();
     //Semaforo.rosa_roxo_c.acquire();
      MainController.rc[13].acquire();
     //Semaforo.rosa_marrom_c.acquire();
     move_down(335);
      MainController.rc[30].release();
     //Semaforo.a1c1.release();
     move_down(470);
     move_right(51);
     MainController.rc[9].release();
     //Semaforo.verde_rosa.release();
     move_right(118);
      MainController.rc[19].acquire();
     //Semaforo.cd2.acquire();
     move_right(208);
      MainController.rc[19].release();
     //Semaforo.cd2.release();
      MainController.rc[14].release();
      //Semaforo.rosa_roxo_c.release();
      MainController.rc[13].release();
      //Semaforo.rosa_marrom_c.release();
     move_right(280);
      MainController.rc[20].acquire();
     //Semaforo.cd3.acquire();
      MainController.rc[38].acquire();
     //Semaforo.d3d4.acquire();
     move_right(365);
      MainController.rc[20].release();
     //Semaforo.cd3.release();
     move_right(435);
      MainController.rc[10].acquire();
     //Semaforo.rosa_azul.acquire();
     move_right(475);
     move_up(419);
      MainController.rc[38].release();
     //Semaforo.d3d4.release();
      MainController.rc[15].release();
      //Semaforo.rosa_laranja.release();
     move_up(390);
     //realoCar();


    } catch (InterruptedException e){}
  }

  private void moveBlueCar(){
    try{
      move_up(336);
      MainController.rc[31].acquire();
      //Semaforo.b2c2.acquire();
      move_up(196);
      MainController.rc[26].acquire();
      //Semaforo.a2b2.acquire();
      move_up(109);
      MainController.rc[31].release();
      //Semaforo.b2c2.release();
      move_up(35);
      MainController.rc[2].acquire();
      //Semaforo.vermelho_azul.acquire();
      MainController.rc[10].acquire();
      //Semaforo.rosa_azul.acquire();
      MainController.rc[23].acquire();
      //Semaforo.a2a3.acquire();
      move_up(-8);
      move_right(205);
      MainController.rc[26].release();
      //Semaforo.a2b2.release();
      move_right(280);
      MainController.rc[24].acquire();
      //Semaforo.a3a4.acquire();
      move_right(362);
      MainController.rc[23].release();
      //Semaforo.a2a3.release();
      move_right(435);
      MainController.rc[28].acquire();
      //Semaforo.a4b4.acquire();
      move_right(475);
      move_down(35);
      MainController.rc[2].release();
      //Semaforo.vermelho_azul.release();
      MainController.rc[24].release();
      //Semaforo.a3a4.release();
      move_down(196);
      MainController.rc[28].release();
      //Semaforo.a4b4.release();
      move_down(419);
      MainController.rc[21].acquire();
      //Semaforo.cd4.acquire();
      move_down(504);
      MainController.rc[10].release();
      //Semaforo.rosa_azul.release();
      MainController.rc[21].release();
      //Semaforo.cd4.release();
      move_down(573);
      MainController.rc[46].acquire();
      //Semaforo.e4f4.acquire();
      move_down(710);
      MainController.rc[16].acquire();
      //Semaforo.azul_laranja.acquire();
      MainController.rc[5].acquire();
      //Semaforo.vermelho_azul_f.acquire();
      MainController.rc[49].acquire();
      //Semaforo.f3f4.acquire();
      move_down(756);
      move_left(435);
      MainController.rc[46].release();
      //Semaforo.e4f4.release();
      move_left(365);
      MainController.rc[48].acquire();
      //Semaforo.f2f3.acquire();
      move_left(280);
      MainController.rc[49].release();
      //Semaforo.f3f4.release();
      move_left(208);
      MainController.rc[44].acquire();
      System.out.println("nao sou eu ");
      //Semaforo.e2f2.acquire();
      move_left(160);
      move_up(710);
      MainController.rc[5].release();
      //Semaforo.vermelho_azul_f.release();
      MainController.rc[16].release();
      //Semaforo.azul_laranja.release();
      MainController.rc[48].release();
      //Semaforo.f2f3.release();
      move_up(659);
      MainController.rc[41].acquire();
      //Semaforo.d2e2.acquire();
      move_up(573);
      MainController.rc[44].release();

      //Semaforo.e2f2.release();
      move_up(504);
      MainController.rc[19].acquire();
      //Semaforo.cd2.acquire();
      move_up(419);
      MainController.rc[19].release();
      //Semaforo.cd2.release();
      MainController.rc[41].release();
      //Semaforo.d2e2.release();
      move_up(426);
      //realoCar();

    } catch (InterruptedException e){}
  }
  private void moveYellowCar(){
    try{
      move_up(504);
      MainController.rc[51].acquire();
      //Semaforo.amarelo_rosa_especial.acquire();
      MainController.rc[1].acquire();
      //Semaforo.vermelho_amarelo.acquire();
      MainController.rc[7].acquire();
      System.out.println("ganhei");
      //Semaforo.verde_amarelo.acquire();
      MainController.rc[20].acquire();
      //Semaforo.cd3.acquire();
      move_up(419);
      MainController.rc[20].release();
      //Semaforo.cd3.release();
      move_up(196);
      MainController.rc[27].acquire();
      //Semaforo.a3b3.acquire();
      move_up(36);
      MainController.rc[24].acquire();
      //Semaforo.a3a4.acquire();
      move_up(-8);
      move_right(362);
      MainController.rc[27].release();
      //Semaforo.a3b3.release();
      MainController.rc[7].release();
      //Semaforo.verde_amarelo.release();
      move_right(435);
      MainController.rc[25].acquire();
      //Semaforo.a4a5.acquire();
      move_right(522);
      MainController.rc[24].release();
      //Semaforo.a3a4.release();
      MainController.rc[51].release();
      //Semaforo.amarelo_rosa_especial.release();
      move_right(595);
      MainController.rc[29].acquire();
      //Semaforo.a5b5.acquire();
      move_right(630);
      move_down(36);
      MainController.rc[25].release();
      //Semaforo.a4a5.release();
      MainController.rc[1].release();
      //Semaforo.vermelho_amarelo.release();
      move_down(109);
      MainController.rc[32].acquire();
      //Semaforo.b5c5.acquire();
      move_down(196);
      MainController.rc[29].release();
      //Semaforo.a5b5.release();
      move_down(337);
      MainController.rc[32].release();
      //Semaforo.b5c5.release();
      move_down(419);
      MainController.rc[42].acquire();
      //Semaforo.d5e5.acquire();
      MainController.rc[22].acquire();
      //Semaforo.cd5.acquire();
      move_down(504);
      MainController.rc[22].release();
      //Semaforo.cd5.release();
      MainController.rc[42].release();
      //Semaforo.d5e5.release();
      move_down(573);
      MainController.rc[47].acquire();
      //Semaforo.e5f5.acquire();
      move_down(710);
      MainController.rc[6].acquire();
      //Semaforo.vermelho_amarelo_f.acquire();
      MainController.rc[17].acquire();
      //Semaforo.amarelo_laranja.acquire();
      MainController.rc[50].acquire();
      //Semaforo.f4f5.acquire();
      move_down(745);
      move_left(592);
      MainController.rc[47].release();
      //Semaforo.e5f5.release();
      move_left(524);
      MainController.rc[49].acquire();
      //Semaforo.f3f4.acquire();
      move_left(435);
      MainController.rc[50].release();
      //Semaforo.f4f5.release();
      move_left(365);
      MainController.rc[45].acquire();
      //Semaforo.e3f3.acquire();
      move_left(321);
      move_left(710);
      MainController.rc[49].release();
      //Semaforo.f3f4.release();
      MainController.rc[6].release();
      //Semaforo.vermelho_amarelo_f.release();
      MainController.rc[17].release();
      //Semaforo.amarelo_laranja.release();
      move_up(573);
      MainController.rc[45].release();
      //Semaforo.e3f3.release();
     // realoCar();
    } catch (InterruptedException e){}
  }

  private void moveOrangeCar(){
    try{
      move_left(527);
      MainController.rc[15].acquire();
      //Semaforo.rosa_laranja.acquire();
      MainController.rc[21].acquire();
      //Semaforo.cd4.acquire();
      move_left(435);
      MainController.rc[21].release();
      //Semaforo.cd4.release();
      move_left(365);
      MainController.rc[20].acquire();
      //Semaforo.cd3.acquire();
      move_left(280);
      MainController.rc[20].release();
      //Semaforo.cd3.release();
      MainController.rc[8].release();
      //Semaforo.verde_laranja.release();
      move_left(208);
      MainController.rc[37].acquire();
      //Semaforo.d1d2.acquire();
      MainController.rc[19].acquire();
      //Semaforo.cd2.acquire();
      move_left(118);
      MainController.rc[19].release();
      //Semaforo.cd2.release();
      move_left(51);
      MainController.rc[8].acquire();
      //Semaforo.verde_laranja.acquire();
      MainController.rc[40].acquire();
      //Semaforo.d1f2.acquire();
      move_left(11);
      move_down(504);
      MainController.rc[37].release();
      //Semaforo.d1d2.release();
      MainController.rc[15].release();
      //Semaforo.rosa_laranja.release();
      move_down(752);
      move_right(118);
      MainController.rc[18].acquire();
      //Semaforo.laranja_marrom.acquire();
      MainController.rc[16].acquire();
      //Semaforo.azul_laranja.acquire();
      MainController.rc[48].acquire();
      //Semaforo.f2f3.acquire();
      move_right(208);
      MainController.rc[40].release();
      //Semaforo.d1f2.release();
      move_right(280);
      MainController.rc[17].acquire();
      //Semaforo.amarelo_laranja.acquire();
      MainController.rc[49].acquire();
      //Semaforo.f3f4.acquire();
      move_right(365);
      MainController.rc[48].release();
      //Semaforo.f2f3.release();
      move_right(435);
      MainController.rc[50].acquire();
      //Semaforo.f4f5.acquire();
      move_right(524);
      MainController.rc[49].release();
      //Semaforo.f3f4.release();
      MainController.rc[16].release();
      //Semaforo.azul_laranja.release();
      move_right(592);
      MainController.rc[43].acquire();
      //Semaforo.d6f5.acquire();
      move_right(678);
      MainController.rc[50].release();
      //Semaforo.f4f5.release();
      MainController.rc[17].release();
      //Semaforo.amarelo_laranja.release();
      MainController.rc[18].release();
      //Semaforo.laranja_marrom.release();
      move_right(745);
      move_up(504);
      MainController.rc[39].acquire();
      //Semaforo.d5d6.acquire();
      move_up(460);
      move_left(718);
      MainController.rc[43].release();
      //Semaforo.d6f5.release();
      move_left(678);
      MainController.rc[22].acquire();
      //Semaforo.cd5.acquire();
      move_left(592);
      MainController.rc[22].release();
      //Semaforo.cd5.release();
      MainController.rc[39].release();
      //Semaforo.d5d6.release();
     // realoCar();
    } catch (InterruptedException e){}
  }
  private void movePurpleCar(){
    try{
      move_right(592);
      MainController.rc[32].acquire();
      //Semaforo.b5c5.acquire();
      move_right(636);
      move_down(254);
      MainController.rc[34].acquire();
      //Semaforo.c5c6.acquire();
      move_down(303);
      move_right(678);
      MainController.rc[32].release();
      //Semaforo.b5c5.release();
      move_right(718);
      MainController.rc[39].acquire();
      //Semaforo.d5d6.acquire();
      MainController.rc[36].acquire();
      //Semaforo.c6d6.acquire();
      move_right(760);
      move_down(337);
      MainController.rc[34].release();
      //Semaforo.c5c6.release();
      move_down(464);
      move_left(718);
      MainController.rc[36].release();
      //Semaforo.c6d6.release();
      move_left(678);
      MainController.rc[42].acquire();
      //Semaforo.d5e5.acquire();
      move_left(636);
      move_down(504);
      MainController.rc[39].release();
      //Semaforo.d5d6.release();
      move_down(619);
      move_left(592);
      MainController.rc[42].release();
      //Semaforo.d5e5.release();
      move_left(524);
      MainController.rc[46].acquire();
      //Semaforo.e4f4.acquire();
      move_left(485);
      move_down(710);
      MainController.rc[49].acquire();
      //Semaforo.f3f4.acquire();
      move_down(756);
      move_left(435);
      MainController.rc[46].release();
      //Semaforo.e4f4.release();
      move_left(365);
      MainController.rc[45].acquire();
      //Semaforo.e3f3.acquire();
      move_left(321);
      move_up(710);
      MainController.rc[49].release();
      //Semaforo.f3f4.release();
      move_up(619);
      move_left(280);
      MainController.rc[45].release();
      //Semaforo.e3f3.release();
      move_left(208);
      MainController.rc[41].acquire();
      //Semaforo.d2e2.acquire();
      move_left(162);
      move_up(504);
      MainController.rc[14].acquire();
      //Semaforo.rosa_roxo_c.acquire();
      MainController.rc[37].acquire();
      //Semaforo.d1d2.acquire();
      move_up(469);
      move_left(118);
      MainController.rc[41].release();
      //Semaforo.d2e2.release();
      move_left(51);
      MainController.rc[35].acquire();
      //Semaforo.c1d1.acquire();
      move_left(11);
      move_up(419);
      MainController.rc[37].release();
      //Semaforo.d1d2.release();
      move_up(336);
      MainController.rc[33].acquire();
      //Semaforo.c1c2.acquire();
      move_up(298);
      move_right(51);
      MainController.rc[35].release();
      //Semaforo.c1d1.release();
      MainController.rc[14].release();
      //Semaforo.rosa_roxo_c.release();
      move_right(118);
      MainController.rc[31].acquire();
      //Semaforo.b2c2.acquire();
      move_right(162);
      move_up(254);
      MainController.rc[33].release();
      //Semaforo.c1c2.release();
      move_up(155);
      move_right(208);
      MainController.rc[31].release();
     //Semaforo.b2c2.release();
      move_right(280);
      MainController.rc[11].acquire();
      //Semaforo.rosa_roxo.acquire();
      MainController.rc[27].acquire();
      //Semaforo.a3b3.acquire();
      move_right(320);
      move_up(35);
      MainController.rc[24].acquire();
      //Semaforo.a3a4.acquire();
      move_up(-8);
      move_right(362);
      MainController.rc[27].release();
      //Semaforo.a3b3.release();
      move_right(440);
      MainController.rc[28].acquire();
      //Semaforo.a4b4.acquire();
      move_right(480);
      move_down(35);
      MainController.rc[24].release();
      //Semaforo.a3a4.release();
      move_down(155);
      move_right(524);
      MainController.rc[28].release();
      //Semaforo.a4b4.release();
      MainController.rc[11].release();
      //Semaforo.rosa_roxo.release();
      //realoCar();
    } catch (InterruptedException e){}
  }
  private void moveBrownCar(){
    try{
     move_up(659);
     MainController.rc[41].acquire();
     //Semaforo.d2e2.acquire();
     move_up(573);
     MainController.rc[44].release();
     //Semaforo.e2f2.release();
     move_up(504);
      MainController.rc[13].acquire();
     //Semaforo.rosa_marrom_c.acquire();
      MainController.rc[37].acquire();
      //Semaforo.d1d2.acquire();
     move_up(469);
     move_left(118);
      MainController.rc[41].release();
     //Semaforo.d2e2.release();
     move_left(51);
      MainController.rc[35].acquire();
     //Semaforo.c1d1.acquire();
     move_left(11);
     move_up(419);
      MainController.rc[37].release();
     //Semaforo.d1d2.release();
     move_up(336);
      MainController.rc[33].acquire();
     //Semaforo.c1c2.acquire();
     move_up(298);
     move_right(51);
      MainController.rc[35].release();
     //Semaforo.c1d1.release();
      MainController.rc[13].release();
      //Semaforo.rosa_marrom_c.release();
     move_right(118);
      MainController.rc[31].acquire();
     //Semaforo.b2c2.acquire();
     move_right(162);
     move_up(254);
      MainController.rc[33].release();
     //Semaforo.c1c2.release();
     move_up(196);
      MainController.rc[26].acquire();
     //Semaforo.a2b2.acquire();
     move_up(109);
      MainController.rc[31].release();
     //Semaforo.b2c2.release();
     move_up(36);
      MainController.rc[0].acquire();
     //Semaforo.vermelho_marrom.acquire();
      MainController.rc[12].acquire();
      //Semaforo.rosa_marrom.acquire();
      MainController.rc[23].acquire();
      //Semaforo.a2a3.acquire();
     move_up(-8);
     move_right(208);
      MainController.rc[26].release();
     //Semaforo.a2b2.release();
     move_right(280);
      MainController.rc[24].acquire();
     //Semaforo.a3a4.acquire();
     move_right(362);
      MainController.rc[23].release();
     //Semaforo.a2a3.release();
     move_right(435);
      MainController.rc[25].acquire();
     //Semaforo.a4a5.acquire();
     move_right(524);
      MainController.rc[24].release();
     //Semaforo.a3a4.release();
      MainController.rc[12].release();
      //Semaforo.rosa_marrom.release();
     move_right(592);
      MainController.rc[29].acquire();
     //Semaforo.a5b5.acquire();
     move_right(638);
     move_down(36);
      MainController.rc[25].release();
     //Semaforo.a4a5.release();
      MainController.rc[0].release();
      //Semaforo.vermelho_marrom.release();
     move_down(109);
      MainController.rc[32].acquire();
     //Semaforo.b5c5.acquire();
     move_down(196);
      MainController.rc[29].release();
     //Semaforo.a5b5.release();
     move_down(254);
      MainController.rc[34].acquire();
     //Semaforo.c5c6.acquire();
     move_down(303);
     move_right(678);
      MainController.rc[32].release();
     //Semaforo.b5c5.release();
     move_right(718);
      MainController.rc[39].acquire();
     //Semaforo.d5d6.acquire();
      MainController.rc[36].acquire();
      //Semaforo.c6d6.acquire();
     move_right(760);
     move_down(337);
      MainController.rc[34].release();
     //Semaforo.c5c6.release();
     move_down(464);
     move_left(718);
      MainController.rc[36].release();
     //Semaforo.c6d6.release();
     move_left(678);
      MainController.rc[42].acquire();
     //Semaforo.d5e5.acquire();
     move_left(636);
     move_down(504);
      MainController.rc[39].release();
     //Semaforo.d5d6.release();
     move_down(573);
      MainController.rc[47].acquire();
     //Semaforo.e5f5.acquire();
     move_down(659);
      MainController.rc[42].release();
     //Semaforo.d5e5.release();
     move_down(710);
      MainController.rc[4].acquire();
     //Semaforo.vermelho_marrom_f.acquire();
      MainController.rc[18].acquire();
      //Semaforo.laranja_marrom.acquire();
      MainController.rc[50].acquire();
      //Semaforo.f4f5.acquire();
     move_down(756);
     move_left(592);
      MainController.rc[47].release();
     //Semaforo.e5f5.release();
     move_left(524);
      MainController.rc[49].acquire();
     //Semaforo.f3f4.acquire();
     move_left(435);
      MainController.rc[50].release();
     //Semaforo.f4f5.release();
     move_left(366);
      MainController.rc[48].acquire();
     //Semaforo.f2f3.acquire();
     move_left(280);
      MainController.rc[49].release();
     //Semaforo.f3f4.release();
     move_left(208);
      MainController.rc[44].acquire();
     //Semaforo.e2f2.acquire();
     move_left(162);
     move_up(710);
      MainController.rc[48].release();
     //Semaforo.f2f3.release();
      MainController.rc[18].release();
      //Semaforo.laranja_marrom.release();
      MainController.rc[4].release();
      //Semaforo.vermelho_marrom_f.release();

    } catch (InterruptedException e){}
  }
  private void pausando(){
    while (isPaused && !Thread.interrupted()){
      try {
        sleep(10);
      } catch (InterruptedException e) {}
    }
  }
  /* ***************************************************************
   * Metodo: pausar
   * Funcao: Modifica a flag responsavel por pausar o processo para
   *         que este seja pausado.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void pausar(){
    isPaused = true;
  }

  /* ***************************************************************
   * Metodo: retomar
   * Funcao: Modifica a flag responsavel por pausar o processo para
   *         que este seja retomado.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void retomar(){
    isPaused = false;
  }





}
