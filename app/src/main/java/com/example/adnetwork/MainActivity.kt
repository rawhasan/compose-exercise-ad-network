package com.example.adnetwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.adnetwork.ui.theme.AdNetworkTheme
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdNetworkTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AdNetworkApp()
                }
            }
        }

        // initialize the Mobile Ads SDK
        MobileAds.initialize(this) { }
    }
}

@Composable
fun AdNetworkApp() {
    Text(text = "Hello Ad Network!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AdNetworkTheme {
        AdNetworkApp()
    }
}