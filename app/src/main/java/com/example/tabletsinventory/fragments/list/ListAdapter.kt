package com.example.tabletsinventory.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tabletsinventory.R
import com.example.tabletsinventory.model.Tablets
import com.example.tabletsinventory.utils.convertStringToUpperCase
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
        holder.itemView.tagNum_txt.text = currentItem.tagNum
        holder.itemView.location_txt.text = currentItem.location
        holder.itemView.custodian_txt.text = currentItem.custodian
        holder.itemView.projectName_txt.text = currentItem.projectName.convertStringToUpperCase()

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(tablets: List<Tablets>) {
        this.tabletList = tablets
        notifyDataSetChanged()
    }
}