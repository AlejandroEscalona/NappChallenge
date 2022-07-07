package com.example.napptiluschallenge.mainModel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.databinding.ActivityMainBinding
import com.example.napptiluschallenge.mainModel.viewModel.MainViewModel
import com.example.napptiluschallenge.BR
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
    }

    private fun setupObservers() {
        mBinding.viewModel?.let {
            it.getSnackbarMsg().observe(this){ resMsg ->
                Snackbar.make(mBinding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.viewModel, vm)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            mBinding.viewModel.getWorkers()
        }
    }
}