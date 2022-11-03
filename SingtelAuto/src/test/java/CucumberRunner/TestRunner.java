package CucumberRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/Features",
        glue = {"StepDefinition"},
        plugin = { "pretty",
                "html:target/cucumber.html","junit:target/cucumber.xml"},
        tags = "@TodoMvcTest",monochrome = true)

public class TestRunner {
}
