SlidingIntoView 

[![API](https://img.shields.io/badge/API-11%2B-brightgreen.svg?style=flat-square)](https://android-arsenal.com/api?level=9) [![License](https://img.shields.io/npm/l/express.svg?style=flat-square)](https://opensource.org/licenses/MIT) [![Jitpack](https://jitpack.io/v/ihsanbal/SlidingIntoView.svg?style=flat-square)](https://jitpack.io/#ihsanbal/SlidingIntoView) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SlidingIntoView-green.svg?style=flat-square)](https://android-arsenal.com/details/1/4534) 
<a href="http://www.methodscount.com/?lib=com.github.ihsanbal%3ASlidingIntoView%3A1.0.1"><img src="https://img.shields.io/badge/Methods and size-core: 91 | deps: 16517 | 25 KB-e91e63.svg"/></a>
================

Simple Sliding Intro View

![Illustration of behavior](https://github.com/ihsanbal/SlidingIntoView/blob/master/device-2016-10-12-125715.gif "Illustration of behavior")


How to use
----------

Import dependency using Gradle or Maven:

```groovy
repositories {
	    maven {
	        url "https://jitpack.io"
	    }
	}

dependencies {
	         compile 'com.github.ihsanbal:SlidingIntoView:1.0.1'
	}
```
or Maven:
```xml
<repository>
   <id>jitpack.io</id>
   <url>https://jitpack.io</url>
</repository>

<dependency>
	    <groupId>com.github.ihsanbal</groupId>
	    <artifactId>SlidingIntoView</artifactId>
	    <version>1.0.1</version>
</dependency>
```

Example implementation:

```xml
<com.ihsanbal.introview.IntroView
        android:id="@+id/intro.view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:paddingTextLeft="14dp"
        app:paddingTextRight="14dp"
        app:paddingTitleLeft="14dp"
        app:paddingTitleRight="14dp"
        app:paddingTitleTop="30dp"
        app:scaleType="center"
        app:scrollDelay="5000"
        app:scrollDuration="5"
        app:textColor="#fc2f2f"
        app:textSize="7sp"
        app:titleTextColor="#ffffff"
        app:titleTextSize="13sp" />
```

```java
mIntroView.init(getSupportFragmentManager(),
                getResources().getStringArray(R.array.titles),
                getResources().getStringArray(R.array.texts),
                R.drawable.istanbul_wp, R.drawable.rize_wp, R.drawable.diyarbekir_wp, R.drawable.izmir_wp,...);
```


Parameters:
-----

You can control these parameters via XML:

```
        <attr name="scrollDuration" format="integer" />
        <attr name="scrollDelay" format="integer" />
        <attr name="paddingTitleTop" format="dimension" />
        <attr name="paddingTitleBottom" format="dimension" />
        <attr name="paddingTitleLeft" format="dimension" />
        <attr name="paddingTitleRight" format="dimension" />
        <attr name="paddingTextTop" format="dimension" />
        <attr name="paddingTextBottom" format="dimension" />
        <attr name="paddingTextLeft" format="dimension" />
        <attr name="paddingTextRight" format="dimension" />
        <attr name="titleTextColor" format="color" />
        <attr name="textColor" format="color" />
        <attr name="titleTextSize" format="dimension" />
        <attr name="textSize" format="dimension" />
        <attr name="scaleType" format="enum">
            <enum name="center" value="0" />
            <enum name="centerCrop" value="1" />
            <enum name="centerInside" value="2" />
            <enum name="fitCenter" value="3" />
            <enum name="fitEnd" value="4" />
            <enum name="fitStart" value="5" />
            <enum name="fitXY" value="6" />
            <enum name="matrix" value="7" />
        </attr>
```


Changelog
---------

* 1.0.1 - initial release


License
----

Library for Android

Copyright (c) 2015 İhsan BAL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
