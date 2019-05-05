package org.ideaccum.libs.commons.logger;

/**
 * ログ出力を行う為の処理インタフェースを提供します。<br>
 * <p>
 * このインタフェースクラスの名称は、意図的にSLF4Jの{@link org.slf4j.Logger}と同じ名称としています。<br>
 * 既存実装において、slf4jの{@link org.slf4j.Logger}を利用している個所について、置換を行う際に修正個所を局所的にすることを目的としています。<br>
 * </p>
 * 
 * @author Kitagawa<br>
 * 
 *<!--
 * 更新日		更新者			更新内容
 * 2018/06/13	Kitagawa		新規作成
 * 2019/05/06	Kitagawa		is***Enabledメソッドをインタフェース側にプルアップ
 *-->
 */
public interface Logger {

	/**
	 * デバッグレベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isDebugEnabled()
	 */
	public boolean isDebugEnabled();

	/**
	 * エラーレベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isErrorEnabled()
	 */
	public boolean isErrorEnabled();

	/**
	 * 情報レベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isInfoEnabled()
	 */
	public boolean isInfoEnabled();

	/**
	 * トレースレベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isTraceEnabled()
	 */
	public boolean isTraceEnabled();

	/**
	 * 警告レベルロギングが有効であるか判定します。<br>
	 * @return 有効な場合にtrueを返却
	 * @see org.slf4j.Logger#isWarnEnabled()
	 */
	public boolean isWarnEnabled();

	/**
	 * メッセージコードで定義されたメッセージに対して必要なバインド内容をバインドしてログ出力を行います。<br>
	 * 指定するコードはメッセージレベルを含まないメッセージコードを指定し、当メソッドでは定義されたメッセージレベルに準じて合致するログレベルで出力を行います。<br>
	 * @param code メッセージコード
	 * @param e 例外オブジェクト
	 * @param binds バインドオブジェクト配列
	 */
	public void output(String code, Throwable e, Object... binds);

	/**
	 * メッセージコードで定義されたメッセージに対して必要なバインド内容をバインドしてログ出力を行います。<br>
	 * 指定するコードはメッセージレベルを含まないメッセージコードを指定し、当メソッドでは定義されたメッセージレベルに準じて合致するログレベルで出力を行います。<br>
	 * @param binds バインドオブジェクト配列
	 * @param code メッセージコード
	 */
	public void output(String code, Object... binds);

	/**
	 * メッセージコードで定義されたメッセージに対して必要なバインド内容をバインドしてログ出力を行います。<br>
	 * 指定するコードはメッセージレベルを含まないメッセージコードを指定し、当メソッドでは定義されたメッセージレベルに準じて合致するログレベルで出力を行います。<br>
	 * @param code メッセージコード
	 * @param e 例外オブジェクト
	 */
	public void output(String code, Throwable e);

	/**
	 * メッセージコードで定義されたメッセージに対して必要なバインド内容をバインドしてログ出力を行います。<br>
	 * 指定するコードはメッセージレベルを含まないメッセージコードを指定し、当メソッドでは定義されたメッセージレベルに準じて合致するログレベルで出力を行います。<br>
	 * @param code メッセージコード
	 */
	public void output(String code);
}
