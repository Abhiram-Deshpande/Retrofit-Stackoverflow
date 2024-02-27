package com.devtides.stackoverflow.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverflowApi {

    @GET("/2.3/questions?order=desc&sort=activity&site=stackoverflow")
    fun getQuestions(@Query("page") page:Int): Call<ResponseWrapper<Question>>
}