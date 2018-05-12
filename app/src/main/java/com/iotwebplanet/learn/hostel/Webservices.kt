package com.iotwebplanet.learn.hostel

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by developer on 02/07/17.
 */

class Webservices{
   // Don't forget to enter current IP because of localhost

    val SERVER="http://xx/"
    val APP="Hostel/"
    val ALL_COMPLAINT=SERVER+APP+"all_prob.php"
    val ALL_COMPLAINT_STATUS=SERVER+APP+"all_prob_status.php"
    val FINDER=SERVER+APP+"finder.php"
    val ADD_COMPLAINT=SERVER+APP+"add_problem.php"
    val LOGIN_CHECK=SERVER+APP+"login_check.php"
    val WARDEN_DATA=SERVER+APP+"warden_menu.php"
    val STAT_DATA=SERVER+APP+"stat.php"
    val ALL_HOSTEL=SERVER+APP+"all_hostel.php"
    val ADD_WARDEN=SERVER+APP+"add_warden.php"
    val UPDATE_PROBLEM=SERVER+APP+"update_problems.php"

    fun statusChanger(status: Int):String{

        var st="Pending"
        when(status){

            1->{
                st="Processing"
            }
            2->{
                st="Solved"
            }
            3->{
                st="Rejected"
            }else->{

            st="Pending"

        }
        }
      return st
    }



}