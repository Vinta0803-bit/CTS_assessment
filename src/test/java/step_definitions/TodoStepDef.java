package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import page_actions.TodoPage;

import java.io.IOException;
import java.util.List;

public class TodoStepDef {

    TodoPage todoPage = new TodoPage();

    @Given("^The user is logged in to todo application$")
    public void theUserIsLoggedInToTodoApplication() {
        todoPage.launch_application();
    }

    @When("^The user adds (.*) to todo list$")
    public void iAddANewTask(String task) {
        todoPage.add_task(task);
    }

    @Then("^The task lists should be updated with the (.*)$")
    public void theTaskShouldAppearInTheListOfTodos(String task) {
        Assert.assertNotNull(todoPage.verify_task_added(task));
    }

    @Given("^Below tasks are added todo list$")
    public void belowTasksAreAddedTodoList(io.cucumber.datatable.DataTable dataTable) {
        todoPage.add_tasks(dataTable);
    }

    @When("^The user clicks the completion checkbox for (.*)$")
    public void theUserClicksTheCompletionCheckboxForTask(String task) {
        todoPage.complete_the_task(task);
    }

    @Then("^The (.*) is completed$")
    public void theTaskIsCompleted(String task) {
        Assert.assertTrue(todoPage.get_complete_task_status(task).getAttribute("class").contains("completed"));

    }

    @Then("close the browser")
    public void closeTheBrowser() throws IOException {
        todoPage.close_browser();
    }


    @Given("^The (.*) is added to todo list$")
    public void theTaskIsAddedToTodoList(String task) {
        todoPage.add_task(task);
    }


    @When("^The user edits (.*) to (.*)$")
    public void theUserEditsToNew_task(String task,String new_task) throws Exception {
        todoPage.edit_task(task,new_task);
    }

    @Given("^Below tasks are added todo list and few are marked as completed$")
    public void belowTasksAreAddedTodoListAndFewAreMarkedAsCompleted(io.cucumber.datatable.DataTable dataTable) {
        todoPage.add_tasks(dataTable);
        todoPage.complete_the_tasks(dataTable);
    }

    @When("^The user clicks the (.*) filter$")
    public void theUserClicksTheFilter(String filter) {
        todoPage.click_filter(filter);
    }

    @Then("^The tasks should be (.*)$")
    public void theTasksShouldBeFilter(String filter) {
        if(filter.equals("Completed")){
           todoPage.completed_tasks_list();
        }else if(filter.equals("Active")){
            todoPage.active_tasks_list();
        }else{
            todoPage.all_tasks_list();
        }
    }

    @When("^The user clicks the clear completed$")
    public void theUserClicksTheClearCompleted() {
        todoPage.click_clear_completed();
    }

    @Then("The active todo tasks should be listed ony")
    public void theActiveTodoTasksShouldBeListedOny() {
        todoPage.active_tasks_list();
    }
}
