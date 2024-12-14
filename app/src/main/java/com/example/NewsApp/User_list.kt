package com.example.NewsApp



class User_list(val id:String, val name: String, val  image_id: String) {
    constructor() : this("","","")
}

class News_list(val displayName:String, val text: String, val  image_id: String) {
    constructor() : this("","","")
}

class Cubs_list(val name:String, val description_cub: String, val  image_id: String) {
    constructor() : this("","","")
}