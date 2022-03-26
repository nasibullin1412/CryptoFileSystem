package com.homework.cryptofilesystem.presentation.main

import com.homework.cryptofilesystem.domain.entity.AuthEntity
import com.homework.cryptofilesystem.presentation.main.mainitem.MainItem
import com.homework.cryptofilesystem.presentation.main.mainitem.MainListItem
import com.homework.cryptofilesystem.presentation.utils.CustomFragmentFactory
import com.homework.cryptofilesystem.presentation.utils.FragmentTag

class MainFragment : MainBaseFragment() {

    override fun initButton() = with(binding) {
        floatingActionButtonAddFile.setOnClickListener {
            navigateController?.navigateFragment(CustomFragmentFactory.create(FragmentTag.SELECT_FILE_FRAGMENT_TAG))
        }
    }

    override fun updateList(newList: List<MainListItem>) {
        mainAdapter.submitList(newList)
    }

    override fun clickFileItemAction(id: Int) {
        val file = mainAdapter.currentList[id]
        if (file !is MainItem) {
            return
        }
        encryptAdapter.decryptFile(file = file.file, decryptDirectory = decryptDirectory)
        val newList = mainAdapter.currentList.toMutableList()
        newList.remove(file)
        updateList(newList)
    }

    override fun clickAuthChange(authEntity: AuthEntity) {
        viewModel.changeAuth(authEntity)
    }

}