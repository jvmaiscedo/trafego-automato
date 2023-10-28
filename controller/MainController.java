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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Filosofo;

public class MainController implements Initializable {

  //Elementos FXML do baterista 1
  @FXML
  public ImageView bateristaEscutando1;
  @FXML
  public ImageView bateristaTocando1;
  @FXML
  public ImageView bateristaEsperando1;
  @FXML
  public Slider velBateristaOuvindo1;
  @FXML
  public Slider velBateristaTocando1;
  @FXML
  public Button pauseBaterista1;
  @FXML
  public Button restartBaterista1;
  @FXML
  public Label estadoBaterista1;

  //Elementos FXML do baterista 2
  @FXML
  public ImageView bateristaEscutando2;
  @FXML
  public ImageView bateristaTocando2;
  @FXML
  public ImageView bateristaEsperando2;
  @FXML
  public Slider velBateristaOuvindo2;
  @FXML
  public Slider velBateristaTocando2;
  @FXML
  public Button pauseBaterista2;
  @FXML
  public Button restartBaterista2;
  @FXML
  public Label estadoBaterista2;

  //Elementos FXML do baterista 3
  @FXML
  public ImageView bateristaEscutando3;
  @FXML
  public ImageView bateristaTocando3;
  @FXML
  public ImageView bateristaEsperando3;
  @FXML
  public Slider velBateristaOuvindo3;
  @FXML
  public Slider velBateristaTocando3;
  @FXML
  public Button pauseBaterista3;
  @FXML
  public Button restartBaterista3;
  @FXML
  public Label estadoBaterista3;

  //Elementos FXML do baterista 4

  @FXML
  public ImageView bateristaEscutando4;
  @FXML
  public ImageView bateristaTocando4;
  @FXML
  public ImageView bateristaEsperando4;
  @FXML
  public Slider velBateristaOuvindo4;
  @FXML
  public Slider velBateristaTocando4;
  @FXML
  public Button pauseBaterista4;
  @FXML
  public Button restartBaterista4;
  @FXML
  public Label estadoBaterista4;

  //Elementos FXML do baterista 5

  @FXML
  public ImageView bateristaEscutando5;
  @FXML
  public ImageView bateristaTocando5;
  @FXML
  public ImageView bateristaEsperando5;
  @FXML
  public Slider velBateristaOuvindo5;
  @FXML
  public Slider velBateristaTocando5;
  @FXML
  public Button pauseBaterista5;
  @FXML
  public Button restartBaterista5;
  @FXML
  public Label estadoBaterista5;
  @FXML
  public ImageView baqueta0;
  @FXML
  public ImageView baqueta1;
  @FXML
  public ImageView baqueta2;
  @FXML
  public ImageView baqueta3;
  @FXML
  public ImageView baqueta4;

