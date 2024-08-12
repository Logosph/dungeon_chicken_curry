package ru.logosph.dungeon_chicken_curry.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.FragmentLoginBinding
import ru.logosph.dungeon_chicken_curry.ui.fragments.LoadingStates

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

        binding.blocking.setOnClickListener {  }

        lifecycleScope.launch {
            viewModel.loginWithToken(requireContext()).collect {
                processState(it, shouldSayError = false)
            }
        }

        binding.loginBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.login(
                    username = binding.loginEt.text.toString(),
                    password = binding.passwordEt.text.toString(),
                    context = requireContext()
                ).collect {
                    processState(it)
                }
            }
        }

        return binding.root
    }

    fun processState(
        state: LoadingStates,
        shouldSayError: Boolean = true
    ) {
        when (state) {
            LoadingStates.LOADING -> {
                binding.blocking.visibility = View.VISIBLE
                binding.progress.visibility = View.VISIBLE
            }

            LoadingStates.SUCCESS -> {
                binding.blocking.visibility = View.GONE
                binding.progress.visibility = View.GONE
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }

            LoadingStates.ERROR -> {
                binding.blocking.visibility = View.GONE
                binding.progress.visibility = View.GONE
                if (shouldSayError) Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
