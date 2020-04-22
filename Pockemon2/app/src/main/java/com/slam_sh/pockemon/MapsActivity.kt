package com.slam_sh.pockemon

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        loadpockemons()
        checkPermsions()

    }
    val AccesLocation=999
    fun checkPermsions(){
        if (Build.VERSION.SDK_INT>=23){

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf( Manifest.permission.ACCESS_FINE_LOCATION),AccesLocation)
                return
            }
        }

        getUserLocation()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            AccesLocation->{
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getUserLocation()
                }else{ Toast.makeText(this,"location access is deny",Toast.LENGTH_LONG).show()}
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun getUserLocation(){

        Toast.makeText(this,"location access now",Toast.LENGTH_LONG).show()
        //TODO: access user location
        val mylocation=MyLocationListener()
        val locationManager=getSystemService(Context.LOCATION_SERVICE)as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,mylocation)
        val myThread=MyThread()
        myThread.start()
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


    }
    var mylocation:Location?=null
   inner class MyLocationListener:LocationListener{
        constructor(){
            mylocation= Location("me")
            mylocation!!.longitude=0.0
            mylocation!!.latitude=0.0
        }
        override fun onLocationChanged(location: Location?) {
            mylocation= location

        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }

    }
   var oldLocation:Location?=null
    inner class MyThread:Thread{
        constructor():super(){
            oldLocation= Location("oldLocation")
            oldLocation!!.longitude=0.0
            oldLocation!!.latitude=0.0
        }

        override fun run() {
            while (true){
                try {

                    if (oldLocation!!.distanceTo(mylocation)==0f){
                        continue
                    }
                    oldLocation=mylocation

                    runOnUiThread {
                    mMap.clear()
                    val sydney = LatLng(mylocation!!.latitude, mylocation!!.longitude)
                    mMap.addMarker(MarkerOptions().position(sydney)
                        .snippet("here is my location")
                        .title("Me")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15.9f))
                        for (i in 0..listOfPockemon.size-1){
                            var newPockemon=listOfPockemon[i]
                            if(newPockemon.isCatch==false){
                                val pockLocation = LatLng(newPockemon.locatin!!.latitude,newPockemon.locatin!!.longitude)
                                mMap.addMarker(MarkerOptions().position(pockLocation)
                                    .snippet(newPockemon.des+"Power"+ newPockemon.power)
                                    .title(newPockemon.name)
                                    .icon(BitmapDescriptorFactory.fromResource(newPockemon.image!!)))
                                if (mylocation!!.distanceTo(newPockemon.locatin)<2){
                                    myPower=myPower+newPockemon.power!!
                                    newPockemon.isCatch=true
                                    listOfPockemon[i]=newPockemon
                                    Toast.makeText(applicationContext,"You catch new pockemon, your new power is $myPower",Toast.LENGTH_LONG).show()


                                }
                            }
                        }
                  }


                    Thread.sleep(1000)
                }catch (ex:Exception){}
            }

        }
    }
    var myPower:Double=0.0
    var listOfPockemon=ArrayList<Pockemon>()
    fun loadpockemons(){
        listOfPockemon.add(Pockemon("Charmander","kind of multiple Pokemon and is a fiery kind.",R.drawable.ch,89.5,37.0616242,37.365532))
        listOfPockemon.add(Pockemon("Bulbasaur","the many species of Pokemon and is of vegetable type.",R.drawable.bu,75.5,37.0633514,37.3643504))
        listOfPockemon.add(Pockemon("Squirtle","the many species of Pokemon and is a watery type.",R.drawable.sq,80.2,37.0598386,37.3657922))

    }
}
