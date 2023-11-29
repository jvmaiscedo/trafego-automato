package model;

import java.util.concurrent.Semaphore;

public class Semaforo {

  public static Semaphore a1a2 = new Semaphore(1);
  public static Semaphore a2a3 = new Semaphore(1);
  public static Semaphore a3a4 = new Semaphore(1);
  public static Semaphore a4a5 = new Semaphore(1);
  public static Semaphore a5a6 = new Semaphore(1);
  public static Semaphore a2b2 = new Semaphore(1);
  public static Semaphore a3b3 = new Semaphore(1);
  public static Semaphore a4b4 = new Semaphore(1);
  public static Semaphore a5b5 = new Semaphore(1);
  public static Semaphore a1c1 = new Semaphore(1);
  public static Semaphore a6c6 = new Semaphore(1);
  public static Semaphore b2b3 = new Semaphore(1);
  public static Semaphore b4b5 = new Semaphore(1);
  public static Semaphore b2c2 = new Semaphore(1);
  public static Semaphore b3c3 = new Semaphore(1);
  public static Semaphore b4c4 = new Semaphore(1);
  public static Semaphore b5c5 = new Semaphore(1);
  public static Semaphore c1c2 = new Semaphore(1);
  public static Semaphore c3c4 = new Semaphore(1);
  public static Semaphore c4c5 = new Semaphore(1);
  public static Semaphore c5c6 = new Semaphore(1);
  public static Semaphore c1d1 = new Semaphore(1);
  public static Semaphore c2d2 = new Semaphore(1);
  public static Semaphore c3d3 = new Semaphore(1);
  public static Semaphore c4d4 = new Semaphore(1);
  public static Semaphore c5d5 = new Semaphore(1);
  public static Semaphore c6d6 = new Semaphore(1);
  public static Semaphore d1d2 = new Semaphore(1);
  public static Semaphore d2d3 = new Semaphore(1);
  public static Semaphore d3d4 = new Semaphore(1);
  public static Semaphore d4d5 = new Semaphore(1);
  public static Semaphore d5d6 = new Semaphore(1);
  public static Semaphore d1f2 = new Semaphore(1);
  public static Semaphore d2e2 = new Semaphore(1);
  public static Semaphore d3e3 = new Semaphore(1);
  public static Semaphore d4e4 = new Semaphore(1);
  public static Semaphore d5e5 = new Semaphore(1);
  public static Semaphore d6f5 = new Semaphore(1);
  public static Semaphore e2e3 = new Semaphore(1);
  public static Semaphore e4e5 = new Semaphore(1);
  public static Semaphore e2f2 = new Semaphore(1);
  public static Semaphore e3f3 = new Semaphore(1);
  public static Semaphore e4f4 = new Semaphore(1);
  public static Semaphore e5f5 = new Semaphore(1);
  public static Semaphore f2f3 = new Semaphore(1);
  public static Semaphore f3f4 = new Semaphore(1);
  public static Semaphore f4f5 = new Semaphore(1);
  //carro vermelho
  public static Semaphore vermelho_marrom = new Semaphore(1);
  public static Semaphore vermelho_amarelo = new Semaphore(1);
  public static Semaphore vermelho_azul = new Semaphore(1);
  public static Semaphore vermelho_roxo = new Semaphore(1);
  public static Semaphore vermelho_verde = new Semaphore(1);
  public static Semaphore vermelho_marrom_f = new Semaphore(1);
  public static Semaphore vermelho_azul_f = new Semaphore(1);
  public static Semaphore vermelho_amarelo_f = new Semaphore(1);
  //carro verde
  public static Semaphore verde_amarelo = new Semaphore(0);
  public static Semaphore verde_laranja = new Semaphore(0);
  public static Semaphore verde_rosa = new Semaphore(1);
  //carro rosa
  public static Semaphore rosa_azul = new Semaphore(0);
  public static Semaphore rosa_roxo = new Semaphore(1);
  public static Semaphore rosa_marrom = new Semaphore(1);
  public static Semaphore rosa_marrom_c = new Semaphore(1);
  public static Semaphore rosa_roxo_c = new Semaphore(1);
  public static Semaphore rosa_laranja = new Semaphore(1);
  //carro azul
  public static Semaphore azul_laranja = new Semaphore(1);
  //carro amarelo
  public static Semaphore amarelo_laranja = new Semaphore(1);
  //carro laranja
  public static Semaphore laranja_marrom = new Semaphore(1);




  //cruzamentos
  public static Semaphore cd2 = new Semaphore(1);
  public static Semaphore cd3 = new Semaphore(1);
  public static Semaphore cd4 = new Semaphore(1);
  public static Semaphore cd5 = new Semaphore(1);

//especiais
  public static Semaphore amarelo_rosa_especial = new Semaphore(1);
  public Semaforo(){}

}
