package com.slam_sh.pockemon

import android.location.Location

class Pockemon{
    var name:String?=null
    var des:String?=null
    var image:Int?=null
    var power:Double?=null
    var locatin:Location?=null
    var isCatch:Boolean?=false
    constructor(name:String,des:String,image:Int,power:Double,lat:Double,log:Double){
        this.name=name
        this.des=des
        this.image=image
        this.power=power
        this.locatin= Location(name)
        this.locatin!!.latitude=lat
        this.locatin!!.longitude=log

    }
}