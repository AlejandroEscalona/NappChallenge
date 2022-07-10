package com.example.napptiluschallenge.detailModel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.napptiluschallenge.BR
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.databinding.ActivityDetailBinding
import com.example.napptiluschallenge.detailModel.viewModel.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setupObservers()
        setupViewModel()
    }

    override fun onStart() {
        super.onStart()
        val extras = this.intent.extras
        val idWorker = extras?.getInt("workerId")
        lifecycleScope.launch{
            mBinding.worker?.getWorker(idWorker?: 0)
            mBinding.worker?.getResult()?.observe(this@DetailActivity){
                loadImage(it.image)
            }
        }
    }

    private fun setupObservers() {
        mBinding.worker?.let { it ->
            it.getSnackbarMsg().observe(this){ resMsg ->
                Snackbar.make(mBinding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupViewModel() {
        val vm: DetailViewModel by viewModels()
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.worker, vm)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    fun composeEmail(view: View) {
        val email = mBinding.tvEmail.text.toString().trim()
        val uriText = "mailto:$email" +
                "?subject=" + Uri.encode("Email From RRHH") +
                "&body=" + Uri.encode("some text here")

        val uri = Uri.parse(uriText)

        val sendIntent = Intent(Intent.ACTION_SENDTO)
        sendIntent.data = uri
        startActivity(Intent.createChooser(sendIntent, "Send email"))
    }

    fun viewCountry(view: View){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:0,0?q=40.6892494,-74.0466891(Escalona)")
            `package` = "com.google.android.apps.maps"
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }else
            Toast.makeText(this,"Error in email", Toast.LENGTH_SHORT).show()
    }

    private fun loadImage(url: String?){
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .into(mBinding.workerImage)
    }
}

