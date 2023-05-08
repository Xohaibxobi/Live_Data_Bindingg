package com.example.livedatabinding.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.livedatabinding.Database.UserDataBase
import com.example.livedatabinding.Model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository  {

    companion object {

        private var userDataBase:UserDataBase?=null

        fun initialiseDB(context: Context): UserDataBase? {
            return UserDataBase.getInstence(context)


        }


        fun insert(context:Context,user: User) {

            userDataBase= initialiseDB(context)

            CoroutineScope(IO).launch {
                userDataBase?.getaDao()?.insert(user)
            }



        }

        fun getAllUserData(context: Context): LiveData<List<User>>? {
            userDataBase= initialiseDB(context)

            return userDataBase?.getaDao()?.getUserData()

        }
    }
}