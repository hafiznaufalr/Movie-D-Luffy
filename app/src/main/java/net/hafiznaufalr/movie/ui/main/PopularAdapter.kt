package net.hafiznaufalr.movie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.hafiznaufalr.movie.BuildConfig
import net.hafiznaufalr.movie.data.movie.model.MovieModel
import net.hafiznaufalr.movie.databinding.ItemPopularBinding

class PopularAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemPopularBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieModel) {
            binding.textViewTitle.text = item.title
            binding.textViewRating.text = item.voteAverage.toString()
            Glide.with(binding.imageViewPoster.context)
                .load(BuildConfig.BASE_IMAGE_URL + item.posterPath)
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
