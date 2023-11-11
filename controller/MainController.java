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
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    for (int i=0; i<5; i++){
      estudante[i] = new Reader(i, this);
      professor[i] = new Writer(i, this);
    }
    studentUdr = new ImageView[]{student1udr, student2udr, student3udr, student4udr, student5udr};
    studentBack = new ImageView[]{student1back, student2back, student3back, student4back, student5back};
    drTextStudent = new Label[]{drTextStudent1, drTextStudent2, drTextStudent3, drTextStudent4, drTextStudent5};
    professorFront = new ImageView[]{professor1front, professor2front, professor3front, professor4front, professor5front};
    professorBack = new ImageView[] {professor1back, professor2back, professor3back, professor4back, professor5back};

    for (int j=0; j<5; j++){
      estudante[j].start();
      professor[j].start();
    }



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
}
