package com.jayto.jakmall.view.page.list

import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jayto.jakmall.R
import com.jayto.jakmall.databinding.ItemAddMoreBinding
import com.jayto.jakmall.databinding.ItemCategoryBinding
import com.jayto.jakmall.databinding.ItemListBinding
import com.jayto.jakmall.domain.category.entity.CategoryAliases
import com.jayto.jakmall.utils.Utils.getAmount

class ListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(
        item: CategoryAliases,
        action: (Int) -> Unit,
        actionDropDown: (String, Int, Int) -> Unit,
        openDetail: (String) -> Unit
    ) {
        binding.btnGoTop.setOnClickListener {
            if (absoluteAdapterPosition == 0) return@setOnClickListener
            action(absoluteAdapterPosition)
        }
        binding.btnDropDown.setOnClickListener {
            if (binding.ctSubCategory.childCount == 0) {
                actionDropDown(item.resolved, absoluteAdapterPosition, item.getAmount())
            } else {
                binding.ctCategory.background = ContextCompat.getDrawable(
                    binding.ctCategory.context, R.drawable.bg_rectangle_white_5_stroke
                )
                binding.btnDropDown.rotation = 0F

                if (binding.ctSubCategory.childCount > 0) {
                    binding.ctSubCategory.removeAllViews()
                }
            }
        }

        binding.tvNumber.text = absoluteAdapterPosition.plus(1).toString()
        binding.tvText.text = item.alias
        binding.btnGoTop.setText(binding.btnGoTop.context.getString(
            when (absoluteAdapterPosition) {
                0 -> R.string.text_top
                else -> R.string.text_go_top
            }
        ))
        binding.btnGoTop.setEnableClick(absoluteAdapterPosition != 0)

        val subCategory = item.subCategory
        when {
            subCategory != null && subCategory.jokes.isNotEmpty() -> {
                binding.ctCategory.background = ContextCompat.getDrawable(
                    binding.ctCategory.context, R.drawable.bg_rectangle_white_stroke_top
                )

                binding.btnDropDown.rotation = 180F

                if (binding.ctSubCategory.childCount > 0) {
                    binding.ctSubCategory.removeAllViews()
                }

                subCategory.jokes.mapIndexed { idx, itemJoke ->
                    val bindingSub = ItemCategoryBinding.inflate(
                        LayoutInflater.from(binding.ctSubCategory.context),
                        binding.ctSubCategory, false
                    )

                    bindingSub.ctDesc.setOnClickListener {
                        openDetail(itemJoke.joke)
                    }

                    if (idx == subCategory.jokes.size.minus(1)) {
                        if (bindingSub.ctAdd.childCount > 0) {
                            bindingSub.ctAdd.removeAllViews()
                        }
                        val bindingSubAdd = ItemAddMoreBinding.inflate(
                            LayoutInflater.from(bindingSub.ctAdd.context), bindingSub.ctAdd, false
                        )
                        bindingSubAdd.btnAddMore.setEnableClick(subCategory.amount < 4)
                        bindingSubAdd.btnAddMore.setOnClickListener {
                            if (subCategory.amount <= 4) {
                                actionDropDown(item.resolved, absoluteAdapterPosition, item.getAmount())
                            }
                        }
                        bindingSub.ctAdd.addView(bindingSubAdd.root)
                    }

                    bindingSub.tvDesc.text = itemJoke.joke
                    binding.ctSubCategory.addView(bindingSub.root)
                }
            }
            else -> {
                if (binding.ctSubCategory.childCount > 0) {
                    binding.ctSubCategory.removeAllViews()
                }

                binding.btnDropDown.rotation = 0F

                binding.ctCategory.background = ContextCompat.getDrawable(
                    binding.ctCategory.context, R.drawable.bg_rectangle_white_5_stroke
                )
            }
        }
    }
}