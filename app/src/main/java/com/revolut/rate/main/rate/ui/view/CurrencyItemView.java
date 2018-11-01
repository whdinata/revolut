package com.revolut.rate.main.rate.ui.view;

import android.content.Context;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.revolut.rate.R;
import com.revolut.rate.common.util.StringUtil;
import com.revolut.rate.main.rate.model.Currency;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

@EViewGroup(R.layout.view_currency_item)
public class CurrencyItemView extends RelativeLayout {

    @ViewById
    ImageView flag;

    @ViewById
    TextView code;

    @ViewById
    TextView fullName;

    @ViewById
    EditText value;

    @DimensionPixelSizeRes
    int currencyItemPadding;

    private TextWatcher textWatcher;

    public CurrencyItemView(Context context) {
        super(context);
    }

    public CurrencyItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrencyItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    void afterViews() {
        setPadding(currencyItemPadding, currencyItemPadding, currencyItemPadding, currencyItemPadding);
    }

    public void bind(Currency currency) {
        Glide.with(getContext())
                .load(currency.getFlagUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(flag);

        code.setText(currency.getCurrency());
        fullName.setText(currency.getName());
        setValue(currency.getValue());
        value.setSelection(value.getText().length());

        if (textWatcher != null) {
            value.addTextChangedListener(textWatcher);
        }
    }

    public void setEnabled(boolean enabled) {
        if(enabled) {
            value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else {
            value.setInputType(InputType.TYPE_NULL);
        }
    }

    public void setValue(String v) {
        value.setText(StringUtil.getValueWithoutLeadingZeroes(v));
    }

    public String getValue() {
        return value.getText().toString();
    }

    public void setValueChangedListener(TextWatcher textWatcher) {
        this.textWatcher = textWatcher;
    }
}
