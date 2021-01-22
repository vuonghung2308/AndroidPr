package com.example.openweather.common.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.common.coroutine.CoroutineContextProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun snackbar(@StringRes message: Int, rootView: View) {
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
}

fun snackbar(message: String, rootView: View) {
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
}

fun ProgressBar.visible() {
    visibility = View.VISIBLE
}

fun ProgressBar.gone() {
    visibility = View.GONE
}

fun FragmentActivity.goBack() {
    supportFragmentManager.popBackStack()
}

fun FragmentActivity.showFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.beginTransaction().apply {
        if (addToBackStack)
            addToBackStack(fragment.tag)
    }
        .replace(container, fragment)
        .commitAllowingStateLoss()
}

fun <T : Any> LiveData<T>.subcribe(
    owner: LifecycleOwner,
    action: (T) -> Unit
) {
    observe(owner, action)
}

inline fun ViewModel.launch(
    coroutineContext: CoroutineContext = CoroutineContextProvider().main,
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(coroutineContext) { block() }
}

fun View.hideKeyboard() {
    val input = context.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    input.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}