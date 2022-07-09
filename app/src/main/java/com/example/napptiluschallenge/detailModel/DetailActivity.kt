package com.example.napptiluschallenge.detailModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.napptiluschallenge.BR
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.common.entities.Worker
import com.example.napptiluschallenge.databinding.ActivityDetailBinding
import com.example.napptiluschallenge.detailModel.viewModel.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityDetailBinding

    private lateinit var mWorker : Worker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setupObservers()
        setupViewModel()
        //setupImage()
    }

//    private fun setupImage() {
//        val worker = mBinding.worker
//        loadImage(worker)
//    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            mBinding.worker?.getWorker(2)
        }
    }


    private fun setupObservers() {
        mBinding.worker?.let {
            it.getSnackbarMsg().observe(this){ resMsg ->
                Snackbar.make(mBinding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
//            it.getResult().observe(this){
//                loadImage(it.image)
//            }
        }

    }

    private fun setupViewModel() {
        val vm: DetailViewModel by viewModels()
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.worker, vm)
    }


    private fun loadImage(url : String){
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(mBinding.workerImage)
    }
}