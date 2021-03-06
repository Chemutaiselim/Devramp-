package com.pamela.login

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login_page.*


class LogIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var signInEmail: String
    private lateinit var signInPassword: String
    private lateinit var signInInputArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        auth = Firebase.auth

        signInInputArray = arrayOf(email_login_page, password_login_page)


        signup_login_page.setOnClickListener {

            val i = Intent(applicationContext, AccountSetUp::class.java)
            startActivity(i)
            finish()

        }

        textView7.setOnClickListener {
            val i = Intent(applicationContext, ResetPassword::class.java)
            startActivity(i)


        }
        login_btn_login_page.setOnClickListener {
            signInUser()
        }

    }
    private fun notEmpty(): Boolean =signInEmail.isNotEmpty() && signInPassword.isNotEmpty()
    private fun signInUser(){
        signInEmail=email_login_page.text.toString().trim()
        signInPassword=password_login_page.text.toString().trim()
        if (notEmpty()){
            auth.signInWithEmailAndPassword(signInEmail,signInPassword).addOnCompleteListener { signIn->
                if(signIn.isSuccessful){
                    val i = Intent(applicationContext, FirstPage::class.java)
                    startActivity(i)
                    Toast.makeText(this,"LogIn Successful :)",Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this,"LogIn Failed :(",Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            signInInputArray.forEach { input->
                if(input.text.toString().trim().isEmpty()){
                    input.error="${input.hint} is required :)"
                }
            }
        }
    }

}