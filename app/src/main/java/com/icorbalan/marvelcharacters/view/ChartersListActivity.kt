package com.icorbalan.marvelcharacters.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.icorbalan.marvelcharacters.databinding.ActivityMainBinding
import com.icorbalan.marvelcharacters.viewmodel.CharactersViewModel

class ChartersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charactersViewModel.characterModel.observe(this, Observer { characterModel ->
            binding.tvCharacterName.text = characterModel.name
        })

        binding.viewContainer.setOnClickListener {
            charactersViewModel.randomCharacter()
        }
    }
}