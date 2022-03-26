package com.homework.cryptofilesystem.presentation.main.mainitem

import java.io.File

sealed class MainListItem(val id: Int)

data class MainItem(val file: File, val idItem: Int) : MainListItem(idItem)

data class ChangePassItem(val idItem: Int) : MainListItem(idItem)
