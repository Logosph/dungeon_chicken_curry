package ru.logosph.dungeon_chicken_curry.ui.fragments.create_room_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.CarouselItemBinding

class CreateRoom2Adapter : RecyclerView.Adapter<CreateRoom2Adapter.ViewHolder>() {

    class ViewHolder(val binding: CarouselItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CarouselItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 11
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position == 10) {
            holder.binding.carouselImageView.setImageResource(R.drawable.ic_plus)
            holder.itemView.setOnClickListener {
                Snackbar.make(
                    holder.binding.root,
                    "Тут будет добавить изображение",
                    Snackbar.LENGTH_SHORT
                    ).show()
            }
        } else {

            with(holder.binding) {
                when (position % 4) {
                    0 -> carouselImageView.setImageResource(R.drawable.elf)
                    1 -> carouselImageView.setImageResource(R.drawable.character_to_frame)
                    2 -> carouselImageView.setImageResource(R.drawable.signup_login_label)
                    3 -> carouselImageView.setImageResource(R.drawable.frame_log)

                }
            }
        }
    }

}