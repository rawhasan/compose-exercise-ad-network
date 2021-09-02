package com.example.adnetwork

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adnetwork.ui.theme.AdNetworkTheme
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

private var mInterstitialAd: InterstitialAd? = null

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

        // load interstitial ad
        InterstitialAd.load(
            this,
            getString(R.string.ad_id_interstitial),
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    Log.d("MainActivity", adError.message)
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    Log.d("MainActivity", "Ad was loaded.")
                }
            }
        )

        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                Log.d("MainActivity", "Ad failed to show.")
            }

            override fun onAdShowedFullScreenContent() {
                mInterstitialAd = null
                Log.d("MainActivity", "Ad showed fullscreen content.")
            }

            override fun onAdDismissedFullScreenContent() {
                Log.d("MainActivity", "Ad was dismissed.")
            }
        }

        // show the interstitial ad
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d("MainActivity", "The interstitial ad wasn't ready yet.")
        }

    }
}

@Composable
fun AdNetworkApp() {
    val deviceCurrentWidthDp = LocalConfiguration.current.screenWidthDp
    val deviceCurrentDp = LocalConfiguration.current.densityDpi
    val deviceCurrentWidth = (deviceCurrentWidthDp / deviceCurrentDp).toInt()

    val context = LocalContext.current
    val interstitial_ad_id = stringResource(id = R.string.ad_id_interstitial)

    //val interestitialAd: InterstitialAd? = null

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
            onClick = {  },
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