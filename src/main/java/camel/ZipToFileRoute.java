package camel;

import enums.PathEnums;
import enums.TimerConfiguration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.zipfile.ZipSplitter;

public class ZipToFileRoute extends RouteBuilder {

    @Override
    public void configure(){
        from(TimerConfiguration.TIMER.getTimerConfig())
                .pollEnrich(PathEnums.ZIPSCANPATH.getPath())
                .unmarshal().zipFile().split(new ZipSplitter()).streaming().convertBodyTo(String.class)
        .to(PathEnums.INBOXPATH.getPath());
    }
}
