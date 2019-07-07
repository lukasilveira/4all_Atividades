package all.com.Atividades_4all;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Atividade_2 
{
	public WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@Parameters("nomeBrowser")
	@BeforeTest
	public void setUp(String nomeBrowser)
	{
		System.out.println("O nome do navegador é " +nomeBrowser);
		System.out.println("Thread id : "+Thread.currentThread().getId());
		
		if (nomeBrowser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://shopcart-challenge.4all.com/");
			driver.manage().window().maximize();
		}
		else if (nomeBrowser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", projectPath+"/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get("http://shopcart-challenge.4all.com/");
			driver.manage().window().maximize();
		}
		else if (nomeBrowser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", projectPath+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.get("http://shopcart-challenge.4all.com/");
			driver.manage().window().maximize();
		}
//		String projectPath = System.getProperty("user.dir");
//		System.out.println("projectPath" + projectPath);
//		System.setProperty("webdriver.gecko.driver", projectPath + "//drivers/geckodriver.exe");
//		driver = new FirefoxDriver();
//		driver.get("http://shopcart-challenge.4all.com/");
//		driver.manage().window().maximize();
	}
	
	@Test
	public void atividade2() throws Exception
	{
		//Selecionar a lista de categorias
		driver.findElement(By.id("open-categories-btn")).click();;
		
		//Selecionar a opção Doces
		driver.findElement(By.id("category-0")).click();
		
		//Adicionar os itens no carrinho
		driver.findElement(By.id("add-product-0-btn")).click();
		driver.findElement(By.id("add-product-1-btn")).click();
		driver.findElement(By.id("add-product-2-btn")).click();
		
		//Selecionar a lista de categorias
		driver.findElement(By.id("open-categories-btn")).click();;
		
		//Selecionar a opção Todos
		driver.findElement(By.id("category-all")).click();
		
		//Adicionar Rissole no carrinho
		driver.findElement(By.id("add-product-3-btn")).click();
		
		//Selecionar o Carrinho
		driver.findElement(By.id("cart-btn")).click();;
		
		//Adicionando 9 unidades do Rissole
	    driver.findElement(By.id("add-product-3-qtd")).click();;
	    driver.findElement(By.id("add-product-3-qtd")).click();;		
	    driver.findElement(By.id("add-product-3-qtd")).click();;
	    driver.findElement(By.id("add-product-3-qtd")).click();;
	    driver.findElement(By.id("add-product-3-qtd")).click();;
	    driver.findElement(By.id("add-product-3-qtd")).click();;
	    driver.findElement(By.id("add-product-3-qtd")).click();;
	    driver.findElement(By.id("add-product-3-qtd")).click();;
	    driver.findElement(By.id("add-product-3-qtd")).click();;
	    
		//Diminuindo 5 unidades do Rissole
	    driver.findElement(By.id("remove-product-3-qtd")).click();
	    driver.findElement(By.id("remove-product-3-qtd")).click();
	    driver.findElement(By.id("remove-product-3-qtd")).click();
	    driver.findElement(By.id("remove-product-3-qtd")).click();
	    driver.findElement(By.id("remove-product-3-qtd")).click();
	    
	    //Validando valor total
	    String valorTotal = driver.findElement(By.id("price-total-checkout")).getText();
	    assertEquals("R$ 36,00", valorTotal);
	    
	    //Finalizando a compra
	    driver.findElement(By.id("finish-checkout-button")).click();;
	    
	    //Validando mensagem do pedido
	    String validacaoMensagem = driver.findElement(By.cssSelector("#root > div.ReactModalPortal > div > div > div > h2")).getText(); 
	    assertEquals("Pedido realizado com sucesso!", validacaoMensagem);
	    
	    //Clicar em fechar
	    driver.findElement(By.cssSelector("#root > div.ReactModalPortal > div > div > div > button")).click();	
	}	
	
	@AfterTest
	public void fecharPagina() 
	{
		driver.quit();
	}
}