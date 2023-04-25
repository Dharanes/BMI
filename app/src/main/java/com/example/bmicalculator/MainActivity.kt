package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmicalculator.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private lateinit var list: ArrayList<Info>
    private lateinit var binding: ActivityMainBinding
//    lateinit var imageId: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewItems.setHasFixedSize(true)
        list = ArrayList()
//        list.add(Info(R.drawable.h1,"CMC","GOVERNMENT HOSPITAL CHENNAI","OPEN 24HRS","MULTISPECIALITY"))
//        list.add(Info(R.drawable.h2,"KMCH","PRIVATE HOSPITAL CHENNAI","OPEN 7AM-11PM","ORTHO"))
//        imageId = arrayOf(
//            R.drawable.h1,
//            R.drawable.h2
//        )

        try{
            val obj = JSONObject(getJSONFromAssets()!!)
            val userArray = obj.getJSONArray("hospitals")

            for( i in 0 until userArray.length()){
                val user = userArray.getJSONObject(i)
                val hName = user.getString("name")
                val hDesc = user.getString("description")
                val hTiming = user.getString("state")
                val hType = user.getString("category")
//                val hImage = user.getString("imagename")

                val userDetails = Info(R.drawable.h1,hName,hDesc,hTiming,hType)
                list.add(userDetails)
            }

        }catch (e :JSONException){
            e.printStackTrace()
        }
        adapter = Adapter(list)
        binding.recyclerViewItems.adapter = adapter
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(this)
    }

    private fun getJSONFromAssets(): String? {
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try{
            val `is` = assets.open("landmarkData.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer,charset)
        }catch (e: IOException){
            e.printStackTrace()
        }
        return json
    }

}