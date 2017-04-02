# Use-cases and examples

This repository contains examples for Android with generated code, use-cases and tests of the annotation based [Code Generator](http://uc-mobileapps.com/android-code-generator/ "Android Code Generator") that integrates with existing Android and Java based development environments.

It shows 
* creating SQL database peer classes including the DDL 
* the versioning mechanism via the SQLiteOpenHelper
* ContentProvider with create/read/update/delete implementations
* inline model definitions via source level annotations

  no additional files duplicating the model definitions are required

# Building 

You need gradle to build the examples e.g. with Android Studio

`gradle build` will build the APK and run the tests.

# License
for the examples of this repository
```
Copyright (C) 2017 Klaus Sausen

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
