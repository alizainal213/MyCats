package com.example.mycats;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mycats.retrofit.ApiCat;
import com.example.mycats.retrofit.ApiServiceCat;
import com.example.mycats.retrofit.DataCat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ProgressDialog dialog;
    private AdapterCat adapter;
    private RecyclerView listCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Load Data Cat....");
        dialog.setTitle("Loading..");
        dialog.show();

        DataCat dataCat = ApiCat.getRetrofit().create(DataCat.class);
        Call<List<ApiServiceCat>> callCat = dataCat.getAllInfo();
        callCat.enqueue(new Callback<List<ApiServiceCat>>() {
            @Override
            public void onResponse(Call<List<ApiServiceCat>> call, Response<List<ApiServiceCat>> response) {
                dialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ApiServiceCat>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Goblok Kau...Coba Lagi Dong!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateDataList(List<ApiServiceCat> body) {
        listCat = findViewById(R.id.listCat);
        adapter = new AdapterCat(this, body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        listCat.setLayoutManager(layoutManager);
        listCat.setAdapter(adapter);
    }

}
