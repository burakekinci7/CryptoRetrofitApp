package com.flashex.cryptoretrofitspp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.flashex.cryptoretrofitspp.R;
import com.flashex.cryptoretrofitspp.adapter.RecyclerViewAdapter;
import com.flashex.cryptoretrofitspp.model.CryptoModel;
import com.flashex.cryptoretrofitspp.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://github.com/atilsamancioglu/K21-JSONDataSet/blob/master/crypto.json
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        recyclerView=findViewById(R.id.recyclerView);

        //RETROFİT & JSON
        Gson gson = new GsonBuilder().setLenient().create();

        //init
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }

    private void loadData() {
        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);
        Call<List<CryptoModel>> calls = cryptoAPI.getData();
        calls.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()) {
                    //response -> bize verilen cevap => we are get data
                    List<CryptoModel> responceList = response.body();
                    cryptoModels = new ArrayList<>(responceList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter=new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(adapter);

                    for (CryptoModel cryptoModel:cryptoModels){
                        System.out.println(cryptoModel.currency);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}