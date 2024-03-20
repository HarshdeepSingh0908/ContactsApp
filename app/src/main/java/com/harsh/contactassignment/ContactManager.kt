package com.harsh.contactassignment
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract


class ContactManager(private val context: Context) {

    @SuppressLint("Range")
    fun getAllContacts(): ArrayList<Pair<String, String>> {
        val contactsList = ArrayList<Pair<String, String>>()
        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val name =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add(Pair(name, number))
            }
        }

        cursor?.close()
        return contactsList
    }
}
