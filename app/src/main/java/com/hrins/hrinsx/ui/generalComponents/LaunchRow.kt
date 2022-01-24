package com.hrins.hrinsx.ui.generalComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hrins.hrinsx.R
import com.hrins.hrinsx.domain.Launch
import com.hrins.hrinsx.domain.Rocket
import com.hrins.hrinsx.network.models.LinksDto

@Composable
fun LaunchRow(launch: Launch, onClick: (Launch) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(launch) })
    ) {
        ConstraintLayout(
            Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentHeight()

        ) {
            val (missionImage, content, status) = createRefs()
            var url = "https://images2.imgbox.com/40/e3/GypSkayF_o.png"
            if (launch.image != null)
                url = launch.image
            val image = loadPicture(
                url = url,
                defaultImage = R.drawable.ic_baseline_airplanemode_active_24
            ).value

            Image(
                bitmap = image,
                contentDescription = "image",
                Modifier
                    .size(45.dp)
                    .padding(end = 4.dp)
                    .fillMaxWidth(0.1f)
                    .constrainAs(missionImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )



            Column(

                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(missionImage.end)
                    }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.mission),
                        modifier = Modifier.padding(2.dp),
                        fontSize = 16.sp, fontWeight = FontWeight.SemiBold

                    )
                    Text(text = launch.mission, Modifier.padding(2.dp), fontSize = 16.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.datetime),
                        Modifier.padding(2.dp),
                        fontSize = 16.sp, fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = launch.time,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,

                        )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.rocket),
                        Modifier.padding(2.dp),
                        fontSize = 16.sp, fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = launch.rocket.rocketName,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,

                        )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.days),
                        Modifier.padding(2.dp),
                        fontSize = 16.sp, fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = launch.launchDate,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,

                        )
                }
            }


            var statusResource = R.drawable.unsuccessful
            if (launch.launchIsSuccessful)
                statusResource = R.drawable.successful

            Image(
                painterResource(id = statusResource),
                contentDescription = "image",
                Modifier
                    .fillMaxWidth(0.1f)
                    .size(45.dp)
                    .padding(end = 6.dp)
                    .constrainAs(status) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            )


        }
        Divider(color = Color.Black, thickness = 2.dp)
    }

}

@Preview
@Composable
fun Preview() {


    LaunchRow(
        launch = Launch(
            "1",
            "alpha 1",
            "2020-01,12",
            Rocket("345345-5345-534", "FireBall", "Falcon"),
            "from now 3 days",
            "https://images2.imgbox.com/40/e3/GypSkayF_o.png",
            true
        , LinksDto()
        ),
        onClick = {}
    )
}

