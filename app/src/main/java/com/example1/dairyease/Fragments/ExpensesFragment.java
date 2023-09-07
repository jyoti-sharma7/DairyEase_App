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
import android.widget.TextView;
import android.widget.Toast;

import com.example1.dairyease.ModelResponse.ExpanceBalanceResponse;
import com.example1.dairyease.ModelResponse.ExpensesResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.Recycler.ExpensesAdapter;
import com.example1.dairyease.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesFragment extends Fragment {

    RecyclerView rvExpensesDetail;
    List<ExpensesResponse.ExpensesData> expensesDataList;
    TextView balanceAMOUNT;
    //Context context;

    public ExpensesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_expenses, container, false);

        totalExpances(view);


        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        rvExpensesDetail = view.findViewById(R.id.rvExpensesDetail);
        // rvExpensesDetail.setLayoutManager(new LinearLayoutManager(requireContext()));

        Call<ExpensesResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getExpensesList("Bearer " + accessToken);

        call.enqueue(new Callback<ExpensesResponse>() {
            @Override
            public void onResponse(Call<ExpensesResponse> call, Response<ExpensesResponse> response) {
                if(response.isSuccessful()){
                    expensesDataList = response.body().getData();
                    rvExpensesDetail.setAdapter(new ExpensesAdapter(getActivity(), expensesDataList));
                    rvExpensesDetail.setLayoutManager(new LinearLayoutManager(requireContext()));
                } else {
                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ExpensesResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void totalExpances(View view) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        Call<ExpanceBalanceResponse> callE = RetrofitClient.getInstance().getApi().getExpensesBalance("Bearer " + accessToken);
        callE.enqueue(new Callback<ExpanceBalanceResponse>() {
            @Override
            public void onResponse(Call<ExpanceBalanceResponse> call, Response<ExpanceBalanceResponse> response) {
                ExpanceBalanceResponse expanceBalanceResponse = response.body();
                if(response.isSuccessful()){
                    balanceAMOUNT = view.findViewById(R.id.balanceAMOUNT);

                    balanceAMOUNT.setText(expanceBalanceResponse.getTotal_balance());
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ExpanceBalanceResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });


    }
}