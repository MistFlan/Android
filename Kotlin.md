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
// Android Studio通过Gradle构建项目 它基于Groovy的领域特定语言DSL来进行项目设置，摒弃了基于XML（如Ant和Maven）的各种繁琐配置

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
// 用于打印琐碎的、意义最小的日志信息。对应级别verbose
Log.v()
// 用于打印一些调试信息。对应级别debug
Log.d()
// 用于打印一些比较重要的数据，这些数据应该是可以帮助分析以用户行为的数据，对应级别info
Log.i()
// 用于打印警告信息，提示程序在这个地方可能会有潜在的风险，对应级别warn
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
// 创建一个Person类 右键New->Kotlin File/Class->Class 输入类名
// Kotlin中实例一个类的方式和Java是基本类似的 简略了new关键字 当你调用某个类的构造函数时 你的意图只可能是对这个类进行实例化 因此即使没有new关键字 也能清晰表达出你的意图
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

// Kotlin和Java一样 都是单继承结构的语言 任何一个类最多只能继承一个父类 但可以实现任意多个接口 可以在接口类中定义一系列的抽象行为 然后由具体的类去实现
// 定义一个Study类让Student实现 Kotlih中统一使用冒号表示继承和实现 中间用逗号进行分割 接口没有构造函数 所以不需要添加括号 Kotlin使用override关键字来重写父类或者实现接口中的函数
interface Study {
    fun readBooks()
    fun doHomework()
}

class Student(name: String, age: Int) : Person(name, age), Study {
    override fun readBooks() {
        println(name + " is reading.")
    }

    override fun doHomework() {
        println(name + " is doing homework.")
    }
}
// 由于Student实现了Study类 所以doStudy可以接收Student类 并且调用函数 这就是面向接口编程 即多态
fun main() {
    val student = Student("Hajiang", 7)
    doStudy(student)
}

fun doStudy(study: Study) {
    study.readBooks()
    study.doHomework()
}

// Kotlin还支持对接口中定义的函数进行默认实现 JDK1.8也支持这个功能
// 在抽象方法doHomework后添加方法体 这个方法体的内容就是它的默认实现 当一个类实现Study接口时 只会强制要求实现readBooks()函数 而doHomeWord()函数则可以选择自由实现 不实现时就会自动使用默认的实现逻辑
interface Study {
    fun readBooks()
    fun doHomework() {
        println("do homework default implementation.")
    }
}

class Student(name: String, age: Int) : Person(name, age), Study {
    override fun readBooks() {
        println(name + " is reading.")
    }
}

// Java中有public、private、protected和default4种函数可见性修饰符 Kotlin也有4种public、private、protected和internal直接定义在fun关键字前面
// private 在两种语言种的作用是一致的 都表示只对当前类内部可见 
// public 虽然作用也是一致的 对所有类都可见 但是在Kotlin种public是默认修饰符 Java则是defalut
// protected 在Java中表示对当前类、子类和同一包路径下的类可见 而在Kotlin中则表示只对当前类和子类可见
// internal Kotlin抛弃了Java中的defalutdui同一包路径下的类可见 引入了新的可见性概念internal 对同一模块中的类可见 有一些函数只允许在模块内部调用 不想暴露给外部就可把函数声明成internal

// 在一个规范的系统架构中 数据类通常占据着非常重要的角色 用于将服务端或数据库的数据映射到内存中 为编程逻辑提供数据模型的支持 数据类通常需要重写equals()、hashCode()、toString()等方法 其中equals()方法用于判断两个数据类是否相等 hashCode()方法作为equals()的配套方法 也需要重写 否则会导致Map、Set类等hash相关的系统类无法正常使用 toString()方法用于提供清晰的输入日志
// 新建一个类 当一个类使用data关键字声明时 就表示这个类是一个数据类 Kotlin根据主构造函数中的参数帮你将equals()、hashCode()、toString()等方法自动生成 当一个类中没有任何代码时 可以忽略大括号
// 使用data关键字声明的类 创建同一个数据 他们的对象是相等的 反之亦然
data class CellPhone(val brand: String, val price: Double)

fun main() {
    val cellPhone1 = CellPhone("Apple", 6666.666)
    val cellPhone2 = CellPhone("Apple", 6666.666)
    println(cellPhone1)
    println("cellphone1 equals cellPhone2 " + (cellPhone1 == cellPhone2))
}

