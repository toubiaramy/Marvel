
# Marvel Android Application 

This is an android application developped using Kotlin.
This application retreive marvel characters from marvel server and allows to see check each character comis, events, stories and series.



# Tech Stack

### Architecture

Model — View — ViewModel **(MVVM)**  : Suggests separating the data presentation logic(Views or UI) from the core business logic part of the application. 

* Model: This layer is responsible for the abstraction of the data sources. Model and ViewModel work together to get and save the data.
* View: The purpose of this layer is to inform the ViewModel about the user’s action. This layer observes the ViewModel and does not contain any kind of application logic.
* ViewModel: It exposes those data streams which are relevant to the View. Moreover, it serves as a link between the Model and the View.


### Dependency Injection

* Hilt : Provides a standard way to incorporate Dagger dependency injection into an Android application.

### REST client

* Retrofit: Retrofit is type-safe REST client for Android and Java which aims to make it easier to consume RESTful web services
* Gson: This is a converter to convert serialise JSON into Gson.

### Database

* Room Database: Room is a persistence library, part of the Android Jetpack. It provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

### Image Loader

* Glide: It is an Image Loader Library for Android developed by bumptech and is a library that is recommended by Google.

### Navigation

* Navigation Components: Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within android app.

### Testing 

* Junit4
* Mockito

### Others

* Kotlin Coroutines
* kotlin Flows
* Data Binding
* View Binding
## Authors

- [@toubiaRamy](https://github.com/toubiaramy)


## Deployment

To deploy this project

```bash
  Download the source code from git. 
  Import the code to android studio.
  Sync and build the application. 
  Now you can install it on emulators or any physical device.
```


## Screenshots

![App Screenshot](https://github.com/toubiaramy/Marvel/blob/main/listCharacters.png?raw=true) 

![App Screenshot](https://github.com/toubiaramy/Marvel/blob/main/listCharacterDetails.png?raw=true)
