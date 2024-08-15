package ru.logosph.dungeon_chicken_curry.ui.fragments.create_room_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.CarouselItemBinding
import ru.logosph.dungeon_chicken_curry.databinding.ItemForListBinding


class CreateRoom1Adapter : RecyclerView.Adapter<CreateRoom1Adapter.ViewHolder>() {

    class ViewHolder(val binding: ItemForListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemForListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding){
            listImageView.setImageResource(R.drawable.elf)
            listNameView.setText("эльф")
        }

    }
}
