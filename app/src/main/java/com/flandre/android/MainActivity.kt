package com.flandre.android

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.flandre.android.dto.Human
import com.flandre.android.fragment.AnotherRightFragment
import com.flandre.android.fragment.RightFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag = "MainActivity"

    private val data = listOf(
        "Karen Hoyle",
        "Josephine Kellogg",
        "Irene Ferguson",
        "Winfred Colclough",
        "Ferdinand Tomlinson",
        "Kerwin Benedict",
        "Katherine Edith",
        "Alexia Stowe",
        "Bishop Mike",
        "Valentine Wood",
        "Humphrey Madge",
        "Brandon Sam",
        "Mamie Babbitt",
        "Susan Archibald",
        "Barry Benjamin",
        "Hunter Hodgson",
        "Hilary Gregory",
        "Carey MacDonald",
        "Richard Saroyan",
        "Ella Chesterton"
    )

    private val humanList = ArrayList<Human>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Log.v(tag, "onCreate")
        initHuman()

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter = HumanRecyclerAdapter(humanList)
        recyclerView.adapter = adapter

//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        recyclerView.layoutManager = layoutManager
//        val adapter = HumanRecyclerAdapter(humanList)
//        recyclerView.adapter = adapter

//        val layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
//        val adapter = HumanRecyclerAdapter(humanList)
//        recyclerView.adapter = adapter

// 5、控件ListView
//        val adapter = HumanAdapter(this, R.layout.human_item, humanList)
//        listView.adapter = adapter
//        listView.setOnItemClickListener { _, _, position, _ ->
//            val human = humanList[position]
//            Toast.makeText(this, human.name, Toast.LENGTH_SHORT).show()
//        }

//        listView.setOnItemClickListener { parent, view, position, id ->
//            val human = humanList[position]
//            Toast.makeText(this, human.name, Toast.LENGTH_SHORT).show()
//        }

//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
//        listView.adapter = adapter

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

        // 2.2 Fragment的动态添加
        replaceFragment(RightFragment())
    }

    private fun initHuman() {
        repeat(2) {
            humanList.apply {
                add(Human(getRandomLengthString("Karen Hoyle"), R.drawable.image_1))
                add(Human(getRandomLengthString("Josephine Kellogg"), R.drawable.image_2))
                add(Human(getRandomLengthString("Irene Ferguson"), R.drawable.image_3))
                add(Human(getRandomLengthString("Winfred Colclough"), R.drawable.image_4))
                add(Human(getRandomLengthString("Ferdinand Tomlinson"), R.drawable.image_5))
                add(Human(getRandomLengthString("Kerwin Benedict"), R.drawable.image_6))
                add(Human(getRandomLengthString("Katherine Edith"), R.drawable.image_7))
                add(Human(getRandomLengthString("Alexia Stowe"), R.drawable.image_8))
                add(Human(getRandomLengthString("Bishop Mike"), R.drawable.image_9))
                add(Human(getRandomLengthString("Valentine Wood"), R.drawable.image_10))
                add(Human(getRandomLengthString("Humphrey Madge"), R.drawable.image_11))
                add(Human(getRandomLengthString("Brandon Sam"), R.drawable.image_12))
                add(Human(getRandomLengthString("Mamie Babbitt"), R.drawable.image_13))
                add(Human(getRandomLengthString("Susan Archibald"), R.drawable.image_14))
                add(Human(getRandomLengthString("Barry Benjamin"), R.drawable.image_15))
                add(Human(getRandomLengthString("Hunter Hodgson"), R.drawable.image_16))
                add(Human(getRandomLengthString("Hilary Gregory"), R.drawable.image_17))
                add(Human(getRandomLengthString("Carey MacDonald"), R.drawable.image_18))
                add(Human(getRandomLengthString("Richard Saroyan"), R.drawable.image_19))
                add(Human(getRandomLengthString("Ella Chesterton"), R.drawable.image_20))
            }
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

//    private fun initHuman() {
//        repeat(2) {
//            humanList.apply {
//                add(Human("Karen Hoyle", R.drawable.image_1))
//                add(Human("Josephine Kellogg", R.drawable.image_2))
//                add(Human("Irene Ferguson", R.drawable.image_3))
//                add(Human("Winfred Colclough", R.drawable.image_4))
//                add(Human("Ferdinand Tomlinson", R.drawable.image_5))
//                add(Human("Kerwin Benedict", R.drawable.image_6))
//                add(Human("Katherine Edith", R.drawable.image_7))
//                add(Human("Alexia Stowe", R.drawable.image_8))
//                add(Human("Bishop Mike", R.drawable.image_9))
//                add(Human("Valentine Wood", R.drawable.image_10))
//                add(Human("Humphrey Madge", R.drawable.image_11))
//                add(Human("Brandon Sam", R.drawable.image_12))
//                add(Human("Mamie Babbitt", R.drawable.image_13))
//                add(Human("Susan Archibald", R.drawable.image_14))
//                add(Human("Barry Benjamin", R.drawable.image_15))
//                add(Human("Hunter Hodgson", R.drawable.image_16))
//                add(Human("Hilary Gregory", R.drawable.image_17))
//                add(Human("Carey MacDonald", R.drawable.image_18))
//                add(Human("Richard Saroyan", R.drawable.image_19))
//                add(Human("Ella Chesterton", R.drawable.image_20))
//            }
//        }
//    }

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

//            R.id.button -> {
//                AlertDialog.Builder(this).apply {
//                    setTitle("This is Dialog")
//                    setMessage("Something important.")
//                    setCancelable(false)
//                    setPositiveButton("OK") { dialog, which ->
//                    }
//                    setNegativeButton("Cancel") { dialog, which ->
//                    }
//
//                    show()
//                }
//            }

            // 2.2 Fragment的动态添加
            R.id.button -> {
                replaceFragment(AnotherRightFragment())
            }
        }
    }

    // 2.2 Fragment的动态添加
//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.rightLayout, fragment)
//        transaction.commit()
//    }

    // 2.3 Fragment的返回栈
    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.rightLayout, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
    }
}
