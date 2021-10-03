var express = require('express');
const fetch = require("node-fetch");
const MongoClient = require('../config/db.config');
// const axios = require('axios');
const _ = require('lodash');


async function postWeatherInDB() {
    let allHikeLocationPoints = await _getAllHikeLocationPoint();
    let dateOfDay = new Date().toLocaleDateString();
    _dropCollectionWeather();
    _.each(allHikeLocationPoints || [], item => {
        DataProcessing(item.latitude, item.longitude, dateOfDay);
    });
}

function DataProcessing(lat, lon, date) {

    fetch('https://api.openweathermap.org/data/2.5/weather?lat=' + lat + '&lon=' + lon + '&appid=ae741a64456a183b1692422d804fd5fd&units=metric&lang=fr')
        .then(res => res.json())
        .then(res => _insertWeather(res));

    //faire en sorte que ca delete all in db les dates qui sont trop vieille de 7 jours (DayDeadLine)
    // let allWeathers = await _getAllWeatherInDB();
    // if(allWeathers.date)
}

async function _getAllHikeLocationPoint() {
    const db = await MongoClient.init();
    return await db.collection("hike_location").find({}).toArray();
}

async function _dropCollectionWeather(){
    const db = await MongoClient.init();
    db.collection("weather").drop();
}

async function _insertWeather(api) {
    const db = await MongoClient.init();
    //processing trie de donnée
    let meteo = {
    }
    meteo.latitude = api.coord.lat;
    meteo.longitude = api.coord.lon;
    meteo.temp = api.main.temp;
    meteo.feels_like = api.main.feels_like;
    meteo.temp_min = api.main.temp_min;
    meteo.temp_max = api.main.temp_max;
    meteo.humidity = api.main.humidity;
    meteo.visibility = api.visibility;
    meteo.cloudsPourcentage = api.clouds.all;
    meteo.name = api.name;


    db.collection("weather").insertOne(meteo, null, function (error, results) {
        if (error) throw error;
        console.log("Insert Weather ok");
    });
}


async function _getAllWeatherInDB() {
    const db = await MongoClient.init();
    db.collection("weather").find({});
}


async function _deleteDataWeather(date) {
    const db = await MongoClient.init();
    var myquery = { "date": date };
    console.log(myquery);
    db.collection("weather").deleteMany(myquery, function (error, obj) {
        if (error) throw error;
        console.log("One Deleted weather");
    })
}

//fonction à changer pour delete et garder que 7 jours grands max dans la bdd
// async function _deleteWeather(date, lat, lon) {
//     const db = await mongoClient.init();
//     var myquery = { date: date, lat: lat, lon: lon };
//     console.log(myquery);
//     db.collection("weather").deleteOne(myquery, function (error, obj) {
//         if (error) throw error;
//         console.log("One Deleted weather");
//     })
// }

module.exports.postWeatherInDB = postWeatherInDB;