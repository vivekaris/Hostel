package com.iotwebplanet.learn.hostel

/**
 * Created by developer on 02/07/17.
 */

class Webservices{
    val SERVER="http://192.168.43.130/"
    val ALL_COMPLAINT="http://www.iotwebplanet.com/playground/Hostel/all_problem.php"
    val FINDER=SERVER+"Hostel/finder.php"
    val ADD_COMPLAINT="http://www.iotwebplanet.com/playground/Hostel/add_problem.php"
    //val ALL_COMPLAINT="http://192.168.1.5/Hostel/all_prob.php"
    val LOGIN_CHECK="http://192.168.43.130/Hostel/login_check.php"
    val WARDEN_DATA="http://192.168.1.5/Hostel/warden_menu.php"


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