package MainApp;

import MainApp.Model.SingleTaskTime.TaskTime;
import MainApp.Model.SingleTaskTime.TaskTimeEntity;
import MainApp.Model.TaskTimeList.ObservableTaskTimeList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

// TODO: JavaDoc コメントの @param 及び @return について、 Nullable / not Nullable を明記

/**
 * @since 0.0
 * @version 0.0
 */
public class Controller implements Initializable {

  // TODO: オプションを設定する画面の作成

  /* デバッグ用 */

  private int Count = 0;

  @FXML
  private void dump() {
    Object target;
    /*
    target = taskNameCol.getOnEditStart();
    str = Count + (
        target == null ?
            "NULL" :
            target.toString());
    */

//    str = LocalTime.now().toNanoOfDay()+"";

    target = getSelectedTaskEntity();
    if (Count == 0) System.out.println("selected");

    String str = Count + ":" + (target == null ? "NULL" : target.toString());
    Count++;
    System.out.println(str);
  }


  /* 実行時定数 (になる予定) */

  /**
   * 再生ボタンの色
   */
  private static final Color PLAY_BUTTON_COLOR = Color.valueOf("00FF00");
  /**
   * 再生ボタンのテキスト
   */
  public static final String PLAY_BUTTON_TEXT = "▶";

  /**
   * 一時停止ボタンの色
   */
  private static final Color PAUSE_BUTTON_COLOR = Color.BLACK;
  /**
   * 一時停止ボタンのテキスト
   */
  public static final String PAUSE_BUTTON_TEXT = "\u23F8"; /* ⏸ */

  /* コンパイル時定数 (になる予定) */

  /**
   * タスクの新規作成時名前
   */
  private static final String NEW_TASK_NAME = "ENTER NEW TASK NAME";

  /**
   * 無選択状態時ディスプレイ名
   */
  private static final String TASK_NOT_SELECTED = "notSelected";

  /* # 表示データ */

  @FXML
  private AnchorPane root;

  /* ## 計測中フィールド情報 */

  /**
   * 現在計測中のタスク
   */
  private TaskTimeEntity currentTask;

  /**
   * 選択・計測中のタスクの名前を大胆に表示
   */
  @FXML
  private Label currentTaskNameLabel;

  /**
   * メインタイマーが示す 経過時間
   */
  private Duration timerDuration = Duration.ZERO;
  /**
   * メインタイマーのテキスト表示
   */
  @FXML
  private Label timerLabel;

  /**
   * アニメーションのためのタイマー
   */
  private Timeline timeline;

  /* ## 各種操作 */

  /**
   * 再生/一時停止ボタン
   */
  @FXML
  private ToggleButton btnPlayPause;

  /* ## タスク一覧 */

  /**
   * タスク一覧を表示するテーブル
   */
  @FXML
  private TableView<TaskTimeEntity> taskTable;
  /**
   * タスクの名前列
   */
  @FXML
  private TableColumn<TaskTimeEntity, String> taskNameCol;
  /**
   * タスクの時間列
   */
  @FXML
  private TableColumn<TaskTimeEntity, String> taskTimeCol;


  /* # 操作するモデル */

  /**
   * タスク一覧を格納したりするモデル
   * データ保存をしない間はとっても出番が少ない
   */
  private ObservableTaskTimeList taskList = new ObservableTaskTimeList();

  /* # メソッド */

  /* ## 初期化メソッド */

  /**
   * コンテンツの初期化
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setCurrentTask(null);
    initTaskTable();
    initTimeLine();

  }

  /**
   * TaskTable の初期化を行う
   */
  private void initTaskTable() {
    // よぐわがんねけど書くらしいから書いた
    taskNameCol.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
    taskTimeCol.setCellValueFactory(new PropertyValueFactory<>("TaskTime"));

    // 名前は編集可能にする
    taskNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

    taskTable.setItems(taskList);

    // TODO: タスクテーブルの初期化とテストケースの削除
    // テーブルに新しい値を入れるテストケース
    taskTable.getItems().add(
        new TaskTimeEntity(
            new TaskTime("TestTask")
        )
    );
  }

  /**
   * アニメーション用の TimeLine の初期化を行う
   */
  private void initTimeLine() {
    // 1秒周期でメインタイマーの時間をすすめる
    KeyFrame timerKF = new KeyFrame(Duration.seconds(1), this::onKeyFrameFinished);
    timeline = new Timeline(1, timerKF);
    timeline.setCycleCount(Timeline.INDEFINITE);
  }

