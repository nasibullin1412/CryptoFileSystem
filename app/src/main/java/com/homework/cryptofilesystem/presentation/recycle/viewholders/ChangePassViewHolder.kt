package com.homework.cryptofilesystem.presentation.recycle.viewholders

import com.homework.cryptofilesystem.databinding.ChangePasswordItemBinding
import com.homework.cryptofilesystem.domain.entity.AuthEntity

class ChangePassViewHolder(
    viewBinding: ChangePasswordItemBinding,
) : MainListViewHolder(viewBinding.root) {

    private val changePasswordItemBinding = ChangePasswordItemBinding.bind(viewBinding.root)

    fun bind(onAuthItemClick: (AuthEntity) -> Unit) = with(changePasswordItemBinding) {
        btnLogin.setOnClickListener {
            onAuthItemClick(
                AuthEntity(
                    etUsername.text.toString(),
                    etPassword.text.toString()
                )
            )
        }
    }
}
