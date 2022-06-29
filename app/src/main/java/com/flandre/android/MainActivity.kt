package com.flandre.android

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Log.v(tag, "onCreate")

        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            tempData?.let { Log.d(tag, it) }
        }

        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }

        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            Toast.makeText(this, "onCreate button", Toast.LENGTH_SHORT).show()
        }
        button.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "Someting you just typed"
        outState.putString("data_key", tempData)
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

    override fun onClick(v: View?) {
        Log.d(tag, v?.id.toString())
        when (v?.id) {
//            R.id.button -> {
//                Toast.makeText(this, "onClick button", Toast.LENGTH_SHORT).show()
//            }

//            R.id.button -> {
//                val inputText = editText.text.toString()
//                Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
//            }

//            R.id.button -> {
//                imageView.setImageResource(R.drawable.w_2)
//            }

//            R.id.button -> {
//                if (progressBar.visibility == View.VISIBLE) {
//                    progressBar.visibility = View.GONE
//                } else {
//                    progressBar.visibility = View.VISIBLE
//                }
//            }

//            R.id.button -> {
//                progressBar.progress = progressBar.progress + 10
//            }

            R.id.button -> {
                AlertDialog.Builder(this).apply {
                    setTitle("This is Dialog")
                    setMessage("Something important.")
                    setCancelable(false)
                    setPositiveButton("OK") { dialog, which ->
                    }
                    setNegativeButton("Cancel") { dialog, which ->
                    }

                    show()
                }
            }
        }
    }
}
