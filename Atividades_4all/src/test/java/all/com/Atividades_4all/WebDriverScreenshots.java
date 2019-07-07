package all.com.Atividades_4all;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class WebDriverScreenshots {
	
	WebDriver driver;
	
	public WebDriverScreenshots(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void tirarScreenshot(String nomeScreenshot) throws IOException
	{
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("C:\\Users\\Lucas Silveira\\eclipse-workspace\\Atividades_4All\\src\\test\\java\\all\\com\\Atividades_4all\\Screenshots\\" + nomeScreenshot + ".png"));
	}
}