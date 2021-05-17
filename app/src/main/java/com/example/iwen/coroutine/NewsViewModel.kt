package com.example.iwen.coroutine

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @author iwen大大怪
 * @Create 2021/05/17 12:16
 */
class NewsViewModel : ViewModel() {

    val news: MutableLiveData<String?> = MutableLiveData()

    init {
        news.value = null
    }

    fun loadNews() {
        viewModelScope.launch(Dispatchers.Main) {
            news.value = realLoad()
        }
    }

    suspend fun realLoad(): String? {
        return withContext(Dispatchers.IO) {
            // 开始加载数据
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://v.juhe.cn/toutiao/index?type=keji&page=&page_size=3&key=83655098a31b2917e66fe336cdafa6e6")
                .get()
                .build()
            client.newCall(request).execute().use {
                if (it.isSuccessful) {
                    // 返回成功
                    return@use it.body?.string()
                } else {
                    // 失败
                    return@use "error"
                }
            }
        }
    }
}