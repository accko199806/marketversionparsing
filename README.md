# marketversionparsing-android
![AppScreen](https://github.com/accko199806/marketversionparsing/blob/master/appScreen.gif)
###### An example of parsing [Divisor Calculator](https://play.google.com/store/apps/details?id=net.accko.divisorcalculator).

When you update a new app, you'll need to notify users to update it. Help compare your current app version to the app version of the Google Play Store.

this process follow 4-steps:

1. Parsing the *App Version* in Google Play.

2. gets the version within the app.

3. Compare version.

4. alert *Update dialog*!

## Setup
This app uses the [jsoup library](https://jsoup.org) for parsing.
### Gradle
Edit `root/app/build.gradle` like below
```gradle
// jsoup HTML parser library @ https://jsoup.org/
compile 'org.jsoup:jsoup:1.11.2'
```

### AndroidManifest.xml
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## How to use
- Write the package name will be parsed.
```java
WhatsNewParsing.getPackageName("Write Your App Package");
```

- Add a String that gets the parsed *App Version*.
```java
String getWhatsNew = WhatsNewParsing.getWhatsNew();
```

- Add a String that gets the version within the app.
```java
String getAppVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
```

- Compare version.
```java
if (getAppVersion.toString().equals(getMarketVersion)) { // This is the latest version.
} else {
// Required update.
}
```

## License
```
Copyright 2018 acckolee

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```