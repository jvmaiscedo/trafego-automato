package model;


public class Positions {

  private double coordenadaX;//Coordenada X.
  private double coordenadaY;//Coordenada Y.
  private double angulo; //Angulo inicial.

  public Positions(double x, double y, double angulo) {
    this.coordenadaX = x;
    this.coordenadaY = y;
    this.angulo = angulo;
  }

  /* ***************************************************************
  * Metodo: getCoordenadaX
  * Funcao: Esta funcao retorna a coordenada X do conjunto (x,y) que
            compoe o objeto coordenada.
  * Parametros: sem parametros
  * Retorno: retorna a coordenada x de tipo inteiro.
  *************************************************************** */
  public double getCoordenadaX() {
    return coordenadaX;
  }

  /* ***************************************************************
  * Metodo: getCoordenadaY
  * Funcao: Esta funcao retorna a coordenada Y do conjunto (x,y) que
            compoe o objeto coordenada.
  * Parametros: sem parametros
  * Retorno: retorna a coordenada y de tipo inteiro.
  *************************************************************** */
  public double getCoordenadaY() {
    return coordenadaY;
  }

  public double getAngulo() {
    return angulo;
  }
}
