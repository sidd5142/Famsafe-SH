package com.example.otp_number.guardiandetail


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.otp_number.R
import com.example.otp_number.database.Guardian


class GuardianAdapter(val guardians: List<Guardian>)
    : RecyclerView.Adapter<GuardianAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : GuardianAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: GuardianAdapter.ViewHolder, position: Int) {
        holder.name.text = guardians[position].guardianName
        holder.relation.text = guardians[position].guardianRelation
        holder.phone.text = guardians[position].guardianPhoneNo
        holder.email.text = guardians[position].guardianEmail

    }

    override fun getItemCount(): Int {
        return guardians.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById<TextView>(R.id.textName)
        val relation : TextView = itemView.findViewById<TextView>(R.id.textRelation)
        val phone : TextView = itemView.findViewById<TextView>(R.id.textPhone)
        val email : TextView = itemView.findViewById<TextView>(R.id.textEmail)
    }

}