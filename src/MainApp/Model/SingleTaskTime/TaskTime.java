package MainApp.Model.SingleTaskTime;


import java.time.Duration;

/**
 * ひとつのタスクに割り当てられた、名前と時間を格納する。
 *
 * @since 0.0
 * @version 0.0
 */
public class TaskTime {

  /* コンストラクタ */

  /**
   * タスクの名前を指定してコンストラクト
   * @param name タスクの名前を指定
   */
  public TaskTime(String name){
    this.name = name;
    resetTimer();
  }

  /* プロパティ */

  /**
   * タスクの名前
   */
  private String name;

  /**
   * タスクの名前 を取得
   * @return タスク名 (name)
   */
  public String getName() {
    return name;
  }

  /**
   * タスクの名前 を設定
   * @param name 設定するタスク名
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * タスクにかけた時間
   */
  private Duration duration;

  /**
   * タスクにかけた時間 を設定
   * @param duration 設定する時間
   */
  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  /**
   * タスクにかけた時間 を取得
   * @return タスクにかけた時間　duration
   */
  public Duration getDuration() {
    return duration;
  }

  /* 動作 */

  /**
   * タスクにかけた時間 をゼロにする
   */
  public void resetTimer(){
    duration = Duration.ZERO;
  }
}
