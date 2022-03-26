package com.homework.cryptofilesystem.presentation.recycle.viewholders

import com.homework.cryptofilesystem.databinding.FilenameItemBinding
import com.homework.cryptofilesystem.presentation.main.mainitem.MainItem

class MainViewHolder(
    private val viewBinding: FilenameItemBinding
) : MainListViewHolder(viewBinding.root) {

    fun bind(fileItem: MainItem, onClickAction: (id: Int) -> Unit) {
        with(viewBinding) {
            tvFileName.text = fileItem.file.name
            root.setOnLongClickListener {
                onClickAction(fileItem.id)
                true
            }
        }
    }
}
