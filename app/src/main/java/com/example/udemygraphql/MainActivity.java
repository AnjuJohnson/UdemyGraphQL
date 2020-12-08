package com.example.udemygraphql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.udemygraphql.adapter.UserAdapter;
import com.example.udemygraphql.util.AplClient;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private TextView mNameTextView;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configViews();
        getUsers();
    }

    //apollo stuff
    private void configViews() {
        progressBar = findViewById(R.id.progressbar);
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

    }

    private void getUsers() {
        AplClient.getmApolloClient()
                .query(UsersQuery.builder().build())
                .enqueue(new ApolloCall.Callback<UsersQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<UsersQuery.Data> response) {
                        String name = response.data().users().get(0).name;
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                userAdapter = new UserAdapter(response.getData().users());
                                mRecyclerView.setAdapter(userAdapter);
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
    }
}