package com.widsons.validator.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.widsons.validator.custom.IViewValidate
import com.widsons.validator.data.ValidationResult

/**
 * Created on : June/24/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */



fun List<ValidationResult>.filterVisibleValidationResult(view: ViewGroup): List<ValidationResult> {
    return this.filter { if (it.targetId != -1) view.findViewById<View>(it.targetId).visibility == View.VISIBLE else true }
}


fun Any.initialEditFocusValidationListener(fragment: Fragment) {
    (fragment.view as ViewGroup?)?.let {
        initialEditFocusValidationListener(it)
    }
}

fun Any.initialEditFocusValidationListener(view: ViewGroup) {
    getFields().forEach { field ->

        field.getTargetView()?.let {
            if(it.targetId != -1) {
                view.findViewById<View>(it.targetId)?.let {
                    if(it is TextView) {

                        it.onFocusChangeListener = object : View.OnFocusChangeListener {
                            override fun onFocusChange(p0: View?, p1: Boolean) {
                                this@initialEditFocusValidationListener.onFocusChangeListener(view, p1, field.name)
                            }
                        }
                    }
                    else if(it is TextInputLayout) {
                        it.editText?.onFocusChangeListener = object : View.OnFocusChangeListener {
                            override fun onFocusChange(p0: View?, p1: Boolean) {
                                this@initialEditFocusValidationListener.onFocusChangeListener(view, p1, field.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Any.onFocusChangeListener(fragment: Fragment, isFocus : Boolean, fieldName: String) {
    println("field name is ${fieldName}")
    onFocusChangeListener(fragment.requireView() as ViewGroup, isFocus = isFocus, fieldName)
}


fun Any.onFocusChangeListener(view: ViewGroup, isFocus : Boolean, fieldName: String) {
    if(isFocus) {
        this.clearError(view, fieldName)
    }
    else {
        this.getField(fieldName)?.validateField(this)?.showResult(view)
    }
}

fun Any.validateObjectAndShowResult(
    fragment: Fragment,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    val fragmentView = fragment.view
    if (fragmentView != null) {
        validate().showResult(fragmentView as ViewGroup, onResultValidationListener)
    }
}

fun Any.clearError(fragment: Fragment, fieldName: String? = null) {
    (fragment.view as ViewGroup?)?.let {
        this.clearError(it, fieldName)
    }
}

fun Any.clearError(viewGroup : ViewGroup, fieldName : String? = null) {
    if(fieldName != null) {
        getField(fieldName)?.getCorrectValidationResult()?.showResult(viewGroup)
    }
    else {
       this.validateWithCorrectResult().showResult(viewGroup)
    }
}

fun Any.validateObjectAndShowResult(
    view: ViewGroup,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    validate().showResult(view, onResultValidationListener)
}

fun List<ValidationResult>.showResult(
    view: ViewGroup,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    this.filterVisibleValidationResult(view).let {
        showValidationResultToView(it, view)
        onResultValidationListener(it.isValid())
    }
}
fun List<ValidationResult>.showResult(
    fragment: Fragment,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    (fragment.view as ViewGroup)?.let {
        this.filterVisibleValidationResult(it).let { itValidationResults ->
            showValidationResultToView(itValidationResults, it)
            onResultValidationListener(itValidationResults.isValid())
        }
    }

}

fun showValidationResultToView(validationResults: List<ValidationResult>, view: ViewGroup) {
    validationResults.forEachIndexed { index, validationResult ->

        val targetViewId = validationResult.errorViewId
        if (targetViewId == -1) {
            if (validationResult.getMessage(view.context).isNotEmpty())
                Toast.makeText(
                    view.context,
                    validationResult.getMessage(view.context),
                    Toast.LENGTH_SHORT
                ).show()
        } else {
            when (val viewTarget = view.findViewById<View>(validationResult.errorViewId)) {
                is IViewValidate -> {
                    if (validationResult.getMessage(view.context).isEmpty()) {
                        viewTarget.onHideError(validationResult.getMessage(view.context))
                    } else
                        viewTarget.onShowError(validationResult.getMessage(view.context))
                }
                is TextInputLayout -> {
                    if (validationResult.getMessage(view.context).isEmpty()) {
                        viewTarget.isErrorEnabled = false
                        viewTarget.error = ""
                    } else
                        viewTarget.isErrorEnabled = true
                    viewTarget.error = validationResult.getMessage(view.context)
                }
                is TextView -> {
                    if (validationResult.getMessage(view.context).isEmpty()) {
                        viewTarget.visibility = View.GONE
                    } else {
                        viewTarget.visibility = View.VISIBLE
                        viewTarget.text = validationResult.getMessage(view.context)
                    }
                }
                else -> {
                    if (validationResult.getMessage(view.context).isEmpty()) {
                        viewTarget?.visibility = View.GONE
                    } else {
                        viewTarget?.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
