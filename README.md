# LoL Champions

Android application written in **Kotlin** that fetches data from the **Riot Games Data Dragon REST API** and displays League of Legends champions.

## Features
- Champion list view (name + title)  
- Detailed champion view (description, tags, stats, splash image)  
- Navigation between list and details  
- Custom app icon created in **GIMP**  

## Tech Stack
- **Kotlin**  
- **Jetpack Compose** for UI  
- **ViewModel & StateFlow** for state management  
- **Retrofit & Moshi** for REST API communication  
- **Kotlin Coroutines** for asynchronous operations  
- **Coil** for image loading  
- **Navigation-Compose** for navigation between screens  

## API
This app uses the free **Riot Games Data Dragon API**:  
- Versions: `https://ddragon.leagueoflegends.com/api/versions.json`  
- Champions list: `https://ddragon.leagueoflegends.com/cdn/{VERSION}/data/en_US/champion.json`  
- Champion images & splash arts from the CDN  

