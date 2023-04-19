#include <jni.h>
#include <string>

extern "C"
JNIEXPORT void JNICALL
Java_lu_uni_graux_bm20_MainActivity_returnUsingThrow(JNIEnv *env, jobject thisObj) {
    jclass cid = env->GetObjectClass(thisObj);
    jfieldID fidImei = env->GetFieldID(cid, "imei", "Ljava/lang/String;");
    jstring imei_str = (jstring) env->GetObjectField(thisObj, fidImei);

    if(!env->ExceptionCheck()) {
        jclass exception = env->FindClass("java/lang/Exception");
        env->ThrowNew(exception, env->GetStringUTFChars(imei_str, NULL));
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_lu_uni_graux_bm20_MainActivity_postponeException(JNIEnv *env, jobject thisObj) {
    jclass cid = env->GetObjectClass(thisObj);
    jmethodID returnImeiUsing = env->GetMethodID(cid, "returnImeiUsing", "()V");
    env->CallVoidMethod(thisObj, returnImeiUsing);

    jthrowable exc = env->ExceptionOccurred();
    if(exc != NULL) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        env->Throw(exc);
    }
}