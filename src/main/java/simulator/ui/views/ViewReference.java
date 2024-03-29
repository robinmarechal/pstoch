package simulator.ui.views;

public enum ViewReference
{
    HOME("home"),
    MAX_QUEUE_CAPACITY("maxQueueCapacityView"),
    NB_SERVERS("serversView"),
    LAMBDA("lambda"),
    MU("mu"),
    RESULTS("resultsView"),
    INPUTS("inputs");

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
