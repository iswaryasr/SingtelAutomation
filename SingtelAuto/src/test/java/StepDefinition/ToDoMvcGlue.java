package StepDefinition;

import Utils.PropertyUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import org.junit.BeforeClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class ToDoMvcGlue {
    public static WebDriver driver;
    PropertyUtils propertyUtils;

    public ToDoMvcGlue() {

    }

    @FindBy(xpath = "//header/input")
    WebElement inputTextBox;
    @FindBy(xpath = "//ul[@class='todo-list']/li/div/label")
    List<WebElement> addedToList;
    @FindBy(xpath = "//span[@class='todo-count']/strong")
    WebElement activeCount;
    @FindBy(xpath = "//li[@class='todo']/div/input")
    List<WebElement> toDoListCheckBox;
    @FindBy(xpath = "//button[@class='clear-completed']")
    WebElement clearCompletedButton;
    @FindBy(xpath = "//section/label[@for='toggle-all']")
    WebElement toggleAllButton;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "chromedriver");
        if(driver == null){
            driver = new ChromeDriver();
        }
        PageFactory.initElements(driver, this);
    }


    @Given("I am in todo MVC page")
    public void login_to_mvc_webpage() {
        propertyUtils = new PropertyUtils();
        driver.get(propertyUtils.getApplicationUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String ActualTitle = driver.getTitle();
        Assert.assertEquals("Vue.js • TodoMVC", ActualTitle);

    }

    @When("I add the tasks in todoMVC page")
    public void addTheTasksInTodoMVCPage(List<String> dataTable) {
        Iterator<String> it = dataTable.iterator();
        while (it.hasNext()) {
            String next = it.next();
            System.out.println("line " + next);
            inputTextBox.sendKeys(next);
            inputTextBox.sendKeys(Keys.RETURN);

        }
    }

    @When("I complete all active tasks")
    public void iCompleteAllActiveTasks() {
        Iterator<WebElement> it = toDoListCheckBox.iterator();
        while (it.hasNext()) {
            it.next().click();
        }
    }

    @Then("Verify the count of toDoList added is {string}")
    public void verifyTheCountOfToDoListAddedIs(String arg0) {
        Assert.assertEquals(arg0, activeCount.getText());
    }

    @And("Clear the completed tasks")
    public void clearTheCompletedTasks() {
        clearCompletedButton.click();
    }

    @Then("I {string} all the items in todoLList by clicking select all button")
    public void iAllTheItemsInTodoLListByClickingSelectAllButton(String option) {
        if((option.equals("select")) && (!(activeCount.getText()).equals("0"))){
            toggleAllButton.click();
        } else if ((option.equals("deselect")) && ((activeCount.getText()).equals("0"))) {
            toggleAllButton.click();
        }
    }
}
