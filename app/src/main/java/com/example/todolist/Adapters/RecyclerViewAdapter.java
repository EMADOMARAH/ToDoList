package com.example.todolist.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.DashBoards.DashBoard;
import com.example.todolist.Models.taskModel;
import com.example.todolist.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    List<taskModel>taskModels;

    public RecyclerViewAdapter(List<taskModel> taskModels) {
        this.taskModels = taskModels;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final String name;
        final int id,pr,timeM,timeH,dateY,dateM,dateD;
        final taskModel tasks =taskModels.get(position);
        name=tasks.getTaskName();
        pr = tasks.getPriority();
        timeM=tasks.getTimeMin();
        timeH=tasks.getTimeHoure();
        dateD = tasks.getDateDay();
        dateM = tasks.getDateMonth();
        dateY = tasks.getDateYear();
        holder.taskTime.setText(timeH+":"+timeM);
        holder.taskDate.setText(dateY + "/"+dateM+"/"+dateD);
        holder.taskName.setText(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), DashBoard.class);
                intent.putExtra("tt",tasks);
                intent.putExtra("key",2);
                view.getContext().startActivity(intent);
            }
        });

        if(pr == 1 )
        {
            holder.taskTime.setBackgroundResource(R.drawable.circle);
        }else if (pr == 2)
        {
            holder.taskTime.setBackgroundResource(R.drawable.circle2);
        }else if (pr == 3 )
        {
            holder.taskTime.setBackgroundResource(R.drawable.circle3);
        }
    }

    @Override
    public int getItemCount() {
        if(taskModels==null)
            return 0;

        return taskModels.size();
    }

    public taskModel getTaskAt(int position)
    {
        return taskModels.get(position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView taskName,taskDate,taskTime;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.Task_Name);
            taskDate = itemView.findViewById(R.id.Task_Date);
            taskTime = itemView.findViewById(R.id.task_Time);
        }
    }
}
