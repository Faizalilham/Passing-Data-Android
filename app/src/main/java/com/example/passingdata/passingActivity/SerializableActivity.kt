package com.example.passingdata.passingActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.passingdata.R
import com.example.passingdata.databinding.ActivitySerializableBinding
import com.example.passingdata.model.BmiSerializable

class SerializableActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySerializableBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySerializableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getWithSerializable()
    }

    private fun getWithSerializable(){
        val getSerializable = intent.getSerializableExtra("bmi_serializable") as BmiSerializable
        when{
            getSerializable.category.contains("Skinny") -> binding.imgResult.setImageResource(R.drawable.skinny)
            getSerializable.category.contains("Normal") -> binding.imgResult.setImageResource(R.drawable.normal)
            getSerializable.category.contains("Obesity") -> binding.imgResult.setImageResource(R.drawable.fat)
        }
        val result = """
            Get Data With Serializable
            Your age : ${getSerializable.age}
            height   : ${getSerializable.height}
            weight   : ${getSerializable.weight}
            your bmi : ${getSerializable.bmi}
            category : ${getSerializable.category}
        """.trimIndent()
        binding.tvResult.text = result
    }
}