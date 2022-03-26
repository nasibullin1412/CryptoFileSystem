package com.homework.cryptofilesystem.presentation.utils

import androidx.fragment.app.Fragment
import com.homework.cryptofilesystem.presentation.authorization.AuthFragment
import com.homework.cryptofilesystem.presentation.main.MainFragment
import com.homework.cryptofilesystem.presentation.selectfile.SelectFileFragment

class CustomFragmentFactory(var fragment: Fragment, var fragmentTag: FragmentTag) {
    companion object {
        fun create(fragmentTag: FragmentTag): CustomFragmentFactory {
            val fragment: Fragment = when (fragmentTag) {
                FragmentTag.MAIN_FRAGMENT_TAG -> MainFragment()
                FragmentTag.AUTH_FRAGMENT_TAG -> AuthFragment()
                FragmentTag.SELECT_FILE_FRAGMENT_TAG -> SelectFileFragment()
            }
            return CustomFragmentFactory(fragment, fragmentTag)
        }
    }
}
