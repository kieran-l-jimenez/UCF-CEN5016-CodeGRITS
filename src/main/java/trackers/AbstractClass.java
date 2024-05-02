public abstract class AbstractFileTracker {

    protected boolean isTracking = false;
    protected String projectPath = "";
    protected String dataOutputPath = "";

    public AbstractFileTracker(String projectPath, String dataOutputPath) {
        this.projectPath = projectPath;
        this.dataOutputPath = dataOutputPath;
    }

    public abstract void startTracking();

    public abstract void stopTracking();

    public abstract void pauseTracking();

    public abstract void resumeTracking();

    public boolean isTracking() {
        return isTracking;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public void setDataOutputPath(String dataOutputPath) {
        this.dataOutputPath = dataOutputPath;
    }

    // To be continued
}