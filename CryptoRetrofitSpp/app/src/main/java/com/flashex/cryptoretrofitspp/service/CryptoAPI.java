package com.flashex.cryptoretrofitspp.service;

import com.flashex.cryptoretrofitspp.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //GET(get data -> veriyi almak), POST(write data in server -> sunucuya veri yazmak), UPDATE, DELETE
    //www.website.com/price?key=xxx
    //URL BASE -> www.website.com
    //GET ->price?key=xxx
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Call<List<CryptoModel>> getData();
}
