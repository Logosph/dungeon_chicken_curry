package ru.logosph.dungeon_chicken_curry.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.switchToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.loginBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.login(
                    username = binding.loginEt.text.toString(),
                    password = binding.passwordEt.text.toString(),
                    context = requireContext()
                ).collect {
                    if (it) {
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    }
                }
            }
        }

        return binding.root
    }
}
