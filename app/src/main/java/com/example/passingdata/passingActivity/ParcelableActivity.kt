package com.example.passingdata.passingActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.passingdata.R
import com.example.passingdata.databinding.ActivityParcelableBinding
import com.example.passingdata.model.BmiParcelable

class ParcelableActivity : AppCompatActivity() {
    private lateinit var binding : ActivityParcelableBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParcelableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getWithParcelable()
    }


    private fun getWithParcelable(){
        val getParcelable = intent.getParcelableExtra<BmiParcelable>("bmi_parcelable")
        if(getParcelable != null){
            when{
                getParcelable.category.contains("Skinny") -> binding.imgResult.setImageResource(R.drawable.skinny)
                getParcelable.category.contains("Normal") -> binding.imgResult.setImageResource(R.drawable.normal)
                getParcelable.category.contains("Obesity") -> binding.imgResult.setImageResource(R.drawable.fat)
            }
        }
        val result = """
            Get Data With Parcelable
            Your age : ${getParcelable?.age}
            height   : ${getParcelable?.height}
            weight   : ${getParcelable?.weight}
            your bmi : ${getParcelable?.bmi}
            category : ${getParcelable?.category}
        """.trimIndent()
        binding.tvResult.text = result
    }
}