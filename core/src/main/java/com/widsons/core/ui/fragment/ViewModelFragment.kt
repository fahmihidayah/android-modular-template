package com.widsons.core.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.widsons.core.utils.toast
import com.widsons.core.ui.BaseViewModel

/**
 * Created on : October/22/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */
abstract class ViewModelFragment< VM : BaseViewModel> : BaseFragment(){
    abstract val viewModel : VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLivedataObserver(viewModel)
    }

    open fun onShowMessage(message : Any) {
        if(message is Int) {
            toast(message)
        }
        else if(message is String) {
            toast(message)
        }
    }

    open fun onLoading(isLoading : Boolean) {

    }

    open fun finish() {
        parentFragmentManager.popBackStack()
    }

    open fun getContentView() : View? {
        return null
    }

    open fun getErrorView() : View? {
        return null
    }

    open fun initLivedataObserver(_viewModel : BaseViewModel) {
        _viewModel.errorMessage.observe(this) {

        }

        _viewModel.errorPageLiveData.observe(this) {
            if(it) {
                getContentView()?.visibility = View.GONE
                getErrorView()?.visibility = View.VISIBLE
            }
            else {
                getContentView()?.visibility = View.VISIBLE
                getErrorView()?.visibility = View.GONE
            }
        }

        _viewModel.isLoading.observe(this) {
            onLoading(it)
        }

        _viewModel.messageLiveData.observe(this) {
            onShowMessage(it)
        }

        _viewModel.finishLiveData.observe(this) {
            if(it) {
                finish()
            }
        }
    }
}