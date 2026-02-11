package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WeatherResponse{
    public MainData main;

    public static class MainData{
        public double temp;
        @JsonProperty("feels_like")
        public double feelsLike;
        @JsonProperty("temp_min")
        public double tempMin;
        @JsonProperty("temp_max")
        public double tempMax;
        public int pressure;
        public int humidity;
        @JsonProperty("sea_level")
        public int seaLevel;
        @JsonProperty("grnd_level")
        public int grndLevel;

        public double getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(double feelsLike) {
            this.feelsLike = feelsLike;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getTempMin() {
            return tempMin;
        }

        public void setTempMin(double tempMin) {
            this.tempMin = tempMin;
        }

        public double getTempMax() {
            return tempMax;
        }

        public void setTempMax(double tempMax) {
            this.tempMax = tempMax;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getSeaLevel() {
            return seaLevel;
        }

        public void setSeaLevel(int seaLevel) {
            this.seaLevel = seaLevel;
        }

        public int getGrndLevel() {
            return grndLevel;
        }

        public void setGrndLevel(int grndLevel) {
            this.grndLevel = grndLevel;
        }
    }
}


