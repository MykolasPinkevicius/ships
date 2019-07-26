package enums;

public enum PathEnums {

    SCANPATH("file:C:\\Dev\\zip\\logbook?noop=false&delete=true"),
    ZIPSCANPATH("file:C:\\Dev\\zip?delete=true"),
    CSVDELETEPATH("file:C:\\Dev\\zip\\"),
    //  Currently using database configuration table, left it for different app configuration
//  ZIPPATH("C:\\Dev\\zip\\"),
    ZIPINBOXPATH("file:C:\\Dev\\Savedfiles\\zip"),
    CSVZIPPATH("C:\\Dev\\SavedFiles\\zip\\"),
    CSVZIPTESTPATH("C:\\Dev\\zip\\");

    private String path;

    PathEnums(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
