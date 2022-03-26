package com.homework.cryptofilesystem.presentation.main.mainitem

import java.io.File

sealed class MainListItem(var id: Int)

data class MainItem(val file: File, val idItem: Int) : MainListItem(idItem)

data class ChangePassItem(val idItem: Int) : MainListItem(idItem)
