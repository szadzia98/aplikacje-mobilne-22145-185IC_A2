package com.example.lab7;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.util.TimingLogger;

import androidx.appcompat.app.AppCompatActivity;

public class Async extends AppCompatActivity {
    private Button btnAddNew, main, clear100, init100;
    private Button btnClearCompleted;
    private Button btnSave;
    private Button btnCancel;
    private EditText etNewTask;
    private ListView lvTodos;
    private LinearLayout llControlButtons;
    private LinearLayout llNewTaskButtons;
    private ProgressBar progBar;

    private TodoDbAdapter todoDbAdapter;
    private Cursor todoCursor;
    private List<TodoTask> tasks;
    private TodoTasksAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        initUiElements();
        initListView();
        initButtonsOnClickListeners();
    }


    private void clear100Tasks(){
        for(int i = 0; i <100; i++){
            TodoTask task = tasks.get(i);
            todoDbAdapter.updateTodo(task.getId(), task.getDescription(), true);
        }
        clearCompletedTasks();
    }
    private void init100Todos(){
        long start=System.currentTimeMillis();
        new LoaderTasks().execute();
        long stop = System.currentTimeMillis();
        System.out.println("Czas wykonania async:"+(stop-start));
    }

    private void initUiElements() {
        btnAddNew = (Button) findViewById(R.id.btnAddNewAsync);
        btnClearCompleted = (Button) findViewById(R.id.btnClearCompletedAsync);
        btnSave = (Button) findViewById(R.id.btnSaveAsync);
        btnCancel = (Button) findViewById(R.id.btnCancelAsync);
        etNewTask = (EditText) findViewById(R.id.etNewTaskAsync);
        lvTodos = (ListView) findViewById(R.id.lvTodosAsync);
        llControlButtons = (LinearLayout) findViewById(R.id.llControlButtonsAsync);
        llNewTaskButtons = (LinearLayout) findViewById(R.id.llNewTaskButtonsAsync);
        main = (Button) findViewById(R.id.btnMainAsync);
        clear100 = (Button) findViewById(R.id.btnClear100Async);
        init100 = (Button) findViewById(R.id.btnCInit100Async);
        progBar = (ProgressBar) findViewById(R.id.progBar);
     }

    private void initListView() {
        fillListViewData();
        initListViewOnItemClick();
    }

    private void fillListViewData() {
        todoDbAdapter = new TodoDbAdapter(getApplicationContext());
        todoDbAdapter.open();
        getAllTasks();
        listAdapter = new TodoTasksAdapter(this, tasks);
        lvTodos.setAdapter(listAdapter);
    }

    private void getAllTasks() {
        tasks = new ArrayList<TodoTask>();
        todoCursor = getAllEntriesFromDb();
        updateTaskList();
    }

    private Cursor getAllEntriesFromDb() {
        todoCursor = todoDbAdapter.getAllTodos();
        if(todoCursor != null) {
            startManagingCursor(todoCursor);
            todoCursor.moveToFirst();
        }
        return todoCursor;
    }

    private void updateTaskList() {
        if(todoCursor != null && todoCursor.moveToFirst()) {
            do {
                long id = todoCursor.getLong(TodoDbAdapter.ID_COLUMN);
                String description = todoCursor.getString(TodoDbAdapter.DESCRIPTION_COLUMN);
                boolean completed = todoCursor.getInt(TodoDbAdapter.COMPLETED_COLUMN) > 0 ? true : false;
                tasks.add(new TodoTask(id, description, completed));
            } while(todoCursor.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        if(todoDbAdapter != null)
            todoDbAdapter.close();
        super.onDestroy();
    }

    private void initListViewOnItemClick() {
        lvTodos.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                                    long id) {
                TodoTask task = tasks.get(position);
                if(task.isCompleted()){
                    todoDbAdapter.updateTodo(task.getId(), task.getDescription(), false);
                } else {
                    todoDbAdapter.updateTodo(task.getId(), task.getDescription(), true);
                }
                updateListViewData();
            }
        });
    }

    private void updateListViewData() {
        todoCursor.requery();
        tasks.clear();
        updateTaskList();
        listAdapter.notifyDataSetChanged();
    }

    private void initButtonsOnClickListeners() {
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnAddNewAsync:
                        addNewTask();
                        break;
                    case R.id.btnSaveAsync:
                        saveNewTask();
                        break;
                    case R.id.btnCancelAsync:
                        cancelNewTask();
                        break;
                    case R.id.btnClearCompletedAsync:
                        clearCompletedTasks();
                        break;
                    case R.id.btnMainAsync:
                        Intent intent = new Intent(Async.this, MainActivity.class);
                        startActivity(intent);
                        onStop();
                        break;
                    case R.id.btnCInit100Async:
                        init100Todos();
                        break;
                    case R.id.btnClear100Async:
                        clear100Tasks();
                        break;
                    default:
                        break;
                }
            }
        };
        btnAddNew.setOnClickListener(onClickListener);
        btnClearCompleted.setOnClickListener(onClickListener);
        btnSave.setOnClickListener(onClickListener);
        btnCancel.setOnClickListener(onClickListener);
        clear100.setOnClickListener(onClickListener);
        main.setOnClickListener(onClickListener);
        init100.setOnClickListener(onClickListener);
    }

    private void showOnlyNewTaskPanel() {
        setVisibilityOf(llControlButtons, false);
        setVisibilityOf(llNewTaskButtons, true);
        setVisibilityOf(etNewTask, true);
    }

    private void showOnlyControlPanel() {
        setVisibilityOf(llControlButtons, true);
        setVisibilityOf(llNewTaskButtons, false);
        setVisibilityOf(etNewTask, false);
    }

    private void setVisibilityOf(View v, boolean visible) {
        int visibility = visible ? View.VISIBLE : View.GONE;
        v.setVisibility(visibility);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etNewTask.getWindowToken(), 0);
    }

    private void addNewTask(){
        showOnlyNewTaskPanel();
    }

    private void saveNewTask(){
        String taskDescription = etNewTask.getText().toString();
        if(taskDescription.equals("")){
            etNewTask.setError("Your task description couldn't be empty string.");
        } else {
            todoDbAdapter.insertTodo(taskDescription);
            etNewTask.setText("");
            hideKeyboard();
            showOnlyControlPanel();
        }
        updateListViewData();
    }

    private void cancelNewTask() {
        etNewTask.setText("");
        showOnlyControlPanel();
    }

    private void clearCompletedTasks(){
        if(todoCursor != null && todoCursor.moveToFirst()) {
            do {
                if(todoCursor.getInt(TodoDbAdapter.COMPLETED_COLUMN) == 1) {
                    long id = todoCursor.getLong(TodoDbAdapter.ID_COLUMN);
                    todoDbAdapter.deleteTodo(id);
                }
            } while (todoCursor.moveToNext());
        }
        updateListViewData();
    }

    private class LoaderTasks extends AsyncTask<Void,Integer,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progBar.setVisibility(View.VISIBLE);
            progBar.setProgress(0);
        }
        @Override
        protected Void doInBackground(Void... params){
            for(int i = 1; i <=100; i++){
                String taskDescription = "Task" + i;
                todoDbAdapter.insertTodo(taskDescription);
                publishProgress(i);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progBar.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progBar.setVisibility(View.INVISIBLE);
            updateListViewData();
        }
    }
}