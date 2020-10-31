package com.example.recycleview.data

class Location() {
    var name: String? = null
    var lat: String? = null
    var lon: String? = null
    var link: String? = null
    constructor(name: String?, lat: String?, lon: String?, link: String?):this() {
        this.name = if(name == null) "" else name
        this.lat = if(lat == null) "" else lat
        this.lon = if(lon == null) "" else lon
        this.link = if(link == null) "" else link
    }
}