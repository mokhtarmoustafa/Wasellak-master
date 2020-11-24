package com.unicom.wasalakclientproduct.ui.order;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentOrderDetailsBinding;
import com.unicom.wasalakclientproduct.model.order.OrderResponse;
import com.unicom.wasalakclientproduct.ui.cartConfirm.CartProductsAdapter;
import com.unicom.wasalakclientproduct.ui.guest.GuestActivity;
import com.unicom.wasalakclientproduct.utils.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.scopes.ActivityScoped;
import es.dmoral.toasty.Toasty;

@AndroidEntryPoint
public class OrderDetailsFragment extends Fragment implements OnMapReadyCallback {

    //region Members
    private String TAG = this.getClass().getSimpleName();
    private FragmentOrderDetailsBinding binding;
    private OrderViewModel viewModel;
    private NavController navController;
    private int orderId;
    private Boolean showMore = false;
    private GoogleMap googleMap;
    ProductsAdapter productsAdapter;
    OrderStatusAdapter orderStatusAdapter;
    //endregion

    //region Events
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(OrderViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initMap();

        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

        binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });


        binding.setLifecycleOwner(this);
        binding.setFragment(this);

//        if (getArguments() != null) {
//            {
//                orderId = 20118; //OrderDetailsFragmentArgs.fromBundle(getArguments()).getOrderId();
//                subscribeObserver(orderId);
//            }

