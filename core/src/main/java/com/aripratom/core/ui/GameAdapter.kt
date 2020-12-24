package com.aripratom.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.core.R
import com.aripratom.core.domain.model.Game
import com.aripratom.core.utils.OnGameClick
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val onGameClick: OnGameClick) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var gameList = arrayListOf<Game>()

    fun setData(newGameList: List<Game>?) {
        if (newGameList == null) return
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }

    fun clearData() {
        gameList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder =
            GameViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
            )

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int = gameList.size

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            with(itemView) {

                Glide.with(context).load(game.backgroundImage).placeholder(android.R.color.darker_gray).into(imgBackground)
                tvGameName.text = game.name


                setOnClickListener { onGameClick.goToDetail(game.id) }
            }
        }

    }

}