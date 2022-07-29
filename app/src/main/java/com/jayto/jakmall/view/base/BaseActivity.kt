package com.jayto.jakmall.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V: ViewBinding, VM: ViewModel> : AppCompatActivity() {

    val binding by lazy {
        buildBinding()
    }

    val viewModel by lazy {
        buildViewModel()
    }

    abstract fun buildBinding() : V
    abstract fun buildViewModel() : VM?
    abstract fun initView()
    abstract fun initStateViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initStateViewModel()
    }
}