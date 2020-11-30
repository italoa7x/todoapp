package br.ifpb.edu.testes;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RealizarLoginSemDadosTest {
	private WebDriver driver;

	@Before
	public void configura() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\darkc\\Documents\\git\\todo-app-jsf\\src\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testaLogarNoSistema() throws InterruptedException {
		driver.get("http://localhost:8080/todoapp/index.xhtml");
		WebElement campoLogin = driver.findElement(By.id("j_idt7:usuariologin"));
		WebElement campoSenha = driver.findElement(By.id("j_idt7:usuariosenha"));

		WebElement btLogin = driver.findElement(By.id("j_idt7:btlogin"));
		btLogin.click();

		Thread.sleep(1000);
		WebElement areaMensagensErro = driver.findElement(By.id("j_idt7:mensagens"));

		assertTrue(areaMensagensErro.getText() != null);
	}

	@After
	public void fechaNavegador() {
		driver.quit();
	}
}
