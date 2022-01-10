package com.triton.dineshkumardinterviewtask.api;

import com.triton.dineshkumardinterviewtask.responsepojo.HealthCheckResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface RestApiInterface {

    /*health screen list*/
    @GET("otp-mgmt/health-check")
    Call<HealthCheckResponse> healthCheckRepsoneResponseCall(@Header("Content-Type") String type);


}
