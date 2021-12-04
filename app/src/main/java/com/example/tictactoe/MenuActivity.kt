package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import com.example.tictactoe.model.TicTacToeModel

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnGameStart.setOnClickListener {
            TicTacToeModel.resetModel()
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnGameRules.setOnClickListener {
            startActivity(Intent(this, GameRulesActivity::class.java))
        }

        btnGameExit.setOnClickListener {
            finish()
        }
    }
}