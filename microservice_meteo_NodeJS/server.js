const express = require("express");
const cors = require("cors");
const { MongoClient } = require("mongodb")
const bodyparser = require('body-parser');
const dataUseless = require("./app/data/dataUseless")
// var weatherApiRoutes = require('./app/routes/weather.routes');

//connexion
const uriLocal = "mongodb://superuser:motdepasse@localhost:27017/admin?authSource=admin";
const uri = process.env.URI_MONGODB || uriLocal;
var dbProjetFinal;

MongoClient.connect(uri, function (err, db) {
    if (err) throw err;
    dbProjetFinal = db.db("projet_final");
});
//


const app = express();

app.use(bodyparser.json());

var corsOptions = {
    origin: "*"
};

app.use(cors(corsOptions));

// parse requests of content-type - application/json
app.use(express.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));


//routing
// app.use(weatherApiRoutes.apiRouter);

app.get('/', (req, res) => {
    res.json({ "message": "Welcome on WeatherAPI" });
});

// { projection: dataUseless }
app.get("/weather/all", async (req, res) => {
    dbProjetFinal.collection("weather").find({}).toArray(function (err, result) {
        if (err) throw err;
        res.status(200).json(result)
        //  console.log(res);
    });
});

app.get("/weather/coord/:lat/:lon", async (req, res) => {
    let lonParam = Number(req.params.lon);
    let latParam = Number(req.params.lat);
    console.log(lonParam, " ", latParam)
    
    dbProjetFinal.collection("weather").find({'latitude': latParam, 'longitude': lonParam}).
    toArray(function (err, result) {
        if (err) throw err;
        res.status(200).json(result)
        console.log(result);
    });
});
	

app.get("/weather/city/:name", async (req, res) => {
    let cityParam = req.params.name;
    dbProjetFinal.collection("weather").find({ 'name': cityParam }, {projection: dataUseless}).toArray(function (err, result) {
        if (err) throw err;
        res.status(200).json(result)
        console.log(result);
    });
});

// app.get("/weather/weather/:id", async (req, res) => {
//     let weatherIdParam = req.params.id;
//     dbProjetFinal.collection("meteo").find({'weather.id':weatherIdParam}).toArray(function(err, result) {
//              if (err) throw err;
//              res.status(200).json(result)
//              console.log(result);
//   });
// });




// set port, listen for requests
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}.`);
});