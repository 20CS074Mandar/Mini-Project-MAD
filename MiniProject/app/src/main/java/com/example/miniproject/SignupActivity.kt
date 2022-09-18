package com.example.miniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.miniproject.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var auth:FirebaseAuth=FirebaseAuth.getInstance()
    val loadingDialog=LoadingDialog(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnToLogin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.btnSignup.setOnClickListener {
            createUser()
        }
    }

    private var email=""
    private var password=""
    private lateinit var confirmPassword:String
    fun createUser()
    {
        email=binding.edtSignupUsername.text.toString().trim()
        password=binding.edtSignupPassword.text.toString().trim()
        confirmPassword=binding.edtConfirmPassword.text.toString().trim()
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(this,"Enter Valid Email Address..",Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty())
        {
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show()
        }
        else if(confirmPassword.isEmpty())
        {
            Toast.makeText(this,"Confirm Password",Toast.LENGTH_SHORT).show()
        }
        else if(confirmPassword!=password)
        {
            Toast.makeText(this,"Password doesn't match",Toast.LENGTH_SHORT).show()
        }
        else
        {
            loadingDialog.startAlertDialog()
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

                val timestamp=System.currentTimeMillis()
                val uid=auth.uid
                val hashMap:HashMap<String ,Any?> = HashMap()
                hashMap["uid"]=uid
                hashMap["email"]=email
                hashMap["profileImage"]=" "
                hashMap["userType"]="user"
                hashMap["timestamp"]=timestamp

                val ref=FirebaseDatabase.getInstance().getReference("Users")
                ref.child(uid!!).setValue(hashMap).addOnSuccessListener {
                    //dialog dismiss
                    loadingDialog.dismisDialog()
                    Toast.makeText(this,"Account Created ....",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                }
                    .addOnFailureListener{e->

                        //dialog dismis
                        loadingDialog.dismisDialog()
                        Toast.makeText(this,"Fialed to register due to ${e.message.toString()}",Toast.LENGTH_SHORT).show()

                    }

            }
        }
    }
}