# 打造一篇即简组件化MVPforAndroid的项目

其实很早以前就打算写一篇关于组件化的项目,因为时间原因（其实就是自己比较懒），一直迟迟没用行动，最近经过自己的天人交战，决定好好完善这篇项目。项目会参考《玩Android》毕竟它的API是公开的，有助于我快速开展。
这个项目设计的知识点比较多，也是对自己知识点的一个梳理。《项目会持续更新....》
功能点：

 1. 组件化 
 2. MVP
 3. 路由Route
 4. Rxjava
 5. RxAandroid
 6. Retrofit
 7. OkHttp
 8. Gson
 9. Glide
 10. .........


第一个基础版本已经产生，稍后会抽时间去介绍：

v1.0.0

maven

```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
	
	<dependency>
	    <groupId>com.github.Johnjson</groupId>
	    <artifactId>MVPforAndroid</artifactId>
	    <version>v1.0.0</version>
	</dependency>


```

gradle

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	
	dependencies {
	        implementation 'com.github.Johnjson:MVPforAndroid:v1.1.0'
	}


```
