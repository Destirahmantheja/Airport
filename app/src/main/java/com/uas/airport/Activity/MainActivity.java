package com.uas.airport.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uas.airport.API.APIRequestData;
import com.uas.airport.API.RetroServer;
import com.uas.airport.Adapter.AdapterAirport;
import com.uas.airport.Model.ModelAirport;
import com.uas.airport.Model.ModelResponse;
import com.uas.airport.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvAirport;
    private FloatingActionButton fabTambah;
    private ProgressBar pbAirport;
    private RecyclerView.Adapter adAirport;
    private RecyclerView.LayoutManager lmAirport;
    private List<ModelAirport> listAirport = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAirport = findViewById(R.id.rv_airport);
        fabTambah = findViewById(R.id.fab_tambah);
        pbAirport = findViewById(R.id.pb_airport);

        lmAirport = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAirport.setLayoutManager(lmAirport);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    protected void onResume() {
        super.onResume();
        retrieveAirport();
    }

    public void retrieveAirport() {
        pbAirport.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        retrofit2.Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listAirport = response.body().getData();

                adAirport = new AdapterAirport(MainActivity.this, listAirport);
                rvAirport.setAdapter(adAirport);
                adAirport.notifyDataSetChanged();

                pbAirport.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                
            }
        });
    }
}

