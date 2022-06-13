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



### 二、Kotlin快速入门

##### 1、Kotlin简介

```tex
Kotlin是由JetBrains公司开发和设计的。2011年公布了第一个版本，2012年开源。2016年发布1.0正式版。2017年Google宣布Kotlin正式成为Android一级开发语言。
虽然Java需要通过javac编译为class文件，但是编译生成的并不是计算机可识别的二进制文件，只有通过JAVA虚拟机才能识别。所以Java属于解释性语言。
只要能将代码编译成相同规格的class文件，Java虚拟机就能识别。所以Kotlin能取代Java开发Andrid。
Kotlin语法更简洁。并且在安全性方面更优秀。几乎杜绝了空指针。
```

##### 2、如何运行Kotlin代码

```tex
和Java一样，只需要创建一个有主函数的类即可点击代码左边的箭头图标运行。
右键NEW-Kotlin File/Class-Class 输入名称。
```

##### 3、开始编程

###### 3.1 变量

```kotlin
// Java是强类型语言 所以创建变量时首先需要声明变量类型
// Kotlin和JS类似 但是只有允许val和var
// val是value的简写 用来声明一个不可变的变量 在初始赋值之后就再也不能重新赋值 对应Java的final声明
// var是variable的简写 用来声明一个可变的变量 在初始赋值之后仍然可以再被重新赋值
// 定义一个变量a 并赋值10 变量a就会自动推导成整型变量
fun main() {
    val a = 10
    println("a = " + a)
}

// 如果需要对一个变量延迟赋值 Kotlin就无法自动推导它的类型了 所以Kotlin提供了显式声明的方法
val a: Int = 10

// 如果对val变量重新赋值会报错
fun main() {
    var a: Int = 10
    a = a * 10
    println("a = " + a)
}

// 一个好的编程习惯是 除非一个变量明确允许被修改 否则都应该加上静态final关键字
// 优先是同val来声明变量 当val不能满足需求时再改为var
```

###### 3.2 函数

```kotlin
// 函数是运行代码的载体 运行函数时 函数中的所有代码都会全部运行
// Kotlin的method语法
// fun是定义函数的关键字 methodName是方法名
// param1: Int, param2: String是入参 声明格式为 参数名:参数类型
// Int是可选 用于声明函数会返回声明类型的数据 如果不需要返回可以忽略
fun methodName(param1: Int, param2: String): Int {
    return 0
}

// 定义一个largerNumber函数 接收两个整型参数 返回两个参数中更大的数
// 函数体内使用了max函数 这个函数是Kotlin提供的一个内置函数 如果使用了自动补全功能 会自动导入import kotlin.math.max 而不需要手动导入
fun largerNumber(num1: Int, num2: Int): Int {
    return max(num1, num2)
}
// 接下来调用largerNumber函数
fun main() {
    val hajiang = 8
    val ayu = 9
    val largerNum = largerNumber(hajiang, ayu)
    println("largerNumber is " + largerNum)
}

// 语法糖 当一个函数只有一行代码 可以不必编写函数体 可以直接将唯一的一行代码卸载函数定义的尾部 中间用等号连接
fun largerNumber(num1: Int, num2: Int): Int = max(num1, num2)
// 由于max函数返回的是Int类型 所以Kotlin可以推导出largerNumber函数返回类型也是Int 这样就可以不必显式声明返回值类型
fun largerNumber(num1: Int, num2: Int) = max(num1, num2)
```

###### 3.3 逻辑控制

```kotlin
// Kotlin的条件语句主要有两种实现方式 if和when
fun largerNumber(num1: Int, num2: Int): Int {
    var value = 0
    if (num1 > num2) {
        value = num1
    } else {
        value = num2
    }
    return value
}
// Kotlin的if语句是可以有返回值的 返回值就是if语句每一个条件中最后一行代码的返回值 所以可以简写如下
// 这里由于是直接赋值给value 没有重复赋值 所以可以使用val来声明value
fun largerNumber(num1: Int, num2: Int): Int {
    val value = if (num1 > num2) {
        num1
    } else {
        num2
    }
    return value
}
// 还可以直接使用if语句直接返回
fun largerNumber(num1: Int, num2: Int): Int {
    return if (num1 > num2) {
        num1
    } else {
        num2
    }
}
// 虽然largerNumber不止一行 但是这个if语句可以视为一个整体 可以使用等号串连在函数定义的尾部
fun largerNumber(num1: Int, num2: Int) = if (num1 > num2) {
    num1
} else {
    num2
}
// 还可以再次精简
fun largerNumber(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

// 编写一个查询成绩的功能 通过输入姓名 返回分数 如果使用if语句来实现
fun getScore(name: String) = if (name == "HaJiang") {
    8
} else if (name == "ayu") {
    110
} else 0
// 如果使用when语句来实现
fun getScore(name: String) = when (name) {
    "HaJiang" -> 8
    "Yu" -> 110
    else -> 0
}
// when语句允许传入一个任意类型的参数 然后在when结构体中定义一些列的条件 格式为 匹配值->{执行逻辑} 当执行逻辑只有一行代码时 {}可以省略
// 除了精确匹配 when语句还运行进行类型匹配 is就是类型匹配的核心 相当于Java的instanceof关键字
fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number is Unknown")
    }
}
// when语句支持不带参数使用
fun getScore(name: String) = when {
    name == "HaJiang" -> 8
    name == "Yu" -> 110
    else -> 0
}
// 假设所有H开头的名字都返回同样分数 则可以通过不带参数的when扩展
fun getScore(name: String) = when {
    name.startsWith("H") -> 8
    name == "Yu" -> 110
    else -> 0
}

// Kotlin也提供了while和for while语句和Java基本一致 for-i循环在Kotlin被舍弃 for-each循环则被Kotlin进行大幅加强 变成for-in
// 首先需要创建一个闭区间
fun main() {
    var range = 0..10
}
// 然后通过for-in循环遍历
fun main() {
    for (i in 0..10) {
        println(i)
    }
}
// Kotlin可以通过until关键自来创建一个左闭右开的区间 即[0, 10)
fun main(){
    val range = 0 until 10
}
// 这个循环将不输出10
fun main() {
    for (i in 0 until 10) {
        println(i)
    }
}
// 默认情况下for-in循环每次执行时会在区间范围内递增1 相当于Java for-i循环的i++ 如果想跳过其中的一些元素可以使用step关键字 相当于i=i+ step后的数字
fun main() {
    for (i in 0 until 10 step 2) {
        println(i)
    }
}
// 如果想创建一个降序的区间 可以使用downTo关键字
fun main() {
    for (i in 10 downTo 1) {
        println(i)
    }
}
```