  //Vetores para armazenar os elementos graficos e auxiliar na manipulacao dos elementos.
  private ImageView[] baquetas;
  private ImageView [] bateristaEscutando;
  private ImageView [] bateristaTocando;
  private ImageView [] bateristaEsperando;
  private Slider[] velocidadeTocando;
  private Slider[] velocidadeEscutando;
  private Label [] estadoBaterista;
  //Elementos utilizados para construcao e funcionamento dos filosofos (bateristas)
  public static final int N = 5; // define o no. de filosofos (bateristas)
  public static Filosofo[] filosofos = new Filosofo[N];// vetor contendo os filosofos
  public volatile static int[] states = new int[N];//vetor contendo o estado dos filosofos
  public  static Semaphore mutex;//Semaforo mutex para garantir a exclusao mutua no teste e alteracao de estados
  public static Semaphore[] forks = new Semaphore[N];//Semaforos para garantir o acesso unico ao recurso compartilhado

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    iniciar();
  }

  /* ***************************************************************
   * Metodo: iniciar
   * Funcao: iniciar os elementos, instanciar os filosofos e dar
   *         start nas threads.
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  public void iniciar(){
    velocidadeEscutando = new Slider[]{velBateristaOuvindo1, velBateristaOuvindo2, velBateristaOuvindo3, velBateristaOuvindo4, velBateristaOuvindo5};
    velocidadeTocando = new Slider[] {velBateristaTocando1, velBateristaTocando2, velBateristaTocando3, velBateristaTocando4, velBateristaTocando5};
    baquetas = new ImageView[]{ baqueta0, baqueta1, baqueta2, baqueta3, baqueta4};
    estadoBaterista = new Label[] {estadoBaterista1, estadoBaterista2, estadoBaterista3, estadoBaterista4, estadoBaterista5};
    bateristaEscutando = new ImageView[]{ bateristaEscutando1, bateristaEscutando2, bateristaEscutando3, bateristaEscutando4, bateristaEscutando5};
    bateristaTocando = new ImageView[]{ bateristaTocando1, bateristaTocando2, bateristaTocando3, bateristaTocando4, bateristaTocando5};
    bateristaEsperando = new ImageView[] {bateristaEsperando1, bateristaEsperando2, bateristaEsperando3, bateristaEsperando4, bateristaEsperando5};
    mutex = new Semaphore(1);
    for(int i=0; i<N ;i++){
      forks [i] = new Semaphore(0);
      filosofos[i] = new Filosofo(i, this);
      states[i] = 0;
      velocidadeEscutando[i].setValue(3);
      velocidadeTocando[i].setValue(3);
    }
    for (int j=0; j<N; j++){
      filosofos[j].setNeighbours();
    }
    filosofos[0].start();
    filosofos[1].start();
    filosofos[2].start();
    filosofos[3].start();
    filosofos[4].start();
  }

  /* ***************************************************************
   * Metodo: resetar
   * Funcao: Chama as subrotinas que configuram o reset da aplicacao
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void resetar(){
    interromperThreads();
    iniciar();
  }

  /* ***************************************************************
   * Metodo: interromperThreads
   * Funcao: Interrompe as threads.
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  public void interromperThreads() {
    for (int i = 0; i < N; i++) {
      filosofos[i].interrupt();
      baquetas[i].setVisible(true);
    }
  }

  /* ***************************************************************
   * Metodo: pauseBaterista1
   * Funcao: Pausa o processo referente ao baterista especificado.
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void pauseBaterista1() {
    filosofos[0].pausar();
  }

  /* ***************************************************************
   * Metodo: restartBaterista1
   * Funcao: restarta o processo referente ao baterista especificado.
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void restartBaterista1() {
    filosofos[0].retomar();
  }
  @FXML
  public void pauseBaterista2() {
    filosofos[1].pausar();
  }
  @FXML
  public void restartBaterista2() {
    filosofos[1].retomar();
  }
  @FXML
  public void pauseBaterista3() {
    filosofos[2].pausar();
  }

  @FXML
  public void restartBaterista3() {
    filosofos[2].retomar();
  }

  @FXML
  public void pauseBaterista4() {
    filosofos[3].pausar();
  }
  @FXML
  public void restartBaterista4() {
    filosofos[3].retomar();
  }
  @FXML
  public void pauseBaterista5() {
    filosofos[4].pausar();
  }
  @FXML
  public void restartBaterista5() {
    filosofos[4].retomar();
  }

  /* ***************************************************************
   * Metodo: getVelocidadeOuvindo
   * Funcao: Verifica o valor atual do slider de velocidade no
   *         estagio escutando rock.
   * Parametros: Sem parametros.
   * Retorno: retorna um valor numerico de tipo inteiro.
   *************************************************************** */
  public int getVelocidadeOuvindo(int id){
    return (int) velocidadeEscutando[id].getValue();
  }

  /* ***************************************************************
   * Metodo: getVelocidadeTocando
   * Funcao: Verifica o valor atual do slider de velocidade no
   *         estagio tocando rock.
   * Parametros: Sem parametros.
   * Retorno: retorna um valor numerico de tipo inteiro.
   *************************************************************** */
  public int getVelocidadeTocando(int id){
    return (int) velocidadeTocando[id].getValue();
  }

  /* ***************************************************************
   * Metodo: setEstadoBaterista
   * Funcao: Modifica o texto disposto no label referente ao estado
   *         do baterista.
   * Parametros: Id do filosofo e seu estado atual.
   * Retorno: Sem retorno
   *************************************************************** */
  public void setEstadoBaterista (int id, String estado){
    estadoBaterista[id].setText(estado);
  }

  /* ***************************************************************
   * Metodo: changeImageToPlaying
   * Funcao: Modifica a visibilidade das imagens do filosofo, deixando
   *         visivel sua imagem tocando.
   * Parametros: Id do filosofo.
   * Retorno: Sem retorno
   *************************************************************** */
  public void changeImageToPlaying(int id){
    bateristaEscutando[id].setVisible(false);
    bateristaEsperando[id].setVisible(false);
    bateristaTocando[id].setVisible(true);
  }

  /* ***************************************************************
   * Metodo: changeImageToListening
   * Funcao: Modifica a visibilidade das imagens do filosofo, deixando
   *         visivel sua imagem escutando.
   * Parametros: Id do filosofo.
   * Retorno: Sem retorno
   *************************************************************** */
  public void changeImageToListening(int id){
    bateristaEscutando[id].setVisible(true);
    bateristaEsperando[id].setVisible(false);
    bateristaTocando[id].setVisible(false);

  }

  /* ***************************************************************
   * Metodo: changeImageToWaiting
   * Funcao: Modifica a visibilidade das imagens do filosofo, deixando
   *         visivel sua imagem esperando.
   * Parametros: Id do filosofo.
   * Retorno: Sem retorno
   *************************************************************** */
  public void changeImageToWaiting(int id){
    bateristaEscutando[id].setVisible(false);
    bateristaEsperando[id].setVisible(true);
    bateristaTocando[id].setVisible(false);
  }

  /* ***************************************************************
   * Metodo: hideSticks
   * Funcao: Modifica a visibilidade das imagens das baquetas,
   *         tornando-as invisiveis.
   * Parametros: Id do filosofo.
   * Retorno: Sem retorno
   *************************************************************** */
  public void hideSticks(int id, int idRight){
    baquetas[id].setVisible(false);
    baquetas[idRight].setVisible(false);
  }

  /* ***************************************************************
   * Metodo: showSticks
   * Funcao: Modifica a visibilidade das imagens das baquetas,
   *         tornando-as visiveis.
   * Parametros: Id do filosofo.
   * Retorno: Sem retorno
   *************************************************************** */
  public void showSticks(int id, int idLeft, int idRight){
    if (states[id]!=2 && states[idLeft]!=2){
      baquetas[id].setVisible(true);
    }
    if (states[idRight]!=2) {
      baquetas[idRight].setVisible(true);
    }
  }

}
