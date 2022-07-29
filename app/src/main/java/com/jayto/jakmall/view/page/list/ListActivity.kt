package com.jayto.jakmall.view.page.list

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jayto.jakmall.databinding.ActivityListBinding
import com.jayto.jakmall.utils.component.DialogDetails
import com.jayto.jakmall.utils.component.DialogLoading
import com.jayto.jakmall.view.base.BaseActivity
import com.jayto.jakmall.view.page.list.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : BaseActivity<ActivityListBinding, ListViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

    private val dialogLoading: DialogLoading by lazy {
        DialogLoading(this)
    }

    private val dialogDetails: DialogDetails by lazy {
        DialogDetails(this)
    }

    private val adapterList: ListAdapter by lazy {
        ListAdapter(
            action = { position -> onItemClick(position) },
            actionDropDown = { category, position, amount ->
                getJokeCategory(category, position, amount)
            },
            openDetail = { dialogDetails.show(it) }
        )
    }

    private fun getJokeCategory(category: String, position: Int, amount: Int) {
        if (adapterList.list[position].subCategory == null) {
            viewModel?.getJokeCategory(category = category, position = position, amount = amount)
        } else {
            if (adapterList.list[position].subCategory?.amount == 2) {
                viewModel?.getJokeCategory(category = category, position = position, amount = amount)
            } else {
                adapterList.setSubCategory(position)
            }
        }
    }

    private fun onItemClick(position: Int) {
        adapterList.goToTop(position)
    }

    override fun buildBinding(): ActivityListBinding {
        return ActivityListBinding.inflate(layoutInflater)
    }

    override fun buildViewModel(): ListViewModel {
        return ViewModelProvider(this)[ListViewModel::class.java]
    }

    override fun initView() {
        initListener()
        initAdapter()
    }

    private fun initListener() {
        binding.swipeRefresh.setOnRefreshListener(this)
    }

    private fun initAdapter() {
        binding.rvList.apply {
            adapter = adapterList
            layoutManager = LinearLayoutManager(this@ListActivity)
        }
    }

    override fun initStateViewModel() {
        viewModel?.state?.observe(this) {
            when {
                it.loading -> {
                    adapterList.loading()
                }
                !it.loading && it.category != null -> {
                    if (binding.swipeRefresh.isRefreshing) {
                        binding.swipeRefresh.isRefreshing = false
                    }
                    adapterList.addItems(it.category.categoryAliases)
                }
                !it.loading && !it.errMsg.isNullOrBlank() -> {
                    if (binding.swipeRefresh.isRefreshing) {
                        binding.swipeRefresh.isRefreshing = false
                    }
                    adapterList.hideLoading()
                    Toast.makeText(this, it.errMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel?.stateSub?.observe(this) {
            when {
                it.loading -> {
                    dialogLoading.start("loading..")
                }
                !it.loading && it.subCategory != null -> {
                    dialogLoading.stop()
                    when {
                        it.subCategory.error -> {
                            Toast.makeText(this, it.subCategory.message, Toast.LENGTH_SHORT).show()
                        }
                        else -> adapterList.setSubCategory(it.position, it.subCategory)
                    }
                }
                !it.loading && !it.errMsg.isNullOrBlank() -> {
                    dialogLoading.stop()
                    Toast.makeText(this, it.errMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }

        this.onRefresh()
    }

    override fun onRefresh() {
        viewModel?.getCategory()
    }
}