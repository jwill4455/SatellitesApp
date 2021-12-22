package burak.ceylan.satellitestrackapp.ui.fragments

import android.os.Bundle
import burak.ceylan.satellitestrackapp.R
import burak.ceylan.satellitestrackapp.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}