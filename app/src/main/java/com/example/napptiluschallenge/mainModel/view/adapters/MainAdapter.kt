package com.example.napptiluschallenge.mainModel.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.napptiluschallenge.BR
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.common.entities.Worker
import com.example.napptiluschallenge.databinding.ItemWorkerBinding

class MainAdapter(private val listener: OnClickListener) :
    ListAdapter<Worker,RecyclerView.ViewHolder>(WorkerDiffCallBack()) {

    private lateinit var mContext: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_worker, parent, false)
        )


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val worker = getItem(position)

        with(holder as ViewHolder){
            holder.mBinding?.setVariable(BR.result, worker)
            holder.mBinding?.executePendingBindings()
            Glide.with(mContext)
                .load(worker.imageURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(mBinding!!.imageURL)
            setListener(worker)
        }
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val mBinding = DataBindingUtil.bind<ItemWorkerBinding>(view)

        fun setListener(worker: Worker){
            mBinding?.root?.setOnClickListener {

            }
        }
    }

    class WorkerDiffCallBack : DiffUtil.ItemCallback<Worker>(){
        override fun areItemsTheSame(oldItem: Worker, newItem: Worker): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Worker, newItem: Worker): Boolean =
            oldItem == newItem
    }


}