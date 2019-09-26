package org.ideaccum.libs.commons.logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.ideaccum.libs.commons.util.ResourceUtil;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * ロギングエンジンの初期化処理を提供します。<br>
 * <p>
 * slf4j+logbackでのログ出力を実装する際の多くの初期化処理は、外部定義されたXMLファイルを読み込み、初期化を行う処理が実装されます。<br>
 * このクラスでは、多くの場合に実装される単純な初期化処理を簡潔に記述するための初期化処理インタフェースを提供します。<br>
 * </p>
 * 
 * @author Kitagawa<br>
 * 
 *<!--
 * 更新日		更新者			更新内容
 * 2018/06/13	Kitagawa		新規作成
 *-->
 */
public final class LoggerConfigurator {

	/**
	 * コンストラクタ<br>
	 */
	private LoggerConfigurator() {
		super();
	}

	/**
	 * リセット済みのロギングエンジンコンテキストオブジェクトを持ったロギングエンジン初期化オブジェクトを生成します。<br>
	 * @return ロギングエンジン初期化オブジェクト
	 */
	private static JoranConfigurator createResetedConfigurator() {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(context);
		context.reset();
		return configurator;
	}

	/**
	 * 内包ロギング定義(コンソール出力のみ)からロギングエンジンを初期化します。<br>
	 * @throws JoranException 正常にロギングエンジンの初期化処理が行えなかった場合にスローされます
	 */
	public static void initialize() throws JoranException {
		JoranConfigurator configurator = createResetedConfigurator();
		try {
			InputStream stream = null;
			try {
				stream = ResourceUtil.getInputStream("/logback-default.xml");
				configurator.doConfigure(stream);
			} catch (Throwable e) {
				throw e;
			} finally {
				if (stream != null) {
					stream.close();
				}
			}
		} catch (IOException e) {
			throw new JoranException("Failed to load default logback.xml", e);
		}
	}

	/**
	 * XML入力ストリームからロギングエンジンを初期化します。<br>
	 * @param stream 入力ストリーム
	 * @throws JoranException 正常にロギングエンジンの初期化処理が行えなかった場合にスローされます
	 */
	public static void initialize(InputStream stream) throws JoranException {
		JoranConfigurator configurator = createResetedConfigurator();
		configurator.doConfigure(stream);
	}

	/**
	 * XMLソースオブジェクトからロギングエンジンを初期化します。<br>
	 * @param source XMLソースオブジェクト
	 * @throws JoranException 正常にロギングエンジンの初期化処理が行えなかった場合にスローされます
	 */
	public static void initialize(InputSource source) throws JoranException {
		JoranConfigurator configurator = createResetedConfigurator();
		configurator.doConfigure(source);
	}

	/**
	 * XMLファイルからロギングエンジンを初期化します。<br>
	 * @param file XMLファイル
	 * @throws JoranException 正常にロギングエンジンの初期化処理が行えなかった場合にスローされます
	 */
	public static void initialize(File file) throws JoranException {
		JoranConfigurator configurator = createResetedConfigurator();
		configurator.doConfigure(file);
	}

	/**
	 * XMLリソースURLからロギングエンジンを初期化します。<br>
	 * @param url XMLリソースURL
	 * @throws JoranException 正常にロギングエンジンの初期化処理が行えなかった場合にスローされます
	 */
	public static void initialize(URL url) throws JoranException {
		JoranConfigurator configurator = createResetedConfigurator();
		configurator.doConfigure(url);
	}

	/**
	 * XMLファイルからロギングエンジンを初期化します。<br>
	 * @param file XMLファイルパス
	 * @throws JoranException 正常にロギングエンジンの初期化処理が行えなかった場合にスローされます
	 */
	public static void initialize(String file) throws JoranException {
		JoranConfigurator configurator = createResetedConfigurator();
		configurator.doConfigure(file);
	}
}
