package dataadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadoprod.databinding.ActivityMainBinding
import com.example.listadoprod.databinding.ItemlistaBinding
import dataclass.Producto

class ProductoAdapter (val listProd: List<Producto>,
                       private val onClickListener: (Producto) -> Unit,
                       private val onClickDelete: (Int) -> Unit,
                       private val onClickUpdate: (Int) -> Unit
) :

        RecyclerView.Adapter<ProductoAdapter.ProductoHolder>(){
            inner class ProductoHolder(val binding: ItemlistaBinding) :
                    RecyclerView.ViewHolder(binding.root){

                        fun cargar(producto: Producto, onClickListener: (Producto) -> Unit,
                            onClickDelete: (Int) -> Unit,
                            onClickUpdate:  (Int) -> Unit
                        ){
                            with(binding){
                                tvCodProd.text = producto.id.toString()
                                tvNombreProd.text = producto.nombre
                                tvPrecioProd.text = producto.precio.toString()
                                itemView.setOnClickListener { onClickListener(producto) }
                                binding.btnEliminar.setOnClickListener { onClickDelete(adapterPosition) }
                                binding.btnEditar.setOnClickListener{onClickUpdate(adapterPosition)}
                            }
                        }
                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {
        val binding = ItemlistaBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ProductoHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        holder.cargar(listProd[position], onClickListener, onClickDelete, onClickUpdate)
    }

    override fun getItemCount(): Int = listProd.size
}