package com.aripratom.gamecatalogue.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.aripratom.core.data.Resource
import com.aripratom.core.domain.model.Game
import com.aripratom.core.utils.dateFormat
import com.aripratom.gamecatalogue.R
import com.aripratom.gamecatalogue.databinding.ActivityGameDetailBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_content.*
import org.koin.android.viewmodel.ext.android.viewModel


class GameDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameDetailBinding

    private var id: String? = null

    private val viewModel: GameDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getIntExtra("id", 0).toString()

        getDetail(id)

    }

    private fun getDetail(id: String?) {
        viewModel.getGameDetail(id!!).observe(this, Observer { game ->
            when (game) {
                is Resource.Loading -> {
                    binding.lytDesc.root.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.lytDesc.root.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    showGameDetail(game.data)
                }
                is Resource.Error -> {
                    binding.lytDesc.root.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, game.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        })
    }

    private fun showGameDetail(data: Game?) {
            if (data != null) {
                var favoriteStatus = data.isFavorite
                setFavoriteStatus(favoriteStatus)

                val rating = "${data.rating} from ${data.ratingCount} votes"
                Glide.with(this).load(data.backgroundImage)
                    .placeholder(android.R.color.darker_gray).into(binding.imgBackground)
                tvGameName.text = data.name
                tvReleaseDate.text = dateFormat(data.released)
                tvRating.text = rating
                tvDescription.text = data.description

                binding.fab.setOnClickListener {
                    favoriteStatus = !favoriteStatus
                    viewModel.setFavoriteGame(data, favoriteStatus)
                    setFavoriteStatus(favoriteStatus)
                }

            }

    }

    private fun setFavoriteStatus(status: Boolean) {
            if (status) {
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite
                    )
                )
            } else {
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite_less
                    )
                )
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}