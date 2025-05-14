package scenarios;

import com.x.webAutomation.controllers.SetUpTest;
import dao.HomePageDAO;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class HomePageScenarios extends SetUpTest {
    protected static Logger log = com.x.webAutomation.common.Log4jUtil.loadLogger(SetUpTest.class);

    private HomePageDAO hpDao;
    public synchronized HomePageDAO getSTProps(String scenario, Map<Object, String> testData)
            throws Exception {
        this.hpDao = new HomePageDAO(scenario, testData);
        return hpDao;
    }

    public void getHomePageWelcomeText(Map<Object, String> testData) throws Exception {
        String scenario = "";
        scenario = testData.get("TestCaseID") + "-"
                + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        log.info("Scenario: " + scenario);
        hpDao.getWelcomeHeadingFromHomePage();
    }
}
