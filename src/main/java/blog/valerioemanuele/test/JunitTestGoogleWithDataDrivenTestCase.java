package blog.valerioemanuele.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import blog.valerioemanuele.page.GoogleSearchPage;

@TestInstance(value=Lifecycle.PER_CLASS)
public class JunitTestGoogleWithDataDrivenTestCase {
	private WebDriver driver;
	private GoogleSearchPage objGoogleSearchPage;
	

	@BeforeAll
	public void setup() throws URISyntaxException {
		System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.com/?hl=en");
		driver.manage().window().maximize();
	}


	@Test
	public void test_DataDrivenGoogleSearch() throws InterruptedException {
		objGoogleSearchPage = new GoogleSearchPage(driver);
		
		objGoogleSearchPage.setQuery("valerioemanuele.blog");
		objGoogleSearchPage.search();
		
		List<WebElement> linkElements = objGoogleSearchPage.getResults();
		
		
		Optional<WebElement> result = linkElements.stream()
				.filter(a -> a.getText().toLowerCase().contains("valerio emanuele"))
				.findAny();
		assertTrue(result.isPresent());
		driver.quit();
	}
	
	private String getChromeDriverPath() {
		return Paths.get("src","main","resources","chromedriver.exe").toString();
	}

}
