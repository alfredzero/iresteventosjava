package com.app.eventosirest1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("Data/data.json")
    Call<ApiResponse> getData();
}
