package com.example.adnetwork

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.adnetwork.ui.theme.AdNetworkTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
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

        // load the interstitial ad
        loadInterstitial(this)

        // add the interstitial ad callbacks
        addInterstitialCallbacks(this)
    }
}

@Composable
fun AdNetworkApp() {
    val adWidth = LocalConfiguration.current.screenWidthDp - 32
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        loadInterstitial(context)
        showInterstitial(context)
        Log.d("MainActivity", "Interstitial loaded on composable")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Adaptive Banner", modifier = Modifier.padding(bottom = 16.dp))

        // shows an adaptive banner test ad
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                        context,
                        adWidth
                    )
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

        Text("Inline Adaptive Banner", modifier = Modifier.padding(16.dp))

        // shows an inline adaptive banner test ad
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    adSize = AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(
                        context,
                        adWidth
                    )
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

        Text("Regular Banner", modifier = Modifier.padding(16.dp))

        // shows a traditional banner test ad
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    adSize = AdSize.BANNER
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

        // shows an interstitial ad on button click (on the first click only)
        Button(
            onClick = {
                loadInterstitial(context)
                showInterstitial(context)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Show Interstitial")
        }

        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello Ad Network!")
            Text("More texts")
        }

        Text("And some more texts")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AdNetworkApp()
}