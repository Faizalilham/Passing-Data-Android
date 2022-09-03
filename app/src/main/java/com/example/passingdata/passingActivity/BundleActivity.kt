package com.example.passingdata.passingActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.passingdata.R
import com.example.passingdata.databinding.ActivityBundleBinding

class BundleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBundleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBundleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getWithBundle()
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
                category.contains("Obesity") -> binding.imgResult.setImageResource(R.drawable.fat)
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