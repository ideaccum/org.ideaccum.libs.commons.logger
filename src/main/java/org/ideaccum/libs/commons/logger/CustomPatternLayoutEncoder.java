package org.ideaccum.libs.commons.logger;

import java.util.LinkedList;
import java.util.List;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * {@link org.ideaccum.libs.commons.logger.Logger}が提供するログ出力機構を利用した際のログ出力におけるエンコード処理を提供します。<br>
 * <p>
 * このクラスのエンコード仕様は全て{@link ch.qos.logback.classic.encoder.PatternLayoutEncoder}に委譲します。<br>
 * クラスが提供する機能は{@link org.ideaccum.libs.commons.logger.Logger}によってログ出力する際のスタックトレースオフセット補正のみです。<br>
 * </p>
 * 
 *<!--
 * 更新日      更新者           更新内容
 * 2018/06/04  Kitagawa         新規作成
 *-->
 */
public final class CustomPatternLayoutEncoder extends PatternLayoutEncoder {

	/** スタックトレースオフセット */
	private static int OFFSET = 2;

	/**
	 * ロギングイベント内容をバイトコードとしてエンコードします。<br>
	 * @param  event ロギングイベントオブジェクト
	 * @return エンコードされたバイトコード
	 * @see ch.qos.logback.core.encoder.LayoutWrappingEncoder#encode(java.lang.Object)
	 */
	@Override
	public byte[] encode(ILoggingEvent event) {
		StackTraceElement[] elements = event.getCallerData();

		List<StackTraceElement> list = new LinkedList<StackTraceElement>();
		for (int i = OFFSET; i <= elements.length - 1; i++) {
			list.add(elements[i]);
		}

		for (int i = 0; i <= list.size() - 1; i++) {
			elements[i] = list.get(i);
		}

		return super.encode(event);
	}
}
