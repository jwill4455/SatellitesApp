package burak.ceylan.satellitestrackapp.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import burak.ceylan.satellitestrackapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity @Inject constructor() : AppCompatActivity() {
    val controller by lazy { Navigation.findNavController(this, R.id.hostFragment) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}