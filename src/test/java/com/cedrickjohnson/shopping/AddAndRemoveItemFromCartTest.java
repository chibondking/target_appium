package com.cedrickjohnson.shopping;

import com.cedrickjohnson.BaseTestClass;
import com.cedrickjohnson.pages.ProductPage;
import com.cedrickjohnson.pages.SearchResultsPage;
import com.cedrickjohnson.pages.ShoppingCartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Nested
@DisplayName("Target Mobile App")
public class AddAndRemoveItemFromCartTest extends BaseTestClass {

    private String specificLaCroixProductToDrink = "LaCroix Passionfruit Sparkling Water - 8pk/12 fl oz Cans";

    @BeforeEach
    public void beforeEach() {
        System.out.println("test");
    }

    @Test
    @DisplayName("Add LaCroix Water to Shopping Cart")
    public void addLacroixWaterToShoppingCart() {

        SearchResultsPage result = basePage.searchForProduct("lacroix");
        ProductPage productPage = result.selectSpecificProduct(specificLaCroixProductToDrink);
        productPage.clickAddToCartButton();
        productPage.clickShoppingCartIcon();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        assertFalse(shoppingCartPage.isCartEmptyForAnonymousUser());
        assertEquals(specificLaCroixProductToDrink, shoppingCartPage.getCartProductTitle());
    }
}
