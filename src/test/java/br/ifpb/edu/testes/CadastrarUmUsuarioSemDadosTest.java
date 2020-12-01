package br.ifpb.edu.testes;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastrarUmUsuarioSemDadosTest {
	private WebDriver driver;

	@Before
	public void configura() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\darkc\\Documents\\git\\todo-app-jsf\\src\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testaCadastrarUmUsuarioSemDados() throws InterruptedException {
		driver.get("http://localhost:8080/todoapp/index.xhtml");
		Thread.sleep(3000);

		WebElement btCriarConta = driver.findElement(By.className("link-cadastro"));
		btCriarConta.click();
		Thread.sleep(2000);

		WebElement campoNome = driver.findElement(By.id("j_idt6:nomeusuario"));
		Thread.sleep(1000);

		WebElement campoLogin = driver.findElement(By.id("j_idt6:loginusuario"));
		Thread.sleep(1000);

		WebElement campoSenha = driver.findElement(By.id("j_idt6:senhausuario"));
		Thread.sleep(1000);

		WebElement btSalvar = driver.findElement(By.id("j_idt6:btcadastrar"));
		btSalvar.click();
		Thread.sleep(1000);

		WebElement areaMensagensErro = driver.findElement(By.id("j_idt6:mensagens"));

		assertTrue(areaMensagensErro.getText() != null);
	}

	@After
	public void fechaNavegador() {
		driver.quit();
	}
}
