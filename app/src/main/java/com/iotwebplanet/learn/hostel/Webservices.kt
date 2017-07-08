package com.iotwebplanet.learn.hostel

/**
 * Created by developer on 02/07/17.
 */

class Webservices{
    //val SERVER="http://192.168.1.4/"
    val SERVER="https://www.iotwebplanet.com/playground/"
    val APP="Hostel/"
    val ALL_COMPLAINT=SERVER+APP+"all_prob.php"
    val FINDER=SERVER+APP+"finder.php"
    val ADD_COMPLAINT=SERVER+APP+"add_problem.php"
    val LOGIN_CHECK=SERVER+APP+"login_check.php"
    val WARDEN_DATA=SERVER+APP+"warden_menu.php"
    val STAT_DATA=SERVER+APP+"stat.php"
    val ALL_HOSTEL=SERVER+APP+"all_hostel.php"

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