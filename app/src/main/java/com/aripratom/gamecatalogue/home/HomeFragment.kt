package com.aripratom.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.core.data.Resource
import com.aripratom.core.domain.model.Game
import com.aripratom.core.ui.GameAdapter
import com.aripratom.core.utils.OnGameClick
import com.aripratom.gamecatalogue.databinding.FragmentHomeBinding
import com.aripratom.gamecatalogue.detail.GameDetailActivity
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), OnGameClick {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private lateinit var adapterGamePopular: GameAdapter

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            adapterGamePopular = GameAdapter(this)
            getPopularGames(binding!!.rvPopularGame, adapterGamePopular)
        }
    }


    private fun observeViewModel(adapter: GameAdapter, progressBar: ShimmerFrameLayout) =
            Observer<Resource<List<Game>>> { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            adapter.setData(game.data)
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.INVISIBLE
                            Toast.makeText(context, game.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

    private fun getPopularGames(rv: RecyclerView, gameAdapter: GameAdapter) {
        viewModel.popularGames.observe(viewLifecycleOwner, observeViewModel(adapterGamePopular, binding!!.progressPopular))
        rv.apply {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = gameAdapter
        }
    }

    override fun goToDetail(id: Int?) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}