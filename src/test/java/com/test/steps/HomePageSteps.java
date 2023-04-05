package com.test.steps;

import com.task.base.SeleniumDriver;
import com.task.pages.actions.HomePageActions;
import com.task.pages.locators.HomePageLocators;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import cucumber.api.java.en.Then;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;

public class HomePageSteps extends HomePageActions{
    public static List<WebElement> list = null;
    HomePageLocators homePageLocators = new HomePageLocators();
    HomePageActions homePageActions = new HomePageActions();
    Actions action = new Actions(SeleniumDriver.getDriver());
    HashMap map = new HashMap();
    @Given("^User navigates to \"([^\"]*)\"$")
    public void user_navigates_to_something(String strArg1) throws Throwable {
        //SeleniumDriver.openPage(strArg1);
        SeleniumDriver.getDriver().get(strArg1);
    }

    @When("user filters the list of endpoints")
    public void user_filters_the_list_of_endpoints(){

        //WebElement list = homePageActions.homePageLocators.endpoint;
        list = SeleniumDriver.getDriver().findElements(homePageLocators.endpoint);
        System.out.println("it came here");
        System.out.println(list.get(1).getText());

    }

    @Then("^display the different request type and endpoints$")
    public void display_the_different_request_type_and_endpoints() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i).getText() + " " + list.get(i).getAttribute("href").toString());
        }
    }

    @Then("^validate the number of endpoints to be \"([^\"]*)\"$")
    public void validate_the_number_of_endpoints_to_be(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        System.out.println("Number of Records matches? " + String.valueOf(list.size()).compareTo(arg1));
    }

    @Then("^click on each endpoint and display all the request and response details$")
    public void click_on_each_endpoint_and_display_all_the_request_and_response_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        for(int i = 0; i<list.size(); i++){
            //System.out.println(list.get(i).getText() + " " + list.get(i).getAttribute("href").toString());
            //homePageActions.toString();
            clickOnTheElement(list.get(i));
            System.out.println("--------------------------------------");
            System.out.println("URI is: " + SeleniumDriver.getDriver().findElement(homePageLocators.URI).getText());
            System.out.println("URI is: " + SeleniumDriver.getDriver().findElement(homePageLocators.responseCode).getText());
            System.out.println("URI is: " + SeleniumDriver.getDriver().findElement(homePageLocators.responseBody).getText());
            //takeScreenShot(list.get(i).getText());
            if(SeleniumDriver.getDriver().findElement(homePageLocators.responseCode).getText().contains("404")){
                map.put(SeleniumDriver.getDriver().findElement(homePageLocators.URI).getText(), SeleniumDriver.getDriver().findElement(homePageLocators.responseCode).getText());
            }
        }
    }

    @Then("^take screenshot of each API details$")
    public void take_screenshot_of_each_API_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        System.out.println("screenshot taken");
    }

    @Then("^navigate to support page$")
    public void navigate_to_support_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        clickOnTheElement(SeleniumDriver.getDriver().findElement(homePageLocators.supportPageButton));
        Thread.sleep(5000);
    }

    @When("^user navigates to support page$")
    public void user_navigates_to_support_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        clickOnTheElement(SeleniumDriver.getDriver().findElement(homePageLocators.supportPageButton));
        //Thread.sleep(5000);
    }

    @Then("^user enters the amount \"([^\"]*)\"$")
    public void user_enters_the_amount(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        inputValuesInElement(SeleniumDriver.getDriver().findElement(homePageLocators.inputAmount), arg1);
    }

    @Then("^click on the support request button$")
    public void click_on_the_support_request_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        clickOnTheElement(SeleniumDriver.getDriver().findElement(homePageLocators.supportReqButton));
    }

    @Then("^validate the amount paid result$")
    public void validate_the_amount_paid_result() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String result = SeleniumDriver.getDriver().findElement(homePageLocators.oneTimeAmountResult).getText();
        System.out.println(result);
    }

    @Then("^mouse hover to the amount input and clicks amount increment button four times$")
    public void mouse_hover_to_the_amount_input_and_clicks_amount_increment_button_four_times() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        clickOnTheElement(SeleniumDriver.getDriver().findElement(homePageLocators.inputAmount));

        action.contextClick(SeleniumDriver.getDriver().findElement(homePageLocators.inputAmount)).sendKeys(Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP).click().perform();


    }

    @Then("^validate the amount selected is \"([^\"]*)\"$")
    public void validate_the_amount_selected_is(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(3000);
        String amount = SeleniumDriver.getDriver().findElement(homePageLocators.inputAmount).getAttribute("value");
        System.out.println(amount);
        System.out.println(amount.contentEquals(arg1));

    }

    @Then("^mouse hover to the amount input and clicks amount decrement button one time$")
    public void mouse_hover_to_the_amount_input_and_clicks_amount_decrement_button_one_time() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        clickOnTheElement(SeleniumDriver.getDriver().findElement(homePageLocators.inputAmount));

        action.contextClick(SeleniumDriver.getDriver().findElement(homePageLocators.inputAmount)).sendKeys(Keys.ARROW_DOWN).click().perform();

    }
    @Then("^display if there are broken links$")
    public void display_if_there_are_broken_links() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Broken links are: " + map.toString());
    }
    @Then("^user selects monthly support plan$")
    public void user_selects_monthly_support_plan() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        clickOnTheElement(SeleniumDriver.getDriver().findElement(homePageLocators.monthlySupportSelect));
        Thread.sleep(3000);
    }

    @Then("^validate the monthly amount payment to be \"([^\"]*)\"$")
    public void validate_the_monthly_amount_payment_to_be(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String amount = SeleniumDriver.getDriver().findElement(homePageLocators.monthlySupportResult).getText();
        System.out.println(amount);
        System.out.println(amount.contentEquals(arg1));
    }
}
