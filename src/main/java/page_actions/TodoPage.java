package page_actions;

import io.cucumber.datatable.DataTable;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TodoPage {

    private final WebDriver driver = new ChromeDriver();
    List<String> subTaskList;

    public void launch_application() {
        driver.get("https://todomvc.com/examples/react/dist/");
    }

    public void add_task(String task) {
        WebElement taskInputField = driver.findElement(By.id("todo-input"));
        taskInputField.sendKeys(task);
        taskInputField.sendKeys(Keys.ENTER);
    }

    public WebElement verify_task_added(String task) {
        return driver.findElement(By.xpath("//label[text()='" + task + "']"));

    }

    public void close_browser() throws IOException {

        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        File snapshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(snapshot, new File(System.getProperty("user.dir") + "\\snapshots\\" + fileName + ".jpeg"));
        driver.quit();
    }

    public void add_tasks(DataTable dataTable) {
        List<String> tasks = dataTable.asList();
        for (String task : tasks) {
            WebElement taskInputField = driver.findElement(By.id("todo-input"));
            taskInputField.sendKeys(task);
            taskInputField.sendKeys(Keys.ENTER);
        }
    }

    public void complete_the_task(String task) {
        WebElement checkbox = driver.findElement(By.xpath("//label[text()='" + task + "']/preceding-sibling::input"));
        checkbox.click();
    }

    public void complete_the_tasks(DataTable dataTable) {
        List<String> taskList = dataTable.asList();
        subTaskList = taskList.subList(0, 2);
        for (String tasks : subTaskList) {
            WebElement taskInputField = driver.findElement(By.xpath("//label[text()='" + tasks + "']/preceding-sibling::input"));
            taskInputField.click();
        }
    }

    public WebElement get_complete_task_status(String task) {
        return driver.findElement(By.xpath("//label[text()='" + task + "']/parent::div/parent::li"));
    }

    public void edit_task(String task, String new_task) throws Exception {
        WebElement taskInputField = driver.findElement(By.xpath("//label[text()='" + task + "']"));

        Actions act = new Actions(driver);
        act.doubleClick(taskInputField)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(new_task)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

    }

    public void click_filter(String filter) {
        WebElement filterLink = driver.findElement(By.xpath("//a[text()='" + filter + "']"));
        filterLink.click();
    }

    public void completed_tasks_list() {
        List<WebElement> elements = driver.findElements(By.xpath("//li[@class='completed']"));
        List<String> taskList = new ArrayList<>();
        for (WebElement ele : elements) {
            taskList.add(ele.getText());
        }
        if (taskList.equals(subTaskList)) {
            System.out.println("Only Completed task list is printed");
        }

    }

    public void active_tasks_list(){
        List<WebElement> elements = driver.findElements(By.xpath("//li[@class='completed']"));
        if(elements.isEmpty())
        {
            System.out.println("Only Active task list is printed");
        }
    }

    public void all_tasks_list(){
        List<WebElement> completed_elements = driver.findElements(By.xpath("//li[@class='completed']"));
        List<WebElement> all_elements = driver.findElements(By.xpath("//li[@data-testid='todo-item']"));

        if((!completed_elements.isEmpty())&&(!all_elements.isEmpty()))
        {
            System.out.println("All task list is printed");
        }
    }

    public void click_clear_completed() {
        WebElement btnClear = driver.findElement(By.className("clear-completed"));
        btnClear.click();
    }

}
