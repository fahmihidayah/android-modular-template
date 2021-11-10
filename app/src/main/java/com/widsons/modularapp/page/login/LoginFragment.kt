package com.widsons.modularapp.page.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.widsons.modularapp.databinding.LoginFragmentBinding

/**
 * Created on : November/10/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */
class LoginFragment : Fragment() {

    lateinit var viewBinding : LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = LoginFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }


}