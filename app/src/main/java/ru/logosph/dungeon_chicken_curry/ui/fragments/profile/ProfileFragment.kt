package ru.logosph.dungeon_chicken_curry.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.logOutButton.setOnClickListener {
            viewModel.logOut(requireContext())
            // Restart the app
            findNavController().apply {
                popBackStack(R.id.loginFragment, inclusive = false, saveState = false)
                navigate(R.id.loginFragment)
            }
        }

        return binding.root
    }

}