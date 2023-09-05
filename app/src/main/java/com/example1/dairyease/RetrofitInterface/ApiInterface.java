package com.example1.dairyease.RetrofitInterface;

import com.example1.dairyease.ModelResponse.CategoryResponse;
import com.example1.dairyease.ModelResponse.DashBoardResponse;
import com.example1.dairyease.ModelResponse.EventResponse;
import com.example1.dairyease.ModelResponse.ExpensesResponse;
import com.example1.dairyease.ModelResponse.ForgetPasswordResponse;
import com.example1.dairyease.ModelResponse.LoginResponse;
import com.example1.dairyease.ModelResponse.MilkResponse;
import com.example1.dairyease.ModelResponse.ProductResponse;
import com.example1.dairyease.ModelResponse.TokenOTPResponse;
import com.example1.dairyease.ModelResponse.ProfileResponse;
import com.example1.dairyease.ModelResponse.RegisterResponse;
import com.example1.dairyease.ModelResponse.NewPasswordResponse;
import com.example1.dairyease.ModelResponse.VerifyOtpResponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> register(
            @Field("name") String name,
            @Field("contact") String contact,
            @Field("address") String address,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String conformPass
    );

    @FormUrlEncoded
    @POST("verify-email")
    Call<VerifyOtpResponse> verifyemail(
            @Field("otp") String otp
    );

    @FormUrlEncoded
    //end point of url ("login")
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    //end point of url ("forget-password")
    @POST("forgot-password")
    Call<ForgetPasswordResponse> sendOtp(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("change-password")
    Call<NewPasswordResponse> resetPassword(
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Header("Authorization") String accessToken
    );

    @FormUrlEncoded
    @POST("verify-otp")
    Call<TokenOTPResponse> verifyOtp(
            @Field("otp") String otp
    );


    @GET("profile")
    Call<ProfileResponse> getUserProfile(
            @Header("Authorization") String accessToken
    );

    @Multipart
    @PUT("profile")
    Call<ResponseBody> updateProfilePicture(@Part MultipartBody.Part image);


    @GET("list")
    Call<MilkResponse> getMilkList(
            @Header("Authorization") String accessToken
    );

    @GET("list-expenses")
    Call<ExpensesResponse> getExpensesList(
            @Header("Authorization") String accessToken
    );

    @GET("list-product")
    Call<ProductResponse> getProducts(
            @Header("Authorization") String accessToken
    );

    @GET("list-event")
    Call<EventResponse> getEvents(
            @Header("Authorization") String accessToken
    );

    @GET("showDashboard")
    Call<DashBoardResponse> getDashboardData(
            @Header("Authorization") String accessToken
    );

    @GET("list-category")
    Call<CategoryResponse> getCategoryData(
            @Header("Authorization") String accessToken
    );



}
