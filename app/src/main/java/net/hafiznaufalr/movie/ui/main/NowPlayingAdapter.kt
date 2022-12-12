package net.hafiznaufalr.movie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.hafiznaufalr.movie.BuildConfig.BASE_IMAGE_URL
import net.hafiznaufalr.movie.R
import net.hafiznaufalr.movie.domain.movie.model.MovieModel
import net.hafiznaufalr.movie.databinding.ItemNowPlayingBinding

class NowPlayingAdapter(
    val onItemClickListener: (MovieModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemNowPlayingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.invoke(differ.currentList[bindingAdapterPosition])
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieModel) {
            binding.textViewTitle.text = item.title
            binding.textViewRating.text = String.format("%s/10", item.voteAverage)
            Glide.with(binding.imageViewPoster.context)
                .load(BASE_IMAGE_URL + item.posterPath)
                .placeholder(R.drawable.placeholder)
                .into(binding.imageViewPoster)
        }
    }

    private var differCallback = object : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(
            oldItem: MovieModel,
            newItem: MovieModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieModel,
            newItem: MovieModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
