package br.ifpb.edu.testes;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CriaUmaTarefaInvalidaTest {
	private WebDriver driver;

	@Before
	public void configura() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\darkc\\Documents\\git\\todo-app-jsf\\src\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testaCriarUmaNovaTarefaComTodosOsDados() throws InterruptedException {
		driver.get("http://localhost:8080/todoapp/index.xhtml");
		// realiza o login primeiro
		// login: italo
		// senha: 123

		driver.get("http://localhost:8080/todoapp/index.xhtml");
		Thread.sleep(3000);
		WebElement campoLogin = driver.findElement(By.id("j_idt7:usuariologin"));
		Thread.sleep(3000);
		campoLogin.sendKeys("italo");
		WebElement campoSenha = driver.findElement(By.id("j_idt7:usuariosenha"));
		campoSenha.sendKeys("123");
		Thread.sleep(3000);
		WebElement btLogin = driver.findElement(By.id("j_idt7:btlogin"));
		btLogin.click();
		Thread.sleep(4000);

		// CRIA A NOVA TAREFA
		WebElement btNovaTarefa = driver.findElement(By.id("formMenu:menunovatarefa"));
		btNovaTarefa.click();

		WebElement campoTituloTarefa = driver.findElement(By.id("j_idt6:campotitulo"));
		Thread.sleep(1000);

		WebElement campoDescricaoTarefa = driver.findElement(By.id("j_idt6:campodescricao"));
		Thread.sleep(1000);

		WebElement btSalvar = driver.findElement(By.id("j_idt6:btsalvar"));
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