  /**
   * タイマーのアニメーションを行うためのイベントハンドラ。<br>
   * KeyFrame 側で指定した時間毎に発生する。
   * @param finEve イベント引数
   */
  private void onKeyFrameFinished(ActionEvent finEve) {
    // 経過時間を取得
    Duration duration = ((KeyFrame) finEve.getSource()).getTime();

    // タイマーに経過時間をぶつける
    timerDuration = timerDuration.add(duration);

    // 表示の更新
    setTimerLabel(timerDuration);
    currentTask.setTaskTime(timerDuration.toSeconds());
    taskTable.refresh();
  }

  /* ## 共通利用メソッド */

  /* ### プロパティ */

  /**
   * タイマーが再生中であるかどうかを返す
   *
   * @return 再生中ならば true それ以外は false
   */
  private boolean isPlaying() {
    return btnPlayPause.isSelected();
  }


  /**
   * ウィンドウタイトルを設定する
   *
   * @param str ウィンドウタイトルに設定する文字列
   */
  private void setTitle(String str) {
    ((Stage) root.getUserData()).setTitle(str);
  }

  /**
   * 画面に表示するメインタイマー文字列を設定する<br>
   * java.time 産 Duration を、 javafx産 Duration に変換<br>
   * java.time.  Duration duration;<br>
   * javafx.util.Duration(duration.toMillis());<br>
   *
   * @param duration タイマーに表示する時間
   */
  private void setTimerLabel(Duration duration) {
    setTimerLabel(java.time.Duration.ofSeconds((long) duration.toSeconds()));
  }

  /**
   * 画面に表示するメインタイマー文字列を設定する
   *
   * @param duration タイマーに表示する時間
   */
  private void setTimerLabel(java.time.Duration duration) {
    timerLabel.setText(TaskTimeEntity.getTaskTimeString(duration));
  }

  /**
   * {@link Controller#taskTable} で最後にクリックされた TaskTimeEntity を返す<br>
   * クリック後に別の場所をクリックすると Null になる。<br>
   * 現在計測中のデータを要求する場合は {@link Controller#currentTask}を参照すること
   *
   * @return {@link Controller#taskTable} にて選択された task のエンティティを返す
   */
  private TaskTimeEntity getSelectedTaskEntity() {
    return taskTable.getSelectionModel().getSelectedItem();
  }

  /* ### 動作 */

  /**
   * タイマーを再生状態に変更する
   */
  private void timerStart() {
    if (currentTask == null) {
      Alert dialog = new Alert(Alert.AlertType.WARNING, "先に測定するタスクを選択してください。", ButtonType.CLOSE);
      dialog.showAndWait();
      btnPlayPause.setSelected(false);
      return;
    }

    btnPlayPause.setSelected(true);
    btnPlayPause.setText(PAUSE_BUTTON_TEXT);
    btnPlayPause.setTextFill(PAUSE_BUTTON_COLOR);
    timeline.play();
  }

  /**
   * タイマーを一時停止状態に変更する
   */
  private void timerPause() {
    btnPlayPause.setSelected(false);
    btnPlayPause.setText(PLAY_BUTTON_TEXT);
    btnPlayPause.setTextFill(PLAY_BUTTON_COLOR);
    timeline.pause();
  }

  /* イベントハンドラ */

  /**
   * タイマーの、再生状態と、一時停止状態を切り替える
   */
  @FXML
  private void toggleTimer() {
    if (isPlaying()) {
      // Pausing then Play
      timerStart();
    } else {
      // Playing then Pause
      timerPause();
    }
  }

  /**
   * 新しいタスクとタイムのセットを生成する
   */
  @FXML
  private void createNewTask() {
    TaskTime newTask = new TaskTime(NEW_TASK_NAME);
    TaskTimeEntity newTaskEntity = new TaskTimeEntity(newTask);

    taskTable.getItems().add(newTaskEntity);

    // TODO: 新規行追加時、 edit を実行しても画面上及び処理的には edit にならない問題。 先に refresh したら解決するか？
//    taskTable.edit(taskTable.getItems().size() - 1, taskNameCol);
////    taskNameCol.getCellFactory().call(taskNameCol).startEdit();
//    taskTable.refresh();
  }

