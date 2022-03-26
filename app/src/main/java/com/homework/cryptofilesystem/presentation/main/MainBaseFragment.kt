package com.homework.cryptofilesystem.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.cryptofilesystem.databinding.CryptoFileFragmentBinding
import com.homework.cryptofilesystem.domain.entity.AuthEntity
import com.homework.cryptofilesystem.presentation.main.mainitem.ChangePassItem
import com.homework.cryptofilesystem.presentation.main.mainitem.MainItem
import com.homework.cryptofilesystem.presentation.main.mainitem.MainListItem
import com.homework.cryptofilesystem.presentation.recycle.adapters.MainAdapter
import com.homework.cryptofilesystem.presentation.selectfile.SelectFileFragment
import com.homework.cryptofilesystem.presentation.selectfile.SelectFileFragment.Companion.REQUEST_KEY
import com.homework.cryptofilesystem.presentation.showToast
import com.homework.cryptofilesystem.presentation.utils.NavigateController
import java.io.File

abstract class MainBaseFragment : Fragment() {

    protected val binding get() = _binding!!
    protected val viewModel: MainViewModel by viewModels()
    private var _binding: CryptoFileFragmentBinding? = null
    protected lateinit var mainAdapter: MainAdapter
    internal var navigateController: NavigateController? = null
    protected var cryptDirectory = ""
    protected var decryptDirectory = ""
    protected val encryptAdapter: EncryptAdapter = EncryptAdapter()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigateController) {
            navigateController = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CryptoFileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
        initRecycleViewImpl()
        initResultListener()
        initFileList()
    }

    private fun initRecycleViewImpl() {
        with(binding.rvMessage) {
            mainAdapter = MainAdapter({ clickFileItemAction(it) }, { clickAuthChange(it) })
            adapter = mainAdapter
            val currLayoutManager = LinearLayoutManager(context)
            layoutManager = currLayoutManager
        }
        updateList(listOf(ChangePassItem(0)))

    }

    override fun onDetach() {
        super.onDetach()
        navigateController = null
    }

    abstract fun initButton()
    abstract fun updateList(newList: List<MainListItem>)
    abstract fun clickFileItemAction(id: Int)
    abstract fun clickAuthChange(authEntity: AuthEntity)

    private fun initFileList() {
        cryptDirectory = requireActivity().getExternalFilesDir(null)?.path + "/crypt/"
        decryptDirectory = requireActivity().getExternalFilesDir(null)?.path + "/decrypt/"
        encryptAdapter.init()
    }

    private fun initResultListener() {
        setFragmentResultListener(SelectFileFragment.FRAGMENT_RESULT) { _, bundle ->
            val result: File = bundle.getSerializable(REQUEST_KEY) as File
            showToast(result.name)
            addToResult(result)
        }
    }

    private fun addToResult(file: File) {
        val res: Pair<Int, String> = encryptAdapter.encryptFile(file, cryptDirectory)
        if (res.first == 0) {
            addToList(File(res.second))
            return
        }
        with(AlertDialog.Builder(requireActivity())) {
            setMessage("error code $res")
            setPositiveButton("OK", null)
            setCancelable(true)
            create().show()
        }

    }

    private fun addToList(file: File) {
        val currentList = mainAdapter.currentList.toMutableList()
        currentList.add(MainItem(file, mainAdapter.currentList.lastIndex + 1))
        updateList(currentList)
    }
}