// 单例模式是最常用、最基础的设计模式之一 用于避免创建重复的对象 全局唯一
// 在Kotlin中创建单例类只需要将class关键字改成object关键字即可 右键New->Kotlin File/Class->Object
// 不需要像Java中私有构造函数 提供getInstace()方法 调用也类似Java中静态方法的调用 虽然看上去像是静态方法的调用 Kotlin在背后自动帮我们创建了一个Singleton类的实例 并保证全局唯一
object Singleton {
    fun singletonTest(){
        println("Singleton is called.")
    }
}
fun  main() {
    Singleton.singletonTest()
}
```

###### 3.5 Lambda

```Kotlin
// 许多现代高级编程语言在很早之前就开始支持Lambda编程 JDK1.8之后加入了Lambda编程的语法支持 Kotlin第一个版本就支持Lambda编程

// 传统意义上的集合主要指List和Set 再广泛一点 像Map这样的键值对数据结构也可以包含进来 List、Set和Map在Java中都是接口 List主要实现类是ArrayList和LinkedList Set的主要实现类是HashSet Map的主要实现类是HashMap
// 这种初始化集合的方式比较繁琐
 fun main(){
    val list = ArrayList<String>()
    list.add("HaJiang")
    list.add("ayu")
    list.add("Flan")
}
// Kotlin专门提供了一个内置的listOf()函数来简化初始化集合的写法 for-in不仅可以用来遍历区间 还可以用来遍历集合
fun main() {
    val list = listOf("HaJiang", "ayu", "Flan")
    for (name in list) {
        println(name)
    }
}
// 不过需要注意的是 listOf()函数创建的是一个不可变的集合 该集合只能用于读取 无法对集合进行添加、修改或删除操作 这和val关键字、类默认不可继承的设计初衷是类似的 Kotlin在不可变性方面控制得极其严格 可以通过mutableListOf()来创建一个可变集合
fun main(){
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    list.add("ZhuFanDe")
    for (name in list) {
        println(name)
    }
}

// Set集合得用法几乎一致 只是将创建集合得方式换成了SetOf()和mutableSetOf()函数 需要注意 Set集合底层是使用hash映射机制来存放数据得 因此集合中得元素无法保证有序 这事和List集合最大得不同处
fun main() {
    val set = setOf("HaJiang", "ayu", "Flan")
    for (name in set) {
        println(name)
    }
}
fun main() {
    val set = mutableSetOf("HaJiang", "ayu", "Flan")
    set.add("ZhuFanDe")
    for (name in set) {
        println(name)
    }
}

// Map是一种键值对形式的数据结构 隐藏在用法上和List、Set集合有较大的不同 传统的Map用法是创建一个HashMap的实例 然后将键值对添加到Map
fun main() {
    val map = HashMap<String, Int>()
    map.put("HaJiang", 8)
    map.put("ayu", 1)
    map.put("Flan", 233)
}
// 在Kotlin中并不建议使用put()和get()方法来对Map进行添加和读取数据操作 而是使用一种类似于数组下标的语法结构
fun main(){
    val map = HashMap<String, Int>()
    map["HaJiang"] = 8
    map["ayu"] = 1
    map["Flan"] = 233

    println(map["HaJiang"])
}
// 这仍然不是最简便的写法 Kotlin提供了mapOf()和mutableMapOf()方法来继续简化Map的用法 在mapOf()函数中 可以直接传入初始化的键值对组合来完成对Map集合的创建 这里的键值对组合看上去好像是使用to这个关键字来进行关联的 但其实to并不是关键字 二十一个infix函数
fun main() {
    val map = mapOf("HaJiang" to 8, "ayu" to 1, "Flan" to 233)
}
// 遍历Map集合的数据 使用的仍然是for-in循环 在循环中 将Map的键值对变量一起声明到一堆括号里面 当进行循环遍历时 每次遍历的结果就会赋值给这两个键值对变量
fun main() {
    val map = mapOf("HaJiang" to 8, "ayu" to 1, "Flan" to 233)
    for ((name, number) in map) {
        println("name is " + name + " number is " + number)
    }
}

// 集合的函数式API有很多 重点学习函数式API的语法结构 也就是Lambda表达式的语法结构
// Lambda就是一小段可以作为参数传递的代码 正常情况下 向某个函数传参时只能传入变量 而借助Lambda允许传入一小段代码 Kotlin没有限制代码的长度 但是通常不建议在Lambda表达式中编写太长的代码 否则可能会影响代码的可读性
// Lambda表达式的语法结构 最外层式一对大括号 如果有参数传入到Lambda表达式中的话 还需要声明参数列表 参数列表的结尾使用一个->符号 表示参数列表的结束以及函数体的开始 函数体中可以编写任意行代码 不建议编写太长的代码 最后一行代码会自动作为Lambda表达式的返回值 在很多情况下 并不需要使用Lambda表达式完整的语法结构 有很多种简化的写法
{参数名1: 参数类型， 参数名2: 参数类型 -> 函数体}
// 如果使用集合的函数式API 就可以只用一行代码来实现 其实maxByOrNull只是一个普通函数 不过它接收的是一个Lambda类型的参数 并且会在遍历集合式将每次遍历的值作为参数传递给Lambda表达式 其实maxByOrNull函数的工作原理是根据传入的条件来遍历集合找到该条件先的最大值
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    var maxLengthName = ""
    for (name in list) {
        if (name.length > maxLengthName.length) {
            maxLengthName = name
        }
    }
    println("max length name is " + maxLengthName)
}
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val maxLengthName = list.maxByOrNull { it.length }
    println("max length name is " + maxLengthName)
}

