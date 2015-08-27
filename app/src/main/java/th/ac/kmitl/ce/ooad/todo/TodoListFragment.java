package th.ac.kmitl.ce.ooad.todo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import th.ac.kmitl.ce.ooad.todo.model.TodoTask;
import th.ac.kmitl.ce.ooad.todo.model.TodoTaskRepository;

public class TodoListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_todo_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new TaskRecyclerViewAdapter(getActivity(), TodoTaskRepository.getDefaultRepository()));
    }

    public static class TaskRecyclerViewAdapter
            extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private final TodoTaskRepository mRepository;
        private int mBackground;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;

            public final View mView;
            public final CheckBox mCheckBox;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mCheckBox = (CheckBox) view.findViewById(R.id.taskcheck);
                mTextView = (TextView) view.findViewById(R.id.taskdescription);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public TaskRecyclerViewAdapter(Context context, TodoTaskRepository repository) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mRepository = repository;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            // Replace the given ViewHolder with values from the specified position
            final TodoTask task = mRepository.get(position);

            holder.mBoundString = task.getDescription();
            holder.mCheckBox.setChecked(task.isChecked());
            holder.mTextView.setText(task.getDescription());

            View.OnClickListener taskClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do something when user click the task

                    holder.mCheckBox.setChecked(task.isChecked());
                }
            };

            holder.mView.setOnClickListener(taskClickListener);
            holder.mCheckBox.setOnClickListener(taskClickListener);
        }

        @Override
        public int getItemCount() {
            return mRepository.getSize();
        }
    }

}
