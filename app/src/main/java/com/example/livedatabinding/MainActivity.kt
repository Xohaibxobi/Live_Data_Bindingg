
package com.example.livedatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedatabinding.Model.User
import com.example.livedatabinding.ViewModel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var builder:AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var name:EditText
    private lateinit var age:EditText
    private lateinit var save:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        floatingActionButton=findViewById(R.id.floatingActionButton)

        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUserData(applicationContext)?.observe(this, Observer {

        })
        floatingActionButton.setOnClickListener{
            openDialoge()
        }



    }

    private fun openDialoge() {

        builder=AlertDialog.Builder(this)
        var itemView: View =LayoutInflater.from(applicationContext).inflate(R.layout.dialog,null)
        dialog=builder.create()
        dialog.setView(itemView)
        name=itemView.findViewById(R.id.name1)
        age=itemView.findViewById(R.id.age1)
        save=itemView.findViewById(R.id.save)
        save.setOnClickListener{
            saveDataIntoRoomDataBase()
        }
        dialog.show()


    }

    private fun saveDataIntoRoomDataBase() {
        val getName=name.text.toString().trim()
        val getAge=age.text.toString().trim()

        if(!TextUtils.isEmpty(getName)&& !TextUtils.isEmpty(getAge)) {

        }
        else{
            Toast.makeText(applicationContext,"Please fill all the fields", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        }
    }
