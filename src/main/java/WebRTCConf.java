import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariOptions;



public class WebRTCConf {
   
   public static int getRoomID() {
      Random random = new Random();
        int roomId = random.nextInt(500000);
        roomId += 100000;
        
        return roomId;
   }
   
   public static ChromeOptions getFireFoxConfiguration() {
      
      FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("media.navigator.streams.fake", true);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        
        ChromeOptions options = new ChromeOptions();
        options.merge(firefoxOptions);
        options.setCapability("media.navigator.streams.fake", true);


        options.setCapability("browser", "Firefox");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Chrome-FireFox");
        options.setCapability("name", "WebRTC Room Joining - FireFox Browser");
        options.setCapability("browserstack.idleTimeout", 300);
        
        return options;
   }
   
   public static ChromeOptions getChromeConfiguration() {
      ChromeOptions options = new ChromeOptions();

        options.addArguments(
              "--use-fake-device-for-media-stream",
                "--use-fake-ui-for-media-stream"
        );

        options.setCapability("browser", "Chrome");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Chrome-FireFox");
        options.setCapability("name", "WebRTC Room Creation - Chrome Browser");
        options.setCapability("browserstack.idleTimeout", 300);
        
        return options;
   }

    public static ChromeOptions getEdgeConfiguration() {

        EdgeOptions edgeOptions = new EdgeOptions();

        ChromeOptions options = new ChromeOptions();
        options.merge(edgeOptions);
        options.addArguments(
                "--use-fake-device-for-media-stream",
                  "--use-fake-ui-for-media-stream"
          );

        options.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
        options.setCapability("browser", "Edge");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Edge-Safari");
        options.setCapability("name", "WebRTC Room Creation - Edge Browser");
        options.setCapability("browserstack.idleTimeout", 300);

        return options;
    }

    public static ChromeOptions getSafariConfiguration() {

        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setUseTechnologyPreview(true);

        ChromeOptions options = new ChromeOptions();
        options.merge(safariOptions);


        options.setCapability("browser", "Safari");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "OS X");
        options.setCapability("os_version", "Big Sur");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Edge-Safari");
        options.setCapability("name", "WebRTC Room Joining - Safari Browser");
        options.setCapability("browserstack.idleTimeout", 300);

        return options;
    }
    
    public static ChromeOptions getiOSConfiguration() {

        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setUseTechnologyPreview(true);

        ChromeOptions options = new ChromeOptions();
        options.merge(safariOptions);


        options.setCapability("device", "iPhone 12");
        options.setCapability("real_mobile", "true");
        options.setCapability("os_version", "14");
        options.setCapability("autoAcceptAlerts", "true");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Android-iOS");
        options.setCapability("name", "WebRTC Room Joining - iOS");
        options.setCapability("browserstack.idleTimeout", 300);

        return options;
    }
    
    public static ChromeOptions getAndroidConfiguration() {

    	ChromeOptions options = new ChromeOptions();

        options.addArguments(
              "--use-fake-device-for-media-stream",
                "--use-fake-ui-for-media-stream"
        );

        options.setCapability("os_version", "11.0");
        options.setCapability("device", "Samsung Galaxy S21");
        options.setCapability("real_mobile", "true");
        options.setCapability("autoGrantPermissions", "true");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Android-iOS");
        options.setCapability("name", "WebRTC Room Creation - Android");
        options.setCapability("browserstack.idleTimeout", 300);
        
        return options;
    }
   
   public static void main(String[] args) {
	   	//Disabling Selenium messages.
	   	java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
	   	
        try {
           //Generating Random new RoomID
           int roomId = getRoomID();
            
        	//Getting User's Selection
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Please Select a Browser Combination to initiate the WebRTC test on BrowserStack : ");
        	System.out.println("1 : Press 1 to initiate WebRTC test on Google Chrome and Firefox Browser Combination ");
        	System.out.println("2 : Press 2 to initiate WebRTC test on Edge and Safari Browser Combination ");
        	System.out.println("3 : Press 3 to initiate WebRTC test on Android and iOS Combination");
        	int userSelection  = sc.nextInt();
        	sc.close();
        	Thread t1 = null;
        	Thread t2 = null;
        	
        	if(userSelection == 1) {
        		System.out.println("Your test would execute on Chrome-Firefox Browser Combination");
        		//Creating capabilities for Chrome Browser.
        		ChromeOptions chromeConfiguration = getChromeConfiguration();
        		//Creating capabilities for FireFox Browser.
        		ChromeOptions fireFoxConfiguration = getFireFoxConfiguration();
        		
        		//Creating the WebRTC Room on Chrome Browser.
        		System.out.println("Creating a Room on Chrome Browser");
                t1 = new Thread(new WebRTCTestRunner(chromeConfiguration, String.valueOf(roomId),false,20000, userSelection));
                t1.start();
                //Waiting for other user to join the above created WebRTC room.
                Thread.sleep(15000);
                
                // Joining the above created Room on FireFox Browser.
                System.out.println("Joining the Room on Firefox Browser");
                t2 = new Thread(new WebRTCTestRunner(fireFoxConfiguration,String.valueOf(roomId),true,20000, userSelection));
                t2.start();
        		
        	}else if(userSelection == 2) {
        		System.out.println("Your test would execute on Edge-Safari Browser Combination");
        		//Creating capabilities for Edge Browser.
                ChromeOptions edgeConfiguration = getEdgeConfiguration();
                //Creating capabilities for Safari Browser.
                ChromeOptions safariConfiguration = getSafariConfiguration();

                //Creating the WebRTC Room on Edge Browser.
                System.out.println("Creating a Room on Edge Browser");
                t1 = new Thread(new WebRTCTestRunner(edgeConfiguration, String.valueOf(roomId),false,20000, userSelection));
                t1.start();
                
                //Waiting for other user to join the above created WebRTC room.
                Thread.sleep(15000);
                
                // Joining the above created Room on Safari Browser.
                System.out.println("Joining the Room on Safari Browser");
                t2 = new Thread(new WebRTCTestRunner(safariConfiguration,String.valueOf(roomId),true,20000, userSelection));
                t2.start();
                
        	}else if(userSelection == 3) {
        		System.out.println("Your test would execute on Android-iOS Combination");
        		//Creating capabilities for Android Mobile Browser.
                ChromeOptions AndroidConfiguration = getAndroidConfiguration();
                //Creating capabilities for iOS Mobile Browser.
                ChromeOptions iOSConfiguration = getiOSConfiguration();

                //Creating the WebRTC Room on Android Mobile Browser.
                System.out.println("Creating a Room on Android Mobile Browser");
                t1 = new Thread(new WebRTCTestRunner(AndroidConfiguration, String.valueOf(roomId),false,20000, userSelection));
                t1.start();
                
                //Waiting for other user to join the above created WebRTC room.
                Thread.sleep(15000);
                
                // Joining the above created Room on iOS Mobile Browser.
                System.out.println("Joining the Room on iOS Mobile Browser");
                t2 = new Thread(new WebRTCTestRunner(iOSConfiguration, String.valueOf(roomId),true,20000, userSelection));
                t2.start();
        	}else {
        		
        		System.out.println("Please select a valid Option");
        		
        	}
          
        	//Wait for threads to finish execution.
            t1.join();
            t2.join();

        }
        catch(Exception ex) {
            ex.printStackTrace();

        } finally {
           System.out.println("Test successfully executed!");
        }
   }
}