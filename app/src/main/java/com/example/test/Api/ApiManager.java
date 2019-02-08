package com.example.test.Api;

import com.example.test.Util.Constants;

// Public Class for return Retrofit Client
public class ApiManager {

    private ApiManager() {
    }

    public static MovieApi getAPIService() {

        return RetrofitClient.getClient(Constants.URL).create(MovieApi.class);
    }
}
