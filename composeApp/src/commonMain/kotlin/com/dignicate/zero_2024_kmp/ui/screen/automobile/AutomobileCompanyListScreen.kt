package com.dignicate.zero_2024_kmp.ui.screen.automobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.dignicate.zero_2024_kmp.domain.automobile.Company
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme
import org.koin.mp.KoinPlatform.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutomobileCompanyListScreen(
    rootNavController: NavController,
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

    val uiState = viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isNotEmpty() && visibleItems.last().index >= uiState.value.data.size - 2) {
                    viewModel.onScrollNearlyEnd()
                }
            }
    }

    PullToRefreshBox(
        modifier = modifier,
        onRefresh = {
            viewModel.onRefresh()
        },
        isRefreshing = uiState.value.isRefreshing,
        state = pullToRefreshState,
    ) {
        AutomobileCompanyListView(
            modifier = Modifier,
            data = uiState.value.data,
            isLoading = uiState.value.isLoading,
            listState = listState,
        )
    }
}

@Composable
fun AutomobileCompanyListView(
    modifier: Modifier,
    data: List<Company>,
    isLoading: Boolean,
    listState: LazyListState,
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(data) { company ->
                AutomobileCompanyListItemView(
                    companyId = company.id,
                    companyName = company.name,
                    country = company.country,
                    foundedYear = company.foundedYear,
                )
            }
            item {
                Spacer(modifier = Modifier.height(0.dp))
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                )
            }
        }
    }
}

@Composable
fun AutomobileCompanyListItemView(
    modifier: Modifier = Modifier,
    companyId: Company.Id,
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
                color = MyCustomTheme.exColors.preset.background,
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${companyId.value}. ",
                    color = MyCustomTheme.exColors.textSub,
                    style = MyCustomTheme.exTypography.itemSub,
                    textAlign = TextAlign.End,
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = companyName,
                    color = MyCustomTheme.exColors.textMain,
                    style = MyCustomTheme.exTypography.itemMain,
                )
            }
            Spacer(Modifier.height(2.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = country,
                    color = MyCustomTheme.exColors.textSub,
                    style = MyCustomTheme.exTypography.itemSub,
                )
                Text(
                    text = "Since $foundedYear",
                    color = MyCustomTheme.exColors.textSub,
                    style = MyCustomTheme.exTypography.itemSub,
                )
            }
        }
    }
}
