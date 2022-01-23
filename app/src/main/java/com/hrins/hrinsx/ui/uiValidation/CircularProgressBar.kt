package com.hrins.hrinsx.ui.uiValidation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.hrins.hrinsx.R

@Composable
fun CircularProgressBar(isDisplayed: Boolean, title: String = "") {
    if (isDisplayed) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (progress, text) = createRefs()
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.constrainAs(progress) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
            )

            Text(text = stringResource(R.string.loading) + " " + title,
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(progress.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        }
    }
}
