package com.homework.cryptofilesystem.presentation.main

import android.util.Log
import android.util.Pair
import java.io.File
import java.io.IOException

class EncryptAdapter {
    private external fun encryptorInit(): Int
    private external fun encryptorEncryptFile(
        filename: String,
        newFilename: String,
        iv: String
    ): Int

    private external fun encryptorDecryptFile(
        filename: String,
        newFilename: String,
        iv: String
    ): Int


    fun init(): Int = encryptorInit()

    fun encryptFile(
        file: File,
        cryptDirectory: String,
        encryptedTag: String = "_crptd"
    ): Pair<Int, String> {
        val fileToEncrypt: String = file.absolutePath

        val encFile = File(cryptDirectory, file.name + encryptedTag)
        Log.d("file", fileToEncrypt)
        Log.d("file", encFile.toString())
        val encryptedFile = encFile.absolutePath
        var bRes = false
        val crypt = encFile.parentFile
        if (!crypt.exists()) {
            val fl = crypt.mkdir()
            Log.d(" crypt dir", fl.toString())
        }
        try {
            bRes = encFile.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Log.d("dir", "is maked file? $bRes")
        val res: Int = encryptorEncryptFile(fileToEncrypt, encryptedFile, file.name)

        return Pair(res, encryptedFile)
    }

    fun decryptFile(file: File, decryptDirectory: String, encryptedTag: String = "_crptd"): Int {
        val fileToDecrypt: String = file.absolutePath
        val decryptedFile = File(decryptDirectory, file.name.replace(encryptedTag, ""))
        val decrypt = decryptedFile.parentFile

        if (!decrypt.exists()) {
            val fl = decrypt.mkdir()
            Log.d(" crypt dir", fl.toString())
        }

        try {
            decryptedFile.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("dir", "decrypted file is not created")
        }

        return encryptorDecryptFile(fileToDecrypt, decryptedFile.path, file.name)
    }
}
