package org.example;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("org/example") // Path where the feature files are located
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty") // Provides readable output
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example") // Where the step definitions are located
public class RunCucumberTest {
}
