package com.example.aston_dev_2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
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
            buttonCount.setOnClickListener {
                toastCountAdd()
                mCount++
                binding.showCount.text = mCount.toString()
            }
        }

        /**
         * Восстанавливаем состояние после пересоздания Activity
         * (Вариант через onCreate, самостоятельно проверяем Bundle, может null)
         */
        if (savedInstanceState != null){
            mCount = savedInstanceState.getInt("count")
            binding.showCount.text = mCount.toString()
            Log.d("MAIN_ACTIVITY", "Состояние восстановлено")

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

    private fun toastCountAdd(){
        val toast: Toast = Toast.makeText(
            this, R.string.toast_message,
            Toast.LENGTH_SHORT
        )
        toast.show()
    }

//    /**
//     * Восстанавливаем состояние после пересоздания Activity
//     * (Вариант через onRestoreInstanceState, вызывается только если Bundle не null)
//     * Отличия только в этапах жизненного цикла
//     */
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        mCount = savedInstanceState.getInt("count")
//        binding.showCount.text = mCount.toString()
//        Log.d("MAIN_ACTIVITY", "Состояние восстановлено")
//    }


}