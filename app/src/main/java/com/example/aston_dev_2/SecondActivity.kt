package com.example.aston_dev_2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.aston_dev_2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mCount = intent.getIntExtra("count", 0)

        binding.textCounter.text = mCount.toString()
    }
}