package com.example.homework3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.homework3.model.ToDoItem;
import com.example.homework3.recycler_adapters.RecyclerViewSecondAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class ToDoListFragment extends Fragment {

    private int userId;
    private String url = "https://jsonplaceholder.typicode.com/todos?userId=";
    private ArrayList<ToDoItem> toDoItems = new ArrayList<>();

    public ToDoListFragment(int userId) {
        this.userId = userId;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.to_do_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getTODOItems();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = getView().findViewById(R.id.todos_recycler_view);
        RecyclerViewSecondAdapter recyclerViewAdapter =
                new RecyclerViewSecondAdapter(getView().getContext(),toDoItems);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
    }

    private void getTODOItems(){

        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url+userId, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int index = 0;index<response.length();index++){
                    try {
                        ToDoItem item = new ToDoItem().fromJSON(response.getJSONObject(index));
                        toDoItems.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                initRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(,"Volley error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        VolleySingleton.getInstance(Objects.requireNonNull(getView()).getContext()).addToRequestQueue(jsonArrayRequest);

    }





}
