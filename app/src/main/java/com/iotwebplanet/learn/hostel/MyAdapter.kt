package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.list_layout.view.*

/**
 * Created by developer on 02/07/17.
 */
class MyAdapter(val pList: ArrayList<Problems>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.bindItems(pList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return pList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(problem: Problems) {
           // val hname = itemView.findViewById(R.id.textViewHostelname) as TextView
          //  val hclass  = itemView.findViewById(R.id.textViewClass) as TextView
          //  val attan  = itemView.findViewById(R.id.attandant) as TextView

            var st="Pending"
              when(problem.status){

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

            itemView.textViewSName.text=problem.name.toUpperCase()
            itemView.textViewCom_id.text=problem.com_id.toUpperCase()
            itemView.textViewStatus.text= st;





            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Clicked on List " + Integer.toString(getAdapterPosition()), Toast.LENGTH_LONG).show();
                val myActivity= Intent(itemView.context,Problem_actionActivity::class.java)
                //setting value
                myActivity.putExtra("id_num",problem.id_num)
                myActivity.putExtra("name",problem.name)
                myActivity.putExtra("com_id",problem.com_id)
                myActivity.putExtra("status",st)
                myActivity.putExtra("hostel",problem.hostel)
                myActivity.putExtra("problemtype",problem.problem_type)
                myActivity.putExtra("roomnum",problem.room_number)
                myActivity.putExtra("description",problem.description)
                myActivity.putExtra("mobile",problem.mobile)
                myActivity.putExtra("created",problem.created_on)
                itemView.context.startActivity(myActivity)

            }
        }
    }
}