###### 3.4 面向对象

```kotlin
// 典中典 忽略
// 创建一个Person类 右键New->Kotlin File/Class->Class 输入类名
// Kotlin中实例一个类的方式和Java时基本类似的 简略了new关键字 当你调用某个类的构造函数时 你的意图只可能时对这个类进行实例化 因此即使没有new关键字 也能清晰表达出你的意图
class Person {
    var name = ""
    var age = 0

    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}

fun main() {
    val p = Person()
    p.name = "HaJiang"
    p.age = 7
    p.eat()
}

// 创建一个Student类继承Person类 首先需要让Person类可以被继承 在Kotlin中所有非抽象类默认都无法被继承 相当于在Java给类声明了final关键字 因为抽象类本身无法创建实例 必须由子类去继承它才能创建 所以抽象列必须可以被继承
open class Person {
    var name = ""
    var age = 0

    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}

class Student : Person() {
    var sno = ""
    var grade = 0
}

// 任何一个面向对象的编程语言都会有构造函数的概念 Kotlin将构造函数分成了两种 主构造函数和次构造函数 每个类默认都会有一个不带参数的主构造函数 也可以显式的给这个默认的主构造函数声明参数 直接定义在类名的后面
class Student(val sno: String, val grade: Int) : Person() {

}
// 如果声明了主构造函数 则在实例化对象时 必须传入主构造函数的要求的所有参数
fun main() {
    val student = Student("S123", 5)
}
// 如果需要在主构造函数里增加逻辑 可以使用init结构体
class Student(val sno: String, val grade: Int) : Person() {
    init {
        println("sno is : " + sno)
        println("grade is : " + grade)
    }
}
// 因为子类的构造函数必须调用父类的构造函数 所以如果Person也显示指定了主构造函数 子类Student就必须调用 可以在init里添加 这或许是一种方法 但绝不是最好的方法 因为在大多数的场景下 我没是不需要编写init结构体的
open class Person(val name: String, val age: Int) {
    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}
// 在Student类的主构造函数中增加父类Person的主构造函数参数时 不能使用val和var来指定参数 否则会导致和父类同名冲突 所以这里传给父类的参数不需要任何关键字 让它的作用域仅限定在主构造函数当中
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) {
    init {
        println("sno is : " + sno)
        println("grade is : " + grade)
    }
}
// 实例化对象
fun main() {
    val student = Student("S123", 5, "HaJiang", 7)
    student.eat()
}

// Kotlin规定 当一个类既有主构造函数 又有次构造函数时 所有的次构造函数都必须调用主构造函数包括间接调用
// 次构造函数时通过constructor关键字来定义的 这里定义了两个次构造函数 第一个次构造函数接收name和age参数 有通过this关键字调用了主构造函数 并将sno和grade赋成初始值 第二个次构造函数通过调用第一个次构造函数间接调用了主构造函数 所以能通过语法检查
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) {
    init {
        println("sno is : " + sno)
        println("grade is : " + grade)
    }

    constructor(name: Stirng, age: Int) : this("", 0, name, age) {}

    constructor() : this("", 0) {}
}
// 通过三个构造函数实例化对象
fun main() {
    val student1 = Student()
    student1.eat()
    val student2 = Student("HaJiang", 7)
    student2.eat()
    val student3 = Student("S123", 5, "HaJiang", 7)
    student3.eat()
}

// Kotlin允许类只有次构造函数 没有主构造函数 当一个类没有显式的定义主构造函数且定义了次构造函数时 它就是没有主构造函数的
// 首先Student1类没有显式的定义主构造函数 同时又定义了次构造函数 所以现在Student1类时没有主构造函数的 所以继承Person时也不需要加括号 因为没有主构造函数 所以次构造函数只能直接调用父类的构造函数 所以需要使用super关键字
class Student1 : Person {
    constructor(name: String, age: Int) : super(name, age) {}
}
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

