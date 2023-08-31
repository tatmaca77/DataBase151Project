package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "./src/test/resources/features",
        glue = "stepdefinitions",
        tags = "@Register or @Database",
        dryRun = false

)
public class Runner {
}
