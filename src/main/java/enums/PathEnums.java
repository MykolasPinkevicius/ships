package enums;

public enum PathEnums {

    SCANPATH("file:C:\\Dev\\wildfly-9.0.2.Final\\bin\\logbook?noop=false&delete=true"),
    INBOXPATH("C:\\Dev\\wildfly-9.0.2.Final\\bin\\Savedfiles\\");

    private String path;

    PathEnums(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
