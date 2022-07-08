package com.example.napptiluschallenge.detailModel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.common.entities.WorkersEntity
import com.example.napptiluschallenge.databinding.FragmentDetailBinding
import com.example.napptiluschallenge.detailModel.viewModel.DetailViewModel
import com.example.napptiluschallenge.mainModel.view.MainActivity
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment() {

    private lateinit var mBinding: FragmentDetailBinding
    //MVVM
    private lateinit var mDetailViewModel : DetailViewModel


    private lateinit var mWorkerEntity : WorkersEntity
    private var mActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDetailViewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //MVVM
        setupViewModel()
        //setupTextFields()
    }

    private fun setupViewModel() {
        mDetailViewModel.getStoreSelected().observe(viewLifecycleOwner) {
            mWorkerEntity = it
//            if (it.id != 0L) {
//
//                setupActionBar()
//            }

//            mEditStoreViewModel.getResult().observe(viewLifecycleOwner) { result ->
//                hideKeyboard()
//            }
        }
    }

    private fun setupActionBar() {
        mActivity = activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun loadImage(url: String){
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(mBinding.workerImage)
    }
}