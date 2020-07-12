package com.example.inputnumber;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class InputNumberView extends LinearLayout {

    private Button btnPlus;
    private Button btnSubtract;
    private TextView tvAmount;
    private int value;
    private OnValueChangeLinster linster = null;
    private int max;
    private int min;
    private int defaultValue;
    private int step ;
    private boolean disable;
    private int btnBackground;
    private LinearLayout llInputNumber;

    public InputNumberView(Context context) {
        this(context, null);
    }

    public InputNumberView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.input_number_layout, this);
        initAttr(context, attrs);
        initView();
        initAction();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputNumberView);
        max = a.getInt(R.styleable.InputNumberView_max, 0);
        min = a.getInt(R.styleable.InputNumberView_min, 0);
        defaultValue = a.getInt(R.styleable.InputNumberView_defaultValue, 0);
        step = a.getInt(R.styleable.InputNumberView_step, 1);
        disable = a.getBoolean(R.styleable.InputNumberView_disable, true);
        btnBackground = a.getResourceId(R.styleable.InputNumberView_btnBackground, -1);
        a.recycle();
    }

    private void initView() {
        btnPlus = findViewById(R.id.btn_plus);
        btnSubtract = findViewById(R.id.btn_subtract);
        tvAmount = findViewById(R.id.tv_amount);
        llInputNumber = findViewById(R.id.ll_input_number);
        updateValue();
    }

    private void initAction() {
        btnPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value +=step;
                updateValue();
            }
        });
        btnSubtract.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value -=step;
                updateValue();
            }
        });
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        value=defaultValue;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
        llInputNumber.setClickable(disable);
    }

    public int getBtnBackground() {
        return btnBackground;
    }

    public void setBtnBackground(int btnBackground) {
        this.btnBackground = btnBackground;
        btnSubtract.setBackgroundColor(btnBackground);
        btnPlus.setBackgroundColor(btnBackground);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateValue();
    }

    public void setLinster(OnValueChangeLinster linster) {
        this.linster = linster;
    }

    private void updateValue() {
        if (max!=0)
        value=value>max?max:value;
        if (min!=0)
        value=value<min?min:value;
        tvAmount.setText(String.valueOf(value));
        if (linster != null) {
            linster.onValueChange(value);
        }
    }

    public interface OnValueChangeLinster {
        void onValueChange(int value);
    }
}
