package com.homework.cryptofilesystem.presentation.authorization

import com.homework.cryptofilesystem.domain.entity.AuthEntity
import com.homework.cryptofilesystem.presentation.showToast
import com.homework.cryptofilesystem.presentation.utils.CustomFragmentFactory
import com.homework.cryptofilesystem.presentation.utils.FragmentTag

class AuthFragment : AuthBaseFragment() {
    override fun authAction(): Unit = with(binding) {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        viewModel.doAuth(AuthEntity(username = username, password = password))
    }

    override fun initObservers() {
        viewModel.authViewState.observe(viewLifecycleOwner, { screenEffects(it) })
    }

    override fun screenEffects(viewState: AuthViewState) {
        when (viewState) {
            is AuthViewState.ErrorAuth -> {
                showToast(viewState.throwable.message)
            }
            is AuthViewState.SuccessAuth -> {
                navigateController?.navigateFragment(
                    CustomFragmentFactory.create(FragmentTag.MAIN_FRAGMENT_TAG)
                )
            }
        }
    }
}
