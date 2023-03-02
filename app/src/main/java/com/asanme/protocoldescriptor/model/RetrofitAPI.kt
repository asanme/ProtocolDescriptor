package com.asanme.protocoldescriptor.model

import com.asanme.protocoldescriptor.model.entity.Checklist
import com.asanme.protocoldescriptor.model.entity.Topic
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

// TODO Create APIResponse class
interface RetrofitAPI {
    // TODO UseCases
    @GET("/api/topics")
    suspend fun getTopics(): Response<List<Topic>>

    @GET("/api/topics/{id}")
    suspend fun getTopic(@Path("id") topicID: String): Response<Topic>

    @POST("/api/topics")
    suspend fun postTopic(@Body newTopic: Topic): Call<Topic>

    @PUT("/api/topics/{id}")
    suspend fun putTopic(@Path("id") topicID: String, @Body modifiedTopic: Topic): Response<Topic>

    @GET("/api/activities/{id}")
    suspend fun getActivities(@Path("id") topicId: String): Response<List<Checklist>>

    @POST("/api/activities")
    suspend fun postActivity(@Body newTopic: Topic): Call<Topic>

    @GET("/api/activities/checklists/{topicId}/{checklistId}")
    suspend fun getChecklist(
        @Path("topicId") topicId: String,
        @Path("checklistId") checklistId: String,
    ): Response<Checklist>

    @POST("/api/activities")
    suspend fun postChecklist(@Body newChecklist: Checklist): Response<Checklist>

    @PUT("/api/activities/checklists/{topicId}/{checklistId}")
    suspend fun putChecklist(
        @Path("topicId") topicId: String,
        @Path("checklistId") checklistId: String,
        @Body modifiedChecklist: Checklist
    ): Response<List<Checklist>>

    @DELETE("/api/activities/{checklistId}")
    suspend fun deleteChecklist(
        @Path("checklistId") checklistId: String
    ): Response<List<Checklist>>
}