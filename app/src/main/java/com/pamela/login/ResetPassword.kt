package com.pamela.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.reset_password.*

class ResetPassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var inputArray: Array<EditText>
    private lateinit var email: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password)

        inputArray = arrayOf(EmailAddress_forgot_Password)

        back_forgot_password.setOnClickListener{
         //   startActivity(Intent(this, MainActivity::class.java))
            //  finish()
            onBackPressed()
        }

auth= FirebaseAuth.getInstance()

        Submit_forgot_password.setOnClickListener {
            sendEmail()

        }
    }
    private fun notEmpty():Boolean=email.isNotEmpty()

    private fun sendEmail() {
        auth= FirebaseAuth.getInstance()
        email = EmailAddress_forgot_Password.text.toString().trim()

       // if (TextUtils.isEmpty(email)) {
        if (notEmpty()) {

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val i=Intent(this,LogIn::class.java)
                    Toast.makeText(this, "Check your email,to reset Password:)", Toast.LENGTH_LONG).show()
                    startActivity(i)
                    finish()
                } else {
                    Toast.makeText(this, "Failed Kindly Input your email correctly and retry", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            inputArray.forEach { input->
                if(input.text.toString().trim().isEmpty()){
                    input.error="${input.hint}is required :)"
                }
            }
        }
    }
}





