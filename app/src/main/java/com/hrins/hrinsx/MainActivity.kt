package com.hrins.hrinsx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrins.hrinsx.domain.Launch
import com.hrins.hrinsx.ui.generalComponents.LaunchRow
import com.hrins.hrinsx.ui.theme.HrinsXTheme
import com.hrins.hrinsx.ui.uiValidation.CircularProgressBar
import com.hrins.hrinsx.ui.uiValidation.ViewResponse
import com.hrins.hrinsx.ui.uiValidation.ViewStatusValidation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<LaunchesViewModel>()
    private val companyViewModel by viewModels<CompanyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val page = viewModel.page.value
            val pageSize = viewModel.pageSize
            val launches = viewModel.launches.value
            val viewResponse = viewModel.viewResponse.value
            val companyDescription = companyViewModel.companyDescription.value
            val companyViewResponse = companyViewModel.viewResponse.value
            HrinsXTheme {

                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Scaffold(topBar = {
                        TopAppBar(
                            backgroundColor = Color.Transparent, elevation = 0.dp,
                            title = {
                                Text(
                                    getString(R.string.app_name),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth(), fontSize = 22.sp
                                )
                            },
                            actions = {
                                IconButton(onClick = {
                                }) {
                                    Icon(
                                        painterResource(id = R.drawable.ic_baseline_filter_alt_24),
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )
                    }) {
                        Column(Modifier.fillMaxWidth()) {
                            Surface(color = Color.DarkGray, modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = getString(R.string.company),
                                    modifier = Modifier.padding(3.dp),
                                    fontSize = 20.sp,
                                    color = Color.White
                                )

                            }
                            Text(
                                text = companyDescription,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(3.dp)
                            )


                            Surface(color = Color.DarkGray, modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = getString(R.string.LAUNCHES),
                                    modifier = Modifier.padding(3.dp),
                                    fontSize = 20.sp,
                                    color = Color.White
                                )


                            }
                            ViewStatusValidation(viewResponse,
                                resources.getString(R.string.LAUNCHES),
                                Box(modifier = Modifier.fillMaxSize()) {


                                    LazyColumn {
                                        itemsIndexed(items = launches) { index, item: Launch ->
                                            viewModel.onChangeScrollPosition(index)
                                            if ((index + 1) >= (page * pageSize) && viewResponse != ViewResponse.Loading) {
                                                viewModel.nextPage()
                                            }

                                            LaunchRow(launch = item, onClick = {})
                                        }
                                        item {
                                            val isLoading: Boolean =
                                                viewResponse == ViewResponse.NextPageLoading
                                            CircularProgressBar(isDisplayed = isLoading)

                                        }
                                    }

                                },
                                onReload = {
                                    viewModel.getFirst()
                                })
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HrinsXTheme {
            Greeting("Android")
        }
    }
}