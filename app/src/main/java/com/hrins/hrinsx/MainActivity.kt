package com.hrins.hrinsx

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrins.hrinsx.domain.Launch
import com.hrins.hrinsx.ui.generalComponents.LaunchRow
import com.hrins.hrinsx.ui.generalComponents.Spinner
import com.hrins.hrinsx.ui.theme.HrinsXTheme
import com.hrins.hrinsx.ui.uiValidation.CircularProgressBar
import com.hrins.hrinsx.ui.uiValidation.ViewResponse
import com.hrins.hrinsx.ui.uiValidation.ViewStatusValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<LaunchesViewModel>()
    private val companyViewModel by viewModels<CompanyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            HrinsXTheme {
                ModelBottomSheet()

            }
        }
    }

    @Composable
    @ExperimentalMaterialApi
    fun ModelBottomSheet() {

        val context = LocalContext.current

        val launch = viewModel.selectedLaunch.value
        val modalBottomSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden
        )
        val scope = rememberCoroutineScope()
        ModalBottomSheetLayout(
            sheetState = modalBottomSheetState,
            sheetContent = {
                Column(Modifier.padding(8.dp)) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(launch?.links?.articleLink)
                                    )
                                )

                            }) {
                        Text(
                            text = "Article",
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 20.sp,
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(launch?.links?.wikipedia)
                                    )
                                )

                            }) {
                        Text(
                            text = "wikipedia",
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 20.sp,
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(launch?.links?.videoLink)
                                    )
                                )

                            }) {
                        Text(
                            text = "Video",
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 20.sp,
                        )
                    }


                }
            }
        ) {
            MainScreen(scope, modalBottomSheetState)
        }
    }

    @ExperimentalMaterialApi
    @Composable
    private fun MainScreen(scope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState) {
        val page = viewModel.page.value
        val pageSize = viewModel.pageSize
        val launches = viewModel.launches.value
        val viewResponse = viewModel.viewResponse.value
        val companyDescription = companyViewModel.companyDescription.value
        val companyViewResponse = companyViewModel.viewResponse.value
        val showCommentDialog = viewModel.openDialog.value


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
                            viewModel.notifyFilterDialog(true)
                        }
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_baseline_filter_alt_24),
                                contentDescription = "Localized description"
                            )
                        }
                    }

                )
            }) {
                if (showCommentDialog) {
                    FilterDialog()
                }

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
                        modifier = Modifier.padding(6.dp)
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

                                    LaunchRow(launch = item, onClick = {
                                        scope.launch {
                                            viewModel.setSelectedLaunch(item)
                                            modalBottomSheetState.show()
                                        }
                                    })
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

    @Composable
    fun FilterDialog() {

        val openDialog = viewModel.openDialog.value
        val appLyFiler = viewModel.applyFiler.value

        if (openDialog) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.notifyFilterDialog(false)
                },
                title = {
                    Text(text = "Filter Result")
                },
                text = {
                    Column(Modifier.fillMaxWidth()) {
                        val yearState = remember {
                            mutableStateOf(false)
                        }
                        val landingState = remember {
                            mutableStateOf(false)
                        }
                        val sortingState = remember {
                            mutableStateOf(false)
                        }
                        val orderSate = remember {
                            mutableStateOf(false)
                        }
                        Spinner(
                            "Launch Year", viewModel.selectedYear.value,"All Years", viewModel.years,
                            {
                                    viewModel.setFilteredYear(it)
                            }, yearState
                        )
                        Spinner(
                            "Successful Landing?",
                            viewModel.selectedLandingStatus.value.toString(),"All", viewModel.successfulLandings,
                            {
                                    viewModel.setFilterLandingStatus(it as Boolean?)
                            }, landingState
                        )
                        Spinner(
                            "Sorting",
                            viewModel.selectedSorting.value,
                            "default Sorting",
                            viewModel.sorts,
                            {
                                    viewModel.setFilterSorting(it)
                            },
                            sortingState
                        )
                        Spinner(
                            "Order By",viewModel.selectedOrder.value ,"default", viewModel.orders,
                            {
                                    viewModel.setFilteredOrder(it)
                            }, orderSate
                        )

                    }
                },
                buttons = {
                    Column(Modifier.fillMaxWidth()) {
                        if (appLyFiler)
                            Row(
                                modifier = Modifier.padding(all = 8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        viewModel.setFilterToBeApplied(false)
                                        viewModel.notifyFilterDialog (false)
                                        viewModel.getFirst()
                                    }
                                ) {
                                    Text("Clear Filter")
                                }
                            }
                        Row(
                            modifier = Modifier.padding(all = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    viewModel.setFilterToBeApplied(true)
                                    viewModel.notifyFilterDialog (false)
                                    viewModel.getFirst()
                                }
                            ) {
                                Text("apply Filter")
                            }
                        }
                    }

                })
        }
    }




}