package com.example.passingdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.passingdata.databinding.ActivityMainBinding
import com.example.passingdata.model.BmiParcelable
import com.example.passingdata.model.BmiSerializable
import com.example.passingdata.passingActivity.ActivityResult
import com.example.passingdata.passingActivity.BundleActivity
import com.example.passingdata.passingActivity.ParcelableActivity
import com.example.passingdata.passingActivity.SerializableActivity

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding : ActivityMainBinding
    private var resultDropDown :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        dropDownMenu()
        sendData()
    }


    private fun dropDownMenu(){
        val data = resources.getStringArray(R.array.list_passing)
        val adapter = ArrayAdapter(this,R.layout.dropdown_item,data)
        with(binding.tvCalculate){
            setAdapter(adapter)
            onItemClickListener = this@MainActivity
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        resultDropDown = item
    }

    private fun intentPassing(age:Int,height:Int,weight:Int,bmi:Int,category:String){
        startActivity(Intent(this, ActivityResult::class.java).also{
            it.putExtra("age",age)
            it.putExtra("height",height)
            it.putExtra("weight",weight)
            it.putExtra("bmi",bmi)
            it.putExtra("category",category)

        })
    }

    private fun bundlePassing(age:Int,height:Int,weight:Int,bmi:Int,category:String){
        val bundle = Bundle()
        bundle.putInt("age_bundle",age)
        bundle.putInt("height_bundle",height)
        bundle.putInt("weight_bundle",weight)
        bundle.putInt("bmi_bundle",bmi)
        bundle.putString("category_bundle",category)
        startActivity(Intent(this, BundleActivity::class.java).also{
            it.putExtras(bundle)
        })
    }

    private fun serializablePassing(age:Int,height:Int,weight:Int,bmi:Int,category:String){
        val bmiSerializable = BmiSerializable(age,height,weight,bmi,category)
        startActivity(Intent(this, SerializableActivity::class.java).also{
            it.putExtra("bmi_serializable",bmiSerializable)
        })
    }

    private fun parcelablePassing(age:Int,height:Int,weight:Int,bmi:Int,category:String){
        val bmiParcelable = BmiParcelable(age,height,weight,bmi,category)
        startActivity(Intent(this, ParcelableActivity::class.java).also{
            it.putExtra("bmi_parcelable",bmiParcelable)
        })
    }

    private fun calculateBMI(){
        val inputAge = binding.etAge.text.toString().toInt()
        val inputHeight = binding.etHeight.text.toString().toInt()
        val inputWeight = binding.etWeight.text.toString().toInt()
        val m = inputHeight.toDouble() / 100.0
        val bmi = (inputWeight / (m * m)).toInt()
        var kategori = ""

        //checking kategori
        when{
            bmi < 16 -> kategori = "Too Skinny (Terlalu kurus)"
            bmi in 16..17-> kategori = "Quite Skinny (Cukup Kurus)"
            bmi.toDouble() in 17.0..18.5 -> kategori = "A Little Skinny (Sedikit Kurus)"
            bmi.toDouble() in 18.6 ..24.9 -> kategori = "Normal"
            bmi in 25..30 -> kategori = "Fat / Mini Obesity (Gemuk)"
            bmi in 30..35 -> kategori = "Obesity Class I (Obesitas Kelas I)"
            bmi in 35..40 -> kategori = "Obesity Class II (Obesitas Kelas II)"
            bmi > 40 -> kategori = "Obesity Class III (Obesitas Kelas III)"
        }

        //checking passing
        if(resultDropDown != "" || resultDropDown == "Data sent with?"){
            when{
                resultDropDown == "Intent" -> intentPassing(inputAge,inputHeight,inputWeight,bmi,kategori)
                resultDropDown == "Bundle" -> bundlePassing(inputAge,inputHeight,inputWeight,bmi,kategori)
                resultDropDown == "Serializable" -> serializablePassing(inputAge,inputHeight.toInt(),inputWeight,bmi,kategori)
                resultDropDown == "Parcelable" -> parcelablePassing(inputAge,inputHeight,inputWeight,bmi,kategori)

            }
        }else{
            Toast.makeText(this, "Please choose the data transfer method ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendData() {
        binding.btnCalculate.setOnClickListener {
            if(binding.etAge.text.toString().isNotBlank() && binding.etHeight.text.toString().isNotBlank() && binding.etWeight.text.toString().isNotBlank()  ){
                calculateBMI()
            }else{
                Toast.makeText(this, "Make sure all data is filled ", Toast.LENGTH_SHORT).show()
            }
        }
    }


}