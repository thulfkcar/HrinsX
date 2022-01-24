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

     val selectedSorting: MutableState<String?> = mutableStateOf(null)
     val selectedLandingStatus: MutableState<Boolean?> = mutableStateOf(null)
     val selectedOrder: MutableState<String?> = mutableStateOf(null)
     val selectedYear: MutableState<Int?> = mutableStateOf(null)
    val applyFiler: MutableState<Boolean> = mutableStateOf(false)
    val orders = listOf("desc", "asc")
    val sorts = listOf("flight_number", "mission_name", "launch_year", "rocket")
    val successfulLandings = listOf(true, false)
    val openDialog: MutableState<Boolean> = mutableStateOf(false)
    val selectedLaunch: MutableState<Launch?> = mutableStateOf(null)
    val viewResponse: MutableState<ViewResponse> = mutableStateOf(ViewResponse.Non)
    val years = listOf(2018, 2019, 2020, 2021)

    fun setFilterToBeApplied(b: Boolean) {
        applyFiler.value = b
        if (!applyFiler.value){
            setFilterLandingStatus(null)
            setFilteredOrder(null)
            setFilteredYear(null)
            setFilterSorting(null)
        }
    }

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position

    }

    fun setSelectedLaunch(launch: Launch) {
        selectedLaunch.value = launch
    }

    fun nextPage() {
        if ((scrollPosition + 1) >= (page.value * pageSize)) {
            viewResponse.value = ViewResponse.NextPageLoading
            incrementPage()
            if (page.value > 1) {
                viewModelScope.launch {
                    launchesRepo.launches(
                        page.value,
                        selectedSorting.value,
                        selectedOrder.value,
                        pageSize, selectedLandingStatus.value, selectedYear.value,

                        object : OnCompleteListener<List<LaunchDto>> {
                            override fun onComplete(t: List<LaunchDto>) {
                                if (t.isNotEmpty()) {

                                    appendNewItems(
                                        networkMapper.LaunchDtoMapper.mapToListOfDomain(
                                            (t)
                                        )
                                    )
                                    viewResponse.value = ViewResponse.Fetched
                                }
                                viewResponse.value = ViewResponse.NoData
                            }

                            override fun onError(error: String?) {
                                viewResponse.value = ViewResponse.APIError
                            }
                        })
                }

            }
        }
    }

    private fun resetSearchState() {
        page.value = 1
        onChangeScrollPosition(0)
        launches.value = listOf()
    }

    private fun incrementPage() {
        page.value += 1
    }

    fun getFirst() {
        viewResponse.value = ViewResponse.Loading
        resetSearchState()
        viewModelScope.launch {
            launchesRepo.launches(
                0,
                selectedSorting.value,
                selectedOrder.value,
                pageSize, selectedLandingStatus.value,
                selectedYear.value,
                object : OnCompleteListener<List<LaunchDto>> {
                    override fun onComplete(t: List<LaunchDto>) {

                        if (t.isNotEmpty()) {
                            viewResponse.value = ViewResponse.Fetched

                            launches.value = networkMapper.LaunchDtoMapper.mapToListOfDomain(t)

                        } else viewResponse.value = ViewResponse.NoData

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

    fun notifyFilterDialog(b: Boolean) {
        openDialog.value = b
    }


    fun setFilteredYear(it: Int?) {
        selectedYear.value = it
    }

    fun setFilteredOrder(it: String?) {
        selectedOrder.value = it

    }

    fun setFilterLandingStatus(it: Boolean?) {
        selectedLandingStatus.value = it
    }

    fun setFilterSorting(it: String?) {
        selectedSorting.value = it
    }

    fun isThereFilterValue(): Boolean {
        if (selectedLandingStatus.value!= null) return true
        if (selectedOrder.value!=null) return true
        if (selectedYear.value!=null) return true
        if (selectedSorting.value!= null) return true

            return false
    }

    val launches: MutableState<List<Launch>> = mutableStateOf(listOf())
    private var scrollPosition = 0

    val pageSize: Int = 10
    val page = mutableStateOf(1)

    val companyDescription: MutableState<String> = mutableStateOf("")

    init {
        getFirst()
    }


}