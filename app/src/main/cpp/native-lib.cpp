#include <jni.h>
#include <sys/system_properties.h>


struct prop_info {
    char name[PROP_NAME_MAX];
    unsigned volatile serial;
    char value[PROP_VALUE_MAX];
};



jclass java_ArrayList_class;
jclass java_Property_class;
jmethodID java_ArrayList_add;
jobject java_ArrayList_ret;
jmethodID java_Property_init;

void callback(const prop_info *pi, void *cookie) {
    jobject java_Property_object;
    JNIEnv *env = static_cast<JNIEnv *>(cookie);
    char name[PROP_NAME_MAX], value[PROP_VALUE_MAX];
    __system_property_read(pi, name, value);

    // Create property object
    java_Property_object = env->NewObject(java_Property_class, java_Property_init,
                                          env->NewStringUTF(name), env->NewStringUTF(value));

    // Add to list
    env->CallBooleanMethod(java_ArrayList_ret, java_ArrayList_add, java_Property_object);
    env->DeleteLocalRef(java_Property_object);
}

void fill_arraylist(JNIEnv *env) {
    // Class deceleration
    java_ArrayList_class = env->FindClass("java/util/ArrayList");
    java_Property_class = env->FindClass("com/example/owner/jniproperties/SystemProperty");

    // Methods deceleration
    jmethodID java_ArrayList_init = env->GetMethodID(java_ArrayList_class, "<init>", "()V");
    java_ArrayList_add = env->GetMethodID(java_ArrayList_class, "add", "(Ljava/lang/Object;)Z");

    java_Property_init = env->GetMethodID(java_Property_class, "<init>",
                                          "(Ljava/lang/String;Ljava/lang/String;)V");

    // Objects deceleration
    java_ArrayList_ret = env->NewObject(java_ArrayList_class, java_ArrayList_init);

    // Insert the properties into java ArrayList
    __system_property_foreach(callback, env);
}

extern "C" JNIEXPORT jobject

JNICALL
Java_com_example_owner_jniproperties_MainActivity_getPropertiesArrayList(
        JNIEnv *env,
        jobject /* this */) {
    fill_arraylist(env);

    return java_ArrayList_ret;
}


