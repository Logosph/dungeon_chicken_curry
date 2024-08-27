package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.logosph.dungeon_chicken_curry.databinding.ItemForListBinding
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel

class ChoiseListDialogAdapter(
    private val data: MutableList<RaceAndClassModel> = mutableListOf(),
    private val viewModel: ChooseListViewModel
) : RecyclerView.Adapter<ChoiseListDialogAdapter.ViewHolder>() {


    private var selectedItems = mutableSetOf<Int>()

    inner class ViewHolder(val binding: ItemForListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                viewModel.toggleSelection(position)
                updateBackground(position)
            }
        }

        fun updateBackground(position: Int) {
            if (viewModel.getSelectedItems()?.contains(position) == true) {
                binding.root.setBackgroundColor(Color.LTGRAY)
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
            }
        }

    }

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
