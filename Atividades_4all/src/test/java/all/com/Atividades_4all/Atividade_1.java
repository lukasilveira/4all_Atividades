package all.com.Atividades_4all;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Atividade_1
{
	public WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	
	@BeforeSuite
	public void setUp()
	{
        htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
		String projectPath = System.getProperty("user.dir");
		System.out.println("projectPath" + projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "//drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://shopcart-challenge.4all.com/");
		driver.manage().window().maximize();
	}
	
	public void tempo(int segundos)
	{
		try
		{
			Thread.sleep(segundos * 1000);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void atividade1() throws Exception
	{
		WebDriverScreenshots screenshot = new WebDriverScreenshots(driver);
		screenshot.tirarScreenshot("inicial");
		
		ExtentTest test = extent.createTest("Meu primeiro teste", "Relatório");
		test.log(Status.INFO, "This step shows usage of log(status, details)");
	    test.info("This step shows usage of info(details)");
	    test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
	    test.addScreenCaptureFromPath("screenshot.png");
		
		
		//Selecionar a lista de categorias
		driver.findElement(By.id("open-categories-btn")).click();;
		
		//Selecionar a opção Doces
		driver.findElement(By.id("category-1")).click();
		
		//Adicionar os itens no carrinho
		driver.findElement(By.id("add-product-4-btn")).click();
		driver.findElement(By.id("add-product-5-btn")).click();
		
		//Selecionar a lista de categorias
		driver.findElement(By.id("open-categories-btn")).click();;
		
		//Selecionar a opção Todos
		driver.findElement(By.id("category-all")).click();
		
		//Selecionar o Carrinho
		driver.findElement(By.id("cart-btn")).click();;
		
		//Adicionando 4 unidades do Brigadeiro
	    driver.findElement(By.id("add-product-4-qtd")).click();;
	    driver.findElement(By.id("add-product-4-qtd")).click();;		
	    driver.findElement(By.id("add-product-4-qtd")).click();;
	    driver.findElement(By.id("add-product-4-qtd")).click();;
	    tempo(2);
	    screenshot.tirarScreenshot("carrinho");
	    
	    //Finalizando a compra
	    driver.findElement(By.id("finish-checkout-button")).click();;

	    //Validando mensagem do pedido
	    String validacaoMensagem = driver.findElement(By.cssSelector("#root > div.ReactModalPortal > div > div > div > h2")).getText(); 
	    assertEquals("Pedido realizado com sucesso!", validacaoMensagem);
	    tempo(2);
		screenshot.tirarScreenshot("compraFinalizada");

	    //Clicar em fechar
	    driver.findElement(By.cssSelector("#root > div.ReactModalPortal > div > div > div > button")).click();	    
	}
	
	@AfterSuite
	public void fecharPagina() 
	{	
		extent.flush();
		driver.quit();
	}
}