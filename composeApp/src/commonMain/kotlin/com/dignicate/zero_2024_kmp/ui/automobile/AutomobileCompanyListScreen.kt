package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.dignicate.zero_2024_kmp.ui.CustomTopAppBar
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun AutomobileCompanyListScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    modifier: Modifier,
    viewModel: AutomobileCompanyListViewModel = getKoin().get(),
) {

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> viewModel.onCreate()
                Lifecycle.Event.ON_RESUME -> viewModel.onResume()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AutomobileCompanyListView(
        modifier = modifier,
    )
}

@Composable
fun AutomobileCompanyListView(
    modifier: Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                modifier = Modifier,
                text = "Automobile Company List",
            )
        },
        content = {
            Text("Content goes here")
        },
    )
}
