package com.aripratom.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.core.R
import com.aripratom.core.domain.model.Game
import com.aripratom.core.utils.OnGameClick
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_favorite_game.view.*

class FavoriteGameAdapter(private val onGameClick: OnGameClick): RecyclerView.Adapter<FavoriteGameAdapter.FavoriteGameViewHolder>() {

    private var gameList = arrayListOf<Game>()

    fun setData(newGameList: List<Game>?) {
        if (newGameList == null) return
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGameViewHolder =
            FavoriteGameViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_game, parent, false)
            )

    override fun onBindViewHolder(holder: FavoriteGameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int = gameList.size

    inner class FavoriteGameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(game: Game) {
            with(itemView) {
                Glide.with(context).load(game.backgroundImage).placeholder(android.R.color.darker_gray).into(imgBackground)
                tvGameName.text = game.name

                setOnClickListener { onGameClick.goToDetail(game.id) }
            }
        }
    }

}