package com.example.todolist.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todolist.DataBase.AppDataBase;
import com.example.todolist.Models.taskModel;
import com.example.todolist.R;
import com.example.todolist.Adapters.RecyclerViewAdapter;

import java.util.List;

public class Medium_Fragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    List<taskModel> list;
    AppDataBase appDataBase;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_medium_, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Confirm Delete...");
        alertDialog.setMessage("Are you sure you want delete this Note?");
        alertDialog.setIcon(R.drawable.delete);
        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //build DataBase
        appDataBase = Room.databaseBuilder(getContext(), AppDataBase.class,"room2").build();
        new Medium_Fragment.getmedium().execute();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT |ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                new Medium_Fragment.Delete().execute(recyclerViewAdapter.getTaskAt(viewHolder.getAdapterPosition()));
                                Toast.makeText(getContext(),
                                        "You Deleted this note", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                new getmedium().execute();
                                Toast.makeText(getContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    class getmedium extends AsyncTask<Void,Void,List<taskModel>>
    {
        @Override
        protected List<taskModel> doInBackground(Void... voids) {
            list= appDataBase.taskDao().getmedium();
            return list;
        }

        @Override
        protected void onPostExecute(List<taskModel> taskModels) {
            recyclerViewAdapter = new RecyclerViewAdapter(taskModels);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }

    class Delete extends AsyncTask<taskModel,Void,List<taskModel>>
    {
        @Override
        protected List<taskModel> doInBackground(taskModel... taskModels) {
            appDataBase.taskDao().delete(taskModels[0]);
            return appDataBase.taskDao().getmedium();
        }

        @Override
        protected void onPostExecute(List<taskModel> taskModels) {
            recyclerViewAdapter = new RecyclerViewAdapter(taskModels);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
}
