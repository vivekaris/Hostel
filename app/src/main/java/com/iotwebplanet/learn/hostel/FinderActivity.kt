package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http
import kotlinx.android.synthetic.main.activity_finder.*
import java.nio.charset.Charset

class FinderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finder)
    Http.init(this)


        find.setOnClickListener({
            val com_id= com_id.text.toString().toUpperCase()
            val moblie=mobile.text.toString()
            problem_finder(com_id,moblie)
        })


    }

    fun problem_finder(com_id:String,mobile:String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var problem:Problems;



        Http.get {
            url=weburl.FINDER

            val tag = "HTTP_LOG" //for debug

            params {
                "com_id"-com_id  //parameters
                "mobile"-mobile  //parameters

            }


            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
              //  println(text)
                Toast.makeText(this@FinderActivity, "Finding...."+text, Toast.LENGTH_SHORT).show();

                problem = gson.fromJson<Problems>(text)
                //   hostels = gson.fromJson<List<Hostel>>(text) as ArrayList<Hostel>

                when(problem.msg)
                {
                    "Success"->{
                        val mstatus=problem.status;
                        val v=weburl.statusChanger(mstatus)
                        val menuActivity= Intent(this@FinderActivity,Complaint_viewActivity::class.java)
                        menuActivity.putExtra("status",v)
                        startActivity(menuActivity)
                    }
                    else->{
                        Toast.makeText(this@FinderActivity,"Data not Found",Toast.LENGTH_SHORT).show();

                    }

                }


                //now adding the adapter to recyclerview



            }

            onFail { error ->
                Log.d(tag.toString(),"on fail ${error.toString()}")
                Toast.makeText(this@FinderActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();

            }



        }
    }
}
