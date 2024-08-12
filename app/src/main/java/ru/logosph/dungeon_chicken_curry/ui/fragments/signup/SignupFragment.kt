package ru.logosph.dungeon_chicken_curry.ui.fragments.signup

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
import ru.logosph.dungeon_chicken_curry.databinding.FragmentSignupBinding
import ru.logosph.dungeon_chicken_curry.ui.fragments.LoadingStates

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignupBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]

        binding.switchToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.signupBtn.setOnClickListener {
            val pass = binding.passwordEditText.text.toString()
            val passConfirm = binding.password2EditText.text.toString()
            if (pass != passConfirm) {
                binding.password2EditText.error = "Пароли не совпадают"
                return@setOnClickListener
            }
            lifecycleScope.launch {
                viewModel.signup(
                    binding.loginEditText.text.toString(),
                    pass,
                    requireContext()
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
                findNavController().navigate(R.id.action_signupFragment_to_mainFragment)
            }

            LoadingStates.ERROR -> {
                binding.blocking.visibility = View.GONE
                binding.progress.visibility = View.GONE
                if (shouldSayError) Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}