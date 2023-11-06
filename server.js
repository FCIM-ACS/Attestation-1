const express = require("express");
const bodyParser = require("body-parser");
const app = express();
const port = 3000;

app.use(bodyParser.json());
app.use(express.static("public"));

app.post("/add", (req, res) => {
  const { first, second } = req.body;
  if (typeof first === "number" && typeof second === "number") {
    const result = first + second;
    res.status(200).json({ result });
  } else {
    res.status(400).json({ error: "Invalid parameters" });
  }
});

app.post("/subtract", (req, res) => {
  const { first, second } = req.body;
  if (typeof first === "number" && typeof second === "number") {
    const result = first - second;
    res.status(200).json({ result });
  } else {
    res.status(400).json({ error: "Invalid parameters" });
  }
});

app.post("/multiply", (req, res) => {
  const { first, second } = req.body;
  if (typeof first === "number" && typeof second === "number") {
    const result = first * second;
    res.status(200).json({ result });
  } else {
    res.status(400).json({ error: "Invalid parameters" });
  }
});

app.post("/divide", (req, res) => {
  const { first, second } = req.body;
  if (typeof first === "number" && typeof second === "number") {
    if (second === 0) {
      res.status(400).json({ error: "Division by zero" });
    } else {
      const result = first / second;
      res.status(200).json({ result });
    }
  } else {
    res.status(400).json({ error: "Invalid parameters" });
  }
});

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
