package com.example.cleanarchitecture.ui.welcome.view

import android.os.Bundle
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.common.extensions.onClick
import com.example.cleanarchitecture.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        nextScreen.onClick {
            appNavigator.showWeatherActivity()
            finish()
        }
    }
}