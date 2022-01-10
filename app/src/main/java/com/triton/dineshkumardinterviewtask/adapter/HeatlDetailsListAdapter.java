package com.triton.dineshkumardinterviewtask.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.triton.dineshkumardinterviewtask.R;
import com.triton.dineshkumardinterviewtask.responsepojo.HealthCheckResponse;

import java.util.List;


public class HeatlDetailsListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetShopProductDetailsImageAdapter";
    private Context context;

    List<HealthCheckResponse.DataBean.HealthBean.AccessibleBean> accessible;
    HealthCheckResponse.DataBean.HealthBean.AccessibleBean currentItem;
    String fromactivity;




    public HeatlDetailsListAdapter(Context context, List<HealthCheckResponse.DataBean.HealthBean.AccessibleBean> accessible, String fromactivity) {
        this.context = context;
        this.accessible = accessible;
        this.fromactivity = fromactivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_health_access, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);
    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        HealthCheckResponse.DataBean.HealthBean.AccessibleBean accessibleBean = accessible.get(position);

        Log.w(TAG,"fromactivity : "+fromactivity);
        if(accessibleBean.isSuccess()){
            holder.txt_health_status.setTextColor(ContextCompat.getColor(context, R.color.black));
        }else{
            holder.txt_health_status.setTextColor(ContextCompat.getColor(context, R.color.teal_200));


        }
        holder.txt_products_title.setText(accessibleBean.getType());
        holder.txt_health_status.setText(""+accessibleBean.isSuccess());

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }

    @Override
    public int getItemCount() {

        return accessible.size();

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_products_title,txt_health_status;
        LinearLayout ll_root;


        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_products_title = itemView.findViewById(R.id.txt_products_title);
            txt_health_status = itemView.findViewById(R.id.txt_health_status);
            ll_root = itemView.findViewById(R.id.ll_root);


        }




    }


}
