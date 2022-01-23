package com.hrins.hrinsx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrins.hrinsx.ui.theme.HrinsXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            HrinsXTheme {

                val companyDescription =
                    "company description vsvsdfsfgfsb \ngsdgsdfgsfgfgdf \ng fgsfgsfgdsf \nsgsfgf"
                Surface(color = MaterialTheme.colors.background) {
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