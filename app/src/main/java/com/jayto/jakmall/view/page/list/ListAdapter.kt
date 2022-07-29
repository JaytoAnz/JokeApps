package com.jayto.jakmall.view.page.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jayto.jakmall.databinding.ItemListBinding
import com.jayto.jakmall.databinding.ItemLoadingBinding
import com.jayto.jakmall.domain.category.entity.CategoryAliases
import com.jayto.jakmall.domain.subcategory.entity.SubCategory
import java.util.*

class ListAdapter(
    val list: MutableList<CategoryAliases> = mutableListOf(),
    private val action: (Int) -> Unit,
    private val actionDropDown: (String, Int, Int) -> Unit,
    private val openDetail: (String) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val STATE_LOADING = -10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            STATE_LOADING -> LoadingViewHolder(
                ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false))
            else -> ListViewHolder(
                ItemListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        when (holder.itemViewType) {
            STATE_LOADING -> {}
            else -> (holder as ListViewHolder).bind(data, action, actionDropDown, openDetail)
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return list[position].state
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loading() {
        this.list.clear()
        this.list.add(CategoryAliases(state = STATE_LOADING))
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun hideLoading() {
        val isLoading = this.list.any { it.state == STATE_LOADING }
        if (isLoading) {
            this.list.clear()
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<CategoryAliases>) {
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun goToTop(position: Int) {
        Collections.swap(this.list, position, position.minus(1))
        notifyItemMoved(position, position.minus(1))
        notifyItemChanged(position.minus(1))
        notifyItemChanged(position)
    }

    fun setSubCategory(position: Int, subCategory: SubCategory? = null) {
        if (subCategory != null) {
            this.list[position].subCategory = subCategory
        }
        notifyItemChanged(position)
    }
}