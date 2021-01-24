package com.example.attemptatautentification

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.nio.file.Paths

class MainActivity : AppCompatActivity() {
    var login_: String = ""
    var password_: String = ""
    var accounts = mutableMapOf("12345" to "12345")

//    @RequiresApi(Build.VERSION_CODES.O)
//    val file: File = File(Paths.get("").toAbsolutePath().toString() + "info.txt")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    fun Confirm(view: View) {
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
}