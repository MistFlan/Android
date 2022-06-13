# HelloWorld

创建一个Empty Activity项目

最低支持SDK选择API21:Android 5.0(Lollopop)

注意不能勾选使用android核心遗产库 Use legacy android.support libraries



### 一、原始项目分析

##### 1、Anroid Project目录分析

```tex
1. .gradle .idea 这两个目录存放Adnroid Studio自动生成的文件
2. app 项目中的代码与资源存放目录
3. build 编译时自动生成的文件
4. gradle gradle wrapper配置文件 使用gradle wrapper不需要下载gradle 会自动根据本地的缓存情况决定是否需要联网下载gradle Android Studio默认使用gradle wrapper 如果需要更改为离线模式 可以点击导航栏->File->Setting->Build,Execution,Deployment->Gradle
5. .gitignore 将指定的目录或文件排除在版本控制之外。
6. build.gradle 项目的gradle构建脚本 通常不需要修改
7. gradle.properties 项目的gradle配置文件 会影响所有模块的gradle编译脚本
8. gradlew gradlew.bat 在命令行界面中执行gradle命令 前者在Linux或Mac系统使用 后者在Windows系统中使用
9. HelloWorld.iml 根据项目名自动生成的文件 不需要修改
10. local.properties 指定本机的Andriod SDK路径
11. setting.gradle 指定项目中所有引入的模块 通常不需要修改
```

##### 2、app目录分析

```tex
1. build 编译时自动生成的文件 不需要修改
2. libs 如果使用第三方包 需要把这些jar包都放在libs目录下 放在这个目录下的jar包会自动添加到项目的构建路径中
3. androidTest 编写Android Test测试用例 对项目进行一些自动化测试
4. main->java 存放java和kotlin代码
5. main->res 所有图片布局字符串等资源都存放在这个目录下 图片drawable 布局layout 字符串values
6. main->AndroidManifest.xml 项目的配置文件 权限声明以及在代码中定义的四大组件都需要在这里注册
7. test 编写Unit Test测试用例 对项目进行自动化测试的另一种方式
8. .gitignore 与外层.gitignore类似
9. app.iml 项目自动生成的文件 不需要修改
10. build.gradle app模块的gradle构建脚本 指定很多项目构建相关的配置
11. proguard-rules.pro 用于指定项目代码的混淆规则
```

##### 3、项目启动相关

```kotlin
// 打开AndroidManifest.xml文件
// 这里表示注册MainActivity 没有在该文件注册的Activity无法使用
        <activity android:name=".MainActivity">
            <intent-filter>
				// 以下标签表示MainActivity是这个项目的主Activity
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

// 打开MainActivity
// MainActivity继承自AppCompatActivity 它ndroidX提供的一种向下兼容的Activity 是Activity在不同系统版本中的功能保持一致性 而Activity是Android系统提供的一个基类 所有自定义的Activity都必须继承它或者它的子类才能拥有Activity的特性
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

```

##### 4、项目资源

```kotlin
// res目录下 mipmap目录存放应用图标 values目录存放字符串样式颜色等配置 layout目录用于存放布局文件
// 需要另外创建drawable-hdpi drawable-xhdpi drawable-xxhdpi三个目录存放项目图片资源。在理想情况下，程序运行时会根据不同分辨率的高低选择加载不同目录的图片。当只有一份图片时，可以存放在xxhdpi目录下。这是最主流的设备分辨率目录。
// 运行在低分辨率的设备时。如果图片资源在上面三个目录都无法正常显示时，可以新建drawable-large-hdpi drawable-large-ldpi drawable-large-mdpi三个目录存放图片资源。

// 在AndroidManifest.xml文件中 android:icon用于指定应用图标 android:label用于指定应用名称
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
		······
    </application>
```

##### 5、项目构建

