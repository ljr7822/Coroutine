package com.example.iwen.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iwen.coroutine.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    // 使用viewBinding
    private lateinit var binding: ActivityMainBinding

    // 使用viewModel
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 使用viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        // 实例化ViewModel
        viewModel =  ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(NewsViewModel::class.java)

        // 监听数据
        viewModel.news.observe(this){ value ->
            if (value != null){
                binding.textView.text = value
            }
        }

        binding.button.setOnClickListener {

            viewModel.loadNews()
        }
    }
}