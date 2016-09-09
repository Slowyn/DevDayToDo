# to-do

Very simple application for [DevDay conference](https://devday.ru/). 

## How to run
```
$ npm i
$ lein prod-build
```
Then run in iOS from xcode or android by `react-native run-android`

## How to develop
This application is based on re-natal.

1. Select your preferred android device
```
$ re-natal use-android-device real|avd|genymotion
```
2. Start figwheel
```
$ re-natal use-figwheel
$ lein figwheel ios|android
```
3. Run app from xcode (iOS) or `react-native run-android`

## Screenshots
![dashboard-empty](https://raw.githubusercontent.com/Slowyn/DevDayToDo/master/demo/1.png)
![add-todo](https://raw.githubusercontent.com/Slowyn/DevDayToDo/master/demo/2.png)
![dashboard-list-view](https://raw.githubusercontent.com/Slowyn/DevDayToDo/master/demo/3.png)
![todo](https://raw.githubusercontent.com/Slowyn/DevDayToDo/master/demo/4.png)
![todo-done](https://raw.githubusercontent.com/Slowyn/DevDayToDo/master/demo/5.png)
