package com.triton.dineshkumardinterviewtask.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.dineshkumardinterviewtask.R;
import com.triton.dineshkumardinterviewtask.adapter.HealthCheckUpDetailsAdapter;
import com.triton.dineshkumardinterviewtask.api.APIClient;
import com.triton.dineshkumardinterviewtask.api.RestApiInterface;
import com.triton.dineshkumardinterviewtask.responsepojo.HealthCheckResponse;
import com.triton.dineshkumardinterviewtask.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HealthCheckActivity extends AppCompatActivity {
    private String TAG = "HealthCheckActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvNoRecords)
    TextView tvNorecords;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvhealthcheck)
    RecyclerView rvhealthcheck;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;






    String type = "",name = "",userid = "";
    private String fromactivity;


    private List<HealthCheckResponse.DataBean.HealthBean.AccessibleBean> healtAccessibleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);

         healthCheckRepsoneResponseCall();

    }

    @SuppressLint("LogNotTimber")
    public void healthCheckRepsoneResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<HealthCheckResponse> call = apiInterface.healthCheckRepsoneResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<HealthCheckResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<HealthCheckResponse> call, @NonNull Response<HealthCheckResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {

                    Log.w(TAG,"healthCheckRepsoneResponseCall" + new Gson().toJson(response.body()));

                    if(response.body().getData().getHealth() != null && response.body().getData().getHealth().size()>0){
                        Log.w(TAG,"healthCheckRepsoneResponseCall list" + new Gson().toJson(response.body().getData().getHealth()));

                        for(int i=0;i<response.body().getData().getHealth().size();i++){
                            if(response.body().getData().getHealth().get(i).getName() != null && response.body().getData().getHealth().get(i).getAccessible().size()>0){
                                healtAccessibleList = response.body().getData().getHealth().get(i).getAccessible();
                            }

                        }
                        setViewHealthCheckUpDetails(response.body().getData().getHealth(),healtAccessibleList);

                    }





                }




            }


            @Override
            public void onFailure(@NonNull Call<HealthCheckResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"HealthCheckResponse flr"+t.getMessage());
            }
        });

    }

    private void setViewHealthCheckUpDetails(List<HealthCheckResponse.DataBean.HealthBean> health, List<HealthCheckResponse.DataBean.HealthBean.AccessibleBean> healtAccessibleList) {
        rvhealthcheck.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvhealthcheck.setItemAnimator(new DefaultItemAnimator());
        HealthCheckUpDetailsAdapter healthCheckUpDetailsAdapter = new HealthCheckUpDetailsAdapter(getApplicationContext(), health,healtAccessibleList,TAG);
        rvhealthcheck.setAdapter(healthCheckUpDetailsAdapter);
    }
}