package camel;

import enums.PathEnums;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.zipfile.ZipSplitter;


public class ZipToFileRoute extends RouteBuilder {

    @Override
    public void configure(){
        from(PathEnums.ZIPSCANPATH.getPath())
                .split(new ZipSplitter())
                .streaming()
        .to(PathEnums.ZIPINBOXPATH.getPath()).end();
    }
}
