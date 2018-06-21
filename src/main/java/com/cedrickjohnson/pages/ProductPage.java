package com.cedrickjohnson.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.support.PageFactory;

import java.time.temporal.ChronoUnit;

public class ProductPage extends BasePage {
    private AppiumDriver appiumDriver;

    public ProductPage(AppiumDriver driver) {
        this.appiumDriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.target.ui:id/add_to_cart_button")
    @WithTimeout(time=60, chronoUnit = ChronoUnit.SECONDS)
    private MobileElement addToCartButton;

    public void clickAddToCartButton() {
        addToCartButton.click();
    }
}