// 使用Lambada表达式的语法结构
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val lambda = { name: String -> name.length }
    val maxLengthName = list.maxByOrNull(lambda)
    println("max length name is " + maxLengthName)
}
// 可简化的点很多 首先 不需要专门定义一个lambda变量 可以直接将lambda表达式传入maxByOrNull函数当中
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val maxLengthName = list.maxByOrNull({ name: String -> name.length })
    println("max length name is " + maxLengthName)
}
// 然后Kotlin规定 当Lambda参数是函数的唯一一个参数时 可以将Lambda表达式移到函数括号的外面
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val maxLengthName = list.maxByOrNull(){ name: String -> name.length }
    println("max length name is " + maxLengthName)
}
// 接下来 如果Lambda参数时函数的唯一一个参数 可以将函数的括号省略
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val maxLengthName = list.maxByOrNull { name: String -> name.length }
    println("max length name is " + maxLengthName)
}
// 由于Kotlin用于出色的类型推导机制 Lambda表达式中的参数列表其实在大多数情况下不必声明参数类型
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val maxLengthName = list.maxByOrNull { name -> name.length }
    println("max length name is " + maxLengthName)
}
// 最后 当lambda表达式的参数列表中只有一个参数是 也不必声明参数名 而是可以使用it关键字来代替
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val maxLengthName = list.maxByOrNull { it.length }
    println("max length name is " + maxLengthName)
}

// 集合中的map函数是最常用的一种函数式API 用于将集合中的每个元素都映射成另外的值 映射的规则在Lambda表达式中指定 最终生成一个新的集合 Map函数可以按照我们的需求对集合中的元素进行任意的映射转换
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val newList = list.map { it.toUpperCase() }
    for (name in newList) {
        println(name)
    }
}

// 另一个比较常用的函数式API filter函数是用来过滤集合中的数据的 可以单独使用 也可以配合map函数一起使用
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val newList = list.filter { it.length <= 5 }.map { it.toUpperCase() }
    for (name in newList) {
        println(name)
    }
}

// 两个比较常用的函数式API any和all函数 其中any函数用于判断集合中是否至少存在一个元素满足指定条件 all函数用于判断集合中是否所有元素都满足指定条件
fun main() {
    val list = mutableListOf("HaJiang", "ayu", "Flan")
    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult is " + anyResult + ", allResult is " + allResult)
}

// Kotlin在调用Java方法时也可以时殷弘函数式API 只不过有一定的条件限制 如果在Kotlin代码中调用了一个Java方法 并且该方法接收一个Java单抽象方法接口参数 就可以使用函数式API 单抽象方法指的是接口中只有一个待实现方法 如果接口中有多个待实现方法 则无法使用函数式API Java原生API中的Runnable接口只有一个待实现的run()方法
// 这里使用了匿名类 但是Kotlin舍弃了new关键字 所以改用object关键字
fun main() {
    Thread(object : Runnable {
        override fun run() {
            println("Thread is running")
        }
    }).start()
}

// 因为Runnable类中只有一个待实现方法 即使这里没有显式的重写run()方法 Kotlin也能自动明白Runnable后面的Lambda表达式就是要在run()方法实现的内容 
fun main() {
    Thread(Runnable {
        println("Thread is running")
    }).start()
}

// 如果一个Java方法的参数列表不存在一个以上Java单抽象方法接口参数 还可以将接口名进行省略
fun main() {
    Thread({
        println("Thread is running")
    }).start()
}

// 当Lambda表达式是方法的最后一个参数时 可以将Lambada表达式移到方法括号的外面 同时 如果Lambda表达式还是方法的唯一一个参数 还可以将方法的括号省略
fun main() {
    Thread() { println("Thread is running") }.start()
}
fun main() {
    Thread { println("Thread is running") }.start()
}

