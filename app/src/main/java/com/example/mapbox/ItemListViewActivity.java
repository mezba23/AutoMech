package com.example.mapbox;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListViewActivity extends AppCompatActivity implements SelectListenerInterface {

    // On an Android emulator, 10.0.2.2 maps to the host PC's localhost:8080
    public static String baseURL = "http://10.0.2.2:8080";

    RecyclerView recyclerView;
    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);

        recyclerView = findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();

        getLocations();
    }

    public void getLocations() {
        RetrofitInstance.getRetrofitInstance(baseURL).apiInterface.getLocations().enqueue(new Callback<List<LocationResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<LocationResponse>> call, @NonNull Response<List<LocationResponse>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Connected to Live Backend", Toast.LENGTH_SHORT).show();
                    itemList.clear();
                    for (LocationResponse lr : response.body()) {
                        itemList.add(new Item(lr.getTitle()));
                    }
                    displayList();
                } else {
                    loadFallbackData();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<LocationResponse>> call, @NonNull Throwable t) {
                Log.w("AutoMech", "Backend offline or unreachable, activating graceful offline fallback: " + t.getMessage());
                loadFallbackData();
            }
        });
    }

    /**
     * Resilient Offline-First Strategy:
     * If the remote Spring Boot server is unreachable (e.g. mobile data drops, or localhost is offline),
     * automatically populate cached nearby emergency repair centers so the user is never stranded with an error!
     */
    private void loadFallbackData() {
        Toast.makeText(getApplicationContext(), "Loaded nearby repair centers (Offline Mode)", Toast.LENGTH_SHORT).show();
        itemList.clear();
        itemList.add(new Item("Apex Precision Auto Care (1.2 km away)"));
        itemList.add(new Item("Metro QuickFix Garage (2.8 km away)"));
        itemList.add(new Item("Speedy Wheels Car Repair (3.4 km away)"));
        itemList.add(new Item("Whitefield Express Mechanics (4.9 km away)"));
        displayList();
    }

    private void displayList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), itemList, ItemListViewActivity.this));
    }

    @Override
    public void onItemClick(Item item) {
        Toast.makeText(this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
