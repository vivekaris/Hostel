package com.iotwebplanet.learn.hostel

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.github.salomonbrys.kotson.fromJson
import com.google.android.gms.appinvite.AppInviteInvitation
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.nio.charset.Charset

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Http.init(this)

        if (isNetworkAvailable()) {

            val t = Toast.makeText(this, "You are online!!!!", Toast.LENGTH_LONG).show()
            stat_data()
        } else {


                val i = Intent(this, MainActivity::class.java)
                startActivity(i)

           // val t = Toast.makeText(this, "You are not online!!!!", Toast.LENGTH_LONG).show()
           // Log.v("Home", "############################You are not online!!!!")
        }




        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_add -> {
                val mainActivity = Intent(this@MainActivity, Add_complaintActivity::class.java)
                startActivity(mainActivity)
            }

            R.id.nav_find -> {

                val mainActivity = Intent(this@MainActivity, FinderActivity::class.java)
                startActivity(mainActivity)
            }
            R.id.nav_login -> {
                val mainActivity = Intent(this@MainActivity, Wardenl_loginActivity::class.java)
                startActivity(mainActivity)
            }

            R.id.nav_slogin -> {
                val mainActivity = Intent(this@MainActivity, SuperActivity::class.java)
                startActivity(mainActivity)
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun stat_data(): Unit {
        val weburl = Webservices()

        val gson = Gson()

        var status: Warden_data

        Http.get {
            url = weburl.STAT_DATA

            val tag = "HTTP_LOG" //for debug


            onStart {
              //  Log.d(tag.toString(), "on start")
                Toast.makeText(this@MainActivity, "Please wait..", Toast.LENGTH_LONG).show();

            }

            onSuccess { bytes ->
                //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                val text = bytes.toString(Charset.defaultCharset())
               // println(text)
              //  Toast.makeText(this@MainActivity, "Loading...data.." + text, Toast.LENGTH_SHORT).show();

                status = gson.fromJson<Warden_data>(text)

                //creating our adapter  all data will be set

                total.text = status.total
                pending.text = status.pending
                resolved.text = status.resolved
                processing.text = status.processing

                //now disable refresh view recyclerview


            }

            onFail { error ->
              //  Log.d(tag.toString(), "on fail ${error.toString()}")
                Toast.makeText(this@MainActivity, "E:" + error.toString(), Toast.LENGTH_LONG).show();

            }

            onFinish {
                //Log.d(tag.toString(), "on finish")


               // Toast.makeText(this@MainActivity, "Finished", Toast.LENGTH_SHORT).show();


            }


        }
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