// 由于Android SDK使用Java编写的 所以在Kotlin中调用这些SDK接口时 还会用到这种Java函数式API的写法 比如点击事件接口 OnClickListener
button.setOnClickListener {
    
}
```

###### 3.6 空指针检查

```kotlin
// 空指针时一种不收编程语言检查的运行时异常 只能主动通过逻辑判断来避免 但是不可能将所有潜在的空指针异常全部考虑到 Kotlin解决了这个问题 利用编译时判空检查的机制几乎杜绝了空指针异常 虽然编译时判空检查的机制有时候会导致代码比较难写
// Kotlin默认所有参数和变量都不可为空 如果尝试向函数传入null参数 将无法通过编译 Kotlin将空指针异常的检查提前到了编译期 
// Kotlin还提供了另外一套可空的类型系统 但是需要在编译时期就将所有潜在的空指针异常处理 否则也无法通过编译 需要在参数类名后加一个问号
fun main() {
    doStudy(null)
}

fun doStudy(study: Study?) {
    if (null != study) {
        study.readBooks()
        study.doHomework()
    }
}
// 为了在编译器就处理调所有空指针异常 通常需要编写很多额外的检查代码 如果每处检查代码都是用if判断语句 则会让代码变得比较繁琐 而且if判断语句无法处理全局变量的判空
// 判空辅助工具 首先最常用的?.操作符 当对象不为空时正常调用
fun doStudy(study: Study?) {
        study?.readBooks()
        study?.doHomework()
}
// 另一个常用的?:操作符 该操作符左右两边都接收一个表达式 如果左边表达式结果不为空 就返回左边表达式的结果 类似null != 表达式 ? 表达式 : 另一个表达式
fun getTextLength(text: String?) = text?.length ?: 0
// Kotlin的空指针检查机制可能无法从逻辑上判断异常 无论content是否定义为静态val参数 content.toUpperCase()仍旧会报错
val content: String? = "hello"

fun main() {
    if (null != content) {
        printUpperCase()
    }
}

fun printUpperCase() {
    val upperCase = content.toUpperCase()
    println(upperCase)
}
// 如果想强行通过编译 可以用断言 写法时在对象后面加上!! 虽然可以通过编译 但是在使用断言的时候 最好提醒一下自己 是不是还有更好的实现方式 你最自信这个对象不会为空的时候 其实可能就是一个潜在空指针异常发生的时候
fun printUpperCase() {
    val upperCase = content!!.toUpperCase()
    println(upperCase)
}
// 最后一个辅助工具let 如果study不为空 调用let函数 执行let函数的Lambda表达式
fun doStudy(study: Study?) {
    study?.let { study ->
        study.readBooks()
        study.doHomework()
    }
}
// 当Lamada参数列表只有一个参数时 可以不声明参数名 直接使用it代替
fun doStudy(study: Study?) {
    study?.let {
        it.readBooks()
        it.doHomework()
    }
}
// let函数可以处理全局变量的判问题 if判断语句无法做到
var study: Study? = null

fun doStudy() {
    study?.let {
        it.readBooks()
        it.doHomework()
    }
}
```

###### 3.7 其他

```kotlin
// 字符串内嵌表达式 Kotlin从一开始就支持了字符串内嵌表达式的功能 支持将表达式直接写在字符串里面 即使是构建非常复杂的字符串
// Kotlin允许我们在字符串里嵌入${}表达式 并在运行时用表达式执行的结果替代这一部分内容
fun main() {
    val person = Person("HaJiang", 8)
    var str = "hello, ${person.name}. nice to meet you."
    println(str)
}
// Map
val map = mapOf("name" to "HaJiang")

fun main() {
    var str = "hello, ${map["name"]}. nice to meet you."
    println(str)
}

// 函数的参数默认值 次构造函数在Kotlin中很少使用 因为Kotlin提供了给函数设定参数默认值的功能 在很大程度上能够替代次构造函数的作用 可以在定义函数的时候给任意参数设定一个默认值 这样当调用次函数时就不会强制要求调用方为此参数传值 在没有传值的情况下会自动使用参数的默认值
fun main() {
    printParams(123)
}

fun printParams(num: Int, str: String = "hello") {
    println("num is $num, str is $str")
}
// num is 123, str is hello

// 可以通过键值对的方式来传参 就不必按照参数定义的顺序来传参
fun main() {
    printParams(str = "world", num = 123)
}

fun printParams(num: Int, str: String = "hello") {
    println("num is $num, str is $str")
}
// num is 123, str is world

// 通过函数的参数默认值 可以给主构造函数参数设定默认值
class Student(val sno: String = "", val grade: Int? = null, name: String = "", age: Int = 8) :
    Person(name, age), Study {
    override fun readBooks() {
        println(name + " is reading.")
    }
}

fun main() {
    val student = Student(sno = "8", grade = 1, name = "HaJiang")
    student.readBooks()
    student.doHomework()
    student.eat()
}

