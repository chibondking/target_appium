package com.cedrickjohnson.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.support.PageFactory;

import java.time.temporal.ChronoUnit;

public class SearchResultsPage extends BasePage {
    private final AppiumDriver appiumDriver;

    public SearchResultsPage(AppiumDriver driver) {
        this.appiumDriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(className = "android.support.v7.widget.RecyclerView")
    @WithTimeout(time=60, chronoUnit = ChronoUnit.SECONDS)
    private MobileElement productListView;

    public ProductPage selectProduct() {
        productListView.click();
        return new ProductPage(appiumDriver);
    }


}
