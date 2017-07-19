package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http

import kotlinx.android.synthetic.main.activity_add_warden.*
import java.nio.charset.Charset

class add_wardenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_warden)
Http.init(this)
        send.setOnClickListener({
            val name=name.text.toString()
            val username=user_name.text.toString()
            val password=password.text.toString()
            val hostel=hostel_name.text.toString()
            val mobile=mobile_num.text.toString()
            val email= email.text.toString()

add_warden(name,username,password,hostel,mobile,email)
        })



    }

    fun add_warden(name:String,username:String,password:String,hostel:String,mobile: String,email:String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var status=Action_status("0","0","-")


        Http.get {
            url = weburl.ADD_WARDEN

            val tag = "HTTP_LOG" //for debug

            params {
                // "com_id"-com_id  //parameters
                "username" - username
                "name" - name
                "password" -password
                "hostel_name" - hostel
                "mobile" - mobile
                "email" - email
            }

            onStart {
                //   Log.d(tag.toString(),"on start")
                Toast.makeText(this@add_wardenActivity, "Connecting....", Toast.LENGTH_SHORT).show();

            }

            onSuccess { bytes ->
                 Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text = bytes.toString(Charset.defaultCharset())
                  println(text)
                //  Toast.makeText(this@Add_complaintActivity, "Loading...data.."+text, Toast.LENGTH_SHORT).show();

                status = gson.fromJson<Action_status>(text)

            }

            onFail { error ->
                Log.d(tag.toString(), "on fail ${error.toString()}")
                Toast.makeText(this@add_wardenActivity, "E:" + error.toString(), Toast.LENGTH_LONG).show();

            }

            onFinish {
                Log.d(tag.toString(), "on finish")
                Toast.makeText(this@add_wardenActivity, "Finished" + status.msg, Toast.LENGTH_SHORT).show();
                //now adding logic
                when (status.msg) {
                    "Success" -> {
                        val resultActivity = Intent(this@add_wardenActivity, WResultActivity::class.java)
                        resultActivity.putExtra("com_id", status.msg)
                        startActivity(resultActivity)
                    }
                    else -> {
                        Toast.makeText(this@add_wardenActivity, "Error in Input", Toast.LENGTH_SHORT).show();

                    }

                }


            }

        }
    }

}
