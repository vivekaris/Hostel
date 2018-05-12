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
import kotlinx.android.synthetic.main.activity_problem_action.*
import kotlinx.android.synthetic.main.activity_problem_action.view.*
import kotlinx.android.synthetic.main.content_main.*
import java.nio.charset.Charset

class Problem_actionActivity : AppCompatActivity() {
    var hostel=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_action)
        Http.init(this)

        val id_num=intent.extras.getString("id_num");
        val name=intent.extras.getString("name");
        val st=intent.extras.getString("status");
        val mobile=intent.extras.getString("mobile")
        val com_id=intent.extras.getString("com_id")
         hostel=intent.extras.getString("hostel")
        val problemtype=intent.extras.getString("problem_type")
        val roomnum=intent.extras.getString("room_number")
        val description=intent.extras.getString("description")
        val created=intent.extras.getString("created_on")



        //set
        sIdTextViewPView.text=id_num
        nameTextViewPView.text=name
        mobileTextViewPView.text=mobile
        comIdTextViewPView.text=com_id
        hostelTextViewPView.text=hostel
        problemTypeTVPView.text=problemtype
        roomTextViewPView.text=roomnum
        statusTVPView.text=st
        descTextViewPView.text=description
        registrationPa.text=created




        //textIdnumValue.text=id_num
     // textStatusValue.text=st
      //  textNameValue.text=name
        resolve.setOnClickListener({

            val id_num=id_num;

            val status="2";

            //Toast.makeText(this@Add_complaintActivity, "Test .."+id_num+name+mobile+hostel+room_number+problem_type+description, Toast.LENGTH_LONG).show();


            resolve_complaint(com_id,status)

        })

        //reject
        reject.setOnClickListener({

            val id_num=id_num;

            val status="3";

            //Toast.makeText(this@Add_complaintActivity, "Test .."+id_num+name+mobile+hostel+room_number+problem_type+description, Toast.LENGTH_LONG).show();


            resolve_complaint(com_id,status)

        })
//processing

        process.setOnClickListener({

            val id_num=id_num;

            val status="1";

            //Toast.makeText(this@Add_complaintActivity, "Test .."+id_num+name+mobile+hostel+room_number+problem_type+description, Toast.LENGTH_LONG).show();


            resolve_complaint(com_id,status)

        })

    }


    fun resolve_complaint(id_num: String,cstatus: String):Unit{
        val weburl = Webservices()

        val gson = Gson()

        var status=Action_status("0","0","-")


        Http.get {
            url = weburl.UPDATE_PROBLEM

            val tag = "HTTP_LOG" //for debug

            params {
                // "com_id"-com_id  //parameters
                "id_num" - id_num
                "status" - cstatus

            }

            onStart {
                //   Log.d(tag.toString(),"on start")
                Toast.makeText(this@Problem_actionActivity, "Connecting....", Toast.LENGTH_SHORT).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text = bytes.toString(Charset.defaultCharset())
                //   println(text)
                //  Toast.makeText(this@Add_complaintActivity, "Loading...data.."+text, Toast.LENGTH_SHORT).show();

                status = gson.fromJson<Action_status>(text)

            }

            onFail { error ->
                Log.d(tag.toString(), "on fail ${error.toString()}")
                Toast.makeText(this@Problem_actionActivity, "E:" + error.toString(), Toast.LENGTH_LONG).show();

            }

            onFinish {
                Log.d(tag.toString(), "on finish")
                Toast.makeText(this@Problem_actionActivity, "Finished" + status.msg, Toast.LENGTH_SHORT).show();
                //now adding logic
                when (status.msg) {
                    "Success" -> {
                        val mainActivity= Intent(this@Problem_actionActivity,Warden_menuActivity::class.java)

                        mainActivity.putExtra("hostel_name",hostel)
                        startActivity(mainActivity)


                    }
                    else -> {
                        Toast.makeText(this@Problem_actionActivity, "Error in Input", Toast.LENGTH_SHORT).show();

                    }

                }


            }

        }
    }

}
