package com.homework.cryptofilesystem.presentation.selectfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.cryptofilesystem.databinding.SelectFileFragmentBinding
import com.homework.cryptofilesystem.presentation.recycle.adapters.FileAdapter
import com.homework.cryptofilesystem.presentation.selectfile.baseitem.FileItem
import java.io.File

abstract class SelectFileBaseFragment : Fragment() {

    protected val binding get() = _binding!!
    private var _binding: SelectFileFragmentBinding? = null
    protected lateinit var fileAdapter: FileAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SelectFileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
        initRecycleViewImpl()

    }

    private fun initRecycleViewImpl() {
        with(binding.rvFiles) {
            fileAdapter = FileAdapter { onItemClick(it) }
            adapter = fileAdapter
            val currLayoutManager = LinearLayoutManager(context)
            layoutManager = currLayoutManager
        }
    }

    abstract fun initButton()
    abstract fun updateList(newList: List<FileItem>)
    abstract fun onItemClick(file: File)
}
