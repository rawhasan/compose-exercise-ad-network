# Ad Network
Exercise project to display AdMob ads on Android apps using Jetpack Compose.

## Takeaways
- Show AdMob traditional banner, adaptive banner, and inline adaptive banner.
- Show AdMob interstitial ad on button click from a composable.
- Extension function to get the current activity from any composable.

## Dependencies
```
<application
    ...
    android:hardwareAccelerated="true"> <!-- Enable hardware acceleration for video ads on banner -->

    <!-- Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713 -->
    <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="ca-app-pub-3940256099942544~3347511713"/>
```

```
// Mobile Ads SDK
implementation 'com.google.android.gms:play-services-ads:20.3.0'
```