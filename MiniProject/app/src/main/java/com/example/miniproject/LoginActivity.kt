package com.example.miniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.miniproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.PatternSyntaxException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var auth:FirebaseAuth
    val loadingDialog=LoadingDialog(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        auth= FirebaseAuth.getInstance()
        binding.btnToSignUp.setOnClickListener{
            startActivity(Intent(this,SignupActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
         //loadingDialog.startAlertDialog()
            loginUser()
        }
    }

    private var email=""
    private var password=""
    private fun loginUser()
    {
        email=binding.edtLoginUsername.text.toString().trim()
        password=binding.edtLoginPassword.text.toString().trim()
            //2)Validate data
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                print("------------------------------clicked ---------     -------")
                Toast.makeText(this,"Invalid Email Pattern...", Toast.LENGTH_SHORT).show()
            }
            else if(password.isEmpty())
            {
                Toast.makeText(this,"Enter Password...", Toast.LENGTH_SHORT).show()
            }
            else{
                loadingDialog.startAlertDialog()
                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                    loadingDialog.dismisDialog()
                    startActivity(Intent(this,HomePage::class.java))
                    finish()
                }
                    .addOnFailureListener {
                        loadingDialog.dismisDialog()
                        Toast.makeText(this,"Login Failure",Toast.LENGTH_SHORT).show()
                    }
            }
    }
}