package com.example.aroundegypttask.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aroundegypttask.R
import com.example.aroundegypttask.databinding.ExperienceItemBinding
import com.example.aroundegypttask.domain.model.Experience

class HomeAdapter(
    private val onItemClick: (model: Experience) -> (Unit)
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var list: List<Experience>? = null
    var context: Context? = null
    private var viewName: String? = null


    fun setAdapterModel(list: List<Experience>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ExperienceItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        context = holder.itemView.context
        holder.bind(list!![position])
        holder.itemView.setOnClickListener {
            onItemClick.invoke(list!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }


    class ViewHolder(
        private val binding: ExperienceItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private var context: Context = itemView.context

        fun bind(experienceModel: Experience) {
            binding.nameTv.text = experienceModel.title
            binding.favouriteCountTv.text = experienceModel.likesNo.toString()
            binding.viewTv.text = experienceModel.viewsNo.toString()
            binding.nameTv.text = experienceModel.title
            binding.placeIv.clipToOutline = true
            if (experienceModel.recommended == 1) {
                binding.recommendedTv.visibility = View.VISIBLE
            } else
                binding.recommendedTv.visibility = View.GONE

            Glide.with(context).load(experienceModel.coverPhoto).thumbnail(0.1f)
                .into(binding.placeIv)

            when (experienceModel.isLiked) {
                1 -> binding.favouriteIv.setImageDrawable(context.getDrawable(R.drawable.ic_favourite))
                else -> binding.favouriteIv.setImageDrawable(context.getDrawable(R.drawable.ic_favourite_border))
            }
        }
    }
}
