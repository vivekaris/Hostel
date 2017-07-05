package com.iotwebplanet.learn.hostel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http
import kotlinx.android.synthetic.main.activity_add_complaint.*
import java.nio.charset.Charset

class Add_complaintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_complaint)

Http.init(this)

send_btn.setOnClickListener({

    val id_num=textViewSid.text.toString()
    val name=textViewSName.text.toString()
    val mobile=textViewMobile.text.toString()
    val hostel=textViewHostel.text.toString()
    val room_number=textViewRoom.text.toString()
    val problem_type=textViewProbType.text.toString()
    val description=textViewdescription.text.toString()

    //Toast.makeText(this@Add_complaintActivity, "Test .."+id_num+name+mobile+hostel+room_number+problem_type+description, Toast.LENGTH_LONG).show();


    add_complaint(id_num,name,mobile,hostel,room_number,problem_type,description)

})




    }

    fun add_complaint(id_num: String,name:String,mobile: String,hostel:String,room_number: String,problem_type:String,description: String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var problem:Problems;

        Http.get {
            url=weburl.ADD_COMPLAINT

            val tag = "HTTP_LOG" //for debug

            params {
               // "com_id"-com_id  //parameters
                "id_num"- id_num
                "name"-name
                "mobile"-mobile
                "hostel"-hostel
                "room_number"-room_number
                "problem_type"-problem_type
                "description"-description
            }

            onStart {
                Log.d(tag.toString(),"on start")
                Toast.makeText(this@Add_complaintActivity, "Finder Started..", Toast.LENGTH_SHORT).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
                println(text)
                Toast.makeText(this@Add_complaintActivity, "Loading...data.."+text, Toast.LENGTH_SHORT).show();

               // problem = gson.fromJson<Problems>(text)
                //   hostels = gson.fromJson<List<Hostel>>(text) as ArrayList<Hostel>

                //creating our adapter  all data will be set



                //now adding the adapter to recyclerview



            }

            onFail { error ->
                Log.d(tag.toString(),"on fail ${error.toString()}")
                Toast.makeText(this@Add_complaintActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();

            }

            onFinish { Log.d(tag.toString(), "on finish")
                Toast.makeText(this@Add_complaintActivity, "Finished", Toast.LENGTH_SHORT).show();



            }

        }
    }

}
