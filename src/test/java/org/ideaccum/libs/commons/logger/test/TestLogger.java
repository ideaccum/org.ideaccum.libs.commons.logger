package org.ideaccum.libs.commons.logger.test;

import org.ideaccum.libs.commons.logger.Logger;
import org.ideaccum.libs.commons.logger.LoggerConfigurator;
import org.ideaccum.libs.commons.logger.LoggerFactory;
import org.ideaccum.libs.commons.message.Messages;
import org.ideaccum.libs.commons.util.PropertiesUtil;
import org.ideaccum.libs.commons.util.ResourceUtil;

public class TestLogger {

	public static void main(String[] args) throws Throwable {
		new TestLogger().test();
	}

	private void test() throws Throwable {
		// メッセージリソースを読み込みます
		Messages.instance().addMessage(PropertiesUtil.load("/org/ideaccum/libs/commons/logger/test/TestLogger.properties"));

		// 必要に応じてログ出力環境定義情報で初期化を行います(slf4jで利用する初期化定義体と同様)
		LoggerConfigurator.initialize(ResourceUtil.getURL("/logback-sample.xml"));

		// ログ出力クラスインスタンスを取得します(slf4j同様)
		Logger logger = LoggerFactory.getLogger(getClass());

		// ログ出力を行います(info、error等のメソッドではなく、一律outputメソッドとなります)
		//logger.info("slf4の標準的なログ出力");
		logger.output("MSG0001");
		logger.output("MSG0002");
		logger.output("MSG0003");
		logger.output("MSG0004");
		logger.output("MSG0005");
		logger.output("MSG0006");
		logger.output("FOO");
	}
}
