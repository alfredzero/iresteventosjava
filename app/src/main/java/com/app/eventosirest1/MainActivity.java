package com.app.eventosirest1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements EventosRecyclerAdapter.OnCardClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private List<ApiResponse.Evento> eventoList = new ArrayList<>();
    private EventosRecyclerAdapter adapter;
    private LinearProgressIndicator progressIndicator;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        recyclerView = findViewById(R.id.eventos_recycle_view);
        progressIndicator = findViewById(R.id.progress_bar);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout); // Initialize swipeRefreshLayout here

        // Setup RecyclerView
        setupRecyclerView();

        // Setup SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this::refreshData);

        // Handle Window Insets for padding around system bars
        ViewCompat.setOnApplyWindowInsetsListener(recyclerView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initial data load
        loadData();
    }

    private void loadData() {
        // Show progress indicator
        progressIndicator.show();

        // Initialize Retrofit API call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://alfredzero.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Make API Call
        Call<ApiResponse> call = apiService.getData();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                // Hide progress indicator and swipe refresh indicator
                progressIndicator.hide();
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "API Response: " + response.body().toString());

                    // Update event list and notify adapter
                    eventoList.clear(); // Clear existing data to prevent duplication
                    eventoList.addAll(response.body().getEventos().getEventos());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "API Response Error: " + response.code() + ", " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Hide progress indicator and swipe refresh indicator
                progressIndicator.hide();
                swipeRefreshLayout.setRefreshing(false);
                Log.e(TAG, "API Call Failure: ", t);
            }
        });
    }

    private void refreshData() {
        // Refresh the data
        loadData();
    }

    @Override
    public void onCardClick(String urlToRegister) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("URL", urlToRegister);
        startActivity(intent);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventosRecyclerAdapter(eventoList, this);
        recyclerView.setAdapter(adapter);
    }
}
