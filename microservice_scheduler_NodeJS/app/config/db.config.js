const { MongoClient } = require("mongodb")
// const dotenv = require("dotenv");

// dotenv.config();
const uriLocal = "mongodb://superuser:motdepasse@localhost:27017/admin?authSource=admin";
const uri = process.env.URI_MONGODB || uriLocal;


const client = new MongoClient(uri, {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

async function init() {
    //connect the client to the server
    await client.connect();
    //etablish and verify connection
    let db = await client.db('projet_final');
    return db;
}



module.exports.init = init;