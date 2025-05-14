package dao;
import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.objectRepository.HomePageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonUtilities;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;



public class HomePageDAO {

    private HomePageLocators homePageLocators;
    private WebDriver driverInstance;
    WebDriverWait explicitWait;
    private Properties useData;
    private CommonUtilities commonUtilities;

    public HomePageDAO(String scenario, Map<Object, String> testData) throws Exception {
        this();
        this.driverInstance = DriverManager.getDriver();
        this.commonUtilities = new CommonUtilities(scenario);
        this.homePageLocators = PageFactory.initElements(this.driverInstance, HomePageLocators.class);
        explicitWait = new WebDriverWait(driverInstance, Duration.ofSeconds(3));

    }

    public HomePageDAO() throws Exception {
        useData = new Properties();
        useData.load(
                new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/utils.properties"));
    }

    public void getWelcomeHeadingFromHomePage() throws Exception {
        commonUtilities.click(homePageLocators.btnWelcomeText);
    }
}
