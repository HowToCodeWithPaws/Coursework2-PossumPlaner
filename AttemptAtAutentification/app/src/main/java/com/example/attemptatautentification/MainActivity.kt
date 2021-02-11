package com.example.attemptatautentification

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.nio.file.Paths

class MainActivity : AppCompatActivity() {
    var login_: String = ""
    var password_: String = ""
    var accounts = mutableMapOf("12345" to "12345")
    private lateinit var auth: FirebaseAuth

//    @RequiresApi(Build.VERSION_CODES.O)
//    val file: File = File(Paths.get("").toAbsolutePath().toString() + "info.txt")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun getAnswer(
            login_: String, password_: String
    ) {
        if (accounts.containsKey(login_) && accounts[login_] == password_) {
            fromFile.text = "Hello, ${login_}! You successfully logged in"
            input.refreshDrawableState()
        } else {
            if (!accounts.containsKey(login_)) {
                accounts.put(login_, password_)
                fromFile.text = "Hello, ${login_}! You successfully signed in"
                input.refreshDrawableState()
            } else {
                fromFile.text = "${login_}, your password is wrong"
                input.refreshDrawableState()
            }
        }
    }

   /* fun Confirm(view: View) {
        login_ = login.text.toString()
        password_ = password.text.toString()

        input.text = "\n${login_}\n${password_}\n"
        input.refreshDrawableState()
                //TODO разобраться с файлами?????
        //TODO возможно мы хотим отправлять запросы на сервер
        //  file.writeText("блять")
        //   file.appendText("\n" + login_ + "\n" + password_ + "\n")
        //   fromFile.text = "ршшшш"
        getAnswer(login_, password_)
    }
*/
    fun Confirm(view: View){
       login_ = login.text.toString()
       password_ = password.text.toString()
       signIn(login_, password_)
    }
    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            input.text = user.email
        }
        else{
            input.text = "null"
        }
        input.refreshDrawableState()
    }
  /*  private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                   updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        // [END create_user_with_email]
    }
*/
    fun signIn(email: String, password: String) {
/*        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }*/


        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                    // [START_EXCLUDE]
                   // checkForMultiFactorFailure(task.exception!!)
                    // [END_EXCLUDE]
                }

                /*// [START_EXCLUDE]
                if (!task.isSuccessful) {
                    binding.status.setText(R.string.auth_failed)
                }
                // [END_EXCLUDE]*/
            }
        // [END sign_in_with_email]
    }



   /* private fun getUserProfile() {
        // [START get_user_profile]
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
        // [END get_user_profile]
    }*/

    companion object {
        private const val TAG = "EmailPassword"
        private const val RC_MULTI_FACTOR = 9005
    }
}