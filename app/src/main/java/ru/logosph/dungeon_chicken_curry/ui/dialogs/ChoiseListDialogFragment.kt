package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.DialogChoiseListBinding
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel

class ChoiseListDialogFragment : DialogFragment() {

    lateinit var binding: DialogChoiseListBinding
    lateinit var viewModel: ChooseListViewModel
    var isRace: Boolean = false
    var isAllowed: Boolean = false
    lateinit var onDismissListener: ((list: Pair<Boolean, List<RaceAndClassModel>>) -> Unit) // () -> Unit
    lateinit var adapter: ChoiseListDialogAdapter


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())
        binding = DialogChoiseListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ChooseListViewModel::class.java]
        builder.setView(binding.root)

        viewModel.isButtonAllowed = isAllowed
        updateButtonAppearance(viewModel.isButtonAllowed)

        if (isRace) viewModel.getRaces(requireContext()) else viewModel.getClasses(requireContext())

        adapter = ChoiseListDialogAdapter()
        adapter.onItemClickListener = { position ->
            viewModel.toggleSelection(position)
        }

        binding.allowedRestrictedButton.setOnClickListener {
            viewModel.toggleButtonState()
            updateButtonAppearance(viewModel.isButtonAllowed)
        }

        with(binding) {
            listRecyclerView.adapter = adapter
            listRecyclerView.layoutManager = GridLayoutManager(context, 2)

            if (isRace) {
                adapter.updateData(viewModel.racesAndClasses)
            } else {
                adapter.updateData(viewModel.racesAndClasses)
            }

            for (i in viewModel.racesAndClasses) {
                val chip = Chip(requireContext()).apply {
                    text = i.name
                }
                chipsFlexViewGroup.addView(chip)
            }

            toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->

                if (isChecked) {
                    when (checkedId) {
                        R.id.list_button -> {
                            chipsFlexViewGroup.visibility = android.view.View.GONE
                            listRecyclerView.visibility = android.view.View.VISIBLE
                        }

                        R.id.chips_button -> {
                            listRecyclerView.visibility = android.view.View.GONE
                            chipsFlexViewGroup.visibility = android.view.View.VISIBLE
                        }
                    }
                }
            }
        }

        return builder.create()
    }

    private fun updateButtonAppearance(isButtonAllowed: Boolean) {
        with(binding.allowedRestrictedButton) {
            if (isButtonAllowed) {
                text = "Allowed"
                setTextColor(resources.getColor(R.color.allowed, null))
            } else {
                text = "Restricted"
                setTextColor(resources.getColor(R.color.md_theme_error, null))
            }
        }

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener(viewModel.isButtonAllowed to viewModel.selectedItems.toList())
    }
}