package com.flandre.android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.second_layout.*

class SecondActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("param1", data1)
                putExtra("param2", data2)
            }
            context.startActivity(intent)
        }
    }

// 5.3 启动Activity的最佳写法
//    companion object {
//        fun actionStart(context: Context, data1: String, data2: String) {
//            val intent = Intent(context, SecondActivity::class.java)
//            intent.putExtra("param1", data1)
//            intent.putExtra("param2", data2)
//            context.startActivity(intent)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity", "Task id is $taskId")
        setContentView(R.layout.second_layout)
        button2.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

// 4.2 singleTop
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("SecondActivity", this.toString())
//        setContentView(R.layout.second_layout)
//        button2.setOnClickListener {
//            val intent = Intent(this, FirstActivity::class.java)
//            startActivity(intent)
//        }
//    }

// 2.5 返回Activity数据
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.second_layout)
//        button2.setOnClickListener {
//            val intent = Intent()
//            intent.putExtra("data_return", "Hello FirstActivity")
//            setResult(RESULT_OK, intent)
//            finish()
//        }
//    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("data_return", "Hello FirstActivity By Back")
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy")
    }

// 2.4 传递Activity数据
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.second_layout)
//        val extraData = intent.getStringExtra("extra_data")
//        Log.d("SecondActivity", "extra data is $extraData")
//    }
}
