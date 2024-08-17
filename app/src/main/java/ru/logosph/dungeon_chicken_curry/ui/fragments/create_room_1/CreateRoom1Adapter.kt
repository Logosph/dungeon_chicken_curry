package ru.logosph.dungeon_chicken_curry.ui.fragments.create_room_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.ItemForListBinding
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel

// TODO: Rename to Choose List DF and relocate it to dialogs
class CreateRoom1Adapter(
    private val data: MutableList<RaceAndClassModel> = mutableListOf()
) : RecyclerView.Adapter<CreateRoom1Adapter.ViewHolder>() {

    class ViewHolder(val binding: ItemForListBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateData(data: List<RaceAndClassModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

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
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding){
            listImageView.setImageBitmap(data[position].image)
            listNameView.text = data[position].name
        }

    }
}