/**
HaJiang is reading.
do homework default implementation.
HaJiang is eating. He is 8 years old.
*/
```

### 三、探究Activity

##### 1、Activity的基本用法

```kotlin
// 右键New->Activity->Empty Activity命名为FirstActivity 不勾选 Generate Layout File 和 Launcher Activity 第一个表示会自动为FirstActivity创建一个对应的布局文件 第二个表示会自动将FirstActivity设置为当前项目的主Activity 勾选Backwards Conmpatibility(AppCompat) 表示会为项目启用向下兼容旧版系统的模式（新版无此选项） 
// 任何Activity都应该重写onCreate()方法 AS会自动完成
class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

// 在app/src/main/re目录下右键New->Directory创建layout文件夹 右键New->Layout resource file File name: first_layout | Root element: LinearLayout
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

</LinearLayout>
// 添加一个按钮 android:id是给当前的元素定义一个唯一的标识 android:layout_width指定了当前元素的宽度match_parent表示让当前元素和父元素一样宽 android:layout_height指定了当前元素的高度 wrap_content表示让当前元素的高度能刚好包含内容 android:text表示指定元素中显示的文字内容
    <Button
        android:id="@+id/button1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button 1" />
// 在Activity中加载这个布局 setContentView()方法给当前Activity加载一个布局 一般传入一个布局文件
class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
    }
}
// 在AndroidManifest文件注册 所有的Activity都要在AndroidManifest.xml中注册才能生效 实际上AS会自动对Activity注册 在<activity>中 android:name指定具体注册哪个Activity ".FirstActivity"表示com.flandre.android.FirstActivity的缩写 因为最外层package标签已经指定了程序的包名是com.flandre.android 仅仅注册了Activity 程序还不能运行 还需要为程序配置主Activity 在<activity>标签加入<intent-filter>标签 还可以使用 android:label 指定Activity中标题栏的内容 需要注意label还会成为启动器中应用程序显示的名称
        <activity android:name=".FirstActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
// 如果应用程序没有声明任何一个Activity作为主Activity 这个程序仍然可以正常安装 只是无法在启动器中看到或者打开 这种程序一般作为第三方服务供其他应用在内部进行调用

// Toast是Android系统提供的一种非常好的提醒方式 在程序中可以使用它将一些短小的信息通知给用户 这些信息会在一段时间后自动消失 并且不会占用任何屏幕空间
// 在Activity中 可以通过 findViewById() 方法获取在布局文件中定义的元素 方法返回的是一个继承自View的泛型对象 因此Kotlin无法自动推导它的类型 所以需要显式声明类型 得到按钮实例后 调用 setOnClickListener() 方法为按钮注册一个监听器 通过静态方法 makeText() 创建出一个Toast对象 调用 show() 方法将Toast显示出来 需要注意 makeText() 方法需要传入3个参数 第一个是Context 也就是Toast要求的上下文 Activity本身就是一个Context对象 因此可以传入this 第二个是Toast显示的文本内容 第三个是Toast显示的时长 一般使用两个内置常量 Toast.LENGTH_SHORT和Toast.LENGTH_LONG
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            Toast.makeText(this, "You clicked Button1", Toast.LENGTH_SHORT).show()
        }
    }
// findViewById()方法的作用就是获取布局文件中控件的实例 如果布局文件中有多个控件 就需要调用多次 写法虽然正确 但是很笨拙 于是就有诸如ButterKnife之类的第三方开源库来简化调用 Kotlin在app/build.gradle文件的头部默认引入了一个kotlin-android-extensions插件 这个插件会根据布局文件中定义的控件id自动生成相同名称的变量 可以直接使用 不用再调用findViewById()方法 这种写法是Koutlin编程最推荐的写法
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            Toast.makeText(this, "You clicked Button1", Toast.LENGTH_SHORT).show()
        }
    }

// Android提供了一种方式 可以显示菜单 而且不占用屏幕空间
// 右键res目录-New-Directory directory name:menu 右键menu文件夹-New-Menu resource file file name:main
// <item>标签用来创建具体的某一个菜单项 android:id给这个菜单项指定唯一标识符 android:title给这个菜单项指定名称
    <item
        android:id="@+id/add_item"
        android:title="Add" />
    <item
        android:id="@+id/remove_item"
        android:title="Remove" />
// 在FisrtActivity中重写onCreateOptionsMenu方法 menuInflater使用了语法糖 调用了父类的getMenuInflater()方法 再调用MenuInflater对象的inflate()方法 给当前Activity创建菜单 inflate()方法接收两个参数 第一个参数用于指定通过哪个资源文件来创建菜单 第二个参数用于指定菜单项将添加到哪一个Menu对象中 最后返回true 表示显示创建的菜单
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mian, menu)
        return true
    }
// 还需要重写onOptionsItemSelected()方法定义菜单响应事件 通过调用item.itemId来判断点击的是哪一个菜单项
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT)
                .show()
        }
        return true
    }

// Activity类提供了一个finish()方法 调用这个方法就可以销毁当前的Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            finish()
        }
    }
