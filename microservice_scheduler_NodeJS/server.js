const express = require("express");
const cors = require("cors");
const robot = require('node-schedule');
const { MongoClient } = require("mongodb");

const postWeatherInDB = require('./app/services/weather.service');
const init = require("./app/config/db.config");

const app = express();

var corsOptions = {
  origin: "*"
};

app.use(cors(corsOptions));

// parse requests of content-type - application/json
app.use(express.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));

// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to my scheduller application." });
});

init.init();

// scheduler
// pour 10h du matin (décalage de 2h sur heroku)
const doit1 = robot.scheduleJob('00 8 * * *', function () {
  postWeatherInDB.postWeatherInDB();
});
const doit2 = robot.scheduleJob('00 11 * * *', function () {
  postWeatherInDB.postWeatherInDB();
});
const doit3 = robot.scheduleJob('00 14 * * *', function () {
  postWeatherInDB.postWeatherInDB();
});

// const doit1Test = robot.scheduleJob('41 15 * * *', function () {
//   postWeatherInDB.postWeatherInDB();
// });
// const doit2Test = robot.scheduleJob('55 9 * * *', function () {
//   postWeatherInDB.postWeatherInDB();
// });
// const doit3Test = robot.scheduleJob('0 10 * * *', function () {
//   postWeatherInDB.postWeatherInDB();
// });

  // set port, listen for requests
  const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});