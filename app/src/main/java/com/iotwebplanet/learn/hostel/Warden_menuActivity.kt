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
        warden_data()
        CardView1wardenMenu.setOnClickListener({
            val mainActivity= Intent(this,All_problemActivity::class.java)
            startActivity(mainActivity)
        })

    }

    fun warden_data():Unit{
        val weburl = Webservices()

        val gson = Gson()

        var status:Warden_data



        Http.get {
            url=weburl.WARDEN_DATA

            val tag = "HTTP_LOG" //for debug

            params {
                // "com_id"-com_id  //parameters
                //"username"- username
                //"password"-password

            }

            onStart {
                Log.d(tag.toString(),"on start")
                Toast.makeText(this@Warden_menuActivity, "Login Started..", Toast.LENGTH_SHORT).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
                println(text)
                Toast.makeText(this@Warden_menuActivity, "Loading...data.."+text, Toast.LENGTH_SHORT).show();

                status = gson.fromJson<Warden_data>(text)

                //creating our adapter  all data will be set

                total.text=status.total
                pending.text=status.pending
                resolved.text=status.resolved
                processing.text=status.processing

                //now adding the adapter to recyclerview



            }

            onFail { error ->
                Log.d(tag.toString(),"on fail ${error.toString()}")
                Toast.makeText(this@Warden_menuActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();

            }

            onFinish { Log.d(tag.toString(), "on finish")


                Toast.makeText(this@Warden_menuActivity, "Finished", Toast.LENGTH_SHORT).show();





            }

        }
    }

}
