package com.flashex.cryptoretrofitspp.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {
    @SerializedName("currency")
    public String currency;

    @SerializedName("price")
    public String price;
}
