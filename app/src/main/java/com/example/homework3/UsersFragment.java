package com.example.homework3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.homework3.model.User;
import com.example.homework3.recycler_adapters.RecyclerViewMainAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class UsersFragment extends Fragment {

    private final static String URL = "https://jsonplaceholder.typicode.com/users";
    private ArrayList<User> usersList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUsers();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.users_recycler_view);
        RecyclerViewMainAdapter recyclerViewAdapter =
                new RecyclerViewMainAdapter(getView().getContext(), usersList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
    }

    private void getUsers() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int index = 0; index < response.length(); index++) {
                    try {
                        User user = new User().fromJSON(response.getJSONObject(index));
                        usersList.add(user);
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

        VolleySingleton.getInstance(getView().getContext()).addToRequestQueue(jsonArrayRequest);

    }
}
