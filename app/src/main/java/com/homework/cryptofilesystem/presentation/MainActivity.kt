package com.homework.cryptofilesystem.presentation

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.homework.cryptofilesystem.R
import com.homework.cryptofilesystem.presentation.utils.CustomFragmentFactory
import com.homework.cryptofilesystem.presentation.utils.FragmentTag
import com.homework.cryptofilesystem.presentation.utils.NavigateController

class MainActivity : AppCompatActivity(), NavigateController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragmentFactory = CustomFragmentFactory.create(FragmentTag.AUTH_FRAGMENT_TAG)
            addFragment(fragmentFactory)
        }
        val req_string = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(this, req_string, 1)
        ActivityCompat.requestPermissions(this, req_string, 2)
        System.loadLibrary("encryptor-native-lib")
    }

    override fun navigateFragment(customFragmentFactory: CustomFragmentFactory) {
        addFragment(customFragmentFactory)
    }
}
