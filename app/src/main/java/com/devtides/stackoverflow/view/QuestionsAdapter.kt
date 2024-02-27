package com.devtides.stackoverflow.view

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devtides.stackoverflow.R
import com.devtides.stackoverflow.databinding.QuestionLayoutBinding
import com.devtides.stackoverflow.model.Question
import com.devtides.stackoverflow.model.convertTitle
import com.devtides.stackoverflow.model.getDate
import java.util.Date

class QuestionsAdapter(val questions: ArrayList<Question>): RecyclerView.Adapter<QuestionsAdapter.AdapterViewHolder>() {

    fun addQuestions(newQuestions: List<Question>) {
        val currentLength = questions.size
        questions.addAll(newQuestions)
        notifyItemInserted(currentLength)
    }

    fun clearQuestions() {
        questions.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AdapterViewHolder(
            QuestionLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    class AdapterViewHolder(binding: QuestionLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val title = binding.itemTitle
        val score = binding.itemScore
        val date =  binding.itemDate

        fun bind(question: Question) {
            title.text = convertTitle(question.title)
            score.text = question.score
            date.text = getDate(question.date)
        }
    }


}