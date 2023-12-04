/* ***************************************************************
 * Autor............: Joao Victor Gomes Macedo
 * Matricula........: 202210166
 * Inicio...........: 22/11/2023
 * Ultima alteracao.: 03/12/2023
 * Nome.............: Positions
 * Funcao...........: Modela os objetos usados como posicao dos carros
 *************************************************************** */
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
  * Funcao: Esta funcao retorna a coordenada X do conjunto que
            compoe o objeto positions.
  * Parametros: sem parametros
  * Retorno: retorna a coordenada x de tipo inteiro.
  *************************************************************** */
  public double getCoordenadaX() {
    return coordenadaX;
  }

  /* ***************************************************************
  * Metodo: getCoordenadaY
  * Funcao: Esta funcao retorna a coordenada Y do conjunto que
            compoe o objeto positions.
  * Parametros: sem parametros
  * Retorno: retorna a coordenada y de tipo inteiro.
  *************************************************************** */
  public double getCoordenadaY() {
    return coordenadaY;
  }

  /* ***************************************************************
   * Metodo: getAngulo
   * Funcao: Esta funcao retorna o angulo que compoe o objeto positions.
   * Parametros: sem parametros
   * Retorno: retorna a coordenada y de tipo inteiro.
   *************************************************************** */
  public double getAngulo() {
    return angulo;
  }
}
