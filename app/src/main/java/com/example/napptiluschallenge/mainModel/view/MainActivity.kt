package com.example.napptiluschallenge.mainModel.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.databinding.ActivityMainBinding
import com.example.napptiluschallenge.mainModel.viewModel.MainViewModel
import com.example.napptiluschallenge.BR
import com.example.napptiluschallenge.common.entities.Worker
import com.example.napptiluschallenge.detailModel.DetailActivity
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
        setupFilter()
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            mBinding.viewModel?.getWorkers()
        }
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

    private fun setupFilter(){
        val genderList = resources.getStringArray(R.array.genderList)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, genderList)
        mBinding.genderTypes.setAdapter(arrayAdapter)

        val professionList = resources.getStringArray(R.array.professionList)
        val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown_item, professionList)
        mBinding.professionTypes.setAdapter(arrayAdapter2)
    }




    fun onFilter(view: View){
        val gender = mBinding.genderTypes.text.toString()
        val profession = mBinding.professionTypes.text.toString()

        if(profession != "" && gender != ""){
            lifecycleScope.launch{
                mBinding.viewModel?.getWorkersByProfessionAndGender(profession,gender)
            }
        }
        else if(profession != ""){
            lifecycleScope.launch{
                mBinding.viewModel?.getWorkersByProfession(profession)
            }
        }
        else if(gender != ""){
            lifecycleScope.launch{
                mBinding.viewModel?.getWorkersByGender(gender)
            }
        }
    }

//    private fun launchDetailFragment() {
//        val fragment = DetailFragment()
//
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//        fragmentTransaction.add(R.id.containerMain, fragment)
//        fragmentTransaction.commit()
//        fragmentTransaction.addToBackStack(null)
//    }

    //OnClickListener
    override fun onClick(worker: Worker) {
        Snackbar.make(mBinding.root, worker.id.toString(), Snackbar.LENGTH_LONG).show()
        val message = worker.id.toString()
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)

        //launchDetailFragment()
    }
}