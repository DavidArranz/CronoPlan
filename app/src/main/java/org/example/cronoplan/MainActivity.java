package org.example.cronoplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.example.cronoplan.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Task> toDoTasks = new ArrayList<>();
    private List<Task> inProgressTasks = new ArrayList<>();
    private List<Task> doneTasks = new ArrayList<>();

    private KanbanAdapter toDoAdapter;
    private KanbanAdapter inProgressAdapter;
    private KanbanAdapter doneAdapter;

    private RecyclerView toDoRecyclerView;
    private RecyclerView inProgressRecyclerView;
    private RecyclerView doneRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializacion reciclerviews
        toDoRecyclerView = findViewById(R.id.toDoRecyclerView);
        inProgressRecyclerView = findViewById(R.id.inProgressRecyclerView);
        doneRecyclerView = findViewById(R.id.doneRecyclerView);

        // Inicializacion de adapters
        toDoAdapter = new KanbanAdapter(toDoTasks, this);
        inProgressAdapter = new KanbanAdapter(inProgressTasks, this);
        doneAdapter = new KanbanAdapter(doneTasks, this);

        // Asicnacion de adapters a los reciclerViews
        toDoRecyclerView.setAdapter(toDoAdapter);
        inProgressRecyclerView.setAdapter(inProgressAdapter);
        doneRecyclerView.setAdapter(doneAdapter);

        // asignacion de layout manager al reciclerview
        toDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        inProgressRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        doneRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ItemTouchHelper.Callback callbackToDo = new KanbanItemTouchHelper(toDoAdapter);
        ItemTouchHelper.Callback callbackInProgress = new KanbanItemTouchHelper(inProgressAdapter);
        ItemTouchHelper.Callback callbackDone = new KanbanItemTouchHelper(doneAdapter);

        ItemTouchHelper itemTouchHelperToDo = new ItemTouchHelper(callbackToDo);
        ItemTouchHelper itemTouchHelperInProgress = new ItemTouchHelper(callbackInProgress);
        ItemTouchHelper itemTouchHelperDone = new ItemTouchHelper(callbackDone);

        toDoAdapter.setItemTouchHelper(itemTouchHelperToDo);
        inProgressAdapter.setItemTouchHelper(itemTouchHelperInProgress);
        doneAdapter.setItemTouchHelper(itemTouchHelperDone);

        itemTouchHelperToDo.attachToRecyclerView(toDoRecyclerView);
        itemTouchHelperInProgress.attachToRecyclerView(inProgressRecyclerView);
        itemTouchHelperDone.attachToRecyclerView(doneRecyclerView);

        

        // ejemplos
        toDoTasks.add(new Task("Task 1", "Description for task 1", 0));
        toDoTasks.add(new Task("Task 2", "Description for task 2", 0));
        inProgressTasks.add(new Task("Task 3", "Description for task 3", 1));
        doneTasks.add(new Task("Task 4", "Description for task 4", 2));

        // notificacion de los cambios
        toDoAdapter.notifyDataSetChanged();
        inProgressAdapter.notifyDataSetChanged();
        doneAdapter.notifyDataSetChanged();
    }
}