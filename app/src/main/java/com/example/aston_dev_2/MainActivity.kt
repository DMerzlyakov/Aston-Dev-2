package com.example.aston_dev_2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.aston_dev_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    /** Переменная для хранения количества нажатий */
    private var mCount = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /** Обработчики кнопок */
        with(binding) {
            buttonHello.setOnClickListener { launchSecondActivity() }
            buttonCount.setOnClickListener {
                mCount++
                binding.showCount.text = mCount.toString()
            }
        }
    }

    /**
     * Сохраняем состояние при пересоздании Activity
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", mCount)
        Log.d("MAIN_ACTIVITY", "Состояние сохранено")
    }


    /**
     * Восстанавливаем состояние после пересоздания Activity
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mCount = savedInstanceState.getInt("count")
        binding.showCount.text = mCount.toString()
        Log.d("MAIN_ACTIVITY", "Состояние восстановлено")
    }


    /**
     * Метод для запуска второй Activity
     */
    private fun launchSecondActivity() {
        Log.d("MAIN_ACTIVITY", "Переход на вторую Activity")
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("count", mCount)
        startActivity(intent)
    }
}