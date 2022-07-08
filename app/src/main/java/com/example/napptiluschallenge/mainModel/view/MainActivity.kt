package com.example.napptiluschallenge.mainModel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.databinding.ActivityMainBinding
import com.example.napptiluschallenge.mainModel.viewModel.MainViewModel
import com.example.napptiluschallenge.BR
import com.example.napptiluschallenge.common.entities.Worker
import com.example.napptiluschallenge.mainModel.view.adapters.MainAdapter
import com.example.napptiluschallenge.mainModel.view.adapters.OnClickListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter : MainAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupObservers() {
        mBinding.viewModel?.let {
            it.getSnackbarMsg().observe(this){ resMsg ->
                Snackbar.make(mBinding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
            it.getResult().observe(this){ result ->
                mAdapter.submitList(result.results)
            }
        }
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.viewModel, vm)
    }

    private fun setupRecyclerView() {
        mGridLayout = GridLayoutManager(this,resources.getInteger(R.integer.main_column))

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }

    private fun setupAdapter() {
        mAdapter = MainAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            mBinding.viewModel?.getWorkers()
        }
    }

    //OnClickListener
    override fun onClick(worker: Worker) {
        Snackbar.make(mBinding.root, worker.id, Snackbar.LENGTH_LONG).show()
    }
}