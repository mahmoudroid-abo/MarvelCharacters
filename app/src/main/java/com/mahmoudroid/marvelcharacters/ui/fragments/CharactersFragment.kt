package com.mahmoudroid.marvelcharacters.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudroid.marvelcharacters.CharactersActivity
import com.mahmoudroid.marvelcharacters.R
import com.mahmoudroid.marvelcharacters.adapters.CharactersAdapter
import com.mahmoudroid.marvelcharacters.ui.CharactersViewModel
import com.mahmoudroid.marvelcharacters.utils.Resource
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    lateinit var viewModel: CharactersViewModel

    lateinit var charactersAdapter: CharactersAdapter

    val TAG = "CharactersFragment"



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as CharactersActivity).viewModel

        setUpRecyclerView()


        charactersAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("results", it)
            }

            findNavController().navigate(
                R.id.action_charactersFragment_to_detailsFragment,
                bundle
            )
        }


        viewModel.charactersLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data.let { charactersResponse ->
                        charactersAdapter.differ.submitList(charactersResponse!!.data.results)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error!!: $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "Display Logo On ActionBar"
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
            setLogo(R.drawable.marvel_logo)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    charactersRecyclerView.scrollToPosition(0)
                    viewModel.searchCharactersLiveData
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    private fun setUpRecyclerView() {
        charactersAdapter = CharactersAdapter()
        charactersRecyclerView.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }
}
