package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductRoomDataBase: RoomDatabase() {
    abstract fun productDao(): ProductDao
    companion object{
        private var INSTANCE: ProductRoomDataBase? = null


        fun getInstance(context: Context): ProductRoomDataBase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductRoomDataBase::class.java,
                        "product_database",
                    ).fallbackToDestructiveMigrationFrom().build()

                    INSTANCE = instance
                }
                return instance
            }// end syncronized
        } //end getInstance()
    }// end companion object 
}