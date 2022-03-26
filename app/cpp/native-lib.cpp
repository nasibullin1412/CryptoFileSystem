#include <jni.h>
#include <string>

#include "encryptor/encryptor.h"

extern "C"
{


JNIEXPORT jint JNICALL
Java_com_homework_cryptofilesystem_presentation_main_EncryptAdapter_encryptorInit(JNIEnv *env, jobject thiz) {
    return encryptor_init();
}

JNIEXPORT void JNICALL
Java_com_example_cryptapp_MainActivity_encryptor_1free(JNIEnv *env, jobject thiz) {
    encryptor_free();
}

JNIEXPORT jint JNICALL
Java_com_homework_cryptofilesystem_presentation_main_EncryptAdapter_encryptorEncryptFile(JNIEnv *env, jobject thiz,
                                                            jstring filename, jstring new_filename,
                                                            jstring iv) {
    return encryptor_encrypt_file(
            env->GetStringUTFChars(filename, JNI_FALSE),
            env->GetStringUTFChars(new_filename, JNI_FALSE),
            env->GetStringUTFChars(iv, JNI_FALSE));
}

JNIEXPORT jint JNICALL
Java_com_homework_cryptofilesystem_presentation_main_EncryptAdapter_encryptorDecryptFile(JNIEnv *env, jobject thiz,
                                                            jstring filename, jstring new_filename,
                                                            jstring iv) {
    return encryptor_decrypt_file(
            env->GetStringUTFChars(filename, JNI_FALSE),
            env->GetStringUTFChars(new_filename, JNI_FALSE),
            env->GetStringUTFChars(iv, JNI_FALSE));
}


}
