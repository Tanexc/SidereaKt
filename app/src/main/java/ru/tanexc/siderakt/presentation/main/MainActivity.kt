package ru.tanexc.siderakt.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.tanexc.siderakt.presentation.main.components.SidereaApp
import ru.tanexc.siderakt.presentation.main.viewModel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val viewModel: MainViewModel by viewModels()

        setContent {
            actionBar?.hide()
            SidereaApp(viewModel)
        }
    }

}
