const express = require('express');

const app = express();
const port = 8080;

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'home.html'));
});
app.get('/add', (req, res) => {
    const { first, second } = req.query;
    const firstNum = parseFloat(first);
    const secondNum = parseFloat(second);

    if (isNaN(firstNum) || isNaN(secondNum)) {
        res.status(400).send('Both "first" and "second" must be valid numbers.');
    } else {
        res.status(200).send((firstNum + secondNum).toString());
    }
});

app.get('/substract', (req, res) => {
    const { first, second } = req.query;
    const firstNum = parseFloat(first);
    const secondNum = parseFloat(second);

    if (isNaN(firstNum) || isNaN(secondNum)) {
        res.status(400).send('first sau second nu sunt numere');
    } else {
        res.status(200).send((firstNum - secondNum).toString());
    }
});

app.get('/multiply', (req, res) => {
    const { first, second } = req.query;
    const firstNum = parseFloat(first);
    const secondNum = parseFloat(second);

    if (isNaN(firstNum) || isNaN(secondNum)) {
        res.status(400).send('first sau second nu sunt numere');
    } else {
        res.status(200).send((firstNum * secondNum).toString());
    }
});

app.get('/divide', (req, res) => {
    const { first, second } = req.query;
    const firstNum = parseFloat(first);
    const secondNum = parseFloat(second);

    if (isNaN(firstNum) || isNaN(secondNum)) {
        res.status(400).send('first sau second nu sunt numere');
    } else if (secondNum === 0) {
        res.status(400).send('Impartirea la zero nu este permisa');
    } else {
        res.status(200).send((firstNum / secondNum).toString());
    }
});

app.listen(port, () => {
    console.log(`Serverul ascultă la http://localhost:${port}`);
});
