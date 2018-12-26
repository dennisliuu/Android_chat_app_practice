package com.example.danielgood.chatapp3.Fragments;

import com.example.danielgood.chatapp3.Notifications.MyResponse;
import com.example.danielgood.chatapp3.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface
APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAQy-tAM0:APA91bGP0XOARw1Mxek6H2y6sgL9DUbYB2CjXVo6M-bQQiO92vnPXVIIZ4M2orFX5CxtyoQ9wUHYgAWd09LaHT7xxqCIcIXj1cmeL90SG-Ji-sMeN1_142yn8rYHOoyvW4IFeF_cIld_"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
