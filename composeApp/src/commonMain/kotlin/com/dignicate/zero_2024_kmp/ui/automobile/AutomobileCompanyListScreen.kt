package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.dignicate.zero_2024_kmp.ui.appbar.CustomTopAppBar
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme
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

@Composable
fun AutomobileCompanyListItemView(
    modifier: Modifier = Modifier,
    companyName: String,
    country: String,
    foundedYear: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .padding(horizontal = 8.dp)
            .background(
                color = MyCustomTheme.exColors.preset.background
            ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = companyName,
                color = MyCustomTheme.exColors.textMain,
                style = MyCustomTheme.exTypography.itemMain,
            )
            Text(
                text = country,
                color = MyCustomTheme.exColors.textMain,
                style = MyCustomTheme.exTypography.itemSub,
            )
            Text(
                text = foundedYear.toString(),
                color = MyCustomTheme.exColors.textMain,
                style = MyCustomTheme.exTypography.itemSub,
            )
        }
    }
}
