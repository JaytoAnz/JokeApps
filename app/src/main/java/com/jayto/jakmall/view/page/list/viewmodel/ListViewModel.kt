package com.jayto.jakmall.view.page.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayto.jakmall.domain.base.DomainResult
import com.jayto.jakmall.domain.category.usecase.GetCategoryUseCase
import com.jayto.jakmall.domain.subcategory.entity.GetJokeRequest
import com.jayto.jakmall.domain.subcategory.usecase.GetJokeCategoryUseCase
import com.jayto.jakmall.view.page.list.viewmodel.entity.CategoryState
import com.jayto.jakmall.view.page.list.viewmodel.entity.SubCategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getJokeCategoryUseCase: GetJokeCategoryUseCase
) : ViewModel() {

    private val _state : MutableLiveData<CategoryState> by lazy {
        MutableLiveData<CategoryState>()
    }

    val state: LiveData<CategoryState>
        get() = _state

    private val _stateSub : MutableLiveData<SubCategoryState> by lazy {
        MutableLiveData<SubCategoryState>()
    }

    val stateSub: LiveData<SubCategoryState>
        get() = _stateSub

    fun getCategory() {
        _state.value = CategoryState(loading = true)
        getCategoryUseCase(Unit).onEach {
            when(it) {
                is DomainResult.Success -> {
                    _state.value = CategoryState(loading = false, category = it.data)
                }
                is DomainResult.Error -> {
                    _state.value = CategoryState(loading = false, errMsg = it.errMsg)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getJokeCategory(category: String, position: Int, amount: Int) {
        _stateSub.value = SubCategoryState(loading = true)
        getJokeCategoryUseCase(GetJokeRequest(
            category = category, amount = amount
        )).onEach {
            when(it) {
                is DomainResult.Success -> {
                    _stateSub.value = SubCategoryState(loading = false, subCategory = it.data, position = position)
                }
                is DomainResult.Error -> {
                    _stateSub.value = SubCategoryState(loading = false, errMsg = it.errMsg)
                }
            }
        }.launchIn(viewModelScope)
    }
}