  /**
   * 選択中のタスクを削除する
   */
  @FXML
  private void deleteTask() {
    // Timer が進行中の場合は一時停止する。 TODO: オプションで動作を指定

    if (isPlaying()) timerPause();

    // NOTE:current ではなく、明らかに削除する意図があると取れる selected を利用
    TaskTimeEntity targetTaskEntity = getSelectedTaskEntity();

    // 未選択時は選択がない旨を表示 TODO: Alertが出るのは、うざくないか？ 要検討: 選択がない場合のメッセージ表示の簡易化。
    if (targetTaskEntity == null) {
      Alert dialog = new Alert(
          Alert.AlertType.ERROR,
          "削除するタスクが選択されていません。",
          ButtonType.CLOSE);
      dialog.showAndWait();
      return;
    }

    // TODO: 削除確認メッセージの表示/非表示を指定するオプションの作成。

    // 削除確認メッセージの表示
    String context =
        "下記のタスクを削除します。\n\n  Name\t: " + targetTaskEntity.getTaskName() +
            "\n   Time\t: " + targetTaskEntity.getTaskTime() +
            "\n\nよろしいですか？";

    Alert dialog = new Alert(
        Alert.AlertType.CONFIRMATION,
        context,
        ButtonType.YES, ButtonType.CANCEL);
    dialog.setTitle("削除確認");
    dialog.setHeaderText("タスクの削除確認");
    Optional<ButtonType> deleteConfirmationResult = dialog.showAndWait();

    if (deleteConfirmationResult.isPresent() && deleteConfirmationResult.get().equals(ButtonType.YES)) {
      taskList.remove(targetTaskEntity);
      taskTable.refresh();
      setCurrentTask(null);
    }
  }

  /**
   * taskTable 内でクリックしたときのハンドラ。
   * クリックされたタスクを選択状態にし、該当タスクのタイマーをセットする。
   *
   * @param event イベント引数
   */
  @FXML
  private void selectTask(MouseEvent event) {
//    taskTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // its Default

    taskNameCol.getCellFactory().call(taskNameCol).cancelEdit();
    taskTable.refresh();

    if (getSelectedTaskEntity() == null) return; // なんにもないとこ選んだらnull

    // メインタイマーの切り替え
    setCurrentTask(getSelectedTaskEntity());
  }

  /**
   * メインタイマーで測定を行うタスクを変更する。<br>
   * null を渡す場合、何も選択されていない状態となり、計測不可となる。)
   *
   * @param currentTask メインタスクとして設定する TaskTimeEntity
   */
  private void setCurrentTask(TaskTimeEntity currentTask) {
    this.currentTask = currentTask;

    // Case Nothing Selected
    if (currentTask == null) {
      currentTaskNameLabel.textProperty().unbind();
      currentTaskNameLabel.setText(TASK_NOT_SELECTED);
      setTimerLabel(Duration.ZERO);
      if (root.getUserData() != null)
        setTitle("Task Time Manager");
      return;
    }

    currentTaskNameLabel.textProperty().bind(
        currentTask.getTaskNameProperty()
    );

    java.time.Duration currentTime = currentTask.getTask().getDuration();
    timerDuration = Duration.seconds(currentTime.getSeconds());
    setTimerLabel(currentTime);
    setTitle("TTM : " + currentTask.getTaskName() + " | " + currentTask.getTaskTime());
  }

  /**
   * タスク名の編集開始動作
   *
   * @param editEvent イベント引数
   */
  @FXML
  private void onTaskEditStart(TableColumn.CellEditEvent<TaskTimeEntity, String> editEvent) {
    if (isPlaying()) timerPause(); // TODO: 編集開始時の動作のオプションでの指定
  }

  /**
   * タスク一覧表でのタスク名編集作業が確定された際の動作 <br>
   * 物理的には、 TableColumn の編集完了時に発生する onEditCommit イベントのハンドラ
   *
   * @param editEvent イベント引数
   */
  @FXML
  private void onTaskNameEditCommit(TableColumn.CellEditEvent<TaskTimeEntity, String> editEvent) {

    TaskTimeEntity editedTask = editEvent.getRowValue();

    // 異常時(タスク名に空白文字列が指定された場合)は Rollback
    if (editEvent.getNewValue().trim().isEmpty()) {
      editedTask.setTaskName(editEvent.getOldValue());
      taskTable.refresh();
      return;
    }

    // 正常時のみ Commit
    editedTask.setTaskName(editEvent.getNewValue().trim());
    editEvent.consume();
  }

}
