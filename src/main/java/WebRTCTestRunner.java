import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebRTCTestRunner implements Runnable {

    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public static final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");

    public static final String HUB_URL = "https://" + USERNAME + ":" + ACCESS_KEY
            + "@hub-cloud.browserstack.com/wd/hub";
    WebDriver driver = null;
    String roomId = null;
    boolean joinExisting = false;
    int userSelection = 0;
    long duration = 15000;
    ChromeOptions options = null;

    public WebRTCTestRunner(final ChromeOptions options, final String roomId, final boolean joinExisting,
            final long duration, final int userSelection) {
        this.options = options;
        this.roomId = roomId;
        this.joinExisting = joinExisting;
        this.duration = duration;
        this.userSelection = userSelection;
    }

    public WebRTCTestRunner(FirefoxOptions fireFoxConfiguration, String valueOf, boolean joinExisting2, int duration2,
            int userSelection2) {
    }

    public final void run() {

        try {

            // Creating Remote WebDriver based on the capabilites defined in
            // WebRTCConf.java.
            driver = new RemoteWebDriver(new URL(HUB_URL), options);

            // Creating new WebRTC Room with generated roomID
            if (!joinExisting) {

                if (userSelection != 3) {
                    driver.manage().window().maximize();
                }

                driver.get("https://appr.tc/");
                WebElement roomIdElement = driver.findElement(By.id("room-id-input"));
                roomIdElement.clear();
                roomIdElement.sendKeys(roomId);
                WebElement joinElement = driver.findElement(By.id("join-button"));
                joinElement.click();

            }
            // Joining the WebRTC Room with generated roomID
            else {
                if (userSelection != 3) {
                    driver.manage().window().maximize();
                }

                driver.get("https://appr.tc/r/" + roomId);

                WebElement joinElement = driver.findElement(By.id("confirm-join-button"));
                joinElement.click();

            }

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}