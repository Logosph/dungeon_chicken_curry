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
    lateinit var onDismissListener: ((list: List<RaceAndClassModel>) -> Unit) // () -> Unit
    lateinit var adapter: ChoiseListDialogAdapter


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())
        binding = DialogChoiseListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ChooseListViewModel::class.java]
        builder.setView(binding.root)

        adapter = ChoiseListDialogAdapter(viewModel = viewModel)

        binding.allowedRestrictedButton.setOnClickListener {
            viewModel.toggleButtonState()
            updateButtonAppearance(viewModel.buttonState.value!!)
        }

        viewModel.buttonState.observe(this, { state ->
            updateButtonAppearance(state)
        })


        with(binding) {
            listRecyclerView.adapter = adapter
            listRecyclerView.layoutManager = GridLayoutManager(context, 2)

            if (isRace) {
                adapter.updateData(viewModel.getRaces(requireContext()))
            } else {
                adapter.updateData(viewModel.getClasses(requireContext()))
            }

            for (i in viewModel.getRaces(requireContext())) {
                val chip = Chip(requireContext())
                chip.text = i.name
                chipsFlexViewGroup.addView(
                    chip
                )

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


    private fun updateButtonAppearance(state: ChooseListViewModel.ButtonState) {
        binding.allowedRestrictedButton.text = state.text
        binding.allowedRestrictedButton.setTextColor(state.color)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        onDismissListener(emptyList())
    }

}