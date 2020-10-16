package com.example.recycleview

class Contact(val name: String, val isOnline: Boolean) {
    companion object {
        private var lastContactId = 0
        fun createContactList(numContacts: Int) : ArrayList<Contact> {
            val contact = ArrayList<Contact>()
            for(i in 1..numContacts)
                contact.add(Contact("Person ${lastContactId++}", i<= numContacts/2))
            return contact
        }
    }
}