package com.icorbalan.marvelcharacters.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.icorbalan.marvelcharacters.R
import com.icorbalan.marvelcharacters.databinding.ActivityMainBinding
import com.icorbalan.marvelcharacters.ui.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val charactersAdapter = CharactersListAdapter()

    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charactersViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        binding.characterList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charactersAdapter
        }

        charactersViewModel.characterModel.observe(this, Observer { characterModel ->
            charactersAdapter.submitList(characterModel)
        })

        charactersViewModel.errorMessage.observe(this, Observer { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG)
                .setAction(R.string.snack_bar_retry) {
                    charactersViewModel.onCreate()
                }
                .show()
        })

        charactersViewModel.onCreate()
    }
}