package com.example.testapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.example.testapplication.api.ServerApi
import com.example.testapplication.api.ServerDocument
import com.example.testapplication.api.ServicesApi
import com.example.testapplication.model.DataTest
import com.example.testapplication.taks.DocumentSnapshot
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import android.R.string
import android.os.AsyncTask.execute



class MainActivity : AppCompatActivity() {
    private lateinit var snaphosts: MutableList<DocumentSnapshot>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        getDatas()
       getDatas2()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getDatas() {
        val api = ServerApi.health(this@MainActivity).create(ServicesApi::class.java)
        val call = api.datas()

        call.enqueue(object : Callback<MutableList<DataTest>> {
            override fun onResponse(call: Call<MutableList<DataTest>>, response: Response<MutableList<DataTest>>) {
                if (response.isSuccessful) {
                    name.text = response.body()!![0].name.toString()

                    Toast.makeText(applicationContext, "Data: ${response.body()!![0].name}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MutableList<DataTest>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error: ${t.message.toString()}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getDatas2() {
        val api = ServerDocument.health().create(ServicesApi::class.java)
        val call = api.datas2()

        call.enqueue(object : Callback<MutableList<DocumentSnapshot>> {
            override fun onResponse(call: Call<MutableList<DocumentSnapshot>>, response: Response<MutableList<DocumentSnapshot>>) {
                if (response.isSuccessful) {
                    snaphosts = response.body()!!
                    //snaphosts.result


                    Log.e("Document: ", response.body()!![0].get("title").toString())

                    //Toast.makeText(applicationContext, "Data: ${response.body()!![0].name}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MutableList<DocumentSnapshot>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error: ${t.message.toString()}", Toast.LENGTH_LONG).show()
            }
        })
    }

    /*private fun getDatas2() {
        val api = ServerDocument.health().create(ServicesApi::class.java)
        val call = api.datas2()

        call.enqueue(object : Callback<MutableList<DocumentSnapshot>> {
            override fun onResponse(call: Call<MutableList<DocumentSnapshot>>, response: Response<MutableList<DocumentSnapshot>>) {
                if (response.isSuccessful) {
                    snaphosts = response.body()!!
                    snaphosts.result


                    Log.e("Document: ", response.body()!![0]["title"].toString())

                    //Toast.makeText(applicationContext, "Data: ${response.body()!![0].name}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MutableList<DocumentSnapshot>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error: ${t.message.toString()}", Toast.LENGTH_LONG).show()
            }
        })
    }*/
}
