package com.unicom.wasalakclientproduct.ui.productDetails;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentProductRatingBinding;


public class ProductRatingFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {


    private FragmentProductRatingBinding binding;

    public ProductRatingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_product_rating, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rate1.getSeekBar().setOnSeekBarChangeListener(this);
        binding.rate2.getSeekBar().setOnSeekBarChangeListener(this);
        binding.rate3.getSeekBar().setOnSeekBarChangeListener(this);
        binding.rate4.getSeekBar().setOnSeekBarChangeListener(this);
        binding.rate5.getSeekBar().setOnSeekBarChangeListener(this);
        binding.rate1.getSeekBar().setProgress(10);
        binding.rate2.getSeekBar().setProgress(20);
        binding.rate3.getSeekBar().setProgress(30);
        binding.rate4.getSeekBar().setProgress(40);
        binding.rate5.getSeekBar().setProgress(50);

        binding.commentRecycle.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.commentRecycle.setNestedScrollingEnabled(false);
        binding.commentRecycle.setAdapter(new CommentProductsAdpater());





    }
    public Drawable getThumb(int progress) {
        View thumbView = LayoutInflater.from(getContext()).inflate(R.layout.layout_seek_thumb, null, false);
        ((TextView) thumbView.findViewById(R.id.tvProgress)).setText(progress + "");
        thumbView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        thumbView.layout(0, 0, thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight());
        thumbView.draw(canvas);

        return new BitmapDrawable(getResources(), bitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekBar.setThumb(getThumb(progress));


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}