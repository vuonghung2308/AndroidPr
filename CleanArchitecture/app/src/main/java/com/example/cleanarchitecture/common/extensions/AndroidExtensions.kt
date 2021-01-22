package com.example.cleanarchitecture.common.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.data.common.coroutine.CoroutineContextProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

inline fun <T> LiveData<T>.subcribe(
    owner: LifecycleOwner,
    crossinline onDataReceived: (T) -> Unit
) =
    observe(owner, Observer { onDataReceived(it) })

fun View.hideKeyboard() {
    val im = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun snackbar(@StringRes messange: Int, rootView: View) =
    Snackbar.make(rootView, messange, Snackbar.LENGTH_SHORT).show()

fun snackbar(messange: String, rootView: View) =
    Snackbar.make(rootView, messange, Snackbar.LENGTH_SHORT).show()

fun ProgressBar.visible() {
    this.visibility = View.VISIBLE
}

fun ProgressBar.gone() {
    this.visibility = View.GONE
}

fun FragmentActivity.showFragment(
    fragment: Fragment,
    @IdRes containerId: Int,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.beginTransaction().apply {
        if (addToBackStack) {
            addToBackStack(fragment.tag)
        }
    }
        .replace(containerId, fragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.goBack() {
    supportFragmentManager.popBackStack()
}

inline fun ViewModel.launch(
    coroutineContext: CoroutineContext = CoroutineContextProvider().main,
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(coroutineContext) { block() }
}

inline fun View.onClick(crossinline onClick: () -> Unit) {
    setOnClickListener { onClick() }
}