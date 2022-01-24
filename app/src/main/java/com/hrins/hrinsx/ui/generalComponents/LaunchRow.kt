package com.hrins.hrinsx.ui.generalComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrins.hrinsx.R
import com.hrins.hrinsx.domain.Launch
import com.hrins.hrinsx.domain.Rocket

@Composable
fun LaunchRow(launch: Launch, onClick: (Launch) -> Unit) {
    Column(Modifier.fillMaxWidth().clickable(onClick = { onClick(launch) })) {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight()

        ) {

            var url="https://images2.imgbox.com/40/e3/GypSkayF_o.png"
            if (launch.image!=null)
                url=launch.image
            val image = loadPicture(
                url = url,
                defaultImage = R.drawable.ic_baseline_airplanemode_active_24
            ).value

            Image(
                bitmap = image,
                contentDescription = "article ain image",
                Modifier
                    .size(45.dp)
                    .padding(end = 8.dp), contentScale = ContentScale.Crop
            )



            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {

                Row {
                    Text(
                        text = stringResource(R.string.mission),
                        Modifier.padding(2.dp),
                        fontSize = 16.sp
                    )
                    Text(text = launch.mission, Modifier.padding(2.dp), fontSize = 16.sp)
                }

                Row {
                    Text(
                        text = stringResource(R.string.datetime),
                        Modifier.padding(2.dp),
                        fontSize = 16.sp
                    )

                    Text(
                        text = launch.launchDate,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold,
                        maxLines = 3,

                        )
                }
                Row {
                    Text(
                        text = stringResource(R.string.rocket),
                        Modifier.padding(2.dp),
                        fontSize = 16.sp
                    )
                    Text(
                        text = launch.rocket.rocketName,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold,
                        maxLines = 3,

                        )
                }
                Row {
                    Text(
                        text = stringResource(R.string.days),
                        Modifier.padding(2.dp),
                        fontSize = 16.sp
                    )
                    Text(
                        text = launch.launchDate,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold,
                        maxLines = 3,

                        )
                }


            }

        }
        Divider(color = Color.Black,thickness = 2.dp)
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
            "https://images2.imgbox.com/40/e3/GypSkayF_o.png"
        ),
        onClick = {}
    )
}

