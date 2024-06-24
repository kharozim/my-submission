package com.ozimos.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ozimos.myapplication.R
import com.ozimos.myapplication.data.remote.DataResponse
import com.ozimos.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewmodel : DataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getData()
        setObserver()
    }

    private fun setObserver() {
        viewmodel.datas.observe(this){
            when(it){
                is StateUtil.Loading -> {
                    showToast("loading")
                }
                is StateUtil.Error -> {
                    showToast("error : ${it.message}")
                }
                is StateUtil.Success -> {
                    showToast("success : ${it.data?.size}")
                    showData(it.data)
                }
            }
        }
    }

    private fun showData(data: List<DataResponse>?) {
        val adapter = DataAdapter()
        adapter.submitList(data)
        binding.rvMain.adapter = adapter
    }

    private fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun getData() {
       viewmodel.getDataList()
    }
}