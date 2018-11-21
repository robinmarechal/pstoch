package ui.views;

public enum ViewReference
{
    HOME("home"),
    ;

    public final String filename;
    public final String value;

    ViewReference (String filename) {
        this.filename = filename;
        this.value = filename;
    }

    @Override
    public String toString () {
        return this.value;
    }
}
