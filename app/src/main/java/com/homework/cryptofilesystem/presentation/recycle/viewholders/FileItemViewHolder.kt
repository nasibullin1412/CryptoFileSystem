package com.homework.cryptofilesystem.presentation.recycle.viewholders

import com.homework.cryptofilesystem.R
import com.homework.cryptofilesystem.databinding.FileItemBinding
import com.homework.cryptofilesystem.presentation.selectfile.baseitem.FileItem
import java.io.File

class FileItemViewHolder(
    private val viewBinding: FileItemBinding
) : FileListViewHolder(viewBinding.root) {

    fun bind(fileItem: FileItem, onClickAction: (file: File) -> Unit) {
        with(viewBinding) {
            textViewFileName.text = fileItem.file.name
            imageViewFileIcon.setImageResource(R.drawable.ic_baseline_file_24)
            root.setOnClickListener { onClickAction(fileItem.file) }
        }
    }
}
