package com.cinema.moviesclub.ui.fragments.home_fragment.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cinema.moviesclub.R
import com.cinema.moviesclub.domain.model.Filter
import kotlinx.android.synthetic.main.filters_rv_item.view.*

class FiltersRVA(
    private var filtersList: List<Filter>,
    private val context: Context,
    private val topRatedMovies: () -> Unit,
    private val oscarAwardedMovies: () -> Unit,
    private val emmyAwardedMovies: () -> Unit,
    private val updateMoviesRVA: (String) -> Unit
) : RecyclerView.Adapter<FiltersRVA.FiltersViewHolder>() {

    private val _filtersList = mutableListOf<Filter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.filters_rv_item, parent, false)
        return FiltersViewHolder(view)
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        val filter = filtersList[position]
        holder.filterTextView.text = filter.id.trim()

        if (filter.active)
            holder.filterTextView.setTextColor(ContextCompat.getColor(context, R.color.blue))
        else
            holder.filterTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
    }

    override fun getItemCount(): Int {
        return filtersList.size
    }

    fun setFilters(selected: Filter) {
        filtersList.forEachIndexed { index, filter ->
            if (selected.id == filter.id)
                _filtersList.add(filtersList[index].copy(active = true))
            else
                _filtersList.add(filtersList[index].copy(active = false))
        }
        filtersList = _filtersList.toList()
        _filtersList.clear()
    }

    inner class FiltersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filterTextView: TextView = itemView.filter_item_tv

        init {
            filterTextView.setOnClickListener {
                if (filterTextView.text.equals("Top Rated")) {
                    topRatedMovies()
                    updateMoviesRVA(filterTextView.text.toString())
                } else if (filterTextView.text.equals("Oscar Awarded")) {
                    oscarAwardedMovies()
                    updateMoviesRVA(filterTextView.text.toString())
                } else {
                    emmyAwardedMovies()
                    updateMoviesRVA(filterTextView.text.toString())
                }
                setFilters(filtersList[adapterPosition])
                notifyDataSetChanged()
            }
        }
    }
}