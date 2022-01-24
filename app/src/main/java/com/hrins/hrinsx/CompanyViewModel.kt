package com.hrins.hrinsx

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.CompanyDto
import com.hrins.hrinsx.domain.Company
import com.hrins.hrinsx.network.NetworkMapper
import com.hrins.hrinsx.network.api.OnCompleteListener
import com.hrins.hrinsx.repositories.CompanyRepository
import com.hrins.hrinsx.ui.uiValidation.ViewResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val repository: CompanyRepository,
    val networkMapper: NetworkMapper, val app: App
) : ViewModel() {
    val companyDescription: MutableState<String> = mutableStateOf("")
    val company: MutableState<Company?> = mutableStateOf(null)
    val viewResponse: MutableState<ViewResponse> = mutableStateOf(ViewResponse.Non)

    init {
        viewResponse.value = ViewResponse.Loading
        viewModelScope.launch {
            repository.info(object : OnCompleteListener<CompanyDto> {
                override fun onComplete(t: CompanyDto) {
                    viewResponse.value = ViewResponse.Fetched
                    company.value = networkMapper.companyMapper.mapToDomainModel(t)
                    val des = company.value
                    companyDescription.value =
                        des!!.name +
                                app.getString(R.string.wasFounded) +
                                des.founder +
                                app.getString(R.string.foundedIn) +
                                des.date +
                                app.getString(R.string.it_has) +
                                des.employees +
                                app.getString(R.string.employees) +
                                t.companyLinksDto!!.website +
                                app.getString(R.string.site) +
                                des.value


                }

                override fun onError(error: String?) {
                    viewResponse.value = ViewResponse.APIError
                }
            })

        }
    }
}