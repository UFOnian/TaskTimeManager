# TaskTimeManager - このプロジェクトの概要
このプロジェクトは、Taskにかけた時間の管理が出来なさすぎるカーペンターことわたしが、作成する、  
「タスク毎にかけた時間を管理するためのツール」である。

もとよりタスクの管理が苦手な人間ゆえ、進捗が出にくかったものを、
あえて世に出すことで進捗を出そうという目論見でGitHub公開に至った。

もの自体はある程度できた状態(Git管理されていない状態のまま)なので、ある程度整形した状態でまずはリリースを行う予定。

このプロジェクトの経緯みたいのは [このQiita記事](https://qiita.com/UFOnian/private/f3b0e7e03bb3187fa0c8) も参照のこと。  
中途半端で恥ずかしいので限定共有記事になっている。

## Country Language (国語について)
開発者及び想定している共同開発者は日本人である。
当面の間は、日本語でドキュメントを作成するので、海外の人はあしからず。

盛り上がり具合によっては英語にするかも？

## 開発言語・環境 (Programing Language/Development Environment)
* Java jdk 1.8.0_241 及び同梱されている JavaFX
    * 今の所はこの環境にて開発をおこなう。 Java 8 なら多少バージョン高いやつでもコンパイルできるよ。(たぶん)
    * Javaに関しては少し古いバージョンでコンパイルしても動作しそうだが、  
      JavaFXの部分については、そこそこアップデートが頻繁くさいので、動作しない可能性が高い。
         * なお、JavaFXについては、Java 11 になるとそこそこ無視できない機能強化があったりするので、本当は11にしたかったりする。(日本語ドキュメントがあったらそうしていただろうと思う。)
* JetBrains IDEA (Community/Ultimate)  
    筆者は [JetBrains IDEA Ultimate](https://www.jetbrains.com/ja-jp/idea/features/editions_comparison_matrix.html) を使用して開発を行っている。  
    * 少し見てみたところ、Community Editionでも十分に開発を行えそうな雰囲気がある。  
        と、いうのは、このプロジェクトでは JavaFX を使っているので、これとの統合があるほうがよい。Community Edition はこれをサポートしているので、十分であろう。  
    * また、一時期 DataBase(SQLite) を使った方法で TaskList の保存をしようとしていたが、これはたぶん使わないことになるので、気にしなくてよい。  
        DBツールも JetBrains で調達しようとすると Ultimate か DataGrip が必要になり、有償になる。ま、多少不便だけどVSCode+Pluginとかで十分使用に耐えるのでなくても困りはしない。
* IDEなどの代替案 → 任意の Editor + [JavaFX Scene Builder](https://www.oracle.com/java/technologies/javafxscenebuilder-1x-archive-downloads.html)      
    * 別に IDEA にこだわらなくとも、 Scene Builder があれば開発自体は可能。というか、 `fxml` が "*読める*" なら、Scene Builder すら不要。
    * 任意の Editor は、 eclipse とか VSCode とか、まあ色々あると思う。要するに下記が満たされれば万事OK
        * Java を利用した開発。特に、 JavaFX を利用したGUI開発の利便性が高いこと
        * 必要ならば、 JavaFX のGUIを確認・編集するためのツール
        * ※Optional: 筆者は JavaDoc 出力を積極的に利用する予定なので、これができるツール

## 作業中は
[23.exe](https://music.amazon.co.jp/artists/B010GNVVP8?ref=dm_sh_990b-dd32-3c73-cdac-92d02) を聴きなさい。※任意。

