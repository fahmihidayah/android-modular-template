package com.widsons.validator.utils

import com.widsons.validator.annotation.*
import com.widsons.validator.data.ValidationResult
import java.lang.reflect.Field

/**
 * Created on : June/21/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */

fun Field.getTargetView() : TargetView? {
    return this.annotations.find { it is TargetView } as TargetView?
}

fun Field.getErrorTargetView() : ErrorTargetView? {
    return this.annotations.find { it is ErrorTargetView } as ErrorTargetView?
}

fun TargetView?.getId() : Int {
    return this?.targetId?:-1
}

fun ErrorTargetView?.getId() : Int {
    return this?.errorTargetId?:-1
}

fun Any.findAnnotation(match : (Annotation) -> Boolean) : Pair<Field, Annotation>? {
    this::class.java.declaredFields.forEachIndexed { index, field ->
        field.annotations.forEachIndexed { IndexAnnotation, annotation ->
            if(match(annotation)) {
                field.isAccessible = true
                return field to annotation
            }
        }
    }
    return null
}

fun  Field.getFieldValue(targetObject : Any) : Any? {
    this.isAccessible = true
    return this.get(targetObject)
}

fun Any.getField(fieldName : String) : Field? {
    this::class.java.declaredFields.forEachIndexed {index, field ->
        println("field is ${field.name} - ${fieldName}")
        if(field.name == fieldName) {
            return field
        }
    }
    return null
}

fun Any.getValidateResultFromFieldWithAnnotation(field: Field, annotation : Annotation) : ValidationResult? {
    return when(annotation) {
        is Email -> annotation.annotationValidate(field, this)
        is Equal -> annotation.annotationValidate(field, this)
        is Min -> annotation.annotationValidate(field, this)
        is NotEmpty  -> annotation.annotationValidate(field, this)
        is RequiredChecked -> annotation.annotationValidate(field, this)
        else -> null
    }
}

fun Field.validateField(targetObject : Any) :  MutableList<ValidationResult>{
    val listResult = mutableListOf<ValidationResult>()
    val targetView = this.getTargetView()
    val errorTargetView = this.getErrorTargetView()
    this.annotations.forEachIndexed { IndexAnnotation, annotation ->
        val result = targetObject.getValidateResultFromFieldWithAnnotation(this, annotation)
        targetView?.targetId?.let {  result?.targetId = it }
        errorTargetView?.errorTargetId?.let { result?.errorViewId = it}
        result?.let { listResult.add(it) }
    }
    return listResult
}

fun Field.getCorrectValidationResult() : MutableList<ValidationResult> {
    val listResult = mutableListOf<ValidationResult>()
    val targetView = this.getTargetView()
    val errorTargetView = this.getErrorTargetView()
    this.annotations.forEachIndexed { IndexAnnotation, annotation ->
        val result = ValidationResult(errorRes = -1, error = "", isValid = true)
        targetView?.targetId?.let {  result?.targetId = it }
        errorTargetView?.errorTargetId?.let { result?.errorViewId = it}
        result.let { listResult.add(it) }
    }
    return listResult
}

fun Any.validateWithCorrectResult() : MutableList<ValidationResult> {
    var listResult = mutableListOf<ValidationResult>()
    this::class.java.declaredFields.forEachIndexed { index, field ->
        listResult.addAll(field.getCorrectValidationResult())
    }
    return listResult
}

fun Any.validate() : MutableList<ValidationResult> {
    var listResult = mutableListOf<ValidationResult>()
    this::class.java.declaredFields.forEachIndexed { index, field ->
        with(field.validateField(this)) {
            if(this.isNotEmpty())
                listResult.addAll(this)
        }
    }
    return listResult
}

fun List<ValidationResult>.isValid() : Boolean {
    for(item in this) {
        if(!item.isValid)
            return false
    }
    return true
}

fun Any.getFields() = this::class.java.declaredFields
