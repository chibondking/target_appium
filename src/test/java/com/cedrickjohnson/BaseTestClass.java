package com.cedrickjohnson;

import com.cedrickjohnson.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class BaseTestClass {

    public static AppiumDriver driver;
    public static BasePage basePage;
    private static final File classpathRoot = new File(System.getProperty("user.dir"));
    private static File appDir = new File(classpathRoot, "/src/test/apk");
    private static File app = new File(appDir, "com.target.ui.apk");
    private static String appPackage = "com.target.ui";


    @BeforeAll
    @DisplayName("Install APK to Appium")
    public static void setUp() throws Exception {
        // Normally, this would go into a properties file
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("deviceName", "Nexus 5 API 27");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setPlatform(Platform.ANDROID);
        capabilities.setVersion("7.0");
        capabilities.setCapability("appWaitActivity", "*");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", "com.target.ui.activity.NavigationActivity");
        // I would specify this as an environment variable to be passed in when executing tests
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        basePage = new BasePage(driver);
        basePage.clickGettingStartedButton();
        basePage.clickAnonymousLoginButton();
        basePage.clickSearchByZipOrAddressButton();
        basePage.setZipCodeOrAddress("60606");
        basePage.setSelectedStore("Chicago State St.");
        basePage.clickSetAsStoreButton();
    }

    @AfterAll
    public static void tearDown() {
        driver.removeApp("com.target.ui");
        driver.quit();
    }

}
