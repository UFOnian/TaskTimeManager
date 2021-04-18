package MainApp.Model.SingleTaskTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 一件の TaskTime データについて、 そのデータを JavaFX の TableView で表示するための Entity (行)データを意味する。<br>
 * 表示に寄った機能であるが、ユーザーが登録したデータを円滑に反映するために、 setter は実データへ対しても反映されるようにした。
 *
 * @since 0.0
 * @version 0.0
 */
public class TaskTimeEntity {

  public static final DateTimeFormatter HH_mm_ss = DateTimeFormatter.ofPattern("HH:mm:ss");
  /**
   * Duration のオブジェクトから、表示用のタイマーの文字列を作成する。
   * @param duration 表示元の時間を示す Duration
   * @return 表示用の String の 時間
   */
  public static String getTaskTimeString(Duration duration) {
    LocalTime t = LocalTime.MIDNIGHT.plus(duration);
    // TODO: この仕様では、24時間を超える時間を記録することができない。
    return HH_mm_ss.format(t);
  }

  /**
   * TODO: 時間表示形式の指定。 ゆくゆくはオプションで編集できるようにしたら、いいんじゃない？
   */
  private DateTimeFormatter durationFormatter;

  /* # コンストラクタ */

  /**
   * 指定した TaskTime オブジェクトをもとに、 TableView 用の Entity を作成する。
   * @param task 作成もとの TaskTime オブジェクト
   */
  public TaskTimeEntity(TaskTime task) {
    this.task = task;
    taskName = new SimpleStringProperty(task.getName());
    taskTime = new SimpleStringProperty(getTaskTimeString(task.getDuration()));
    durationFormatter = HH_mm_ss;
  }

  /* # プロパティ */

  /* ## TaskTime */
  /**
   * 表示元の TaskTime
   */
  private final TaskTime task;
  /**
   * 表示元の TaskTime を取得
   * @return 表示元の TaskTime
   */
  public TaskTime getTask() {
    return task;
  }

  /* ## TaskTime の name (表示名) */

  /**
   * 表示元の TaskTime の name を格納する Property
   */
  private final StringProperty taskName;
  /**
   * 表示元の TaskTime の name を設定
   * @param taskName 設定する文字列
   */
  public void setTaskName(String taskName) {
    task.setName(taskName);
    this.taskName.set(taskName);
  }
  /**
   * 表示元の TaskTime の name Property を取得
   * @return 表示元の TaskTime の name Property
   */
  public StringProperty getTaskNameProperty() {
    return taskName;
  }
  /**
   * 表示元の TaskTime の name の String を取得
   * @return 表示元の TaskTime の name の String
   */
  public String getTaskName() {
    return taskName.get();
  }

  /* ## TaskTime の time (計測時間) */

  /**
   * 表示元の TaskTime の time を格納する Property
   */
  private final StringProperty taskTime;
  /**
   * 表示元の TaskTime の time を設定
   * @param seconds 設定する時間
   */
  public void setTaskTime(double seconds) {
    task.setDuration(Duration.ofSeconds((long) seconds));
    taskTime.set(getTaskTimeString());
  }
  /**
   * 指定された文字列に整形された時間を受け取った場合にのみ、時間を示す値を更新する。<br>
   * 値の更新に成功した場合にのみ true を返す
   *
   * @param formattedTimeString 整形済みで渡されたい時間文字列 (執筆時点では "HH:mm:ss" 形式)
   * @return 正しく値を更新できた場合に true それ以外は false
   */
  public boolean setTaskTime(String formattedTimeString) {
    // TODO: 経過時間の編集機能の追加
    try {
      LocalTime t = LocalTime.parse(formattedTimeString, HH_mm_ss);
      setTaskTime(t.toSecondOfDay());
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }
  /**
   * 表示元の TaskTime の time Property を取得
   * @return 表示元の TaskTime の time Property
   */
  public StringProperty getTaskTimeProperty() {
    return taskTime;
  }
  /**
   * 表示元の TaskTime の time Property を取得
   * @return 表示元の TaskTime の time Property
   */
  public String getTaskTime() {
    return taskTime.get();
  }
  /**
   * 表示元の TaskTime の time の String を取得
   * @return 表示元の TaskTime の time の String
   */
  public String getTaskTimeString() {
    LocalTime t = LocalTime.MIDNIGHT.plus(task.getDuration());
    // TODO: この仕様では、24時間を超える時間を記録することができない。
    return durationFormatter.format(t);
  }


}