```

[when复习](#3.3 逻辑控制)



##### 2、Activity的Intent

Intent是Android程序中各组件之间进行交互的一种重要方式，不仅可以指明当前组件想要执行的动作，还可以在不同组件之间传递数据。Intent一般可用于启动Activity、启动Service以及发送广播等。

###### 2.1 显示Intent

```kotlin
// 右键New-Activity-Empty Activity-Activity Name:SecondActivity Layout Name:second_layout AS会自动生成SecondActivity.kt和second_layout.xml
// 修改second_layout 定义一个按钮button2
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button 2" />

</LinearLayout>
// 在AndroidManifest.xml注册SecondActivity AS已经自动完成了 由于SecondActivity不是主Activity 所以可以不修改默任配置

// Intent有多个构造函数的重载 其中一个是Intent(Context packageContext, Class<?> cls)第一个参数Context要求提供一个启动Activity的上下文 第二个参数Class用于指定想要启动的目标Activity Activity类提供了一个startActivity()方法专门用于启动Activity 它接收一个Intent参数
// 修改FirstActivity按钮button1的监听事件 构建一个Intent对象 第一个传入当前Activity-this 第二个传入SecondActivity::class.java作目标Activity 相当于Java中SecondActivity.class的写法 最后调用startActivity()方法
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
// 按返回键就可以销毁当前Activity 从而返回上一个Activity 使用这种方式来启动Activity 称为显式Intent
```

###### 2.2 隐式Intent

```kotlin
// 隐式Intent不指出启动的Activity 而是指定action和category 由系统去分析
// 打开AndroidManifest在SecondActivity<activity>标签配置<intent-filter>的<action>标签指定当前Activity可以响应com.flandre.android.ACTION_START这个action和<category>标签 只有<action>和<category>同时匹配才能响应
        <activity android:name=".SecondActivity">
            <intent-filter>
                <action android:name="com.flandre.android.ACTION_START" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
// 使用Intent的另一个构造函数 直接传入action的字符串 因为category默认是android.intent.category.DEFAULT 所以可以不需要指定也能启动
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            val intent = Intent("com.flandre.android.ACTION_START")
            startActivity(intent)
        }
    }
// Intent只能指定一个action 但是能指定多个category addCategory()方法添加一个category
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            val intent = Intent("com.flandre.android.ACTION_START")
            intent.addCategory("com.flandre.android.MY_CATEGORY")
            startActivity(intent)
        }
    }
// 首先需要修改AndroidManifest文件 如果在启动Activity的时候找不到对应的category将会报错 APP会直接崩溃
        <activity android:name=".SecondActivity">
            <intent-filter>
                <action android:name="com.flandre.android.ACTION_START" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="com.flandre.android.MY_CATEGORY" />
            </intent-filter>
        </activity>
```

###### 2.3 隐式Intent更多用法

```kotlin
// 使用隐式Intent 不仅可以启动自己程序的Activity还可以启动其他程序的Activity 使多个应用程序之间的功能共享成为了可能 如果需要展示一个网页 只需要调用系统的浏览器来打开这个网页就行了
// 首先指定Intent的action是Intent.ACTION_VIEW 这是一个Android系统内置的动作 这个动作的常量是android.intent.action.VIEW 然后通过Uri.parse()方法将一个网址字符串解析成一个Uri对象 再去调用Intent的setData()方法传入这个Uri对象 这里使用了语法糖intent.data就等于Java中的setData()方法
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }
    }

// 还可以在<intent-filter>标签中再配置一个<data>标签 用于更精确地指定当前Activity能够相应的数据 <data>标签中主要可以配置一下内容
// android:scheme 用于指定数据的协议部分
// android:host 用于指定数据的主机名
// android:prot 用于指定数据的端口
// android:path 用于指定主机名和端口之后的部分
// android:mimeType 用于指定可以处理的数据类型 允许使用通配符
// 只有当<data>标签中指定的内容和Intent中携带的Data完全一致时 当前Activity才能相应 不过<data>标签中一般不会指定过多的内容

// 右键New-Activity-Empty Activity-Activity Name:ThirdActivity-Layout Name:third_layout
// 修改third_layout.xml添加一个button3
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/button3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button 3" />

</LinearLayout>
// 打开AndroidManifest.xml修改ThirdActivity注册信息 在<intent-filter>标签中配置了<action>为Intent.ACTION_VIEW的常量值和<catetory>为默认的category值 另外在<data>标签中 通过android:scheme指定了数据的协议必须是https协议 这样就能相应一个打开网页的Intent 另外AS认为所有能够相应ACTION_VIEW的Activity都应该加上BROWSABLE的category否则提示警告 可以通过加上tools:ignore="AppLinkUrlError"忽略警告
        <activity android:name=".ThirdActivity">
            <intent-filter tools:ignore="AppLinkUrlError">
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:scheme="https" />
            </intent-filter>
        </activity>
