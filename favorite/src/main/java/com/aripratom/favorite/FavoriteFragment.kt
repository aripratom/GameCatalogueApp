package com.aripratom.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.core.ui.FavoriteGameAdapter
import com.aripratom.core.utils.OnGameClick
import com.aripratom.favorite.databinding.FragmentFavoriteBinding
import com.aripratom.favorite.di.favoriteModule
import com.aripratom.gamecatalogue.detail.GameDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment(), OnGameClick {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapterFav: FavoriteGameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unloadKoinModules(favoriteModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterFav = FavoriteGameAdapter(this)

        if (activity != null) {
            loadKoinModules(favoriteModule)
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.favoriteGame.observe(viewLifecycleOwner) {
            adapterFav.setData(it)
            binding.tvEmptyFavorite.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvFavGame) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterFav
        }
    }

    override fun goToDetail(id: Int?) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }


}