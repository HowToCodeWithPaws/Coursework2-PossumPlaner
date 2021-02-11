package com.example.attemptatautentification.auth

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.concurrent.Executor

/**
 * Менеджер для работы с авторизацией пользователей.
 */
class AuthManager {
    /**
     * Наш авторизатор.
     */
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Получить идентификатор текущего пользователя.
     * @return идентификатор текущего пользователя.
     * Если текущего пользователя нет - null.
     */
    val currentUserId: String?
        get() {
            val currentUser = mAuth.currentUser
            return currentUser?.uid
        }

    /**
     * Получить текущего пользователя.
     * @return текущий пользователь.
     */
    val currentUser: FirebaseUser?
        get() = mAuth.currentUser

    /**
     * Завести нового пользователя с соответствующими параметрами.
     * @param email электронная почта.
     * @param password пароль.
     * @return получилось ли создать пользователя.
     */
    fun createNewAccount(email: String, password: String): Boolean {
        val result = BooleanArray(1)
        mAuth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this , OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        result[0] = true
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        result[0] = false
                    }
                })
        return result[0]
    }

    /**
     * Войти в аккаунт пользователя.
     * @param email электронная почта.
     * @param password пароль.
     * @return получилось ли зайти в аккаунт.
     */
    fun signIn(email: String, password: String): Boolean {
        val result = BooleanArray(1)
        mAuth.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        result[0] = true
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        result[0] = false
                    }
                })
        return result[0]
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
    }

}

private fun <TResult> Task<TResult>.addOnCompleteListener(authManager: AuthManager, onCompleteListener: OnCompleteListener<TResult>) {

}
