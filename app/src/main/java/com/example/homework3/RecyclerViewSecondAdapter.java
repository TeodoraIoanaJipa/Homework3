package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework3.model.ToDoItem;

import java.util.ArrayList;

public class RecyclerViewSecondAdapter extends RecyclerView.Adapter<RecyclerViewSecondAdapter.ViewHolder> {

    private ArrayList<ToDoItem> todos = new ArrayList<>();
    private Context mContext;

    RecyclerViewSecondAdapter(Context context, ArrayList<ToDoItem> toDoItems) {
        this.todos.addAll(toDoItems);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todo_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTextView.setText(todos.get(position).getTitle());
        holder.completedTextView.setText(todos.get(position).getCompleted().toString());
        holder.idTextView.setText(todos.get(position).getId().toString());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
//                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView titleTextView;
        TextView completedTextView;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTODOTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            completedTextView = itemView.findViewById(R.id.completedTextView);
            parentLayout = itemView.findViewById(R.id.todo_parent_layout);
        }
    }
}