package br.com.zup.authentication.presentation.newsfavorite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.authentication.data.response.ArticleResponse
import br.com.zup.authentication.databinding.FavoriteItemBinding
import com.squareup.picasso.Picasso

class AdapterNewsFavorite(
    private var listValue: MutableList<ArticleResponse>,
    private val onClickFavoriteRemove: (click: String) -> Unit

) : RecyclerView.Adapter<AdapterNewsFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    private var listKey = mutableListOf<String>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val newListValue =  listValue[position]
        val listKeys = listKey[position]
        holder.showFavoriteNews(newListValue)
        holder.binding.cvFavoriteNews.setOnClickListener {
            onClickFavoriteRemove(listKeys)

        }
    }


    override fun getItemCount(): Int = listValue.size

    fun updateFavoriteList(newList: HashMap<String, ArticleResponse>) {
        listValue = newList.values.toMutableList()
        listKey = newList.keys.toMutableList()
        notifyDataSetChanged()

    }


    class ViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showFavoriteNews(article: ArticleResponse ) {

                  binding.textTitleFavoriteNews.text = article.title
                  binding.textDetailFavoriteNews.text = article.description
                  Picasso.get().load(article.urlToImage).into(binding.imageFavoriteNews)
        }
    }
}

