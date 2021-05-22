package com.example.attemptatautentification.managerLib

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.attemptatautentification.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth


/**
 * Менеджер для работы с авторизацией пользователей.
 */
class AuthManager(m: MainActivity) {
    /**
     * Наш авторизатор.
     */
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private var mainActivity: MainActivity = m

    private val mGoogleSignInClient: GoogleSignInClient
    init {
        val gso =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build()
        mGoogleSignInClient = GoogleSignIn.getClient(mainActivity, gso)
    }

    fun signIn() {
       val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        //val signInIntent = googleSignInClient.signInIntent
        mainActivity.startActivityForResult(signInIntent, 1)
    }

    fun logIn(){

    }

    fun existingGoogleAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(mainActivity)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            if (account != null) {
                mainActivity.updateUIError(account)
            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.println(Log.ERROR, "SIGNIN", "signInResult:failed code=" + e.statusCode)
            mainActivity.updateUI("Не получилось войти через Google аккаунт")
        }
    }



}

