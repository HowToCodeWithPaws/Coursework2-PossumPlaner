package com.example.attemptatautentification

import com.example.attemptatautentification.auth.AuthManager;

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var login_: String = ""
    var password_: String = ""
    var authManager : AuthManager = AuthManager()

//    @RequiresApi(Build.VERSION_CODES.O)
//    val file: File = File(Paths.get("").toAbsolutePath().toString() + "info.txt")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //это подрубается лайаут
        setContentView(R.layout.activity_main)
        //всё видненько
        login.isVisible = true
        password.isVisible = true
        signIn.isVisible = true
        logIn.isVisible = true
    }

    //при запуске приложеньки
    public override fun onStart() {
        super.onStart()
        // если ты уже зареган то обнови это в интерфейсе
        updateUI(authManager.currentUser)
    }

    //Кнопочка зарегаться
    fun LogIn(view: View) {
        //logIn.setBackgroundColor(android.graphics.Color.rgb(103, 58, 183));
        //signIn.setBackgroundColor(android.graphics.Color.rgb(98, 0, 238));
        login_ = login.text.toString()
        password_ = password.text.toString()
        if (authManager.createNewAccount(login_, password_))
        {
            updateUI(authManager.currentUser)
        }
        else{
            updateUI("Не получилось создать аккаунт")
        }
    }

    //Кнопочка войти
    fun SignIn(view: View) {
        login_ = login.text.toString()
        password_ = password.text.toString()
        if (authManager.signIn(login_, password_)){
            updateUI(authManager.currentUser)
        }
        else{
            updateUI("Не получилось войти в аккаунт")
        }
    }

    //дефолтно обновляем интерфейс для юзера
    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            input.text = user.email
        } else {
            updateUI("Нет пользователя")
        }
        input.refreshDrawableState()
    }

    //дефолтно обновляем интерфейс для юзера
    fun updateUI(warning: String) {
        input.text = warning
        input.refreshDrawableState()
    }
}