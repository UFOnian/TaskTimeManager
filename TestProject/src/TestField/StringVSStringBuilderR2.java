package TestField;

public class StringVSStringBuilderR2 {

  public static void main(String[] args) {
    caseString();
    caseStringBuilder();

  }

  private static void caseString() {
    long t0 = System.nanoTime();
    String context =
        "下記のタスクを削除します。\n\n" +
            "Name : " + getTestStr() + "\n" +
            "Time : " + getTestStr() + "\n\n" +
            "よろしいですか？";
    long tf = System.nanoTime();

    System.out.println("caseString:" + (tf - t0));
  }

  private static void caseStringBuilder() {

    long t0 = System.nanoTime();
    StringBuilder sb = new StringBuilder();
    sb.append("下記のタスクを削除します。");
    sb.append("\n\n");

    sb.append("Name : ");
    sb.append(getTestStr());
    sb.append("\n");

    sb.append("Time : ");
    sb.append(getTestStr());
    sb.append("\n\n");

    sb.append("よろしいですか？");
    long tf = System.nanoTime();

    System.out.println("caseStrBld:" + (tf - t0));
  }

  private static String getTestStr(){
    return "THISISTESTSTRIIIIING";
  }

}
