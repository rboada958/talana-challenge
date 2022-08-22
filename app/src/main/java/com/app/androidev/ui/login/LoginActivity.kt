package com.app.androidev.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.androidev.app.model.login.RequestLogin
import com.app.androidev.app.model.login.ResponseLogin
import com.app.androidev.databinding.ActivityLoginBinding
import com.app.androidev.ui.home.MainActivity
import com.app.androidev.ui.login.mvvm.LoginViewModel
import com.app.androidev.utils.base.gone
import com.app.androidev.utils.base.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    lateinit var result: ResponseLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                RequestLogin(
                    username = binding.inputEmail.text.toString(),
                    password = binding.inputPass.text.toString()
                )
            )
        }

        viewModel.loginUiState.observe(this) {
            it.getContentIfNotHandled()?.let { uiState ->
                when (uiState) {
                    is LoginViewModel.LoginUiState.Error -> {
                        binding.progress.gone()
                        Toast.makeText(this, uiState.msg, Toast.LENGTH_SHORT).show()
                    }
                    is LoginViewModel.LoginUiState.ShowLoading -> {
                        binding.progress.visible()
                    }
                    is LoginViewModel.LoginUiState.Success -> {
                        this@LoginActivity.result = uiState.result
                        viewModel.setApiToken(this.result.apiToken)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}