package com.example.livedatabinding.Database

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.livedatabinding.Dao.UserDao
import com.example.livedatabinding.Model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase:RoomDatabase() {

    abstract fun getaDao():UserDao


    companion object{
        private const val DATABASE_NAME ="userDatabase"

        //throw out the application this object will use
        @Volatile
        var instence:UserDataBase?=null

        fun getInstence(context: Context):UserDataBase?{


            if(instence == null){
                synchronized(UserDataBase::class.java)
                {
                    if (instence == null){
                        instence = Room.databaseBuilder(context,UserDataBase::class.java,
                        DATABASE_NAME)
                            .build()
                    }
                }
            }
            return instence
        }
    }


}