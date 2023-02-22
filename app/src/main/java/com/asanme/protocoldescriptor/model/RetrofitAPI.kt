package com.asanme.protocoldescriptor.model

import com.asanme.protocoldescriptor.model.entity.Activity
import com.asanme.protocoldescriptor.model.entity.Checklist
import com.asanme.protocoldescriptor.model.entity.Topic
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitAPI {
    @POST("/api/topics")
    suspend fun postTopic(@Body newTopic: Topic): Call<Topic>

    @PUT("/api/topics/{id}")
    suspend fun putTopic(@Path("id") topicID: String, @Body modifiedTopic: Topic): Response<Topic>

    @GET("/api/topics")
    suspend fun getTopics(): Response<List<Topic>>

    @GET("/api/topics/{id}")
    suspend fun getTopic(@Path("id") topicID: String): Response<Topic>

    @POST("/api/activities")
    suspend fun postActivity(@Body newTopic: Topic): Call<Topic>

    @PUT("/api/activities/{id}")
    suspend fun putActivity(@Path("id") topicID: String, @Body modifiedActivity: Topic): Response<Topic>

    @GET("/api/activities/{id}")
    suspend fun getActivities(@Path("id") topicId: String): Response<List<Checklist>>

    @POST("/api/activities")
    suspend fun postChecklist(@Body newChecklist: Checklist): Call<Checklist>

    @GET("/api/activities/{topicId}/{checklistId}")
    suspend fun getChecklist(@Path("topicId") topicId: String, @Path("checklistId") checklistId: String): Response<Checklist>
}