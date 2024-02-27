package com.devtides.stackoverflow.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.devtides.stackoverflow.R
import com.devtides.stackoverflow.databinding.ActivityMainBinding
import com.devtides.stackoverflow.viewmodel.QuestionsViewModel


class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding
    private val questionsAdapter = QuestionsAdapter(arrayListOf())
    private val viewModel: QuestionsViewModel by viewModels()
    private val lm = LinearLayoutManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.questionsList.apply {
            layoutManager = lm
            adapter = questionsAdapter
            addOnScrollListener(object : OnScrollListener(){

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                    if(dy>0){
                        val childCount = questionsAdapter.itemCount
                        val lastPosition = lm.findLastCompletelyVisibleItemPosition()
                        if(childCount -1 == lastPosition && binding.loadingView.visibility == View.GONE){
                            binding.loadingView.visibility = View.VISIBLE
                            viewModel.getNextPage()
                        }
                    }
                }
            })
        }

        observeViewModel()

        viewModel.getNextPage()
        binding.swipeLayout.setOnRefreshListener {
            questionsAdapter.clearQuestions()
            viewModel.getFirstPage()
            binding.loadingView.visibility = View.VISIBLE
            binding.questionsList.visibility = View.GONE
        }
    }


    private fun observeViewModel() {
        viewModel.questionsResponse.observe(this, Observer { items ->
            items?.let {
                binding.questionsList.visibility = View.VISIBLE
                binding.swipeLayout.isRefreshing = false
                questionsAdapter.addQuestions(it)
            }
        })

        viewModel.error.observe(this, Observer { errorMsg ->
            binding.listError.visibility = if (errorMsg == null) View.GONE else View.VISIBLE
            binding.listError.text = "Error\n$errorMsg"
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.questionsList.visibility = View.GONE
                }
            }
        })
    }
}
