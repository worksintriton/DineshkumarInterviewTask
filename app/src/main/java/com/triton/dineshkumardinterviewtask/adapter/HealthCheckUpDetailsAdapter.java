package com.triton.dineshkumardinterviewtask.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.dineshkumardinterviewtask.R;
import com.triton.dineshkumardinterviewtask.responsepojo.HealthCheckResponse;

import java.util.List;


public class HealthCheckUpDetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetShopProductDetailsAdapter";
    private Context context;


    HealthCheckResponse.DataBean.HealthBean currentItem;
    List<HealthCheckResponse.DataBean.HealthBean> health;
    List<HealthCheckResponse.DataBean.HealthBean.AccessibleBean> healtAccessibleList;
    String fromactivity;



    public HealthCheckUpDetailsAdapter(Context applicationContext, List<HealthCheckResponse.DataBean.HealthBean> health, List<HealthCheckResponse.DataBean.HealthBean.AccessibleBean> healtAccessibleList, String tag) {
        this.context = applicationContext;
        this.health = health;
        this.healtAccessibleList = healtAccessibleList;
        this.fromactivity = tag;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_health_details, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);
    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        Log.w(TAG,"fromactivity : "+fromactivity);


        currentItem = health.get(position);
        if(health.get(position).getAccessible() != null && health.get(position).getAccessible().size()>0){
            holder.txt_category_title.setVisibility(View.VISIBLE);
            holder.rv_productdetails.setVisibility(View.VISIBLE);
            holder.txt_category_title.setText(currentItem.getName());
        }else{
            holder.txt_category_title.setVisibility(View.GONE);
            holder.rv_productdetails.setVisibility(View.GONE);
        }
        holder.rv_productdetails.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.rv_productdetails.setItemAnimator(new DefaultItemAnimator());
        HeatlDetailsListAdapter heatlDetailsListAdapter = new HeatlDetailsListAdapter(context,health.get(position).getAccessible(),fromactivity);
        holder.rv_productdetails.setAdapter(heatlDetailsListAdapter);
    }

    @Override
    public int getItemCount() {
        return health.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_category_title;
        RecyclerView rv_productdetails;


        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_category_title = itemView.findViewById(R.id.txt_category_title);
            rv_productdetails = itemView.findViewById(R.id.rv_productdetails);

        }




    }


}
