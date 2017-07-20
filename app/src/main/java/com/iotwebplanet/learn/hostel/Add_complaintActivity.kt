package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http
import kotlinx.android.synthetic.main.activity_add_complaint.*
import java.nio.charset.Charset


import android.widget.ArrayAdapter
import com.ohmerhe.kolley.request.Http.get


class Add_complaintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_complaint)

Http.init(this)
        //calling
hostel_data()
send_problem.setOnClickListener({

    val id_num=studentIdAc.text.toString()
    val name=nameAc.text.toString()
    val mobile= mobileAc.text.toString()
    val hostel=hostelAc.selectedItem.toString()
    val room_number=roomAc.text.toString()
    val problem_type= problemTypeAc.text.toString()
    val description=descriptionAc.text.toString()

    //Toast.makeText(this@Add_complaintActivity, "Test .."+id_num+name+mobile+hostel+room_number+problem_type+description, Toast.LENGTH_LONG).show();


    add_complaint(id_num,name,mobile,hostel,room_number,problem_type,description)

})




    }

    fun add_complaint(id_num: String,name:String,mobile: String,hostel:String,room_number: String,problem_type:String,description: String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var status=Action_status("0","0","-")


        get {
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
             //   Log.d(tag.toString(),"on start")
                Toast.makeText(this@Add_complaintActivity, "Connecting....", Toast.LENGTH_SHORT).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
             //   println(text)
              //  Toast.makeText(this@Add_complaintActivity, "Loading...data.."+text, Toast.LENGTH_SHORT).show();

                status = gson.fromJson<Action_status>(text)

            }

            onFail { error ->
                Log.d(tag.toString(),"on fail ${error.toString()}")
                Toast.makeText(this@Add_complaintActivity, "E:"+error.toString(), Toast.LENGTH_LONG).show();

            }

            onFinish { Log.d(tag.toString(), "on finish")
                Toast.makeText(this@Add_complaintActivity, "Finished"+status.msg, Toast.LENGTH_SHORT).show();
                //now adding logic
                when(status.msg)
                {
                    "Success"->{
                        val resultActivity= Intent(this@Add_complaintActivity,ResultActivity::class.java)
                        resultActivity.putExtra("com_id",status.com_id)
                        startActivity(resultActivity)
                    }
                    else->{
                        Toast.makeText(this@Add_complaintActivity,"Error in Input",Toast.LENGTH_SHORT).show();

                    }

                }


            }

        }
    }

    fun hostel_data():Unit{
        val weburl = Webservices()

        val gson = Gson()

        var hostels= mutableListOf<Hostel_data>()

        get {
            url=weburl.ALL_HOSTEL

            val tag = "HTTP_LOG" //for debug

            params {
                // "com_id"-com_id  //parameters


            }

            onStart {
               // Log.d(tag.toString(),"on start")
              //  Toast.makeText(this@Add_complaintActivity, "Call Started..", Toast.LENGTH_SHORT).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text =bytes.toString(Charset.defaultCharset())
              //  println(text)
             //   Toast.makeText(this@Add_complaintActivity, "Loading...data.."+text, Toast.LENGTH_SHORT).show();


                hostels = gson.fromJson<MutableList<Hostel_data>>(text)
                //creating our adapter  all data will be set

                val s=hostels.size;   //size of collection


                val hs = ArrayList<String>()
//printing saved data in pojo class
                //printing list from loop
                hs.add("Select Hostel")
                for (i in 0..s-1) {
                    hs.add(hostels.get(i).hostel_name)

                }

             // Apply the adapter to the spinner
                val hint_adapter = ArrayAdapter(this@Add_complaintActivity,android.R.layout.simple_spinner_item,hs)
                hint_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
               hostelAc.adapter=hint_adapter
                // show hint
                hostelAc.setSelection(0)



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
