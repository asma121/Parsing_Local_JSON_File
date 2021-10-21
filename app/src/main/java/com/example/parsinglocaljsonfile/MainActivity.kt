package com.example.parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    var photos= arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv=findViewById(R.id.rv)

        readGson()
    }

    private fun readGson(){
        var json:String?=null
        try {
            val inputStream:InputStream=assets.open("data.json")
            json=inputStream.bufferedReader().use { it.readText() }

            var jsonArray=JSONArray(json)
            for (i in 0 until jsonArray.length()-1){
                var jsonObject=jsonArray.getJSONObject(i)
                photos.add(jsonObject.getString("hdurl"))
            }
            rv.adapter=myAdapter(photos)
            rv.layoutManager= LinearLayoutManager(this)
        }catch (e: IOException){

        }
    }
}