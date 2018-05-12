package com.iotwebplanet.learn.hostel

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http
import kotlinx.android.synthetic.main.activity_all_problem.*
import java.nio.charset.Charset


class All_problemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_problem)

        Http.init(this) //call to web service

        val hostel_name=intent.extras.getString("hostel_name")
        val c_status=intent.extras.getString("status")

        if (isNetworkAvailable()) {

            val t = Toast.makeText(this, "You are online!!!!", Toast.LENGTH_LONG).show()
            swipeRefreshLayout.setRefreshing(true)
            if(c_status=="-")
            allRefresh(hostel_name)
            myRefresh(hostel_name,c_status)
        } else {

            val t = Toast.makeText(this, "You are not online!!!!", Toast.LENGTH_LONG).show()
            Log.v("Home", "############################You are not online!!!!")
        }

//adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        swipeRefreshLayout.setOnRefreshListener({
            if (isNetworkAvailable()) {

                val t = Toast.makeText(this, "You are online!!!!", Toast.LENGTH_LONG).show()
                if(c_status=="-")
                    allRefresh(hostel_name)
                myRefresh(hostel_name,c_status)
            } else {

                val t = Toast.makeText(this, "You are not online!!!!", Toast.LENGTH_LONG).show()
                Log.v("Home", "############################You are not online!!!!")
            }

        })




    }

    fun myRefresh(hostel_name:String,c_status:String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var problemList=ArrayList<Problems>()

        Http.get {
            url=weburl.ALL_COMPLAINT_STATUS;

            val tag = "HTTP_LOG" //for debug

            params {
                "hostel_name"-hostel_name  //parameters
                "c_status"-c_status  //parameters

            }

            onStart {
             //   Log.d(tag.toString(),"on start")
              //  Toast.makeText(this@All_problemActivity, "Starting...data..", Toast.LENGTH_LONG).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
              //  println(text)
              //  Toast.makeText(this@All_problemActivity, "Loading...data..", Toast.LENGTH_LONG).show();

                problemList = gson.fromJson<ArrayList<Problems>>(text)
                //   hostels = gson.fromJson<List<Hostel>>(text) as ArrayList<Hostel>
 //creating our adapter  all data will be set

                val madapter = MyAdapter(problemList)

                //now adding the adapter to recyclerview
                recyclerView.adapter = madapter
                swipeRefreshLayout.setRefreshing(false)

            }

            onFail { error ->
              //  Log.d(tag.toString(),"on fail ${error.toString()}")
               Toast.makeText(this@All_problemActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(this@All_problemActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();
                val mainActivity= Intent(this@All_problemActivity,Warden_menuActivity::class.java)

                mainActivity.putExtra("hostel_name",hostel_name)
                startActivity(mainActivity)

            }

            onFinish { Log.d(tag.toString(), "on finish")
               // Toast.makeText(this@All_problemActivity, "Finished", Toast.LENGTH_LONG).show();
                // swipeRefreshLayout.setRefreshing(false)

            }

        }
    }

    fun allRefresh(hostel_name:String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var problemList=ArrayList<Problems>()

        Http.get {
            url=weburl.ALL_COMPLAINT;

            val tag = "HTTP_LOG" //for debug

            params {
                "hostel_name"-hostel_name  //parameters


            }

            onStart {
                //   Log.d(tag.toString(),"on start")
                //  Toast.makeText(this@All_problemActivity, "Starting...data..", Toast.LENGTH_LONG).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
                //  println(text)
                //  Toast.makeText(this@All_problemActivity, "Loading...data..", Toast.LENGTH_LONG).show();

                problemList = gson.fromJson<ArrayList<Problems>>(text)
                //   hostels = gson.fromJson<List<Hostel>>(text) as ArrayList<Hostel>
                //creating our adapter  all data will be set

                val madapter = MyAdapter(problemList)

                //now adding the adapter to recyclerview
                recyclerView.adapter = madapter
                swipeRefreshLayout.setRefreshing(false)

            }

            onFail { error ->
                //  Log.d(tag.toString(),"on fail ${error.toString()}")
                Toast.makeText(this@All_problemActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();
                val mainActivity= Intent(this@All_problemActivity,Warden_menuActivity::class.java)

                mainActivity.putExtra("hostel_name",hostel_name)
                startActivity(mainActivity)
            }

            onFinish { Log.d(tag.toString(), "on finish")
                // Toast.makeText(this@All_problemActivity, "Finished", Toast.LENGTH_LONG).show();
                // swipeRefreshLayout.setRefreshing(false)

            }

        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}


