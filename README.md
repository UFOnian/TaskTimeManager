# TaskTimeManager - このプロジェクトの概要

このプロジェクトは、Taskにかけた時間の管理が出来なさすぎるカーペンターことわたしが、作成する、  
「タスク毎にかけた時間を管理するためのツール」である。

もとよりタスクの管理が苦手な人間ゆえ、進捗が出にくかったものを、  
あえて世に出すことで進捗を出そうという目論見でGitHub公開に至った。

もの自体はある程度できた状態(Git管理されていない状態のまま)なので、ある程度整形した状態でまずはリリースを行う予定。

このプロジェクトの経緯みたいのは [このQiita記事](https://qiita.com/UFOnian/private/f3b0e7e03bb3187fa0c8) も参照のこと。  
中途半端で恥ずかしいので限定共有記事になっている。

また、追加したい機能について、多くが TODO のまま放置されている。  
というか、整理してたら「あーこれこの機能実装するために作って放置されてんなー」ってのが少なからずあったので、ちょこちょこTODO書いてった。  
気が向いたら自分で実装するし、気が向いて実装してくれたら気が向いたら(?) Pull する。

## Country Language (国語について)

開発者及び想定している共同開発者は日本人である。  
当面の間は、日本語でドキュメントを作成するので、海外の人はあしからず。

盛り上がり具合によっては英語にするかも？

## 開発言語・環境 (Programing Language/Development Environment)

* Java jdk 1.8.0_241 及び同梱されている JavaFX
    * 今の所はこの環境にて開発をおこなう。 Java 8 なら多少バージョン高いやつでもコンパイルできるよ。(たぶん)
    * Javaに関しては少し古いバージョンでコンパイルしても動作しそうだが、  
      JavaFXの部分については、そこそこアップデートが頻繁くさいので、動作しない可能性が高い。
        * なお、JavaFXについては、Java 11 になるとそこそこ無視できない機能強化があったりするので、本当は11にしたかったりする。  
          (日本語ドキュメントがあったらそうしていただろうと思う。)
* JetBrains IDEA (Community/Ultimate)  
  筆者は [JetBrains IDEA Ultimate](https://www.jetbrains.com/ja-jp/idea/features/editions_comparison_matrix.html)
  を使用して開発を行っている。
    * 少し見てみたところ、Community Editionでも十分に開発を行えそうな雰囲気がある。    
      と、いうのは、このプロジェクトでは JavaFX を使っているので、これとの統合があるほうがよい。Community Edition はこれをサポートしているので、十分であろう。
    * また、一時期 DataBase(SQLite) を使った方法で TaskList の保存をしようとしていたが、これはたぶん使わないことになるので、気にしなくてよい。  
      万が一DBを使用することになったとして、多少不便だけどVSCode+Pluginとかで十分使用に耐えるので、なくても困りはしない。  
* IDEなどの代替案 → 任意の Editor
  と [JavaFX Scene Builder](https://www.oracle.com/java/technologies/javafxscenebuilder-1x-archive-downloads.html)
    * 別に IDEA にこだわらなくとも、 Scene Builder があれば開発自体は可能。  
      というか、 `fxml` が "*読める*" なら、Scene Builder すら不要。(読めるか...?)
    * **任意の Editor** は、 eclipse とか VSCode とか、まあ色々あると思う。  
      要するに下記が満たされれば万事OK
        * Java を利用した開発。特に、 JavaFX を利用したGUI開発の利便性が高いこと
        * 必要ならば、 JavaFX のGUIを確認・編集するためのツール

また、ビルドツール Ant build を使用して、 jar ファイルのビルドで戸惑わないようにしたい。  
今の所、 `build.xml` に記述しているが、不足があれば追記してゆく。

## 公開時点のバージョンについて

公開時点である程度のコーディングがされているが、暫定的にバージョン番号を 0.0 としている。  
バージョン番号の構成について、今のところはなんも考えてない。

# コード規約

特に設ける必要はあんまりないんだけど少しだけ決まりごとを書き込む。  
や、まあ、僕が Pull するときに Review して書き換えりゃいい話なんだが。(めんどい)  

下記に記載。随時追記(?)

* ソースコードについて
    * 読みやすく書こう!(当たり前田)  
      僕は Readable Code を、一応、中途半端に読んでいる。(実践できているとは言っていない)
    * 読みにくかったら遠慮なく指摘しとくれ
* JavaDoc について
    * `@Param` 及び `@Return` を使用する場合、 `Nullable / not Nullable` を明記する。  
      コーディング時のヒントになる(ハズ)。 公開時点では記載できていない。
    * HTMLタグを記述する場合、自己終了要素を使用しない。(エラーが出る) 例: `<br />` → `<br>`

# TestProject について

メモ書きに近い、お試しでコーディングしたもの。  
正直Git管理する必要はなかったが、 Share の目的で追記することにした。  
おためしコーディングしたもので、ぜひシェアしたいものがあればここで試していけばいいと思う。  
ここに対する Pull Request は、ものによっては受け付けるかも。

# 参考資料

* [タスクタイマーを作る](https://qiita.com/UFOnian/private/f3b0e7e03bb3187fa0c8) wrote by @UFOnian in Qiita
    * このプロジェクトについての補足記事。
* [JavaFX：TableViewの基本的な使い方](http://kazyury.hatenadiary.jp/entry/2013/04/08/225253)
* [【超初心者向け】JavaFX超入門](https://qiita.com/tarosa0001/items/05ac653a091b7d1290f9) wrote
  by [@tarosa0001](https://qiita.com/tarosa0001) in Qiita
* [Apache Ant](http://www.ne.jp/asahi/hishidama/home/tech/ant/) wrote by ひしだま in 個人ブログ
* [Eclipse 上で UTF-8 のソースから javadoc を生成する方法](https://beyondseeker.hatenadiary.org/entry/20080515/1210779274)
    * 日本語を含む JavaDoc コメントを出力しようとするとエラーが発生する場合があるのでその対策に。IDEAでも概ね同じ方法で回避可能。
