package com.slam_sh.foodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*
import kotlinx.android.synthetic.main.title_ticket.view.*

class MainActivity : AppCompatActivity() {
    var adapter:FoodAdapter?=null
    var listOfFood= ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFood()
        adapter= FoodAdapter(listOfFood,this)
        lvFoods.adapter=adapter
    }

    fun loadFood(){
        listOfFood.add(Food("title_app","General",R.drawable.suger_cubes))
        listOfFood.add(Food("Coffee", "Coffee is a brewed drink prepared from roasted coffee beans, the seeds of berries from certain Coffee species Roasted beans are ground and then brewed with near-boiling water to produce the beverage known as coffee.",R.drawable.coffee_pot))
        listOfFood.add(Food("Espresso", "Espresso (ess-PRESS-oh) is a full-flavored, concentrated form of coffee that is served in “shots.” It is made by forcing pressurized hot water through very finely ground coffee beans using an espresso machine",R.drawable.espresso))
        listOfFood.add(Food("French Fries","French fries are long, thin pieces of potato fried in oil or fat. The French fries were thin and crispy. To cook the French fries, put them into a deep pan of oil. Peel the potatoes and cut them into French fries.",R.drawable.french_fries))
        listOfFood.add(Food("title_app","Beverage",R.drawable.suger_cubes))
        listOfFood.add(Food("Honey","Honey is a sweet, viscous food substance made by honey bees and some related insects. Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation.",R.drawable.honey))
        listOfFood.add(Food("Strawberry ice cream","Strawberry ice cream is a flavor of ice cream made with strawberry or strawberry flavoring. It is made by blending in fresh strawberries or strawberry flavoring with the eggs, cream, vanilla and sugar used to make ice cream. Most strawberry ice cream is colored pink or light red.",R.drawable.strawberry_ice_cream))
        listOfFood.add(Food("Sugar cubes","Sugar cubes are small lumps of sugar shaped into cubes. You put them in cups of tea and coffee. Place three sugar cubes in a coffee cup. Stir the coffee until the sugar cubes dissolve.",R.drawable.suger_cubes))

    }
   inner class  FoodAdapter:BaseAdapter{
        var context:Context?=null
        var listOfFoodLocal= ArrayList<Food>()
        constructor(listOfFood:ArrayList<Food>,context:Context){
            listOfFoodLocal=listOfFood
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val food=listOfFoodLocal[p0]
            if (food.name=="title_app"){
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val foodView = inflator.inflate(R.layout.title_ticket, null)
                foodView.tvTitle.text=food.des
                return foodView
            }else {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val foodView = inflator.inflate(R.layout.food_ticket, null)
                foodView.tvName.text = food.name!!
                foodView.tvDes.text = food.des!!
                foodView.ivFoodimage.setImageResource(food.image!!)
                foodView.ivFoodimage.setOnClickListener {
                    val intent = Intent(context, FoodDetails::class.java)
                    intent.putExtra("name", food.name!!)
                    intent.putExtra("des", food.des!!)
                    intent.putExtra("image", food.image!!)
                    context!!.startActivity(intent)

                    // add(p0)
                }
                return foodView
            }
        }

        override fun getItem(p0: Int): Any {
            return listOfFoodLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
           return listOfFoodLocal.size
        }

    }
   /* fun delete(index:Int){
        listOfFood.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }
    fun add(index: Int){
        listOfFood.add( index,listOfFood[index])
        adapter!!.notifyDataSetChanged()
    }*/
}
