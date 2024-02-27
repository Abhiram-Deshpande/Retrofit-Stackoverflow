package com.devtides.stackoverflow.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devtides.stackoverflow.R
import com.devtides.stackoverflow.model.Question

class DetailsActivity : AppCompatActivity() {

    companion object{
        val PARAM_QUESTION = "params_question"

        fun getIntent(context: Context, question: Question) =
//            Intent(context,DetailsActivity::class.java).putExtra(PARAM_QUESTION,question)

            //TODO : Make Question class parcelable
    }

    var question:Question?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

       // question = intent.extras?.getParcelable(PARAM_QUESTION)

        if(question==null){
            finish()
        }
    }
}