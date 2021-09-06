# Copose Excercise: Ad Network
Exercise project to display AdMob ads on Android apps using Jetpack Compose.

## Takeaways
- Show AdMob traditional banner, adaptive banner, and inline adaptive banner.
- Show AdMob interstitial ad on button click from a composable.
- Extension function to get the current activity from any composable.
- [ ] FIXME: Load interstitial before showing screens.

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

Banner Ads | Interstitial Ad
:---: | :---:
![Screenshot_20210905_114421](https://user-images.githubusercontent.com/67064997/132116774-212ac4b4-0e3e-4886-86f8-59a925de972f.png) | ![Screenshot_20210905_114402](https://user-images.githubusercontent.com/67064997/132116776-9aa0ba07-d99e-4690-a126-e14e55703c3d.png)
