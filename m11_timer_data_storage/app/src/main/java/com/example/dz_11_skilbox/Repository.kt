package com.example.dz_11_skilbox

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

const val SHARED_NAME="Edit_text_shared"
const val FILE_NAME="Edit_text_file"
const val STRING_EDIT_TEXT="STRING_EDIT_TEXT"

class Repository(private val context: Context) {
    private  var localArg: String? =null
    private val shared=context.getSharedPreferences(SHARED_NAME, AppCompatActivity.MODE_PRIVATE)

    fun getText(): String{
        return when{
            getDataFromLocalVariable() !=null->getDataFromLocalVariable()!!
            getDataFromSharedPreference() !=null->getDataFromSharedPreference()!!
            getDataFromFile() !=null->getDataFromFile()!!
            else ->""
        }
    }

    fun clearText(){
        localArg=null
        val editor = shared.edit()
        editor.remove(STRING_EDIT_TEXT)
        editor.apply()
        context.deleteFile(FILE_NAME)
    }

   fun saveText(text: String){
       localArg=text
       val editor = shared.edit()
       editor.putString(STRING_EDIT_TEXT,text)
       editor.apply()
       var fileOut :FileOutputStream?=null
       try {
           fileOut=context.openFileOutput(FILE_NAME,AppCompatActivity.MODE_PRIVATE)
           fileOut.write(text.toByteArray())
       }catch (e:IOException){

       }finally {
           fileOut?.close()
       }
   }

    private fun getDataFromFile():String?{
        var fileIn :FileInputStream?=null
        return try {
            fileIn=context.openFileInput(FILE_NAME)
            val bytes=ByteArray(fileIn.available())
            fileIn.read(bytes)
            String(bytes)
        }catch (e:IOException){
            null
        }finally {
            fileIn?.close()
        }

    }

    private fun getDataFromSharedPreference(): String?{
        return shared.getString(STRING_EDIT_TEXT,null)
    }

    private fun getDataFromLocalVariable(): String?{
        return localArg
    }
}