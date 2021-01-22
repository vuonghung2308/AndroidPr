package com.example.openweather.ui.base

import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.openweather.common.extension.*
import com.example.openweather.routing.ActivityNavigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

open class BaseActivity : AppCompatActivity() {
    protected val navigator: ActivityNavigator by inject { parametersOf(this) }

    fun showError(@StringRes errorMessage: Int, rootView: View) {
        snackbar(errorMessage, rootView)
    }

    fun showError(errorMessage: String, rootView: View) {
        snackbar(errorMessage, rootView)
    }

    fun showLoading(progressBar: ProgressBar) {
        progressBar.visible()
    }

    fun hideLoading(progressBar: ProgressBar) {
        progressBar.gone()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            finish()
        else goBack()
    }

    fun addFragment(container: Int, fragment: Fragment, addToBackStack: Boolean = false) {
        showFragment(container, fragment, addToBackStack)
    }

}