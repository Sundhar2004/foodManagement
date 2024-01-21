package android.foodmanagement.com.adapter

import android.annotation.SuppressLint
import android.foodmanagement.com.R
import android.foodmanagement.com.model.Food
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class StudentFoodAdapter(private var studentFoods: List<Food>): RecyclerView.Adapter<StudentFoodAdapter.StudentViewHolder>() {

    private var clickListener: OnItemClickListener? = null
    private var currentQuantity: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_food_list, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
       return studentFoods.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val studentFood = studentFoods[position]
        holder.bind(studentFood)
    }

    /** Interface class for item Click listener*/
    fun itemListener(item: OnItemClickListener){
        clickListener = item
    }

    inner class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val textProductName: TextView = itemView.findViewById(R.id.studentTextProductName)
        private val textProductPrice: TextView = itemView.findViewById(R.id.studentTextProductPrice)
        private val textQty: TextView = itemView.findViewById(R.id.quantity_tv)
        private val btnQtyIncrease: MaterialButton = itemView.findViewById(R.id.btn_increase)
        private val btnQtyDecrease: MaterialButton = itemView.findViewById(R.id.btn_decrease)


        @SuppressLint("SetTextI18n")
        fun bind(studentFood: Food) {

            textProductName.text = studentFood.foodName
            textProductPrice.text = "₹${studentFood.foodPrice}"
            textQty.text = currentQuantity.toString()

            /** Fun for increase the Qty of the item*/
            btnQtyIncrease.setOnClickListener {
                currentQuantity++
                updateQty(currentQuantity)
            }

            /** Fun for increase the Qty of the item*/
            btnQtyDecrease.setOnClickListener {
                if (currentQuantity > 1){
                    currentQuantity--
                    updateQty(currentQuantity)
                }
            }
        }

        private fun updateQty(qty: Int) {
            textQty.text = qty.toString()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    clickListener?.onItemClick(studentFoods[position], currentQuantity)
                }
            }
        }
    }


    interface OnItemClickListener{
        fun onItemClick(item: Food, newQty: Int)
    }
}