//        }
        orderId = 20118; //OrderDetailsFragmentArgs.fromBundle(getArguments()).getOrderId();
        subscribeObserver(orderId);

        binding.btnShowHideDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.clShowHide.getVisibility() == View.GONE) {
                    binding.clShowHide.setVisibility(View.VISIBLE);
                    binding.btnShowHideDetails.setText(getString(R.string.hide_details));
                    binding.btnMoreUpdate.setText(getString(R.string.fewer_updates));
                } else {
                    binding.btnShowHideDetails.setText(getString(R.string.show_details));
                    binding.btnMoreUpdate.setText(getString(R.string.more_updates));
                    binding.clShowHide.setVisibility(View.GONE);
                }
            }
        });

        binding.btnMoreUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!showMore) {
//                    showMore = true;
//                    binding.btnMoreUpdate.setText(getString(R.string.fewer_updates));
//                } else {
//                    showMore = false;
//                    binding.btnMoreUpdate.setText(getString(R.string.more_updates));
//                }
                if (binding.clShowHide.getVisibility() == View.GONE) {
                    binding.clShowHide.setVisibility(View.VISIBLE);
                    binding.btnShowHideDetails.setText(getString(R.string.hide_details));
                    binding.btnMoreUpdate.setText(getString(R.string.fewer_updates));
                } else {
                    binding.btnShowHideDetails.setText(getString(R.string.show_details));
                    binding.btnMoreUpdate.setText(getString(R.string.more_updates));
                    binding.clShowHide.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        this.googleMap = googleMap;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //endregion

    //region Helper Functions

    private void subscribeObserver(int id) {

        viewModel.ObserveOrderDetail(id).observe(getViewLifecycleOwner(), new Observer<Resource<OrderResponse>>() {
            @Override
            public void onChanged(Resource<OrderResponse> orderResponseResource) {
//                if (orderResponseResource.data != null && orderResponseResource.data.getSuccess()) {
                    switch (orderResponseResource.status) {
                        case LOADING:
                            binding.progressBarFrame.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            binding.progressBarFrame.setVisibility(View.GONE);
                            break;
                        case SUCCESS:
                            binding.progressBarFrame.setVisibility(View.GONE);
                            if (orderResponseResource.data.getResult() != null) {
                                binding.setOrderData(orderResponseResource.data.getResult());

                                //2020-10-26T06:46:09.63681
                                String t = "yyyy-MM-dd'T'HH:mm:ss";
                                String creationTimeFormat = "dd MMM, yyyy - hh:mm aaa";//20 أبريل، 2020 - 04:12 م
                                String deliveryDateFormat = "dd MMM, yyyy";

                                SimpleDateFormat sdf = new SimpleDateFormat(t, Locale.getDefault());
                                SimpleDateFormat tf = new SimpleDateFormat(creationTimeFormat, Locale.getDefault());
                                SimpleDateFormat df = new SimpleDateFormat(deliveryDateFormat, Locale.getDefault());

                                java.util.Date dateCreationTimeObj = null;
                                try {

                                    if (orderResponseResource.data.getResult().getCreationTime() != null) {
                                        dateCreationTimeObj = sdf.parse(orderResponseResource.data.getResult().getCreationTime());
                                        Calendar dateBirthDate = Calendar.getInstance();
                                        dateBirthDate.setTime(dateCreationTimeObj);
                                        String creationTime = tf.format(dateCreationTimeObj.getTime());
                                        binding.setCreationTime(creationTime);
                                    }


                                    if (orderResponseResource.data.getResult().getDeliveryDatetime() != null) {
                                        dateCreationTimeObj = sdf.parse(orderResponseResource.data.getResult().getDeliveryDatetime());
                                        Calendar dateDeliveryDate = Calendar.getInstance();
                                        dateDeliveryDate.setTime(dateCreationTimeObj);
                                        String deliveryDate = df.format(dateCreationTimeObj.getTime());
                                        binding.setDeliveryDateTime(deliveryDate);
                                    }


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Log.d(TAG, "getOrdersDetails: "+orderResponseResource.data.getResult().getOrdersDetails().size());
                                Log.d(TAG, "getOrderStatusesHistoryForClient: "+orderResponseResource.data.getResult().getOrderStatusesHistoryForClient().size());
                                if (orderResponseResource.data.getResult().getOrdersDetails() != null) {
                                    productsAdapter=new ProductsAdapter();
                                    productsAdapter.submitList(orderResponseResource.data.getResult().getOrdersDetails());
                                    binding.setProductAdapter(productsAdapter);
                                }

                                if (orderResponseResource.data.getResult().getOrderStatusesHistoryForClient() != null) {
                                    orderStatusAdapter=new OrderStatusAdapter();
                                    orderStatusAdapter.submitList(orderResponseResource.data.getResult().getOrderStatusesHistoryForClient());
                                    binding.setOrderStatusAdapter(orderStatusAdapter);
                                }

                            }
                            break;
                    }
//                }
            }
        });
    }


    public void onCancelOrder() {
        showCancelOrderDialog();


    }

    private void showCancelOrderDialog() {

        new MaterialAlertDialogBuilder(getActivity(), R.style.material_theme_rounded)
                .setTitle(getString(R.string.cancel_order))
                .setView(R.layout.layout_cancel_order)
                .setMessage(R.string.cancel_order_message)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(R.string.cancel_order_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etNotes = ((AlertDialog) dialog).findViewById(R.id.etNote);
                        String notes = etNotes.getText().toString();
                        if (notes.trim().isEmpty()) {
                            etNotes.requestFocus();
                            Toasty.info(getContext(), getString(R.string.notes_mandatory));
                            return;
                        } else {
                            int id = 20118;
                            viewModel.ObserveOrderChangeStatus(id, notes, 0).observe(getViewLifecycleOwner(), new Observer<Resource<OrderResponse>>() {
                                @Override
                                public void onChanged(Resource<OrderResponse> orderResponseResource) {
                                    //todo navigate from details to parent
                                    Toasty.info(getContext(), getString(R.string.done));
                                }
                            });
                        }

                        dialog.cancel();
                    }
                })
                .show();

    }


    private void initMap() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    public void drawMarker(double lat, double lng) {
        LatLng location = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                location, 15);
        googleMap.moveCamera(cameraUpdate);
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.ic_baseline_location))
                .title("title"));
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    //endregion
}
