package th.ac.kmitl.ce.ooad.todo.model;

import java.util.ArrayList;
import java.util.List;

public class TodoTaskRepository {

    private List<TodoTask> mTasks = new ArrayList<>();

    private static TodoTaskRepository repo = new TodoTaskRepository();

    public static TodoTaskRepository getDefaultRepository() {
        return repo;
    }

    private TodoTaskRepository() { }

    public TodoTask get(int position) {
        return mTasks.get(position);
    }

    public void addTask(String taskDescription) {
        mTasks.add(new TodoTask(false, taskDescription));
    }

    public TodoTask completeTask(int position) {
        TodoTask task = mTasks.get(position);
        task.completeTask();
        return task;
    }

    public int getSize() {
        return mTasks.size();
    }
}
