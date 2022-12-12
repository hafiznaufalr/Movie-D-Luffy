package net.hafiznaufalr.movie.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.hafiznaufalr.movie.BuildConfig
import net.hafiznaufalr.movie.R
import net.hafiznaufalr.movie.domain.movie.model.MovieReviewModel
import net.hafiznaufalr.movie.databinding.ItemReviewBinding
import net.hafiznaufalr.movie.utils.generalDateHelper

class ReviewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemReviewBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieReviewModel) {
            binding.textViewAuthor.text = item.author
            binding.textViewRating.text = String.format("%s/10", item.authorDetails.rating)
            binding.textViewDate.text = item.createdAt.generalDateHelper("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", "dd MMMM yyyy HH.mm")
            binding.textViewReview.text = item.content

            Glide.with(binding.imageViewAvatar.context)
                .load(BuildConfig.BASE_IMAGE_URL + item.authorDetails.avatarPath)
                .placeholder(R.drawable.placeholder)
                .into(binding.imageViewAvatar)
        }
    }

    private var differCallback = object : DiffUtil.ItemCallback<MovieReviewModel>() {
        override fun areItemsTheSame(
            oldItem: MovieReviewModel,
            newItem: MovieReviewModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieReviewModel,
            newItem: MovieReviewModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
