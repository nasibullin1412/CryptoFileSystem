package com.homework.cryptofilesystem.presentation.recycle.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.homework.cryptofilesystem.presentation.selectfile.baseitem.FileItem

class FileCallback : DiffUtil.ItemCallback<FileItem>() {
    override fun areItemsTheSame(oldItem: FileItem, newItem: FileItem): Boolean {
        return oldItem.idItem == newItem.idItem
    }

    override fun areContentsTheSame(oldItem: FileItem, newItem: FileItem): Boolean {
        return oldItem == newItem
    }
}
