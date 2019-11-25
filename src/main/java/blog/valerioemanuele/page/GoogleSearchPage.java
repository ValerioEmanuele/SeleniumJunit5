package blog.valerioemanuele.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {
	WebDriver driver;
	
	@FindBy(name="q")
	WebElement queryInputText;
	
	@FindBy(id="search")
	WebElement searchResultsContainer;
	
	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setQuery(String q) {
		q = q == null ? "" : q;
		queryInputText.sendKeys(q);
	}
	
	public void search() {
		queryInputText.sendKeys(Keys.ENTER);
	}
	
	public List<WebElement> getResults() {
		return searchResultsContainer.findElements(By.tagName("a"));
	}
}
