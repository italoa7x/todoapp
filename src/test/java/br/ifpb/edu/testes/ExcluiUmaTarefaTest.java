package br.ifpb.edu.testes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcluiUmaTarefaTest {
	private WebDriver driver;

	@Before
	public void configura() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\darkc\\Documents\\git\\todo-app-jsf\\src\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testaExcluirUmaTarefa() throws InterruptedException {
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
		WebElement cartaReferenteATarefa = driver.findElement(By.id("formList:j_idt6:1:tarefa"));

		WebElement btExcluirTarefa = cartaReferenteATarefa.findElement(By.className("btn-trash"));
		btExcluirTarefa.click();
		Thread.sleep(3000);

	}

	@After
	public void fechaNavegador() {
		driver.quit();
	}
}
