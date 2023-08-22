package utils;

import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import static driverfactory.DriverFactory.getBrowserType;
public class CustomAttributeReporter extends ScenarioReporter {

    private Map<String, String> getCustomLaunchAttributes() {
        return Collections.singletonMap("browser-type", getBrowserType());
    }

    @Override
    protected StartLaunchRQ buildStartLaunchRq(Date startTime, ListenerParameters parameters) {
        StartLaunchRQ rq = super.buildStartLaunchRq(startTime, parameters);
        getCustomLaunchAttributes().forEach((key, value) -> rq.getAttributes().add(new ItemAttributesRQ(key, value)));
        return rq;
    }
}

