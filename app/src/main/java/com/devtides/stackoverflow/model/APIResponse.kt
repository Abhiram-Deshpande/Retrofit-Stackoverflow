package com.devtides.stackoverflow.model

import android.text.Html
import android.text.format.DateFormat
import com.google.gson.annotations.SerializedName
import java.util.Calendar

data class ResponseWrapper<T>(
    val items:List<T>
)

data class Question(

    @SerializedName("question_id")
    val questionId:Int?,

    val title:String?,
    val score:String?,

    @SerializedName("creation_date")
    val date:Long

)

fun convertTitle(title:String?) =
    Html.fromHtml(title,Html.FROM_HTML_MODE_LEGACY)

fun getDate(timestamp:Long?):String{

    var time = ""
    timestamp?.let {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timestamp * 1000
        time= DateFormat.format("dd-MM-yyyy hh:mm:ss",cal).toString()
    }
    return time
}