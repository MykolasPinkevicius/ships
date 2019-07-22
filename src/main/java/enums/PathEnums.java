package enums;

public enum PathEnums {

    SCANPATH("file:C:\\Dev\\wildfly-9.0.2.Final\\bin\\logbook?noop=false&delete=true"),
    ZIPSCANPATH("file:C:\\Dev\\zip?noop=false&delete=true&delay=5s&initialDelay=5s"),
    //    Currently using database configuration table, left it for different app configuration
    ZIPPATH("C:\\Dev\\wildfly-9.0.2.Final\\bin\\zip\\"),
    ZIPINBOXPATH("file:C:\\Dev\\Savedfiles\\zip"),
    CSVZIPPATH("C:\\Dev\\SavedFiles\\zip\\");

    private String path;

    PathEnums(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
