package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	/**
	 * トップページへアクセスし、
	 * ログイン画面が表示されることおよび画面タイトルを確認する。
	 */
	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {

		goTo("http://localhost:8080/lms");

		String title = webDriver.getTitle();
		assertEquals("ログイン | LMS", title);

		getEvidence(new Object() {
		});
	}

	/**
	 * 初回ログイン済みの受講生ユーザーでログインし、
	 * コース詳細画面が表示されることを確認する。
	 */
	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {

		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StudentAA011");

		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		visibilityTimeout(By.tagName("h2"), 3);

		String title = webDriver.getTitle();
		assertEquals("コース詳細 | LMS", title);

		getEvidence(new Object() {
		});

	}

	/**
	 * 上部メニューの「ヘルプ」リンクから
	 * ヘルプ画面へ遷移することを確認する。
	 */
	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {

		visibilityTimeout(By.className("dropdown-toggle"), 3);

		webDriver.findElement(By.className("dropdown-toggle")).click();

		visibilityTimeout(By.linkText("ヘルプ"), 3);

		webDriver.findElement(By.linkText("ヘルプ")).click();

		visibilityTimeout(By.tagName("h2"), 3);

		String title = webDriver.getTitle();
		assertEquals("ヘルプ | LMS", title);

		getEvidence(new Object() {

		});
	}

	/**
	 * よくある質問画面が表示されることを確認する。
	 */
	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {

		String currentWindow = webDriver.getWindowHandle();

		webDriver.findElement(By.linkText("よくある質問")).click();

		for (String windowHandle : webDriver.getWindowHandles()) {
			if (!windowHandle.equals(currentWindow)) {
				webDriver.switchTo().window(windowHandle);
				break;
			}
		}

		visibilityTimeout(By.tagName("h2"), 3);

		String title = webDriver.getTitle();
		assertEquals("よくある質問 | LMS", title);

		getEvidence(new Object() {
		});

	}
}