```kotlin
// Android Studio通过Gradle构建项目 它基于Groovy的领域特定语言DSL来进行项目设置，摒弃了创痛基于XML（如Ant。》和Maven）的各种繁琐配置

// 项目中存在多个build.gradle文件，一个在最外层目录，一个在app模块目录下。

// 首先在最外层目录的build.gradle中
// 两处repositories闭包中都声明了google()和jcenter()这两行配置。它们分别对应了一个代码仓库，google仓库中包含的主要是Google自家的扩展依赖库，而jcenter仓库中包含的大多是一些第三方的开源库。这两处声明使得可以在项目中轻松引用仓库中的依赖。
// dependencies闭包中使用classpath声明了两个插件：一个Gradle插件和一个Kotlin插件。由于Gradle插件并不是专门为构建Android项目而开发的，Java、C++等项目也可以使用Gradle构建，所以需要在这里声明。声明最后部分是插件的版本号，Gradle插件的版本号一般和AS版本相对应。如果是Java版的Android项目则不需要声明此插件。
// 通常情况下，并不需要修改这个文件的内容，除非添加一些全局的项目构建配置。
buildscript {
    ext.kotlin_version = '1.4.10'
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.6.2'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

// 接下来是app模块目录下的build.gradle文件
// 第一行应用了插件com.android.application表示当前模块是一个应用程序 com.android.library表示库模块 应用程序模块可以直接运行，库模块只能最为代码库依附于别的应用程序模块来运行。
// 接下来两行应用了kotlin-android kotlin-android-extensions这两个插件。其中第一个插件是Kotlin开发Android项目必须的。第二个插件实现了非常好用的Kotlin扩展功能。
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

// android闭包，可以配置项目构建的各种属性。compileSdkVersion用于指定项目的编译版本。buildToolsVersion用于指定项目构建工具的版本。
android {
    compileSdkVersion 30
    buildToolsVersion "30.0.3"

    // defaultConfig闭包，更详细的配置项目。applicationId是每一个应用的唯一标识符，绝对不能重复。minSdkVersion用于指定项目的最低编译版本。targetSdkVersion用于指定目标支持版本。versionCode用于指定项目的版本号。versionName用于指定项目的版本名称。testInstrumentationRunner用于在当前项目中弃用JUnit测试。
    defaultConfig {
        applicationId "com.flandre.android"
        minSdkVersion 21
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    // buildTypes闭包，用于指定生成安装文件的相关配置。debug闭包用于指定生成测试版本安装文件的配置，可以忽略不写，release闭包用于指定正式版本。minifyEnabled用于指定是否对项目的代码进行混淆，false表示不进行混淆。proguardFiles用于指定混淆时使用的规则文件,第一个txt文件在<Android SDK>/tools/proguard目录下，该文件时所有项目通用的混淆规则。第二个pro文件在当前项目的根目录下，用于编写当前项目特有的混淆规则。通过AS直接运行项目生成的都是测试版安装文件。
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}

// dependencies闭包，用于指定当前项目所有的依赖关系。通常AS项目一共有3种依赖方式：本地依赖、库依赖和远程依赖。本地依赖可以对本地的jar包或者目录添加依赖关系，库依赖可以对项目种的库米凯添加依赖关系，远程依赖则可以对jcenter仓库上的开源项目添加依赖关系。
// 第一行就是本地依赖声明，它表示将libs目录下的所有.jar后缀的文件都添加到项目的构建路径中。而implementation则是远程依赖声明，androidx.appcompat:appcompat:1.0.2就是一个标准的远程依赖库格式，其中androidx.appcompat是域名部分，用于和其他公司的库做区分，appcompat是工程名部分，用于和同一个公司中不同的库工程做区分，1.0.2是版本号，用于和同一个库不同的版本做区分。Gradle在构建项目时首先会检查一个本地是否已经有这个库的缓存，如果没有则会自动联网下载，再添加到项目的构建路径中。库依赖格式则是implementation project(':库名称')。testImplementation和androidTestImplementation都是用于声明测试用例库，暂时用不到。
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.core:core-ktx:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}
```

##### 6、项目调试工具

```kotlin
// 使用Log工具类android.util.Log 这个类提供了5个方法打印日志
// Log方法需要多个参数，第一个参数一般是tag，一般传入类名，主要用于对打印信息进行过滤。第二个参数一般是msg，用于输出具体内容
// 用于答应琐碎的、意义最小的日志信息。对应级别verbose
Log.v()
// 用于打印一些调试信息。对应级别debug
Log.d()
// 用于打印一些比较重要的数据，这些数据应该是可以帮助分析以用户行为的数据，对应级别info
Log.i()
// 用于打印警告信息，提示程序在这个地方可能会有潜在的风险，对应界别warn
Log.w()
// 用于打印程序中的错误信息，比如程序进入了catch语句中，对应级别error
Log.e()

// 创建自定义过滤器。点击底部Logcat标签，展开右上角的下拉框，选择Edit Filter Configuration,在弹出的对话框中的Filter Name栏填写过滤器名称，在Log Tag栏填写想要过滤的tag标签。

// Logcat日志级别控制。主要有5个级别，分表对应Log类的5个打印方法，每个选项都会过滤掉不包括自己一下等级的日志。
```



### git需要忽略的文件

```xml
.gradle
/local.properties
/.idea/workspace.xml
/.idea/libraries
.DS_Store
/build
/captures
 
# Built application files
*.apk
*.ap_
 
# Files for the Dalvik VM
*.dex
 
# Java class files
*.class
 
# Generated files
bin/
gen/
 
# Gradle files
.gradle/
build/
 
# Local configuration file (sdk path, etc)
local.properties
 
# Proguard folder generated by Eclipse
proguard/
 
# Log Files
*.log
 
# Others
.idea/
*.iml
```

