package pageObjects;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.testcases.BaseClass;

public class DiabetesPage extends BaseClass{
	WebDriver driver;
	@FindBy(xpath="//a[@href='RecipeCategories.aspx']") WebElement lnkRecipes;
	@FindBy(xpath="//*[@title='Click here to see all recipes under Diabetic recipes']")WebElement lnkDiabeticRecipes;
	@FindBys(@FindBy(xpath="//a[@class='respglink']")) List<WebElement> listWEPages;	
	
	public DiabetesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void GetRecipes() throws InterruptedException, IOException {
		lnkRecipes.click();
		lnkDiabeticRecipes.click();
		int i=1;
		String strWebPageBaseLink = driver.getCurrentUrl() + "?pageindex=";
		int noOfPages = Integer.valueOf(listWEPages.get(listWEPages.size()-1).getText());
		System.out.println("No of pages : " + noOfPages);
		String strWebPageLink = "";
		for(i=1;i<=noOfPages;i++) {					
			System.out.println(i);
			strWebPageLink = strWebPageBaseLink + i;
			driver.navigate().to(strWebPageLink);
			Thread.sleep(2000);
			RecipesPageWise(driver, listDiabetes, "diabetes", 0);
		}
	}
	
	public static void CheckForHealthyRecipes() throws InterruptedException, IOException {
		CheckForHealthyItems(listHDiabetes, 0);
	}
	
	public static void CheckForAllergies() throws InterruptedException, IOException {
		for(int i=0;i<listAllergies.size();i++) {
			CheckForAllergy(listAllergies.get(i), 0);
		}
	}
}
