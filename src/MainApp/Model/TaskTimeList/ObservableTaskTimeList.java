package MainApp.Model.TaskTimeList;


import MainApp.Model.SingleTaskTime.TaskTimeEntity;
import javafx.collections.ModifiableObservableListBase;

import java.util.ArrayList;

/**
 * タスク一覧を格納するためのリスト
 *
 * @since 0.0
 * @version 0.0
 */
public class ObservableTaskTimeList extends ModifiableObservableListBase<TaskTimeEntity> {
  private ArrayList<TaskTimeEntity> taskList = new ArrayList<>();

  // TODO: ファイル入出力の実装。 CSV? JSon?

  @Override
  public TaskTimeEntity get(int index) {
    return taskList.get(index);
  }

  @Override
  public int size() {
    return taskList.size();
  }

  @Override
  public void doAdd(int index, TaskTimeEntity element) {
    taskList.add(index, element);
  }

  @Override
  public TaskTimeEntity doSet(int index, TaskTimeEntity element) {
    return taskList.set(index, element);
  }

  @Override
  public TaskTimeEntity doRemove(int index) {
    return taskList.remove(index);
  }

}
