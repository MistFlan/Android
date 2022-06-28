package com.flandre.android

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.first_layout.*

class FirstActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstActivity", "Task id is $taskId")
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            SecondActivity.actionStart(this, "data1", "data2")
        }
    }

// 4.4 singleInstance
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("FirstActivity", "Task id is $taskId")
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)
//        }
//    }

// 4.2 singleTop
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("FirstActivity", this.toString())
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)
//        }
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("FirstActivity", this.toString())
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent(this, FirstActivity::class.java)
//            startActivity(intent)
//        }
//    }


// 4.1 standard
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("FirstActivity", this.toString())
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent(this, FirstActivity::class.java)
//            startActivity(intent)
//        }
//    }

// 2.5 返回Activity数据
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivityForResult(intent, 1)
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnedCode = data?.getStringExtra("data_return")
                Log.d("FirstActivity", "returned data is $returnedCode")
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("FirstActivity", "onRestart")
    }

// 2.4 传递Activity数据
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val data = "Hello SecondActivity"
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("extra_data", data)
//            startActivity(intent)
//        }
//    }

// 2.3 隐式Intent更多用法
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:10000")
//            startActivity(intent)
//        }
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://www.baidu.com")
//            startActivity(intent)
//        }
//    }

// 2.2 隐式Intent
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent("com.flandre.android.ACTION_START")
//            intent.addCategory("com.flandre.android.MY_CATEGORY")
//            startActivity(intent)
//        }
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent("com.flandre.android.ACTION_START")
//            startActivity(intent)
//        }
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mian, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT)
                .show()
        }
        return true
    }

// 1、Activity的基本用法
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            Toast.makeText(this, "You clicked Button1", Toast.LENGTH_SHORT).show()
//        }
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        val button1: Button = findViewById(R.id.button1)
//        button1.setOnClickListener {
//            Toast.makeText(this, "You clicked Button1", Toast.LENGTH_SHORT).show()
//        }
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.first_layout)
//        button1.setOnClickListener {
//            finish()
//        }
//    }
}
