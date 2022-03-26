package com.homework.cryptofilesystem.presentation.selectfile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import com.homework.cryptofilesystem.presentation.selectfile.baseitem.FileItem
import java.io.File

class SelectFileFragment : SelectFileBaseFragment() {

    private var rootDir = "/sdcard/"
    private var currentDir = rootDir

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFiles(currentDir)
    }

    override fun initButton() = with(binding) {

    }

    override fun updateList(newList: List<FileItem>) {
        fileAdapter.submitList(newList)
    }

    override fun onItemClick(file: File) {
        if (file.isFile) {
            val bundle = Bundle().apply { putSerializable(REQUEST_KEY, file) }
            setFragmentResult(FRAGMENT_RESULT, bundle)
            requireActivity().onBackPressed()
            return
        }
        currentDir = if (file.name == "..") {
            File(currentDir).parent ?: rootDir
        } else {
            currentDir + "/" + file.name
        }
        loadFiles(currentDir)
    }

    private fun loadFiles(path: String) {
        fileAdapter.submitList(emptyList())
        val files: MutableList<FileItem> = mutableListOf()
        if (path != rootDir) {
            files.add(FileItem(file = File(".."), idItem = 0))
        }
        val directory = File(path)
        val filesDir = directory.listFiles()
        if (filesDir != null) {
            files.addAll(filesDir.mapIndexed { idx, item ->
                FileItem(
                    idItem = idx + 1,
                    file = item
                )
            })
        }
        fileAdapter.submitList(files)
    }

    companion object {
        const val FRAGMENT_RESULT = "fileResult"
        const val REQUEST_KEY = "fileKey"
    }
}