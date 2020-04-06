package com.example.homework3.recycler_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework3.R;
import com.example.homework3.ToDoListFragment;
import com.example.homework3.model.User;

import java.util.ArrayList;

public class RecyclerViewMainAdapter extends RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolder> {

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private ToDoListFragment fragment1;

    private ArrayList<User> users = new ArrayList<>();
    private Context mContext;

    public RecyclerViewMainAdapter(Context context, ArrayList<User> toDoItems) {
        this.users.addAll(toDoItems);

        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final User user = users.get(position);
        holder.idTextView.setText(user.getId().toString());
        holder.nameTextView.setText(user.getName());
        holder.usernameTextView.setText("Username: "+ user.getUsername());
        holder.emailTextView.setText("Email: "+ user.getEmail());
        holder.phoneTextView.setText("Phone: "+ user.getPhone());
//        holder.websiteTextView.setText("Website: " + user.getWebsite());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment1 = new ToDoListFragment(user.getId());
                fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.to_do_list_fragment, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView nameTextView;
        TextView usernameTextView;
        TextView emailTextView;
        TextView phoneTextView;
//        TextView websiteTextView;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
//            websiteTextView = itemView.findViewById(R.id.websiteTextView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}