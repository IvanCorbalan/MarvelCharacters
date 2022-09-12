package com.icorbalan.marvelcharacters.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.icorbalan.marvelcharacters.databinding.ActivityMainBinding
import com.icorbalan.marvelcharacters.ui.viewmodel.CharactersViewModel

class ChartersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charactersViewModel.onCreate()

        charactersViewModel.characterModel.observe(this, Observer { characterModel ->
            binding.tvCharacterName.text = characterModel.name
        })

        charactersViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        binding.viewContainer.setOnClickListener {
            charactersViewModel.randomCharacter()
        }
    }
}