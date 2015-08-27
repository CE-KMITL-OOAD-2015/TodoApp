package th.ac.kmitl.ce.ooad.todo.model;

public class TodoTask {
    boolean mChecked;
    String mDescription;

    public boolean isChecked() {
        return mChecked;
    }

    public TodoTask completeTask() {
        mChecked = true;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public TodoTask(boolean checked, String description) {
        mChecked = checked;
        mDescription = description;
    }
}
