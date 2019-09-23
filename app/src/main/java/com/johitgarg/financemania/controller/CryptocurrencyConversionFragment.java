package com.johitgarg.financemania.controller;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.johitgarg.financemania.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class CryptocurrencyConversionFragment extends Fragment {


    public CryptocurrencyConversionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cryptocurrency_conversion, container, false);

        Button button = view.findViewById(R.id.convert_button);
        final EditText editText = view.findViewById(R.id.input_text_number);
        final TextView textView = view.findViewById(R.id.converted_result_text);
        final Spinner spinner = view.findViewById(R.id.currency_spinner);
        final Spinner spinner1 = view.findViewById(R.id.convert_unit_spinner);

        final RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()).getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);

                String base_unit = spinner.getSelectedItem().toString();
                String base_unit_symbol = "";
                String convert_unit_symbol = "";
                String convert_unit = spinner1.getSelectedItem().toString();
                String[] crypto_base_unit_array = getResources().getStringArray(R.array.crypto_content);
                String[] currency_convert_unit_array = getResources().getStringArray(R.array.spinner_content);

                if (crypto_base_unit_array[0].equals(base_unit)) {
                    base_unit_symbol = "BTC";
                } else if (crypto_base_unit_array[1].equals(base_unit)){
                    base_unit_symbol = "ETH";
                }

                if (currency_convert_unit_array[0].equals(convert_unit)){
                    convert_unit_symbol = "INR";
                } else if (currency_convert_unit_array[1].equals(convert_unit)){
                    convert_unit_symbol = "USD";
                }  else if (currency_convert_unit_array[2].equals(convert_unit)){
                    convert_unit_symbol = "JPY";
                }  else if (currency_convert_unit_array[3].equals(convert_unit)){
                    convert_unit_symbol = "KRW";
                }  else if (currency_convert_unit_array[4].equals(convert_unit)){
                    convert_unit_symbol = "CNY";
                }  else if (currency_convert_unit_array[5].equals(convert_unit)){
                    convert_unit_symbol = "EUR";
                }  else if (currency_convert_unit_array[6].equals(convert_unit)){
                    convert_unit_symbol = "GBP";
                }


                final String finalConvert_unit_symbol = convert_unit_symbol;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        "https://min-api.cryptocompare.com/data/price?fsym="+base_unit_symbol+
                                "&tsyms="+convert_unit_symbol+
                                "&api_key=3a5308e2e343be53414e74edd72ce7f8f5117c94c8981efeeaf8e3c9bc59d454",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    //Log.d("JSON:", "onResponse: " + response.getDouble(finalConvert_unit_symbol));
                                    Log.d("JSON", "Edit text " + editText.getText());
                                    double resultOfEditText = Double.valueOf(editText.getText().toString());
                                    double getResponseValue = response.getDouble(finalConvert_unit_symbol);
                                    double finalResult = resultOfEditText * getResponseValue;
                                    String finalDisplay = finalConvert_unit_symbol + " " + finalResult;
                                    textView.setText(finalDisplay);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "onErrorResponse: " + error.getMessage());

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

        return view;
    }
}
