package com.hongenit.simplepainting.colorpicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.hongenit.simplepainting.R;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.sliders.AlphaSlideBar;
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar;

/**
 * Created by Xiaohong on 2018/10/22.
 * desc:
 */
public class ColorDialog extends Dialog implements View.OnClickListener {


    private ColorPickerView colorPickerView2;
    private ImageView iv_choose_color;
    private int currentColor = Color.BLACK;
    private ColorChooseListener colorChooseListener = null;

    public ColorDialog(@NonNull Context context, ColorChooseListener listener) {
        super(context);
        initView();
        this.colorChooseListener = listener;
    }


    private void initView() {
        setContentView(R.layout.dialog_colorpicker);
        colorPickerView2 = findViewById(R.id.colorpickerview2);
        iv_choose_color = findViewById(R.id.iv_choose_color);
        findViewById(R.id.bt_cancel).setOnClickListener(this);
        findViewById(R.id.bt_ok).setOnClickListener(this);

        colorPickerView2.setFlagView(new CustomFlag(getContext(), R.layout.layout_flag));
        colorPickerView2.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                currentColor = Color.parseColor("#" + envelope.getHexCode());
                iv_choose_color.setBackgroundColor(currentColor);
                System.out.println(fromUser + "currentColor ========================= " + envelope.getHexCode());
            }
        });

        // attach alphaSlideBar
        final AlphaSlideBar alphaSlideBar = findViewById(R.id.alphaslidebar2);
        colorPickerView2.attachAlphaSlider(alphaSlideBar);

        // attach brightnessSlideBar
        final BrightnessSlideBar brightnessSlideBar = findViewById(R.id.brightnessslidebar2);
        colorPickerView2.attachBrightnessSlider(brightnessSlideBar);

        colorPickerView2.setPaletteDrawable(ContextCompat.getDrawable(getContext(), R.drawable.palette));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                dismiss();
                break;
            case R.id.bt_ok:
                if (colorChooseListener != null) {
                    colorChooseListener.onColorChose(currentColor);
                }
                dismiss();
                break;
        }
    }


    public interface ColorChooseListener {
        void onColorChose(int color);

    }


}
