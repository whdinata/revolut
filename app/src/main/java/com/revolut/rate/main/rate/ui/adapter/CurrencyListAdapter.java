package com.revolut.rate.main.rate.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;

import com.revolut.rate.main.rate.model.Currency;
import com.revolut.rate.main.rate.ui.view.CurrencyItemView;
import com.revolut.rate.main.rate.ui.view.CurrencyItemViewImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyListAdapter extends RecyclerView.Adapter {

    private List<Currency> currencyList = new ArrayList<>();
    private Map<String, String> currencyMap = new HashMap<>();
    private CurrencyChangedListener listener;
    private static final int HEADER = 0;
    private static final int ITEM = 1;

    private OnItemClickListener itemClickListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CurrencyItemView itemView = CurrencyItemViewImpl.build(parent.getContext());

        return new CurrencyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CurrencyItemView itemView = (CurrencyItemView) holder.itemView;
        itemView.bind(currencyList.get(position));
        itemView.setOnClickListener(view -> {
            swap(position);
            itemClickListener.onItemclick();
        });

        if (holder.getItemViewType() == HEADER) {
            itemView.setEnabled(true);
            itemView.setValueChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String newValue = charSequence.toString();
                    final Currency currency = currencyList.get(0);

                    if (newValue.isEmpty()) {
                        newValue = "0";
                    }

                    currency.setValue(newValue);
                    listener.onCurrencyChanged(currency);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        } else {
            itemView.setEnabled(false);
        }
    }

    private void swap(int position) {
        Collections.swap(currencyList, position, 0);
        notifyItemMoved(position, 0);
    }

    public Currency getEditableCurrency() {
        return currencyList.get(0);
    }

    public void setData(List<Currency> currencyList) {
        if (this.currencyList.isEmpty()) {
            this.currencyList = currencyList;
        } else {
            for (int i = 1; i < currencyList.size(); i++) {
                Currency currency = this.currencyList.get(i);
                currency.setValue(currencyList.get(i).getValue());
            }
        }

        if (!currencyMap.isEmpty()) {
            for(Currency currency : this.currencyList) {
                currency.setName(currencyMap.get(currency.getCurrency()));
            }
        }

        notifyDataSetChanged();
    }

    public void setMap(Map<String, String> currencyMap) {
        this.currencyMap = currencyMap;
    }

    public void setValueChangedListener(CurrencyChangedListener listener) {
        this.listener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return HEADER;
            default:
                return ITEM;
        }
    }

    @Override
    public long getItemId(int position) {
        return currencyList.get(position).getCurrency().hashCode();
    }

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        public CurrencyItemView itemView;

        public CurrencyViewHolder(CurrencyItemView itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    public interface CurrencyChangedListener {
        void onCurrencyChanged(Currency currency);
    }

    public interface OnItemClickListener {
        void onItemclick();
    }
}