// 系统自动弹出了显式能够响应这个Intent的所有程序 如果选择了Android就会启动ThirdActivity 虽然我们声明了ThirdActivity时可以相应打开网页的Intent 但实际上这个Activity并没有加载并显示网页的功能 我们还可以指定很多其他协议 比如geo表示显示地理位置 tel表示拨打电话
// 首先指定action为系统内置动作Intent.ACION_DIAL 然后再data部分指定了协议是tel 号码是10000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("tel:10000")
            startActivity(intent)
        }
    }
```

###### 2.4 传递Activity数据

```kotlin
// Intent在启动Activity的时候还可以传递数据 Intenet中提供了一系列putExtra()方法的重载 可以把数据暂存在Intent中 在启动另一个Activity后 只需要把这些数据从Intent中取出
// 使用显示Intent方式启动SecondActivity 并通过putExtra()方法传递一个字符串 putExtra()方法接收两个参数 第一个参数是键 第二个参数才是数据
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            val data = "Hello SecondActivity"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra_data", data)
            startActivity(intent)
        }
    }
// 在SecondActivity中将传递的数据取出 intent实际上调用的是父类的getIntent()方法 该方法会获取用于启动SecondActivity的Intent 然后调用getStringExtra()方法并传入相应的键值 就能得到数据 由于传递的是字符串所以使用getStringExtra 如果传递的是整形数据 则是哟个getIntExtra()方法 如果传递的是布尔型数据 则使用getBooleanExtra()方法 以此类推
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity", "extra data is $extraData")
    }
```

###### 2.5 返回Activity数据

```kotlin
// 并没有一个用于返回数据的Intent 但是Activity类中还有一个用于启动Activity的startActivityForResult()方法 它期望在Activity销毁的时候能够返回一个结果给上一个Activity 该方法接收两个参数 第一个参数还是Intent 第二个参数是请求码
// 使用startActivityForResult来启动SecondActivity 请求码只要是一个唯一值即可
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }
// 在SecondActivity给按钮注册点击事件 并在点击事件之呢个添加返回数据的逻辑 构建一个Intent 把要传递的数据据存放在Intent中 然后调用setResult()方法 该方法专门用于向上一个Activity返回数据 接收两个参数 第一个参数哟关于向上一个Activity返回处理结果 一般只是用RESULT_OK或RESULT_CANCELED这两个值 第二个参数则把带有数据的Intent传递回去 最后调用finish()方法销毁当前Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "Hello FirstActivity")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
// 在FitstActivity中重写SecondActivity被销毁之后回调的onActivityResult()方法 该方法有三个参数 第一个参数requestCode即启动Activity时传入的请求码 第二个参数resultCode即返回数据时传入的处理结果 第三个参数data即携带返回数据的Intent 由于在一个Activity中有可能调用startActivityForResult()这个方法去启动很多不同的Activity 每一个Activity都会回调到onActivityResult()这个方法 所以需要通过检查requestCode来判断数据来源
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnedCode = data?.getStringExtra("data_return")
                Log.d("FirstActivity", "returned data is $returnedCode")
            }
        }
    }
// 如果用户不是通过点击按钮 而是通过按下Back键回到FirstActivity 就需要在SecondActivity的onBackPressed()方法
    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("data_return", "Hello FirstActivity By Back")
        setResult(RESULT_OK, intent)
        finish()
    }
