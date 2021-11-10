import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Created on : October/20/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */
object AppDependencies {
    //std lib
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    //android ui
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val materialComponent =
        "com.google.android.material:material:${Versions.materialComponent}"

    private val fragmentKtx =
    "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    private val navUiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    private val navFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    private val navSupportModule =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"

    private val testingNavModule =
        "androidx.navigation:navigation-testing:${Versions.navigation}"

    private val jetPackNavIntegration =
        "androidx.navigation:navigation-compose:2.4.0-beta02"

    private val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    private val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    private val lifecycleLivedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    private val timber =
        "com.jakewharton.timber:timber:${Versions.timber}"

    // size
    private val sdp = "com.intuit.sdp:sdp-android:${Versions.sdp}"
    private val ssp = "com.intuit.ssp:ssp-android:${Versions.ssp}"

    // activity

    private val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    // collections
    private val collectionsKts
    = "androidx.collection:collection-ktx${Versions.collectionKts}"

    // coroutine
    private val coroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    // glide
    private val glide =
        "com.github.bumptech.glide:glide:${Versions.glide}"

    // networking
    private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val retrofitConverterGsonApi = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private val retrofitConverterScalarsApi = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    private val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.1"

    // injection

    private val daggarHilt = "com.google.dagger:hilt-android:2.37"
    private val kaptDaggarHilt = "com.google.dagger:hilt-android-compiler:2.37"
    private val kaptDaggarHiltCompailer = "androidx.hilt:hilt-compiler:1.0.0"
    private val daggerViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    private val daggerWorkManager = "androidx.hilt:hilt-work:1.0.0"

    //test libs
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(kotlinReflect)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(glide)
        add(retrofit)
        add(retrofitConverterGsonApi)
        add(retrofitConverterScalarsApi)
        add(okhttpInterceptor)
        add(materialComponent)
        add(fragmentKtx)
        add(navFragmentKtx)
        add(navUiKtx)
        add(navSupportModule)
        add(testingNavModule)
        add(jetPackNavIntegration)
        add(lifecycleExtensions)
        add(lifecycleRuntimeKtx)
        add(lifecycleLivedata)
        add(timber)
        add(sdp)
        add(ssp)
        add(activityKtx)
        add(coroutine)
        add(daggarHilt)
        add(daggerViewModel)
        add(daggerWorkManager)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(kaptDaggarHilt)
        add(kaptDaggarHiltCompailer)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}