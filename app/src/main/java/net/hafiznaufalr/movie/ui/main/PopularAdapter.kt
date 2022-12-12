package net.hafiznaufalr.movie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.hafiznaufalr.movie.BuildConfig
import net.hafiznaufalr.movie.R
import net.hafiznaufalr.movie.domain.movie.model.MovieModel
import net.hafiznaufalr.movie.databinding.ItemPopularBinding

class PopularAdapter(
    private val onItemClickListener: (MovieModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listData: ArrayList<MovieModel> = arrayListOf()

    fun submitList(data: List<MovieModel>, isReset: Boolean = false) {
        if (isReset) {
            listData.clear()
            notifyItemRangeRemoved(0, listData.size)
        }
        val sizeBeforeAdded = listData.size
        listData.addAll(data)
        notifyItemRangeInserted(sizeBeforeAdded, listData.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPopularBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.invoke(listData[bindingAdapterPosition])
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(private val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieModel) {
            binding.textViewTitle.text = item.title
            binding.textViewRating.text = String.format("%s/10", item.voteAverage)
            Glide.with(binding.imageViewPoster.context)
                .load(BuildConfig.BASE_IMAGE_URL + item.posterPath)
                .override(300)
                .placeholder(R.drawable.placeholder)
                .into(binding.imageViewPoster)
        }
    }
}
