package com.asanme.protocoldescriptor.model

import com.asanme.protocoldescriptor.model.entity.Topic
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitAPI {
    @POST("/api/topics")
    suspend fun postTopic(@Body newTopic: Topic): Call<Topic>

    @PUT("/api/topics/{id}")
    suspend fun putTopic(@Path("id") topicID: String, @Body newTopic: Topic): Response<Topic>

    @GET("/api/topics")
    suspend fun getTopics(): Response<List<Topic>>
}