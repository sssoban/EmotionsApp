package com.example.emotionsapp


import android.content.Intent
import android.media.CamcorderProfile.getAll
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emotionsapp.data.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var userDatabase: UserDatabase
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDatabase = UserDatabase.getDatabase(this)
        // Recylcler View
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val addButton: Button = findViewById(R.id.add)
        addButton.setOnClickListener {
            openInputScreen()
        }


    }

    override fun onResume() {
        super.onResume()
        getAll()
        GlobalScope.launch {
            val dataList = userDatabase.dataDao().getAllData()
            runOnUiThread {
                recyclerView.adapter = DataListAdapter(dataList)
            }
        }

    }

    private fun openInputScreen() {
        val intent = Intent(this, InputScreen::class.java)
        startActivity(intent)
    }

    fun getAll() {
        GlobalScope.launch {
            val list = userDatabase.dataDao().getAllData()
            println(list)
        }
    }
}
