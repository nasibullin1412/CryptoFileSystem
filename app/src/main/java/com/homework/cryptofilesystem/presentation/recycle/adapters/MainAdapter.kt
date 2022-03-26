package com.homework.cryptofilesystem.presentation.recycle.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.homework.cryptofilesystem.databinding.ChangePasswordItemBinding
import com.homework.cryptofilesystem.databinding.FilenameItemBinding
import com.homework.cryptofilesystem.domain.entity.AuthEntity
import com.homework.cryptofilesystem.presentation.main.mainitem.ChangePassItem
import com.homework.cryptofilesystem.presentation.main.mainitem.MainItem
import com.homework.cryptofilesystem.presentation.main.mainitem.MainListItem
import com.homework.cryptofilesystem.presentation.recycle.callbacks.ChatCallback
import com.homework.cryptofilesystem.presentation.recycle.viewholders.ChangePassViewHolder
import com.homework.cryptofilesystem.presentation.recycle.viewholders.MainListViewHolder
import com.homework.cryptofilesystem.presentation.recycle.viewholders.MainViewHolder

class MainAdapter(
    private val onFileItemClickAction: (Int) -> Unit,
    private val onAuthItemClick: (AuthEntity) -> Unit
) :
    ListAdapter<MainListItem, MainListViewHolder>(ChatCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (MainViewType.getChatViewTypeByInt(viewType)) {
            MainViewType.MAIN_ITEM -> MainViewHolder(
                FilenameItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            MainViewType.CHANGE_PASSWORD -> ChangePassViewHolder(
                ChangePasswordItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ChangePassViewHolder -> holder.bind(onAuthItemClick)
            is MainViewHolder -> holder.bind(item as MainItem, onFileItemClickAction)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ChangePassItem -> MainViewType.CHANGE_PASSWORD.value
            is MainItem -> MainViewType.MAIN_ITEM.value
        }
    }
}

enum class MainViewType(val value: Int) {
    CHANGE_PASSWORD(0),
    MAIN_ITEM(1);

    companion object {
        fun getChatViewTypeByInt(value: Int) = values().first { it.value == value }
    }
}
