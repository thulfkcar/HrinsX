package com.hrins.hrinsx.ui.uiValidation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.constraintlayout.compose.ConstraintLayout
import com.hrins.hrinsx.R

@Composable
fun UIValidate(
    ui: Unit?,
    isDisplayed: Boolean,
    title: String = stringResource(R.string.networkError),
    img: Int = R.drawable.network_error,
    onClick: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()

    ) {
        val (progress, text,ui) = createRefs()

        if (isDisplayed) {

            Image(
                painterResource(id = img), contentDescription = "",
                modifier = Modifier   .clickable(
                    enabled = true,
                    onClickLabel = "clicked",
                    role = Role.Button,
                    onClick = { onClick() })
                    .constrainAs(progress) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = title,
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(progress.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        }
        else ui
    }
}