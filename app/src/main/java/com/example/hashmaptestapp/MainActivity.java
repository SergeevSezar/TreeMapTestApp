package com.example.hashmaptestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    //получаем ссылки на элементы
    private EditText editTextCountry;
    private EditText editTextCapital;
    private EditText editTextPutCountry;
    private TextView textViewShowDescription;
    private TextView textViewShowCapital;

    //создадим массив типа Map
    private Map<String, String> countries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //присваиваем значения ссылкам
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextCapital = findViewById(R.id.editTextCapital);
        editTextPutCountry = findViewById(R.id.editTextPutCountry);
        textViewShowDescription = findViewById(R.id.textViewShowDescription);
        textViewShowCapital = findViewById(R.id.textViewShowCapital);
        //присвоим значение Мар
        countries = new TreeMap<>();

        //в методе onCreate устанавливаем слушатель на editTextPutCountry, чтобы когда пользователь вводил название страны(ключ) имеющийся в массиве countries,
        //на textViewShowCapital сразу выводилась название столицы(значение), делается это с помощью метода addTextChangedListener(),
        // куда в качестве параметра передаем интерфейсный класс new TextWatcher() и переопределеям его методы
        editTextPutCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            //тут мы будем переопределять метод onTextChanged(в момент измения текста)
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //инициализируем локальную переменную и присвоим ей значение из массива
                String capital = countries.get(s.toString());
                //сделаем проверку если capital не равен нулю (есть значение в массиве), то capital устанавливаем на textViewShowCapital,
                //если нет совпадений (то есть ноль) то устанавливаем пустое значение
                if (capital != null) {
                    textViewShowCapital.setText(capital);
                } else {
                    textViewShowCapital.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    //метод работы кнопки
    public void onClickAddHashMap(View view) {
        //если поля заполнены
        if(fieldsFilled()) {
            //заполненные значения кладем в наш массив countries
            countries.put(editTextCountry.getText().toString().trim(), editTextCapital.getText().toString().trim());
            //после добавления в массив в поля передаем пустые значения
            editTextCountry.setText("");
            editTextCapital.setText("");
            //вызываем метод showAllCountries(), чтобы показывать содержимое массива
            showAllCountries();
        }
    }

    //создадим метод который будет проверять не заполнены ли поля и если поля заполнены, то вернет их
    private boolean fieldsFilled() {
    return editTextCapital.getText() != null && !editTextCapital.getText().toString().trim().isEmpty() && editTextCountry.getText() != null && !editTextCountry.getText().toString().trim().isEmpty();
    }

    //создаем метод котрый будет показывать добавленные ключ и значения в массив
    private void showAllCountries() {
        //textViewShowDescription установим пустое значение
    textViewShowDescription.setText("");
    //создаем StringBuilder
    StringBuilder builder = new StringBuilder();
    //в цикле for из массива извлекаем ключи и присваиваем StringBuilder, добавим (-), из ключа извлекаем значение и добавим перевод строки (\n)
    for (String country : countries.keySet()) {
        builder.append(country).append(" - ").append(countries.get(country)).append("\n");
    }
    //textViewShowDescription установим поученное значение builder и приведем его к строчному типу
        textViewShowDescription.setText(builder.toString());
    }
}