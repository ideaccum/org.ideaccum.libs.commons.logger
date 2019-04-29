package org.ideaccum.libs.commons.logger;

import org.ideaccum.libs.commons.message.Message;
import org.ideaccum.libs.commons.message.MessageLevel;
import org.ideaccum.libs.commons.message.Messages;

/**
 * ログ出力を行う為の実態処理を提供します。<br>
 * <p>
 * 実際にログを出力するための{@link org.ideaccum.libs.commons.logger.Logger}を実装したクラスです。<br>
 * {@link org.ideaccum.libs.commons.logger.LoggerFactory}を利用してインスタンスを取得して利用します。<br>
 * </p>
 * 
 * @author Kitagawa<br>
 * 
 *<!--
 * 更新日		更新者			更新内容
 * 2018/06/13	Kitagawa		新規作成
 *-->
 */
public final class LoggerImpl implements Logger {

	/** SLF4Jロガーオブジェクト */
	private org.slf4j.Logger logger;

	/**
	 * コンストラクタ<br>
	 * @param logger SLF4Jロガーオブジェクト
	 */
	protected LoggerImpl(org.slf4j.Logger logger) {
		super();
		this.logger = logger;
	}

	/**
	 * デバッグレベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isDebugEnabled()
	 */
	public final boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/**
	 * エラーレベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isErrorEnabled()
	 */
	public final boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	/**
	 * 情報レベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isInfoEnabled()
	 */
	public final boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	/**
	 * トレースレベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isTraceEnabled()
	 */
	public final boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	/**
	 * 警告レベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isWarnEnabled()
	 */
	public final boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	/**
	 * メッセージログ出力処理を行います。<br>
	 * @param e 例外オブジェクト
	 * @param code メッセージコード
	 * @param binds バインドオブジェクト配列
	 */
	private void outputCore(Throwable e, String code, Object... binds) {
		if (logger == null) {
			return;
		}

		/*
		 * メッセージ定義情報取得
		 */
		Message message = Messages.instance().get(code);
		if (message == null) {
			//if (logger.isErrorEnabled()) {
			logger.error("Failed to output log message(Undefined message code=" + code + ").");
			//}
			return;
		}

		/*
		 * メッセージレベル毎ログ出力
		 */
		MessageLevel level = message.getLevel();
		String string = message.getCode() + " | " + message == null ? null : message.getMessage(binds);
		if (level == MessageLevel.INFORMATION) {
			if (logger.isInfoEnabled()) {
				if (e != null) {
					logger.info(string, e);
				} else {
					logger.info(string);
				}
			}
		} else if (level == MessageLevel.ERROR) {
			if (logger.isErrorEnabled()) {
				if (e != null) {
					logger.error(string, e);
				} else {
					logger.error(string);
				}
			}
		} else if (level == MessageLevel.WARNING) {
			if (logger.isWarnEnabled()) {
				if (e != null) {
					logger.warn(string, e);
				} else {
					logger.warn(string);
				}
			}
		} else if (level == MessageLevel.DEBUG) {
			if (logger.isDebugEnabled()) {
				if (e != null) {
					logger.debug(string, e);
				} else {
					logger.debug(string);
				}
			}
		} else if (level == MessageLevel.TRACE) {
			if (logger.isTraceEnabled()) {
				if (e != null) {
					logger.trace(string, e);
				} else {
					logger.trace(string);
				}
			}
		} else {
			// No log output
			//if (logger.isTraceEnabled()) {
			//	if (e != null) {
			//		logger.trace(string, e);
			//	} else {
			//		logger.trace(string);
			//	}
			//}
		}
	}

	/**
	 * メッセージコードで定義されたメッセージに対して必要なバインド内容をバインドしてログ出力を行います。<br>
	 * 指定するコードはメッセージレベルを含まないメッセージコードを指定し、当メソッドでは定義されたメッセージレベルに準じて合致するログレベルで出力を行います。<br>
	 * @param code メッセージコード
	 * @param e 例外オブジェクト
	 * @param binds バインドオブジェクト配列
	 * @see org.ideaccum.libs.commons.logger.Logger#output(java.lang.String, java.lang.Throwable, java.lang.Object[])
	 */
	@Override
	public void output(String code, Throwable e, Object... binds) {
		outputCore(e, code, binds);
	}

	/**
	 * メッセージコードで定義されたメッセージに対して必要なバインド内容をバインドしてログ出力を行います。<br>
	 * 指定するコードはメッセージレベルを含まないメッセージコードを指定し、当メソッドでは定義されたメッセージレベルに準じて合致するログレベルで出力を行います。<br>
	 * @param binds バインドオブジェクト配列
	 * @param code メッセージコード
	 * @see org.ideaccum.libs.commons.logger.Logger#output(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void output(String code, Object... binds) {
		outputCore(null, code, binds);
	}

	/**
	 * メッセージコードで定義されたメッセージに対して必要なバインド内容をバインドしてログ出力を行います。<br>
	 * 指定するコードはメッセージレベルを含まないメッセージコードを指定し、当メソッドでは定義されたメッセージレベルに準じて合致するログレベルで出力を行います。<br>
	 * @param code メッセージコード
	 * @param e 例外オブジェクト
	 * @see org.ideaccum.libs.commons.logger.Logger#output(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void output(String code, Throwable e) {
		outputCore(e, code, (Object[]) null);
	}

	/**
	 * メッセージコードで定義されたメッセージに対して必要なバインド内容をバインドしてログ出力を行います。<br>
	 * 指定するコードはメッセージレベルを含まないメッセージコードを指定し、当メソッドでは定義されたメッセージレベルに準じて合致するログレベルで出力を行います。<br>
	 * @param code メッセージコード
	 * @see org.ideaccum.libs.commons.logger.Logger#output(java.lang.String)
	 */
	@Override
	public void output(String code) {
		outputCore(null, code, (Object[]) null);
	}
}
