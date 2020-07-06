# BitpandaTask1

Hi!

I've created an Android Application using classic *MVVM* with Repository from Android architecture samples.y
As the application is not big I didn't implemented UseCases, but with a current structure it's easy to do.

Repository contains only `WebService` which was provided in the task. Repository is also expandable with using some sort of LocalDataSource.

## Instruments and libs
1. ServiceLocator (Injector). For dependency injection I didn't use `dagger` or `koin` as project is not big. 
So to implement DI I used simple `Injector` pattern by Google
2. Coroutines. To make proper environment for getting data I used kotlin coroutines. We have mocked data but with coroutines it'll be 
easier to replace existing `DummyWebservice` to the real one
3. LiveData. For interraction between View and ViewModel I used `LiveData`. It's really nice to manage ViewState. It's also could be easily tested
4. To manage entity I used Kotlin `sealed` classes
5. I used `androidx` and `kotlin-extentions` for nice looking code and tests

Also here I wanted to add answers to your questions:
1. For me as a developer code is the most important thing in the apps. As it's not really possible (legal) to get a source code of apps from google play 
I can't really say that they did a good job. From the user perspective I like these apps:

https://play.google.com/store/apps/details?id=com.duolingo

https://play.google.com/store/apps/details?id=com.google.android.apps.photos

https://play.google.com/store/apps/details?id=ua.gov.diia.app

I like the design and stability of those apps

2. As an agile person I would make the proper preparation and planning of the story. In case if there is no UI/UX designer in the team
I would show different approaches to measure any kind of performance to the boss. My choice would base on my experience.
Then together we can decide which approach looks better from his perspective. The fact that boss is iOS user could bring some discussion. 
To explain Android approach I would use official links to Google Design guidelines, which should be followed by successful Android Application.
Also all requirements should be clarified. After this I can start planning the feature. Only after planning the implementation could be started.

3. In my spare time I like to try new things in Android Development by google and write tech articles. The recent technology I tried was `Jetpack Compose`. 
Also I do some Flutter development
