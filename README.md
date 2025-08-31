# LoL Champions

Mobile app made in Kotlin that shows League of Legends champions using the free Data Dragon API.  
The app lists all champions with their name and title, and when you tap on one you can see details like description, tags, base stats and splash image.  

## Features
- List of champions (name + title)
- Details screen with description, stats, tags and splash image
- Back navigation between screens
- Custom launcher icon created in GIMP

## Tech
- Kotlin  
- Jetpack Compose for UI  
- ViewModel + StateFlow  
- Retrofit + Moshi for API calls  
- Coroutines for background work  
- Coil for image loading  
- Navigation-Compose  

## API
Riot Data Dragon endpoints:  
- Versions: `https://ddragon.leagueoflegends.com/api/versions.json`  
- Champions: `https://ddragon.leagueoflegends.com/cdn/{VERSION}/data/en_US/champion.json`  
- Images from the CDN (icons and splash arts)  

## How to run
1. Clone the repo and open in Android Studio.  
2. Use JDK 17 for Gradle.  
3. Sync Gradle and build the project.  
4. Run on a device or emulator (minSdk 24).  

