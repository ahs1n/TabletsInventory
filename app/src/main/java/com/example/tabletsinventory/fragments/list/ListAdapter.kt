package com.example.tabletsinventory.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabletsinventory.R
import com.example.tabletsinventory.model.Tablets
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var tabletList = emptyList<Tablets>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tabletList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = tabletList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.tagNum_txt.text = currentItem.tagNum
        holder.itemView.location_txt.text = currentItem.location
        holder.itemView.custodian_txt.text = currentItem.custodian
        holder.itemView.accessoriesSend_txt.text = currentItem.accessoriesSend
        holder.itemView.accessoriesRec_txt.text = currentItem.accessoriesRec
        holder.itemView.issueDate_txt.text = currentItem.issueDate
        holder.itemView.projectName_txt.text = currentItem.projectName
    }
    fun setData(tablets: List<Tablets>){
        this.tabletList = tablets
        notifyDataSetChanged()
    }
}