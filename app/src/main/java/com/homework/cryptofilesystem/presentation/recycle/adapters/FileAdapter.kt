package com.homework.cryptofilesystem.presentation.recycle.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.homework.cryptofilesystem.databinding.FileItemBinding
import com.homework.cryptofilesystem.presentation.recycle.callbacks.FileCallback
import com.homework.cryptofilesystem.presentation.recycle.viewholders.DirectoryItemViewHolder
import com.homework.cryptofilesystem.presentation.recycle.viewholders.FileItemViewHolder
import com.homework.cryptofilesystem.presentation.recycle.viewholders.FileListViewHolder
import com.homework.cryptofilesystem.presentation.selectfile.baseitem.FileItem
import java.io.File

class FileAdapter(private val onItemClickAction: (file: File) -> Unit) :
    ListAdapter<FileItem, FileListViewHolder>(FileCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (FileViewType.getChatViewTypeByInt(viewType)) {
            FileViewType.FILE -> FileItemViewHolder(
                FileItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            FileViewType.DIRECTORY -> DirectoryItemViewHolder(
                FileItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: FileListViewHolder, position: Int) {
        val item = getItem(position) as FileItem
        when (holder) {
            is FileItemViewHolder -> holder.bind(item, onItemClickAction)
            is DirectoryItemViewHolder -> holder.bind(item, onItemClickAction)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).file.isFile) {
            FileViewType.FILE.value
        } else {
            FileViewType.DIRECTORY.value
        }
    }
}

enum class FileViewType(val value: Int) {
    DIRECTORY(0),
    FILE(1);

    companion object {
        fun getChatViewTypeByInt(value: Int) = values().first { it.value == value }
    }
}
