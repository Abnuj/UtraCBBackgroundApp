package com.example.utracbbackgroundapp

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.utracbbackgroundapp.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val firebaseStorage = FirebaseStorage.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageReferece =
            firebaseStorage.getReferenceFromUrl("gs://utracbbackgroundapp.appspot.com/Angel Wings CB Background")
//        Log.d("TAG", "list all :${} ")
        val ONE_MEGABYTE: Long = 1024 * 1024
        val localFile = File.createTempFile("images", "jpg")
        imageReferece.listAll().addOnSuccessListener {
            val item = it.items
            item.forEach {
               it.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                   val bitmap = BitmapFactory.decodeByteArray(it,0,it.size)
               }
            }

        }
    }
}