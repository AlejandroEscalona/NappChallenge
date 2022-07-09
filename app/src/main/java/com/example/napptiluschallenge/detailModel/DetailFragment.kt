package com.example.napptiluschallenge.detailModel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.napptiluschallenge.BR
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.common.entities.Worker
import com.example.napptiluschallenge.databinding.FragmentDetailBinding
import com.example.napptiluschallenge.detailModel.viewModel.DetailViewModel
import com.example.napptiluschallenge.mainModel.view.MainActivity
import com.example.napptiluschallenge.mainModel.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {

    private lateinit var mBinding: FragmentDetailBinding
    //MVVM
    private lateinit var mDetailViewModel : DetailViewModel

    private lateinit var mWorker : Worker
    private var mActivity: MainActivity = MainActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDetailViewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.setContentView(mActivity, R.layout.activity_main)

//        mBinding = FragmentDetailBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            mBinding.worker?.getWorker(2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //MVVM
        setupViewModel()
        setupActionBar()
        setupObservers()
//        setUiWorker()
    }


//    private fun setUiWorker() {
//        with(mBinding){
//            tvFirstName.text = mWorker.first_name.trim()
////            etPhone.text = storeEntity.phone.editable()
////            etWebsite.text = storeEntity.website.editable()
////            etPhotoUrl.text = storeEntity.photoUrl.editable()
//        }
//    }

    private fun setupObservers() {
        mBinding.worker?.let {
            it.getSnackbarMsg().observe(viewLifecycleOwner){ resMsg ->
                Snackbar.make(mBinding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
            it.getResult().observe(viewLifecycleOwner){ result ->
                mWorker = result
            }
        }
    }

    private fun setupViewModel() {
        val vm = DetailViewModel()
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.viewModel, vm)
    }

    private fun setupActionBar() {
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
