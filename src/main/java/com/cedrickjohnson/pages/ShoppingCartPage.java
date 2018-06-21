package com.cedrickjohnson.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends BasePage {
    private AppiumDriver appiumDriver;

    public ShoppingCartPage(AppiumDriver driver) {
        this.appiumDriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.target.ui:id/cart_product_overflow_menu")
    private MobileElement productOverflowMenu;

    @AndroidFindBy(id = "com.target.ui:id/cart_product_quantity_spinner")
    private MobileElement productQuantitySpinner;

    @AndroidFindBy(id = "com.target.ui:id/pickup_button")
    private MobileElement productInStorePickupButton;

    @AndroidFindBy(id = "com.target.ui:id/cart_ship_home_button")
    private MobileElement productShippingButton;

    @AndroidFindBy(id = "com.target.ui:id/checkout")
    private MobileElement checkoutButton;

    @AndroidFindBy(id = "com.target.ui:id/cart_product_title")
    private MobileElement cartProductTitle;

    @AndroidFindBy(id = "com.target.ui:id/toolbar_sub_title")
    private MobileElement cartItemCountText;

    public String getCartProductTitle() {
        return cartProductTitle.getText();
    }

    public String getCartItemCountText() {
        return cartItemCountText.getText();
    }

    public boolean isCartEmptyForAnonymousUser() {
        //If the cart is empty, the app will show the sign in frame (for not logged in users)
        if(!appiumDriver.findElements(By.id("com.target.ui:id/cart_sign_in_frame")).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void saveOrDeleteItem(String action) {
        //Save for later or delete item. Need to get TextView element text and click on it
        productOverflowMenu.click();
        //Save or delete
    }

    public void clickInStorePickupButton() {
        productInStorePickupButton.click();
    }

    public void clickShippingButton() {
        productShippingButton.click();
    }

    public void clickCheckoutButton() {
        //In this app this goes to Chrome to perform some action
        checkoutButton.click();
    }

}
