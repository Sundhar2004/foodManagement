package android.foodmanagement.com.adapter

import android.foodmanagement.com.model.ClickedItem
import android.annotation.SuppressLint
import android.foodmanagement.com.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class ClickedItemAdapter(private var invoice: List<ClickedItem>) : RecyclerView.Adapter<ClickedItemAdapter.InvoiceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.invoice_list, parent, false)
        return InvoiceHolder(view)
    }

    override fun getItemCount(): Int {
        return invoice.size
    }

    override fun onBindViewHolder(holder: InvoiceHolder, position: Int) {
        val invoiceList = invoice[position]
        holder.bind(invoiceList)
    }


    inner class InvoiceHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val textProductName: TextView = itemView.findViewById(R.id.invoice_food_name)
        private val textProductPrice: TextView = itemView.findViewById(R.id.invoice_food_price)
        private val textQty: TextView = itemView.findViewById(R.id.invoice_food_qty)

        @SuppressLint("SetTextI18n")
        fun bind(invoice: ClickedItem) {
            textProductName.text = invoice.foodName
            textProductPrice.text = "₹${invoice.foodPrice}"
            textQty.text = invoice.quantity.toString()
        }
    }
}