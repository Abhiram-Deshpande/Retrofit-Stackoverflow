package com.devtides.stackoverflow.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devtides.stackoverflow.model.Question
import com.devtides.stackoverflow.model.ResponseWrapper
import com.devtides.stackoverflow.model.StackoverflowService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsViewModel : ViewModel() {

    var page =0;
    val questionsResponse = MutableLiveData<List<Question>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    private fun getQuestions() {
        StackoverflowService.api.getQuestions(page)
            .enqueue(object : Callback<ResponseWrapper<Question>>{

                override fun onResponse(
                    call: Call<ResponseWrapper<Question>>,
                    response: Response<ResponseWrapper<Question>>
                ) {

                    if(response.isSuccessful){
                        val questions = response.body()
                        questions ?.let {
                            questionsResponse.value = questions.items
                            loading.value = false
                            error.value = null
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseWrapper<Question>>, t: Throwable) {
                    onError(t.localizedMessage)
                }
            })
    }

    fun getFirstPage(){
        page =1
        getQuestions()
    }
    private fun onError(message: String) {
        error.value = message
        loading.value = false
    }

    fun getNextPage(){
        page++
        getQuestions()
    }
}