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
 * 2019/05/06	Kitagawa		is***Enabledメソッドをインタフェース側にプルアップ
 * 2019/05/16	Kitagawa		is***Enabledメソッドを破棄し、メッセージコードを引数としてレベルを動的判定するisOutputEnabledメソッドを追加
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

	// 2019.05.16 Deleted
	///**
	// * デバッグレベルロギングが有効であるか判定します。<br>
	// * メッセージコードによる動的レベル決定のため、このメソッドは基本的には利用しませんが、バインドメッセージ構築で負荷がかかる場合などにおいて例外的な利用の目的で用意されています。<br>
	// * 実際の定義メッセージレベルとの整合性に注意して利用してください。<br>
	// * @return 有効な場合にtrueを返却
	// * @see org.ideaccum.libs.commons.logger.Logger#isDebugEnabled()
	// * @see org.slf4j.Logger#isDebugEnabled()
	// * @deprecated メッセージコードによる動的レベル決定による実装不整合を避けるため、原則は利用禁止です(API仕様の注意事項を理解の上で利用してください)。
	// */
	//@Override
	//public final boolean isDebugEnabled() {
	//	return logger.isDebugEnabled();
	//}

	// 2019.05.16 Deleted
	///**
	// * エラーレベルロギングが有効であるか判定します。<br>
	// * メッセージコードによる動的レベル決定のため、このメソッドは基本的には利用しませんが、バインドメッセージ構築で負荷がかかる場合などにおいて例外的な利用の目的で用意されています。<br>
	// * 実際の定義メッセージレベルとの整合性に注意して利用してください。<br>
	// * @return 有効な場合にtrueを返却
	// * @see org.ideaccum.libs.commons.logger.Logger#isErrorEnabled()
	// * @see org.slf4j.Logger#isErrorEnabled()
	// * @deprecated メッセージコードによる動的レベル決定による実装不整合を避けるため、原則は利用禁止です(API仕様の注意事項を理解の上で利用してください)。
	// */
	//@Override
	//public final boolean isErrorEnabled() {
	//	return logger.isErrorEnabled();
	//}

	// 2019.05.16 Deleted
	///**
	// * 情報レベルロギングが有効であるか判定します。<br>
	// * メッセージコードによる動的レベル決定のため、このメソッドは基本的には利用しませんが、バインドメッセージ構築で負荷がかかる場合などにおいて例外的な利用の目的で用意されています。<br>
	// * 実際の定義メッセージレベルとの整合性に注意して利用してください。<br>
	// * @return 有効な場合にtrueを返却
	// * @see org.ideaccum.libs.commons.logger.Logger#isInfoEnabled()
	// * @see org.slf4j.Logger#isInfoEnabled()
	// * @deprecated メッセージコードによる動的レベル決定による実装不整合を避けるため、原則は利用禁止です(API仕様の注意事項を理解の上で利用してください)。
	// */
	//@Override
	//public final boolean isInfoEnabled() {
	//	return logger.isInfoEnabled();
	//}

	// 2019.05.16 Deleted
	///**
	// * トレースレベルロギングが有効であるか判定します。<br>
	// * メッセージコードによる動的レベル決定のため、このメソッドは基本的には利用しませんが、バインドメッセージ構築で負荷がかかる場合などにおいて例外的な利用の目的で用意されています。<br>
	// * 実際の定義メッセージレベルとの整合性に注意して利用してください。<br>
	// * @return 有効な場合にtrueを返却
	// * @see org.ideaccum.libs.commons.logger.Logger#isTraceEnabled()
	// * @see org.slf4j.Logger#isTraceEnabled()
	// * @deprecated メッセージコードによる動的レベル決定による実装不整合を避けるため、原則は利用禁止です(API仕様の注意事項を理解の上で利用してください)。
	// */
	//@Override
	//public final boolean isTraceEnabled() {
	//	return logger.isTraceEnabled();
	//}

	// 2019.05.16 Deleted
	///**
	// * 警告レベルロギングが有効であるか判定します。<br>
	// * メッセージコードによる動的レベル決定のため、このメソッドは基本的には利用しませんが、バインドメッセージ構築で負荷がかかる場合などにおいて例外的な利用の目的で用意されています。<br>
	// * 実際の定義メッセージレベルとの整合性に注意して利用してください。<br>
	// * @return 有効な場合にtrueを返却
	// * @see org.ideaccum.libs.commons.logger.Logger#isTraceEnabled()
	// * @see org.slf4j.Logger#isWarnEnabled()
	// * @deprecated メッセージコードによる動的レベル決定による実装不整合を避けるため、原則は利用禁止です(API仕様の注意事項を理解の上で利用してください)。
	// */
	//@Override
	//public final boolean isWarnEnabled() {
	//	return logger.isWarnEnabled();
	//}

	/**
	 * 指定メッセージコードの出力が有効であるかを判定します。<br>
	 * メッセージコードにおけるメッセージレベルがロガー定義上で有効になっているかを判定し、ログ出力処理時のパラメータ部構築処理等で明示的にレベルごとに出力制御を行いたい場合に利用します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.ideaccum.libs.commons.logger.Logger#isOutputEnabled(java.lang.String)
	 */
	@Override
	public final boolean isOutputEnabled(String code) {
		Message message = Messages.instance().get(code);
		if (message == null) {
			return false;
		}
		MessageLevel level = message.getLevel();
		if (level == MessageLevel.INFORMATION) {
			return logger.isInfoEnabled();
		} else if (level == MessageLevel.ERROR) {
			return logger.isErrorEnabled();
		} else if (level == MessageLevel.WARNING) {
			return logger.isWarnEnabled();
		} else if (level == MessageLevel.DEBUG) {
			return logger.isDebugEnabled();
		} else if (level == MessageLevel.TRACE) {
			return logger.isTraceEnabled();
		} else {
			return false;
		}
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
