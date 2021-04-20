package com.tahmeel.task.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.tahmeel.task.R
import com.tahmeel.task.databinding.ActivityMainBinding
import com.tahmeel.task.ui.main.adapter.OrdersAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    @VisibleForTesting
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@MainActivity
            adapter = OrdersAdapter()
            vm = viewModel
        }
        filterByCents()
    }

    private fun filterByCents() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    viewModel.getSearchedProducts(s.toString().toInt())
                    Timber.d(s.toString())
                }
                else{
                    viewModel.getSearchedProducts(null)
                }
            }
        })
    }
}