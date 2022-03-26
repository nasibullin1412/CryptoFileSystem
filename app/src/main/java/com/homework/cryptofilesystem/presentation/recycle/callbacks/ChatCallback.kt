package com.homework.cryptofilesystem.presentation.recycle.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.homework.cryptofilesystem.presentation.main.mainitem.ChangePassItem
import com.homework.cryptofilesystem.presentation.main.mainitem.MainItem
import com.homework.cryptofilesystem.presentation.main.mainitem.MainListItem

class ChatCallback : DiffUtil.ItemCallback<MainListItem>() {
    override fun areItemsTheSame(oldItem: MainListItem, newItem: MainListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MainListItem, newItem: MainListItem): Boolean {
        if (oldItem is ChangePassItem && newItem is ChangePassItem) {
            return oldItem == newItem
        }
        if (oldItem is MainItem && newItem is MainItem) {
            return oldItem == newItem
        }
        return false
    }
}
