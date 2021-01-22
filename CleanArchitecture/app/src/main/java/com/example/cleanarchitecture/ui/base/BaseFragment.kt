package com.example.cleanarchitecture.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.cleanarchitecture.common.extensions.gone
import com.example.cleanarchitecture.common.extensions.visible
import com.example.cleanarchitecture.routing.AppFragmentNavigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseFragment : Fragment() {
    protected val appFragmentNavigator: AppFragmentNavigator by inject { parametersOf(activity) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(getLayout(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewReady()
    }

    protected fun onBackPressed() = (activity as BaseActivity).onBackPressed()

    abstract fun getLayout(): Int

    abstract fun viewReady()

    open fun showError(@StringRes errorMessage: Int, rootView: View) =
        (activity as BaseActivity).showError(errorMessage, rootView)

    open fun showError(errorMessage: String, rootView: View) =
        (activity as BaseActivity).showError(errorMessage, rootView)

    open fun showLoading(progressBar: ProgressBar) = progressBar.visible()

    open fun hideLoading(ProgressBar: ProgressBar) = ProgressBar.gone()
}