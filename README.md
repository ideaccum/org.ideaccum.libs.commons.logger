`リポジトリの主な目的は個人的なシステム構築時の実装効率を上げるためのライブラリ管理のため網羅的なテスト実施はしていません`

# Ideaccum Commons Logger
Commons Loggerは、外部定義されたメッセージ定義情報をもとにメッセージコードベースでログ出力を行うためのパッケージです。  

このパッケージにおけるログ出力処理のコア部はslf4jを利用する形で、ログ出力処理自体の品質はslf4jで保証されたものとなります。  
パッケージが提供する機能は、ログ出力時のログメッセージをメッセージ文字列ではなく、外部定義されたメッセージコードに標準化するAPIです。  
これは実装規約によって「メッセージプロパティリソースから取得したメッセージを利用すること」とすることで満足できますが、多くの多様なメンバーで開発する際に厳守しきれず破綻することがあります。  
Commons Loggerでは、ログ出力時に指定するパラメータをメッセージコードに限定することで、未定義のメッセージコードを指定した場合に実行時に明示的に特定のメッセージ出力を行う(ライブラリが固定のエラーメッセージを出力します)ことで、メッセージコードではない実装が行われている箇所の特定を容易にすることを目的としています。  

また、Commons Loggerが利用するメッセージ定義は、Common Messageのコード定義に準拠します。  
メッセージ定義時のコードはサフィックスにメッセージレベル情報を持ち、ログ出力時にこのレベル定義にそってerror、info、debug等の出力レベルを分岐します。  
これは、Commons Loggerを利用する以前にCommons Messageの初期化が行われていることが前提となることを指します。  

- メッセージ定義サンプル  
  ログ出力を行うためのメッセージ定義は下記のようにプロパティリソースに定義します。  

      MSG0001-E=エラーメッセージ。
      MSG0002-W=警告メッセージ。
      MSG0003-I=情報メッセージ。
      MSG0004-D=デバッグメッセージ。
      MSG0005-T=トレースメッセージ。
      MSG0006-H=非表示内部メッセージ。

- ログ出力実装例  
  ログ出力時の実装方法はslf4j類似のログインスタンス生成から出力実装の流れとなります。  
  ファクトリやログ出力クラス名をslf4jのクラス名と揃えている理由はログ出力部の置き換えを極力局所的にすることを目的としているためです。  

      private void test() {
        // メッセージリソースを読み込みます
        Messages.instance().addMessage(PropertiesUtil.load("/org/ideaccum/libs/commons/logger/test/TestLogger.properties"));

        // 必要に応じてログ出力環境定義情報で初期化を行います(slf4jで利用する初期化定義体と同様)
        LoggerConfigurator.initialize(ResourceUtil.getURL("/logback-sample.xml"));

        // ログ出力クラスインスタンスを取得します(slf4j同様)
        Logger logger = LoggerFactory.getLogger(getClass());

        // ログ出力を行います(info、error等のメソッドではなく、一律outputメソッドとなります)
        // ログ出力レベルはメッセージ定義側のメッセージレベルを変えることでログ出力レベル側に反映されます
        //logger.info("slf4の標準的なログ出力");
        logger.output("MSG0001"); // エラーレベルでログ出力が行われます(slf4jのerror)
        logger.output("MSG0002"); // 警告レベルでログ出力が行われます(slf4jのwarn)
        logger.output("MSG0003"); // 情報レベルでログ出力が行われます(slf4jのinfo)
        logger.output("MSG0004"); // デバッグレベルでログ出力が行われます(slf4jのdebug)
        logger.output("MSG0005"); // トレースレベルでログ出力が行われます(slf4jのtrace)
        logger.output("MSG0006"); // ログ出力は行われません
        logger.output("FOO"); // "Failed to output log message(Undefined message code=FOO)."が出力されます
      }

## Documentation
ライブラリに関するAPI仕様は各クラスのJavadocにて記載しています。  

## Source Code
最新のプログラムソースはすべて[GitHub](https://github.com/ideaccum/org.ideaccum.libs.commons.logger)で管理しています。  

## Dependent Libraries
このライブラリパッケージの依存ライブラリ及び、ライセンスは[LIBRARIES.md](https://github.com/ideaccum/org.ideaccum.libs.commons.logger/blob/master/LIBRARIES.md)に記載しています。  

## License
プログラムソースは[MIT License](https://github.com/ideaccum/org.ideaccum.libs.commons.logger/blob/master/LICENSE.md)です。  

## Copyright
Copyright (c) 2018 Hisanori Kitagawa  

## Other
2010年より[SourceForge.jp](https://osdn.net/projects/phosphoresce/)にて公開していたリポジトリ上のWebcoreパッケージから分岐／移行し、更新しているライブラリとなります。  
