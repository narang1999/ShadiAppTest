package com.example.shaditestapp.view.shadimatches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.shaditestapp.databinding.ShadiUserMatchesBinding
import com.example.shaditestapp.extention.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.Menu
import com.example.shaditestapp.R
import com.example.shaditestapp.utils.dialogutil.DialogUtil


class ShadiMatches : AppCompatActivity() {
    private var mainAdapter: ShadiMatchesUsersAdapter? = null
    private val binding by viewBinding(ShadiUserMatchesBinding::inflate)
    private val viewModel: ShadiMatchesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mainAdapter = ShadiMatchesUsersAdapter {
            viewModel.onStatusBtnClicked(it.second, it.first)
        }

        binding.post.adapter = mainAdapter
        subscribeUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.shadimatch, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionReload -> {
                viewModel.getMatchedUsers()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeUi() {
        viewModel.onApiError.observe(this) {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show()
        }

        viewModel.error.observe(this) {
            DialogUtil.showTechnicalDialog(this)
        }
        viewModel.allMatchesUsers.observe(this) {
            mainAdapter?.submitList(it)
        }
    }

    override fun onDestroy() {
        mainAdapter = null
        super.onDestroy()
    }
}
