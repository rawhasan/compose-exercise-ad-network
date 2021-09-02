package com.example.adnetwork

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.adnetwork.ui.theme.AdNetworkTheme
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

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
    val deviceCurrentWidthDp = LocalConfiguration.current.screenWidthDp
    val deviceCurrentDp = LocalConfiguration.current.densityDpi
    val deviceCurrentWidth = (deviceCurrentWidthDp / deviceCurrentDp).toInt()

    val context = LocalContext.current
    val interstitial_ad_id = stringResource(id = R.string.ad_id_interstitial)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Adaptive Banner")

        // TODO: Check success with callback + Ad ID
        // shows an Adaptive banner test ad - Not Working
//        AndroidView(
//            factory = { context ->
//                AdView(context).apply {
//                    adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
//                        context,
//                        //deviceCurrentWidth
//                        300
//                    )
//                    adUnitId = context.getString(R.string.ad_id_adaptive_banner)
//                    loadAd(AdRequest.Builder().build())
//                }
//            }
//        )

        Text("Inline Adaptive Banner")

        // TODO: Check success with callback + Ad ID
        // shows an Inline Adaptive banner test ad - Not Working
//        AndroidView(
//            factory = { context ->
//                AdView(context).apply {
//                    adSize = AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(
//                        context,
//                        //deviceCurrentWidth
//                        300
//                    )
//                    adUnitId = context.getString(R.string.ad_id_adaptive_banner)
//                    loadAd(AdRequest.Builder().build())
//                }
//            }
//        )

        Text("Regular Banner", modifier = Modifier.padding(16.dp))

        // shows a banner test ad - Working
//        AndroidView(
//            factory = { context ->
//                AdView(context).apply {
//                    adSize = AdSize.BANNER
//                    adUnitId = context.getString(R.string.ad_id_banner)
//                    loadAd(AdRequest.Builder().build())
//                }
//            }
//        )

        // Interstitial ad on button click - Working
        // (showing success on log, but not displaying the ad
        Button(
            onClick = {
                InterstitialAd.load(
                    context,
                    interstitial_ad_id,
                    AdRequest.Builder().build(),
                    object : InterstitialAdLoadCallback() {
                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            Log.d("MainActivity", adError.message)
                        }

                        override fun onAdLoaded(interstitialAd: InterstitialAd) {
                            Log.d("MainActivity", "Ad was loaded.")
                        }
                    }
                )
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