package com.hrins.hrinsx

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrins.hrinsx.domain.Launch
import com.hrins.hrinsx.network.NetworkMapper
import com.hrins.hrinsx.network.api.OnCompleteListener
import com.hrins.hrinsx.network.models.LaunchDto
import com.hrins.hrinsx.repositories.LaunchesRepo
import com.hrins.hrinsx.ui.uiValidation.ViewResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject
constructor(
    private val launchesRepo: LaunchesRepo,
    val networkMapper: NetworkMapper
) : ViewModel() {

    val viewResponse: MutableState<ViewResponse> = mutableStateOf(ViewResponse.Non)

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position

    }

    fun nextPage() {
        if ((scrollPosition + 1) >= (page.value * pageSize)) {
            viewResponse.value = ViewResponse.NextPageLoading
            incrementPage()
            if (page.value > 1) {
                viewModelScope.launch {
                    launchesRepo.launches(
                        page.value,
                        "flight_number",
                        "desc",
                        pageSize,

                        object : OnCompleteListener<List<LaunchDto>> {
                            override fun onComplete(t: List<LaunchDto>) {
                                if (t != null) {

                                    appendNewItems(
                                        networkMapper.LaunchDtoMapper.mapToListOfDomain(
                                            (t)
                                        )
                                    )
                                    viewResponse.value = ViewResponse.Fetched
                                }
                                ViewResponse.NoData
                            }

                            override fun onError(error: String?) {
                                viewResponse.value = ViewResponse.APIError
                            }
                        })
                }

            }
        }
    }

    private fun incrementPage() {
        page.value += 1
    }

    fun getFirst() {
        viewResponse.value = ViewResponse.Loading

        viewModelScope.launch {
            launchesRepo.launches(
                0,
                "",
                "desc",
                pageSize,
                object : OnCompleteListener<List<LaunchDto>> {
                    override fun onComplete(t: List<LaunchDto>) {
                        if (t != null) {
                            ViewResponse.Fetched
                            launches.value = networkMapper.LaunchDtoMapper.mapToListOfDomain(
                                (t)
                            )

                        } else ViewResponse.NoData
                    }

                    override fun onError(error: String?) {
                        viewResponse.value = ViewResponse.APIError
                    }
                })
        }
    }


    private fun appendNewItems(launches: List<Launch>) {
        val current = ArrayList(this.launches.value)
        current.addAll(launches)
        this.launches.value = current
    }


    val launches: MutableState<List<Launch>> = mutableStateOf(listOf())
    private var scrollPosition = 0

    val pageSize: Int = 10
    val page = mutableStateOf(1)

    val companyDescription: MutableState<String> = mutableStateOf("")

    init {

    }


}