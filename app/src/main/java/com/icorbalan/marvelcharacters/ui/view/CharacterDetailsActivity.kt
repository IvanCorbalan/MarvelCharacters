package com.icorbalan.marvelcharacters.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.icorbalan.marvelcharacters.R
import com.icorbalan.marvelcharacters.databinding.ActivityCharacterDetailBinding
import com.icorbalan.marvelcharacters.databinding.ActivityMainBinding
import com.icorbalan.marvelcharacters.ui.viewmodel.CharacterDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding

    private val characterDetailsViewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterId = intent.getIntExtra(ChartersListActivity.CHARACTER_ID_KEY, 0)

        characterDetailsViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        characterDetailsViewModel.errorMessage.observe(this, Observer { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG)
                .setAction(R.string.snack_bar_retry) {
                    characterDetailsViewModel.onCreate(characterId)
                }
                .show()
        })

        characterDetailsViewModel.characterModel.observe(this, Observer { character ->
            binding.tvName.text = character.name
            Glide.with(binding.imCharacterImage.context).load(character.thumbnail.imageUrl()).into(binding.imCharacterImage)
        })

        characterDetailsViewModel.onCreate(characterId)
    }
}