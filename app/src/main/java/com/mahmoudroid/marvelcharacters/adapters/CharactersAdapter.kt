package com.mahmoudroid.marvelcharacters.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahmoudroid.marvelcharacters.R
import com.mahmoudroid.marvelcharacters.models.Result
import kotlinx.android.synthetic.main.item_character_list.view.*

class CharactersAdapter(

) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    val TAG = "CharactersAdapter"

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val differCallBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_character_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        val character = differ.currentList[position]
        val image = "${character.thumbnail.path}/landscape_incredible.jpg"
        holder.itemView.apply {
            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.ic_error)
                .into(characterImage)

            characterName.text = character.name
            Log.d(TAG, "####" + character.thumbnail.path)
            setOnClickListener {
                onItemClickListener?.let {
                    it(character)
                    Toast.makeText(context, character.name, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}