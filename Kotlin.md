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

// 当Lambda表达式时方法的最后一个参数时 可以将Lambada表达式移到方法括号的外面 同时 如果Lambda表达式还是方法的唯一一个参数 还可以将方法的括号省略
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

