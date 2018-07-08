var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');
var fs = require('fs');
var ipAddress = '127.0.0.1';
var staticSource = "C:/Users/79263/Desktop/project/android_server"
var connection = mysql.createConnection({
    host: '127.0.0.1',
    user: 'root',
    password: '',
    database: 'android_test'
})
var urlencodeParser = bodyParser.urlencoded({
    extended: true,
});
app.use(bodyParser.json())
app.use(express.static(staticSource));
app.get('/index.html', function (req, res) {
    res.send(__dirname + "/" + "index.html");
});


app.post('/login', urlencodeParser, function (req, res) {
    var data = {
        userAccount: req.body.userAccount,
        password: req.body.password
    }
    console.log(data)
    connection.query('select user_name,user_level from android_test where (iphone = "' + data.userAccount + '" OR email = "' + data.userAccount + '") AND `password` = "' + data.password + '"', function (err, result) {
        if (err) {
            console.log(err);
            res.json({ "flag": "0" });
        }
        else {
            console.log(result)
            res.json({
                flag: "1",
                userData: result[0],
            })
        }
    })
});
app.post('/register', urlencodeParser, function (req, res) {
    var data = {
        user_name: req.body.username,
        user_id: null,
        password: req.body.password,
        email: req.body.email,
        iphone: req.body.iphone,
        user_level: 0,
        user_g: 0,
    }
    console.log(req.body)
    connection.query('insert into android_test set ?', data, function (err, result) {
        if (err) {
            console.log(err);
            res.json({
                "flag": "0"
            });
        }
        else {
            res.json({
                "flag": "1"
            });
        }
    })
});
app.post('/selectAllNews', urlencodeParser, function (req, res) {
    connection.query('select * from image,news', function (err, result) {
        if (err) {
            console.log(err);
            res.json({ "flag": "0" });
        }
        else {
            res.json(result[0])
        }
    })
})
app.post('/newsDetial', urlencodeParser, function (req, res) {
    var id = req.body.newID;
    connection.query('select * from image,news where image.news_id = ' + id + ' and news.news_id = ' + id, function (err, result) {
        if (err) {
            console.log(err);
            res.json({ "flag": "0" });
        }
        else {
            res.json(result[0])
        }
    })
})
// app.use(bodyParser.json({ limit: '50mb' }));
var server = app.listen(3020, function () {
    var host = server.address().address;
    var port = server.address().port
    console.log('android server started at http://%s%s', host, port)
})