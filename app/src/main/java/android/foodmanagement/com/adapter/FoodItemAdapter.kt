package android.foodmanagement.com.adapter

import android.annotation.SuppressLint
import android.foodmanagement.com.R
import android.foodmanagement.com.model.Food
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class FoodItemAdapter(private var foods: List<Food>) : RecyclerView.Adapter<FoodItemAdapter.FoodViewHolder>() {

    private var clickItem: OnItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newProducts: List<Food>) {
        foods = newProducts
        notifyDataSetChanged()
    }

    fun deletedItem(item: OnItemClick){
        clickItem = item
    }

    inner class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val textProductName: TextView = itemView.findViewById(R.id.textProductName)
        private val textProductPrice: TextView = itemView.findViewById(R.id.textProductPrice)
        private val btnEdit: MaterialButton = itemView.findViewById(R.id.btn_edit)
        private val btnDelete: MaterialButton = itemView.findViewById(R.id.btn_delete)

        @SuppressLint("SetTextI18n")
        fun bind(food: Food) {
            textProductName.text = food.foodName
            textProductPrice.text = "₹${food.foodPrice}"

            btnEdit.setOnClickListener {

            }

            btnDelete.setOnClickListener {
                clickItem!!.deleteItem(food)
            }
        }
    }


    interface OnItemClick{
        fun deleteItem(item: Food)
    }
}