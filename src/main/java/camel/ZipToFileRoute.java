package camel;

import enums.PathEnums;
import enums.TimerConfiguration;
import org.apache.camel.builder.RouteBuilder;

public class ZipToFileRoute extends RouteBuilder {

    @Override
    public void configure(){
        from(TimerConfiguration.TIMER.getTimerConfig())
                .pollEnrich(PathEnums.ZIPSCANPATH.getPath())
                .unmarshal().zip()
        .to(PathEnums.INBOXPATH.getPath());
    }
}
