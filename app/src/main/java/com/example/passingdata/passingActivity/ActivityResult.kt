package com.example.passingdata.passingActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.passingdata.R
import com.example.passingdata.databinding.ActivityResultBinding

class ActivityResult : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
       getWithIntent()
    }

    private fun getWithIntent(){
        val age = intent.getIntExtra("age",0)
        val height = intent.getIntExtra("height",0)
        val weight = intent.getIntExtra("weight",0)
        val bmi = intent.getIntExtra("bmi",0)
        val category = intent.getStringExtra("category")

        if(category != null){
            when{
                category.contains("Skinny") -> binding.imgResult.setImageResource(R.drawable.skinny)
                category.contains("Normal") -> binding.imgResult.setImageResource(R.drawable.normal)
                category.contains("Obesity") -> binding.imgResult.setImageResource(R.drawable.fat)
            }
        }

        val result = """
            Get Data With Intent
            Your age : $age
            height   : $height
            weight   : $weight
            your bmi : $bmi
            category : $category
        """.trimIndent()
        binding.tvResult.text = result
    }


    private fun getWithBundle(){
        val age = intent.extras?.getInt("age_bundle")
        val height =  intent.extras?.getInt("height_bundle")
        val weight =  intent.extras?.getInt("weight_bundle")
        val bmi =  intent.extras?.getInt("bmi_bundle")
        val category = intent.extras?.getString("category_bundle")

        if(category != null){
            when{
                category.contains("Skinny") -> binding.imgResult.setImageResource(R.drawable.skinny)
                category.contains("Normal") -> binding.imgResult.setImageResource(R.drawable.normal)
                category.contains("Fat") -> binding.imgResult.setImageResource(R.drawable.fat)
            }
        }

        val result = """
            Get Data With Bundle
            Your age : $age
            height   : $height
            weight   : $weight
            your bmi : $bmi
            category : $category
        """.trimIndent()
        binding.tvResult.text = result
    }

}