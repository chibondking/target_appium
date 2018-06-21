package com.cedrickjohnson.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.temporal.ChronoUnit;

public class BasePage {
    private AppiumDriver appiumDriver;

    public BasePage() {}

    public BasePage(AppiumDriver driver) {
        this.appiumDriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id = "search")
    @AndroidFindBy(accessibility = "search")
    @WithTimeout(time=60, chronoUnit = ChronoUnit.SECONDS)
    private MobileElement targetSearchBox;

    @AndroidFindBy(id = "com.target.ui:id/search_field")
    @WithTimeout(time=60, chronoUnit = ChronoUnit.SECONDS)
    private MobileElement popupSearchBox;

    @AndroidFindBy(id = "com.target.ui:id/prd_search_suggestion")
    private MobileElement popupSearchSuggestion;

    @FindBy(id = "cart")
    @AndroidFindBy(id = "com.target.ui:id/cart_icon")
    @WithTimeout(time=60, chronoUnit = ChronoUnit.SECONDS)
    private MobileElement shoppingCart;

    @AndroidFindBy(id="com.target.ui:id/first_run_get_started_button")
    private MobileElement gettingStartedButton;

    @AndroidFindBy(id="com.target.ui:id/gate_anonymous_login_button")
    private MobileElement anonymousLoginButton;

    @AndroidFindBy(id="com.target.ui:id/gate_login_button")
    private MobileElement loginButton;

    @AndroidFindBy(id="com.target.ui:id/show_nearby_stores")
    private MobileElement showNearbyStoresButton;

    @AndroidFindBy(id="com.target.ui:id/search_by_zip_address")
    @WithTimeout(time=60, chronoUnit = ChronoUnit.SECONDS)
    private MobileElement searchByZipOrAddressButton;

    @AndroidFindBy(id="com.target.ui:id/search_field")
    private MobileElement addressOrZipSearchField;

    @AndroidFindBy(id="com.target.ui:id/set_store_button")
    private MobileElement setAsStoreButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout[2]/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]")
    @WithTimeout(time=60, chronoUnit = ChronoUnit.SECONDS)
    private MobileElement storeList;

    @AndroidFindBy(id="com.target.ui:id/shop_expanded_top_toolbar_subtitle")
    private MobileElement selectedStoreText;


    public SearchResultsPage searchForProduct(String product) {
        targetSearchBox.click();
        popupSearchBox.setValue(product);
        popupSearchSuggestion.click();
        return new SearchResultsPage(appiumDriver);
    }

    public void clickSetAsStoreButton() {
        setAsStoreButton.click();
    }

    public String getSelectedStoreName() {
        return selectedStoreText.getText();
    }

    public void setSelectedStore() {
        storeList.click();
    }

    public void setZipCodeOrAddress(String value) {
        addressOrZipSearchField.setValue(value);
        addressOrZipSearchField.click();
    }

    public void clickShowNearbyStoresButton() {
        showNearbyStoresButton.click();
    }

    public void clickSearchByZipOrAddressButton() {
        searchByZipOrAddressButton.click();
    }

    public void clickGettingStartedButton() {
        gettingStartedButton.click();
    }

    public void clickAnonymousLoginButton() {
        anonymousLoginButton.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickShoppingCartIcon() {
        shoppingCart.click();
    }

}
