package com.hrins.hrinsx.ui.uiValidation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.hrins.hrinsx.R
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun ViewStatusValidation(
    viewResponse: ViewResponse,
    title: String,
    ui: Unit?,
    onReload: () -> Unit
) {


    ConstraintLayout() {

        var messageVisibility = false
        var isLoading = false
        var imgResource = R.drawable.no_data
        var sResource = R.string.nodata

        when (viewResponse) {
            ViewResponse.Loading -> {
                isLoading = true
                messageVisibility = false
            }
            ViewResponse.Fetched -> {
                isLoading = false
                messageVisibility = false
            }
            ViewResponse.APIError -> {
                isLoading = false
                messageVisibility = true
                sResource = R.string.networkError
                imgResource = R.drawable.network_error

            }
            ViewResponse.NoData -> {
                isLoading = false
                messageVisibility = true

                sResource = R.string.nodata
                imgResource = R.drawable.no_data
            }
            ViewResponse.NextPageLoading -> {
                isLoading = false
                messageVisibility = false

            }
            ViewResponse.Non ->{
                isLoading = false
                messageVisibility = false
            }
        }
        UIValidate(
            ui,
            isDisplayed = messageVisibility,
            stringResource(sResource) + ": " + title,
            imgResource
        ) {
            onReload()
        }
        CircularProgressBar(isLoading)
    }


}