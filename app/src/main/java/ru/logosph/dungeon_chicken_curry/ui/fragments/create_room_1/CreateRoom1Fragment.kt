package ru.logosph.dungeon_chicken_curry.ui.fragments.create_room_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import okhttp3.internal.cache.DiskLruCache
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.FragmentCreateRoom1Binding
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel
import ru.logosph.dungeon_chicken_curry.ui.dialogs.ChoiseListDialogFragment

class CreateRoom1Fragment : Fragment() {
    
    private lateinit var binding: FragmentCreateRoom1Binding
    private lateinit var viewModel: CreateRoom1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        
        binding = FragmentCreateRoom1Binding.inflate(
            inflater,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[CreateRoom1ViewModel::class.java]

        with(binding) {

            titleClasses.setOnClickListener{
                val dialog = ChoiseListDialogFragment()
                dialog.onDismissListener = { list ->
                    Snackbar.make(binding.root, "HELLO", Snackbar.LENGTH_SHORT).show()
                }
                dialog.isRace = true
                dialog.show(parentFragmentManager, "gavna")
            }

            nextStage.setOnClickListener {
                findNavController().navigate(R.id.action_createRoom1Fragment_to_createRoom2Fragment)
            }

            topAppBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }



        }



        return binding.root
    }
}