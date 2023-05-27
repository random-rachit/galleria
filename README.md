# Welcome to *Galleria*

- To run the project, you just need to clone the project in your favourite directory and open it in
  Android Studio.
- Once opened, wait for all the dependencies to download and Gradle Sync to complete.
- You'll see the run option enabled in your Android Studio toolbar, just click on it with your
  device/emulator connected and enjoy the app.

## Points to note for users

- Minimum API version required to run this app is 26. Please use a device with Oreo version of
  Android or later.
- The app requires media permissions to access images in the device. No other files are accessed.
- If you are accessing this doc from your device, there is a release apk tagged in the repository
  which you can download and install directly to your device. Or
  just [click here](https://github.com/random-rachit/galleria/releases/download/release/app-release-v1.apk)
  .

## Points to note for reviewers

- The app uses latest version of Navigation component to navigate between fragments, Paging 3
  Library for paginating the response from Content Resolver, Hilt for dependency injection and Glide
  for Image loading.
- As there is no network operation in the app, the splash screen checks for the required permission
  and proceeds to the gallery irrespective of the choice.
- For time saving, the design implementation of the app is quite simple, it still supports dark
  theme for all UI components.

## A peek in the app

![enter image description here](https://res.cloudinary.com/rachitbhutani1998/image/upload/v1685190244/Screenshot_20230527-174637_i3uyw1.png)![enter image description here](https://res.cloudinary.com/rachitbhutani1998/image/upload/v1685190244/Screenshot_20230527-174721_yzzfpy.png)![enter image description here](https://res.cloudinary.com/rachitbhutani1998/image/upload/v1685190244/Screenshot_20230527-174735_hay9n8.png)