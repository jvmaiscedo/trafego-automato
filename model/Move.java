package model;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Move {
  public Move(){}
  private void move_down(double lim, ImageView img){
    img.setRotate(180);
    while(img.getLayoutY()<748){
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY()+2));
    }
  }
  private void move_up(double lim, ImageView img){
    img.setRotate(0);
    while(img.getLayoutY()>-8){
      Platform.runLater(() -> img.setLayoutY(img.getLayoutY()-2));

    }
  }
  private void move_right(double lim, ImageView img){
    img.setRotate(90);
    while(img.getLayoutX()<759){
      Platform.runLater(() -> img.setLayoutX(img.getLayoutX()+2));
    }
  }
  private void move_left(double lim, ImageView img){
    img.setRotate(270);
    while(img.getLayoutX()>20){
      Platform.runLater(() -> img.setLayoutX(img.getLayoutX()-2));
    }
  }
  private void realoCar(ImageView img, double posInicialX, double posInicialY ){
    Platform.runLater(()->{
      img.setLayoutX(posInicialX);
      img.setLayoutY(posInicialY);}
    );
  }
}
