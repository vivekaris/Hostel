package com.iotwebplanet.learn.hostel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_complaint_view.*

class Complaint_viewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_view)

        val s=intent.extras.getString("status");

        st.text=s;

    }
}
