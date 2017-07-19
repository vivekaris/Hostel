package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http
import kotlinx.android.synthetic.main.activity_wardenl_login.*
import java.nio.charset.Charset

class Wardenl_loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Http.init(this)

        setContentView(R.layout.activity_wardenl_login)
        //val mainActivity= Intent(this,All_problemActivity::class.java)
        //startActivity(mainActivity)

        tryagain.setOnClickListener({
            val username= username.text.toString()
            val password=passWardenLogin.text.toString()

            //call
            login_check(username,password)

        })


    }


    //my login_check fun
    fun login_check(username: String,password:String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var status=Action_status("0","-","-")



        Http.get {
            url=weburl.LOGIN_CHECK

            val tag = "HTTP_LOG" //for debug

            params {
                // "com_id"-com_id  //parameters
                "username"- username
                "password"-password

            }

            onStart {
               // Log.d(tag.toString(),"on start")
                Toast.makeText(this@Wardenl_loginActivity, "Please wait..", Toast.LENGTH_SHORT).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
                //println(text)
                //Toast.makeText(this@Wardenl_loginActivity, "Loading...data.."+text, Toast.LENGTH_SHORT).show();

                status = gson.fromJson<Action_status>(text)

                //creating our adapter  all data will be set



                //now adding the adapter to recyclerview



            }

            onFail { error ->
               // Log.d(tag.toString(),"on fail ${error.toString()}")
                Toast.makeText(this@Wardenl_loginActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();

            }

            onFinish { Log.d(tag.toString(), "on finish")


             //   Toast.makeText(this@Wardenl_loginActivity, "Finished"+status.msg,Toast.LENGTH_SHORT).show();

                when(status.msg)
                {
                    "Success"->{
                        val menuActivity= Intent(this@Wardenl_loginActivity,Warden_menuActivity::class.java)
                        val hostel_name=status.hostel_name;
                        menuActivity.putExtra("hostel_name",hostel_name)
                        startActivity(menuActivity)
                    }
                    else->{
                        Toast.makeText(this@Wardenl_loginActivity,"Error in Login",Toast.LENGTH_SHORT).show();

                    }

                }



            }

        }
    }



}
