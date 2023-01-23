package com.example.kotlinmvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.databinding.RowMuseumBinding
import com.example.kotlinmvvm.model.Museum

class MuseumAdapter(private var museums: List<Museum>) :
    RecyclerView.Adapter<MuseumAdapter.MViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder = (
            MViewHolder(
                DataBindingUtil.inflate<RowMuseumBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.row_museum,
                    parent,
                    false
                )

            )
            )


    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.rowMuseumBinding.museum = museums[position]
    }

    override fun getItemCount(): Int {
        return museums.size
    }

    fun update(data: List<Museum>) {
        museums = data
        notifyDataSetChanged()
    }


    inner class MViewHolder(val rowMuseumBinding: RowMuseumBinding) :
        RecyclerView.ViewHolder(rowMuseumBinding.root)


}