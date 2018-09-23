package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import work.williams.niit.rates.R;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    private List<CardItems> cardItemsList;
    private Context context;

    public CurrencyAdapter(List<CardItems> cardItemsList, Context context) {
        this.cardItemsList = cardItemsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardItems cardItems = cardItemsList.get(position);
        holder.currency.setText(cardItems.getCurrency().toString());
        holder.currencyValue.setText(String.valueOf(cardItems.getCurrencyValue()));
    }

    @Override
    public int getItemCount() {
        return cardItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView currency, currencyValue;
        public ViewHolder(View view){
            super(view);
            currency = view.findViewById(R.id.textCurrency);
            currencyValue = view.findViewById(R.id.textCurrencyValue);
        }
    }
}
