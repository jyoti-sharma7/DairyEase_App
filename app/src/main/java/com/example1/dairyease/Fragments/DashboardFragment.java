package com.example1.dairyease.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example1.dairyease.ModelResponse.CategoryData;
import com.example1.dairyease.ModelResponse.CategoryResponse;
import com.example1.dairyease.ModelResponse.DashBoardResponse;
import com.example1.dairyease.ModelResponse.EventData;
import com.example1.dairyease.ModelResponse.EventResponse;
import com.example1.dairyease.ModelResponse.ProductList;
import com.example1.dairyease.ModelResponse.ProductResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.Recycler.CategoryAdapter;
import com.example1.dairyease.Recycler.EventAdapter;
import com.example1.dairyease.Recycler.ProductListAdapter;
import com.example1.dairyease.RetrofitClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {

    RecyclerView RecyclerProductDetail, RecyclerEvents, rvCategory;
    LinearLayoutManager layoutManager;
    ProductListAdapter productListAdapter;
    CategoryAdapter categoryAdapter;
    EventAdapter eventAdapter;
    TextView greetingTextView, greet, perliterAMOUNT, totalmilkAMOUNT, balanceAMOUNT;
    Timer timer;
    ImageView imgCUSTOMER;

    List<ProductList> productLists;
    List<EventData> eventDataList;
    List<CategoryData> categoryDataList;



    public DashboardFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Initialize your views here
        greetingTextView = view.findViewById(R.id.tvGoodMorning);
        greet = view.findViewById(R.id.greet);
        imgCUSTOMER = view.findViewById(R.id.imgCUSTOMER);


        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String Token = sharedPreferences.getString("TOKEN", "");

        Call<DashBoardResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getDashboardData("Bearer " + Token);

        call.enqueue(new Callback<DashBoardResponse>() {
            @Override
            public void onResponse(Call<DashBoardResponse> call, Response<DashBoardResponse> response) {
                DashBoardResponse dashBoardResponse = response.body();
                if (response.isSuccessful()) {
                    perliterAMOUNT = view.findViewById(R.id.perliterAMOUNT);
                    totalmilkAMOUNT = view.findViewById(R.id.totalmilkAMOUNT);
                    balanceAMOUNT = view.findViewById(R.id.balanceAMOUNT);

                    balanceAMOUNT.setText(String.valueOf(dashBoardResponse.getTotal_balance()));
                    perliterAMOUNT.setText(String.valueOf(dashBoardResponse.getPer_liter_amt()));
                    totalmilkAMOUNT.setText(dashBoardResponse.getTotal_milk());
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DashBoardResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        productRecyclerView(view);
        eventRecycler(view);
        categoryRecycle(view);

        timer = new Timer();
        long delay = 0;
        long period = 1000 * 60 * 15;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateGreetingText();
            }
        }, delay, period);

        SharedPreferences sharedPreferences1 = requireActivity().getSharedPreferences("ProfileData", Context.MODE_PRIVATE);
        String name = sharedPreferences1.getString("Name", "");
        String imageUrl = sharedPreferences1.getString("imageUrl", "");

        greet.setText(name);

        if (!imageUrl.isEmpty()) {
            Glide.with(requireActivity())
                    .load(imageUrl)
                    .into(imgCUSTOMER);
        } else {
            Log.e("DashboardFragment", "Image URL is empty or null");
        }


        return view;

    }

    private void eventRecycler(View view) {
        RecyclerEvents = view.findViewById(R.id.RecyclerEvents);
        layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclerEvents.setLayoutManager(layoutManager);
        eventDataList = new ArrayList<>();

        eventAdapter = new EventAdapter(requireContext(), eventDataList);
        RecyclerEvents.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
        populateEvent();
    }

    private void populateEvent() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN", "");

        Call<EventResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getEvents("Bearer " + accessToken);

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {
                    eventDataList.clear();
                    eventDataList.addAll(response.body().getData());
                    eventAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void categoryRecycle(View view) {
        rvCategory = view.findViewById(R.id.rvCategory);
        layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvCategory.setLayoutManager(layoutManager);
        categoryDataList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(requireContext(), categoryDataList);
        rvCategory.setAdapter(categoryAdapter);
        populateCategory();
    }

    private void populateCategory() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        Call<CategoryResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getCategoryData("Bearer " + accessToken);

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    categoryDataList.clear();
                    categoryDataList.addAll(response.body().getData());
                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateGreetingText() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                String greeting;

                if (hour >= 0 && hour < 12) {
                    greeting = "GOOD MORNING";
                } else if (hour >= 12 && hour < 18) {
                    greeting = "GOOD AFTERNOON";
                } else {
                    greeting = "GOOD EVENING";
                }

                greetingTextView.setText(greeting);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
    private void productRecyclerView(View view) {
        RecyclerProductDetail = view.findViewById(R.id.RecyclerProductDetail);
        layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclerProductDetail.setLayoutManager(layoutManager);
        productLists = new ArrayList<>();

        productListAdapter = new ProductListAdapter(requireContext(), productLists);
        RecyclerProductDetail.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
        populateProducts();
    }
    public void populateProducts() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN", "");

        Call<ProductResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getProducts("Bearer " + accessToken);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    productLists.clear();
                    productLists.addAll(response.body().getData());
                    productListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}