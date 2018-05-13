package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http
import kotlinx.android.synthetic.main.activity_warden_menu.*
import java.nio.charset.Charset

class Warden_menuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Http.init(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warden_menu)

        val hostel_name=intent.extras.getString("hostel_name")
        warden_data(hostel_name)

        //total complaint

        CardView1wardenMenu.setOnClickListener({
            Toast.makeText(this@Warden_menuActivity, "Please click on Below status ", Toast.LENGTH_SHORT).show();


        })

        //pending

        CardView2WardenMenu.setOnClickListener({
            val mainActivity= Intent(this,All_problemActivity::class.java)

            mainActivity.putExtra("hostel_name",hostel_name)
            mainActivity.putExtra("status","0")
            val t=pending1.text;
            if(t!="0")
            startActivity(mainActivity)
            Toast.makeText(this@Warden_menuActivity, "NO Record found ", Toast.LENGTH_SHORT).show();
        })
        //pending
        CardView3wardenMenu.setOnClickListener({
            val mainActivity= Intent(this,All_problemActivity::class.java)

            mainActivity.putExtra("hostel_name",hostel_name)
            mainActivity.putExtra("status","2")
            val t=resolved1.text
            if(t!="0")
                startActivity(mainActivity)
            Toast.makeText(this@Warden_menuActivity, "NO Record found ", Toast.LENGTH_SHORT).show();
        })

        //pending
        CardView4wardenMenu.setOnClickListener({
            val mainActivity= Intent(this,All_problemActivity::class.java)

            mainActivity.putExtra("hostel_name",hostel_name)
            mainActivity.putExtra("status","1")
            val t=processing1.text
            if(t!="0")
            startActivity(mainActivity)
            Toast.makeText(this@Warden_menuActivity, "NO Record found ", Toast.LENGTH_SHORT).show();
        })

    }

    fun warden_data(hostel_name:String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var status:Warden_data

        Http.get {
            url=weburl.WARDEN_DATA

            val tag = "HTTP_LOG" //for debug

            params {
                 "hostel_name"-hostel_name  //parameters


            }

            onStart {
              //  Log.d(tag.toString(),"on start")
                Toast.makeText(this@Warden_menuActivity, "Loading Data..of "+hostel_name, Toast.LENGTH_SHORT).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
               // println(text)
               // Toast.makeText(this@Warden_menuActivity, "Loading...data.."+text, Toast.LENGTH_SHORT).show();

                status = gson.fromJson<Warden_data>(text)

                //creating our adapter  all data will be set

                total1.text=status.total
                pending1.text=status.pending
                resolved1.text=status.resolved
                processing1.text=status.processing
                 this@Warden_menuActivity.title="Warden Menu :"+hostel_name
                //now adding the adapter to recyclerview



            }

            onFail { error ->
                Log.d(tag.toString(),"on fail ${error.toString()}")
                Toast.makeText(this@Warden_menuActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();

            }

            onFinish { Log.d(tag.toString(), "on finish")


               // Toast.makeText(this@Warden_menuActivity, "Finished", Toast.LENGTH_SHORT).show();





            }

        }
    }



}
