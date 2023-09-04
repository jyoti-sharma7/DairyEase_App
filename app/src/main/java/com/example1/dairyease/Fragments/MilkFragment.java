package com.example1.dairyease.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example1.dairyease.ModelResponse.MilkResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.Recycler.MilkAdapter;
import com.example1.dairyease.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MilkFragment extends Fragment {

    RecyclerView rvMilkDetail;
    List<MilkResponse.Data> milkResponseList;
    Context context;



    public MilkFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_milk, container, false);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        // RecyclerView
        rvMilkDetail = view.findViewById(R.id.rvMilkDetail);
        rvMilkDetail.setLayoutManager(new LinearLayoutManager(requireContext()));

        Call<MilkResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getMilkList("Bearer " + accessToken);

        call.enqueue(new Callback<MilkResponse>() {
            @Override
            public void onResponse(Call<MilkResponse> call, Response<MilkResponse> response) {
                if(response.isSuccessful()){
                    milkResponseList = response.body().getData();
                    rvMilkDetail.setAdapter(new MilkAdapter(getActivity(), milkResponseList));
                    rvMilkDetail.setLayoutManager(new LinearLayoutManager(getActivity()));
                    //MilkAdapter adapter = new MilkAdapter(requireContext(), milkResponseList);
                    //adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MilkResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });


        return view;

    }
}