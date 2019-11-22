#include <jni.h>
#include <string>

extern "C" {
//引入bspatch.c里的main方法
extern int main(int argc, char *argv[]);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_ccl_bspatch_XBspatchUtil_bsPatch(JNIEnv *env, jclass clazz, jstring old_apk_path,
                                          jstring patch_path, jstring new_apk_patch) {
    const char *oldapk = env->GetStringUTFChars(old_apk_path, NULL);
    const char *patch = env->GetStringUTFChars(patch_path, NULL);
    const char *output = env->GetStringUTFChars(new_apk_patch, NULL);

    int argc = 4;
    char *argv[4] = {"bspatch", const_cast<char *>(oldapk), const_cast<char *>(output),
                     const_cast<char *>(patch)};
    main(argc, argv);

    env->ReleaseStringUTFChars(old_apk_path,oldapk);
    env->ReleaseStringUTFChars(patch_path,patch);
    env->ReleaseStringUTFChars(new_apk_patch,output);

}