package com.harsh.contactassignment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.harsh.contactassignment.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val CONTACTS_PERMISSION_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check contacts permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                CONTACTS_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted
            fetchContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACTS_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                fetchContacts()
            } else {
                // Permission denied
                Toast.makeText(
                    this,
                    "Permission denied. Cannot fetch contacts.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun fetchContacts() {
        val contactManager = ContactManager(this)
        val allContacts = contactManager.getAllContacts()
        val adapter = ContactAdapter(allContacts)
        binding.rvContactList.adapter = adapter
        binding.rvContactList.layoutManager = LinearLayoutManager(this)

    }
}