```



##### 3、Activity的生命周期

###### 3.1 返回栈

```tex
Activity是可以层叠的 每启动一个新的Activity 就会覆盖在原Activity上 点击Back键会销毁最上面的Activity
Android时使用任务task来管理Activity的 一个任务就是一组存放在栈里的Activity的集合 这个栈也被称作返回栈back stack
栈是一种后进先出的数据结构 每当启动一个新的Activity 就会在返回栈中入栈 并处于栈顶 当按下Back键或调用finish()方法去销毁一个Activity时 处于栈顶的Activity就会出栈 前一个入栈的Activity就会重新处于栈顶位置
系统总是会显示处于栈顶的Activity给用户
```

###### 3.2 Activity的状态

```tex
每个Activity在其生命周期中最多可能会有4种状态
1 运行状态
Activity处于返回栈的栈顶时 Activity就处于运行状态 系统一般不会回收
2 暂停状态
Activity不再处于栈顶 但仍然可见时 Activity就进入暂停状态 比如被遮挡的Activity 系统一般也不会回收 只有在内存极低的情况下 系统财会考虑回收
3 停止状态
Activity不再处于栈顶位置 并且完全不可见的时候 就进入了停止状态 有可能会被系统回收
4 销毁状态
Activity从返回栈种移除后就编程了销毁状态 系统最倾向于回收处于这种状态的Activity
```

###### 3.3 Activity的生存期

```tex
Activity类定义了7个回调方法 覆盖了Activity生命周期的每一个环节
onCreate() 在Activity第一次被创建的时候调用 应该在这个方法中完成Activity的初始化操作 比如加载布局、绑定事件
onStart() 在Activity由不可见变成可见的时候调用
onResume() 在Activity准备好和用户进行交互的时候调用 此是的Activity一定位于返回栈的栈顶并且处于运行状态
onPause() 在系统准备去启动获取恢复另一个Activity的时候调用 通常会在这个方法种将一些小号CPU的资源释放调 这个方法的执行速度一定要快 否则会影响到新的栈顶Activity的使用
onStop() 在Activity完全不可见的时候调用 和onPause()方法的主要区别在于 如果启动的新Activity时一个对话框形式的Activity 那么它会得到执行 而onStop()方法不会执行
onDestroy() 在Activity被销毁之前调用 之后Activity的状态将变为销毁状态
onRestart() 在Activity由停止状态变为运行状态之前调用 也就是Activity被重新启动了
除了onRestart()方法 其他都是两两相对的 所以可以将Activity分为以下3种生存期

完整生存期 Activity在onCreate()方法和onDestroy()方法之间就是完整生存期 一般情况下 一个Activity会在onCreate()方法种完成各种初始化操作 而在onDestory()方法中完成释放内存的操作
可见生存期 Activity在onStart()方法和onStop()方法之间就是可见生存期 Activity在此期间内对用户总是可见的 即使有可能无法和用户进行交互 可以通过这两个方法合理地管理那些对用户可见的资源 比如在onStart()方法中对资源进行加载 而在onStop()方法中对资源进行释放 从而保证处于停止状态的Activity不会占用过多内存
前台生存期 Activity在onResume()方法和onPause()方法之间就是前台生存期 在此期间内 Activity总是处于运行状态 此是的Activity是可以和用户进行交互的
```

###### 3.4 Activity的生命周期

```kotlin
// 右键New-Activity-Empty Activity-Activity Name:NormalActivity-Layout Name:normal_layout 修改normal_layout.xml文件 添加一个TextView
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="This is a normal activity" />
</LinearLayout>

// 右键New-Activity-Empty Activity-Activity Name:DialogActivity-Layout Name:dialog_layout 修改dialog_layout.xml文件 添加一个TextView
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="This is a dialog activity" />
</LinearLayout>

// 修改AndroidManifest.xml文件DialogActivity的<activity>标签
// 指定android:theme属性值为@style/Theme.AppCompat.Dialog使用对话框式的主题
        <activity
            android:name=".DialogActivity"
            android:theme="@style/Theme.AppCompat.Dialog">

        </activity>
// 修改activity_main.xml 添加两个按钮 用于启动NormalActivity和DialogActivity
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Start NormalActivity" />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Start DialogActivity" />
</LinearLayout>

// 修改MainActivity
class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(tag, "onCreate")

        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }

        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }
}
// 当MainActivity第一次被创建时会依次执行onCreate()、onStart()、onResume()三个方法
// 点击第一个按钮 会启动NormalActivity 由于NormalActivity完全被遮挡 因此onPause()、onStop()方法都会执行
// 按下Back键返回MainActivity 由于之前MainActivity已经进入停止状态 所以onRestart()方法会执行 之后会依次执行onStart()、onResume()方法
// 注意 此时onCreate()方法不会执行 因为MainActivity没有重新创建

// 点击第二个按钮 启动DialogActivity 因为DialogActivity并没有完全遮挡住MainActivity 此时MainActivity只是进入了暂停状态 没有进入停止状态 所以只有onPause()方法执行 onStop()方法并没有执行
// 按下Back键返回MainActivity 只有onResume()方法会执行
// 最后在MainActivity按下Bac键退出程序 依次会执行onPause()、onStop()、onDestroy()方法 最终销毁MainActivity
```

###### 3.5 Activity的回收后处理

```kotlin
// 当一个Activity进入了停止状态 是有可能被系统回收的 如果在A启动B A进入停止状态 这个时候A被回收 此时返回A时 依旧能显示A 此时不会执行onRestart()方法 而是从onCreate()方法开始执行 A会被重新创建一次

// 基于此 Activit提供了一个onSaveInstanceState()回调方法 该方法可以保证在Activity被回收之前一定会被调用
// onSaveInstanceState()会携带一个Bundle类型的参数 用于保存数据
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "Someting you just typed"
        outState.putString("data_key", tempData)
    }
// 并在onCreate()方法添加处理
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            tempData?.let { Log.d(tag, it) }
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

