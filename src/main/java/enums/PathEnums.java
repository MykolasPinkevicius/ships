package enums;

public enum PathEnums {

    SCANPATH("file:C:\\Dev\\wildfly-9.0.2.Final\\bin\\logbook?noop=false&delete=true"),
    ZIPSCANPATH("file:C:\\Dev\\wildfly-9.0.2.Final\\bin\\zip?noop=false&delete=true&delay=5s"),
//    Currently using database configuration table, left it for different app configuration
    ZIPINBOXPATH("file:C:\\Dev\\wildfly-9.0.2.Final\\bin\\Savedfiles\\zip"),
    INBOXPATH("file:C:\\Dev\\wildfly-9.0.2.Final\\bin\\Savedfiles"),
    CSVZIPPATH("C:\\Dev\\wildfly-9.0.2.Final\\bin\\Savedfiles\\zip\\");

    private String path;

    PathEnums(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
