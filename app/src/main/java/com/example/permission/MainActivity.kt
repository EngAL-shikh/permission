package com.example.permission

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var num=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val contactUri = ContactsContract.Contacts.CONTENT_URI
        val querFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
        val cursor = contentResolver.query(contactUri, querFields, null, null, null)
        cursor.use {
            if (it?.count == 0) {

                num=0
            }

            it?.moveToLast()
            val name = it?.getString(0)


        }








        email.setOnClickListener {

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("omar@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                type = "text/plain"
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }

        }

        massage.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "SMS message text")
                type = "text/plain"
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }


        }

        call.setOnClickListener {
            Log.d("amroz",num.toString())

                if (num==0){

                            etName.visibility=View.VISIBLE
                            etNumber.visibility=View.VISIBLE
                            savecontact.visibility=View.VISIBLE

            }else{

               var num= etNumber.text.toString()
                val webIntent = Intent().apply {
                    action =Intent.ACTION_DIAL
                    data = Uri.parse("tel:$num")

                }


                if (webIntent.resolveActivity(packageManager) != null) {
                    startActivity(webIntent)
                }



            }





        }


        location.setOnClickListener {

            val locIntent = Intent().apply {
                action =Intent.ACTION_VIEW
                data = Uri.parse("geo:33.2,44.8")
            }
            if (locIntent.resolveActivity(packageManager) != null) {
                startActivity(locIntent)
            }
        }

        facebook.setOnClickListener {

            val webIntent = Intent().apply {
                action =Intent.ACTION_VIEW
                data = Uri.parse("https://www.facebook.com/ahbkyk2")
            }
            if (webIntent.resolveActivity(packageManager) != null) {
                startActivity(webIntent)
            }
        }

        twitter.setOnClickListener {

            val webIntent = Intent().apply {
                action =Intent.ACTION_VIEW
                data = Uri.parse("https://www.facebook.com/ahbkyk2")
            }
            if (webIntent.resolveActivity(packageManager) != null) {
                startActivity(webIntent)
            }
        }





    }

    // add permission
    fun  getpermission(){

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
            if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_CONTACTS)!= PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_NUMBERS)!=PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED
            ){

                requestPermissions(arrayOf(android.Manifest.permission.WRITE_CONTACTS,
                    android.Manifest.permission.READ_PHONE_NUMBERS,
                    android.Manifest.permission.CALL_PHONE),2)
            }
        }
    }



    fun addContact(view: View) {





            val name: String = etName.text.toString()
            val phone = etNumber.text.toString()


            val intent = Intent(ContactsContract.Intents.Insert.ACTION)
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE)

            intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone)
            startActivityForResult(intent, 1)
            etName.visibility=View.GONE
            etNumber.visibility=View.GONE
            savecontact.visibility=View.GONE

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        intent: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                Toast.makeText(this, "Added Contact", Toast.LENGTH_SHORT).show()
            }
            if (resultCode == Activity.RESULT_CANCELED) {


            }
        }
    }



    fun check() {









        }


    }

