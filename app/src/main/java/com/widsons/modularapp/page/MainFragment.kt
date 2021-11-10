package com.widsons.modularapp.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.widsons.core.ui.fragment.BaseFragment
import com.widsons.core.ui.fragment.ViewModelFragment

/**
 * Created on : October/22/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */
class MainFragment : ViewModelFragment<MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
}