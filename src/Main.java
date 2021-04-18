import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @since 0.0
 * @version 0.0
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainApp/TaskTimeManager.fxml")));
    primaryStage.setTitle("Task Time Manager");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

    root.setUserData(primaryStage);
  }

  @Override
  public void stop() throws Exception {
    // TODO: ファイル出力機能実装時は、ファイルへの保存状態を取得し、未保存時は保存処理の実施有無を確認する。
    super.stop();
  }
}
