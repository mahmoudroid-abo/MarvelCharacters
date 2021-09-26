package com.mahmoudroid.marvelcharacters.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mahmoudroid.marvelcharacters.CharactersActivity
import com.mahmoudroid.marvelcharacters.R
import com.mahmoudroid.marvelcharacters.adapters.CharactersAdapter
import com.mahmoudroid.marvelcharacters.models.Comics
import com.mahmoudroid.marvelcharacters.models.Result
import com.mahmoudroid.marvelcharacters.ui.CharactersViewModel
import com.mahmoudroid.marvelcharacters.utils.HorizontalItemDecoration
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_character_list.view.*
import java.util.ArrayList
import androidx.appcompat.app.AppCompatActivity




class DetailsFragment : Fragment(R.layout.fragment_details) {

    val TAG = "DetailsFragment"
    lateinit var viewModel: CharactersViewModel

    val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        viewModel = (activity as CharactersActivity).viewModel

        val results = args.results

        characterName.text = results.name
        descriptionText.text = results.description
        val image = "${results.thumbnail.path}/landscape_incredible.jpg"

        Glide.with(this)
            .load(image)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.ic_error)
            .into(characterImage)


//        setupHorizontalLists()
    }

//    private fun hideIfEmpty(view: View, list: Comics?) {
//        if (list?.items == null || list.items!!.size == 0) view.visibility = View.GONE
//    }

//    private fun setupPagination(list: androidx.recyclerview.widget.RecyclerView,
//                                resourceType: String) {
//        val adapter = CharactersAdapter()
//        list.adapter = adapter
//
//        val viewModel = viewModel.characterResourcesViewModels[resourceType]!!
//        val paginationCallback = object : GenericPaginationCallback(viewModel, null) {
//            override fun onLoadMore() {
//                viewModel.loadMore(character.id)
//            }
//
//        }
//    private fun setupHorizontalLists() {
//        val lists = arrayOf(comicsList, seriesList, storiesList, eventsList)
//        val listsContents = arrayOf(result.comics, result.series, result.stories,
//            result.events)
//        val listsContainers = arrayOf(comicsContainer, seriesContainer, storiesContainer,
//            eventsContainer)
//
//        lists.forEachIndexed { index, list ->
//            list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context,
//                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
//            list.addItemDecoration(HorizontalItemDecoration())
//            hideIfEmpty(listsContainers[index], listsContents[index])
//            setupPagination(lists[index], contentTypes[index])
//        }
//    }
}