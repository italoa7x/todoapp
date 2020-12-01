package br.ifpb.edu.testes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FinalizaUmaTarefaTest {
	private WebDriver driver;

	@Before
	public void configura() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\darkc\\Documents\\git\\todo-app-jsf\\src\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testaFinalizarUmaTarefa() throws InterruptedException {
		driver.get("http://localhost:8080/todoapp/index.xhtml");
		// realiza o login primeiro
		// login: italo1
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

		// BUSCA A TAREFA
		WebElement cartaReferenteATarefaQueSeraAtualizada = driver.findElement(By.id("formList:j_idt6:0:tarefa"));

		WebElement btFinalizarTarefa = cartaReferenteATarefaQueSeraAtualizada
				.findElement(By.id("formList:j_idt6:0:btconcluirtarefa"));
		btFinalizarTarefa.click();
		Thread.sleep(3000);

	}

	@After
	public void fechaNavegador() {
		driver.quit();
	}
}
