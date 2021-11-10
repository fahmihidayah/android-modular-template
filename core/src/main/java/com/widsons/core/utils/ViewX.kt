package com.widsons.core.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created on : October/22/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */

fun Fragment.toast(message : String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message : Int) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}