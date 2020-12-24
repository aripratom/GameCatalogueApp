package com.aripratom.gamecatalogue.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.aripratom.core.data.Resource
import com.aripratom.core.ui.GameAdapter
import com.aripratom.core.utils.OnGameClick
import com.aripratom.gamecatalogue.databinding.FragmentSearchBinding
import com.aripratom.gamecatalogue.detail.GameDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), SearchView.OnQueryTextListener, OnGameClick {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding

    private lateinit var adapterGame: GameAdapter

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            adapterGame = GameAdapter(this)
            observeViewModel()
            setupRecyclerView()
        }

    }

    private fun setupRecyclerView() {
        binding!!.rvSearch.apply {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = adapterGame
        }
    }

    private fun observeViewModel() {
        viewModel.searchGames.observe(viewLifecycleOwner, Observer { game ->
            when(game) {
                is Resource.Loading -> {
                    binding?.progressSearch?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                   if (game.data != null){
                       binding?.progressSearch?.visibility = View.GONE
                        adapterGame.setData(game.data)
                        binding?.tvEmpty?.visibility = View.GONE
                    } else binding?.tvEmpty?.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    Toast.makeText(context, game.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding?.search?.setOnQueryTextListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            adapterGame.clearData()
            viewModel.setSearchQuery(query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    override fun goToDetail(id: Int?) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}