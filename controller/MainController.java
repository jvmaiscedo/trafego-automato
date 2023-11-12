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
import model.Reader;
import model.Writer;


public class MainController implements Initializable {
  //Elementos de imagem
  @FXML
  ImageView student1back;
  @FXML
  ImageView student1udr;
  @FXML
  ImageView student2back;
  @FXML
  ImageView student2udr;
  @FXML
  ImageView student3back;
  @FXML
  ImageView student3udr;
  @FXML
  ImageView student4back;
  @FXML
  ImageView student4udr;
  @FXML
  ImageView student5back;
  @FXML
  ImageView student5udr;
  @FXML
  ImageView professor1front;
  @FXML
  ImageView professor1back;
  @FXML
  ImageView professor2front;
  @FXML
  ImageView professor2back;
  @FXML
  ImageView professor3front;
  @FXML
  ImageView professor3back;
  @FXML
  ImageView professor4front;
  @FXML
  ImageView professor4back;
  @FXML
  ImageView professor5front;
  @FXML
  ImageView professor5back;

  //Elementos utilizados para ilustrar os dados
  @FXML
  Label dataBaseText;
  @FXML
  Label drTextStudent1;
  @FXML
  Label drTextStudent2;
  @FXML
  Label drTextStudent3;
  @FXML
  Label drTextStudent4;
  @FXML
  Label drTextStudent5;

  //Elementos de controle
  //professor 1
  @FXML
  Button pauseProf1;
  @FXML
  Button retomarProf1;
  @FXML
  Slider thinkingData1;
  @FXML
  Slider writingData1;
  //professor 2
  @FXML
  Button pauseProf2;
  @FXML
  Button retomarProf2;
  @FXML
  Slider thinkingData2;
  @FXML
  Slider writingData2;
  //professor 3
  @FXML
  Button pauseProf3;
  @FXML
  Button retomarProf3;
  @FXML
  Slider thinkingData3;
  @FXML
  Slider writingData3;
  //professor 4
  @FXML
  Button pauseProf4;
  @FXML
  Button retomarProf4;
  @FXML
  Slider thinkingData4;
  @FXML
  Slider writingData4;
  //professor 5
  @FXML
  Button pauseProf5;
  @FXML
  Button retomarProf5;
  @FXML
  Slider thinkingData5;
  @FXML
  Slider writingData5;

  //aluno 1
  @FXML
  Button pauseStudent1;
  @FXML
  Button retomarStudent1;
  @FXML
  Slider usingDr1;
  @FXML
  Slider readingData1;
  //aluno 2
  @FXML
  Button pauseStudent2;
  @FXML
  Button retomarStudent2;
  @FXML
  Slider usingDr2;
  @FXML
  Slider readingData2;
  //aluno 3
  @FXML
  Button pauseStudent3;
  @FXML
  Button retomarStudent3;
  @FXML
  Slider usingDr3;
  @FXML
  Slider readingData3;
  //aluno 4
  @FXML
  Button pauseStudent4;
  @FXML
  Button retomarStudent4;
  @FXML
  Slider usingDr4;
  @FXML
  Slider readingData4;
  //aluno 5
  @FXML
  Button pauseStudent5;
  @FXML
  Button retomarStudent5;
  @FXML
  Slider usingDr5;
  @FXML
  Slider readingData5;

  public static Semaphore mutex = new Semaphore(1);
  public static int rc = 0;
  public static Semaphore db = new Semaphore(1);

  public Reader [] estudante = new Reader[5];
  public Writer [] professor = new Writer[5];

  public ImageView [] professorFront;
  public ImageView [] professorBack;
  public ImageView [] studentUdr;
  public ImageView [] studentBack;
  public Label [] drTextStudent;
  public Slider [] thinkingData;
  public Slider [] writingData;
  public Slider [] usingDr;
  public Slider [] readingData;

  @Override
  public void initialize(URL url, ResourceBundle rb) {


    studentUdr = new ImageView[]{student1udr, student2udr, student3udr, student4udr, student5udr};
    studentBack = new ImageView[]{student1back, student2back, student3back, student4back, student5back};
    drTextStudent = new Label[]{drTextStudent1, drTextStudent2, drTextStudent3, drTextStudent4, drTextStudent5};
    professorFront = new ImageView[]{professor1front, professor2front, professor3front, professor4front, professor5front};
    professorBack = new ImageView[] {professor1back, professor2back, professor3back, professor4back, professor5back};
    thinkingData = new Slider[]{thinkingData1, thinkingData2, thinkingData3, thinkingData4, thinkingData5};
    writingData = new Slider[] {writingData1, writingData2, writingData3, writingData4, writingData5};
    usingDr = new Slider[] {usingDr1, usingDr2, usingDr3, usingDr4, usingDr5};
    readingData = new Slider[] {readingData1, readingData2, readingData3, readingData4, readingData5};

    for (int i=0; i<5; i++){
      estudante[i] = new Reader(i, this);
      professor[i] = new Writer(i, this);
      thinkingData[i].setValue(3);
      writingData[i].setValue(3);
      usingDr[i].setValue(3);
      readingData[i].setValue(3);
    }

    for (int j=0; j<5; j++) {
      estudante[j].start();
      professor[j].start();
    }


  }

  public int thinkingData(int id){
    return (int) thinkingData[id].getValue();
  }
  public int writingData(int id){
    return (int) writingData[id].getValue();
  }
  public int usingDr(int id){
    return (int) usingDr[id].getValue();
  }
  public int readingData(int id){
    return (int) readingData[id].getValue();
  }
  public void changeStudentUdr(int id){
    studentUdr[id].setVisible(true);
    studentBack[id].setVisible(false);
  }
  public void changeStudentRead(int id){
    studentUdr[id].setVisible(false);
    studentBack[id].setVisible(true);
  }
  public void setDrTextStudent(int id, String text){
    drTextStudent[id].setText(text);
  }

  public void changeProfessorThink(int id){
    professorFront[id].setVisible(true);
    professorBack[id].setVisible(false);
  }
  public void changeProfessorWritedb(int id){
    professorFront[id].setVisible(false);
    professorBack[id].setVisible(true);
  }

  public String getDataBaseText() {
    return dataBaseText.getText();
  }

  public void setDataBaseText(String text) {
    this.dataBaseText.setText(text);
  }
  @FXML
  public void pauseProf1(){
    professor[0].pausar();
  }
  @FXML
  public void retomarProf1(){
    professor[0].retomar();
  }
  @FXML
  public void pauseProf2(){
    professor[1].pausar();
  }
  @FXML
  public void retomarProf2(){
    professor[1].retomar();
  }
  @FXML
  public void pauseProf3(){
    professor[2].pausar();
  }
  @FXML
  public void retomarProf3(){
    professor[2].retomar();
  }
  @FXML
  public void pauseProf4(){
    professor[3].pausar();
  }
  @FXML
  public void retomarProf4(){
    professor[3].retomar();
  }
  @FXML
  public void pauseProf5(){
    professor[4].pausar();
  }
  @FXML
  public void retomarProf5(){
    professor[4].retomar();
  }

}
