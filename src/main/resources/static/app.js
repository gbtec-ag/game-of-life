// Configuration
const canvasSize = 700,
    cellSpaceSize = 3;
let matrixSize = 0;

let stompClient = null;

// UI Elements
let alertBox = null;

let buttonConnect = null;
let buttonDisconnect = null;
let buttonConnecting = null;
const canvas = document.getElementById("generationDataCanvas");

function setConnected() {
    buttonConnect.addClass("d-none");
    buttonDisconnect.removeClass("d-none");
    buttonConnecting.addClass("d-none");

    alertBox.removeClass();
    alertBox.addClass("alert alert-success");
    alertBox.text("Connected :)");
}

function setDisconnected() {
    buttonConnect.removeClass("d-none");
    buttonDisconnect.addClass("d-none");
    buttonConnecting.addClass("d-none");

    alertBox.removeClass();
    alertBox.addClass("alert alert-warning");
    alertBox.text("Server not connected!");
}

function setConnectionPending() {
    buttonConnect.addClass("d-none");
    buttonDisconnect.addClass("d-none");
    buttonConnecting.removeClass("d-none");
}

function connect() {
    setConnectionPending();

    let socket = new SockJS('/gui-websocket-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect(
        {}, // headers
        function (frame) { // connectCallback
            setConnected();

            stompClient.subscribe('/generation/main', function (generationData) {
                drawCellsFromData(JSON.parse(generationData.body).generationData, false);
            });
            stompClient.subscribe('/generation/preview', function (generationData) {
                drawCellsFromData(JSON.parse(generationData.body).generationData, true);
            });
            stompClient.subscribe('/statistics', function (statistics) {
                const body = JSON.parse(statistics.body);
                updateStatistics(body.generation, body.aliveCells);
            });
            stompClient.subscribe('/rulesUpdate', function (rulesData) {
                updateRules(JSON.parse(rulesData.body));
            });
        },
        function (frame) { // errorCallback
            setDisconnected();

            alertBox.removeClass();
            alertBox.addClass("alert alert-danger");
            alertBox.text("Connection failed!");
        }
    );

    $.post("/action/onConnect");

}

function disconnect() {
    setDisconnected();

    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    buttonConnect.click(function () {
        connect();
    });
    buttonDisconnect.click(function () {
        disconnect();
    });

    $("#buttonInit").click(function () {
        // https://api.jquery.com/jquery.post/
        $.post("/action/init");
    });

    $("#buttonNext").click(function () {
        // https://api.jquery.com/jquery.post/
        $.post("/action/next");
    });

    $("#buttonStop").click(function () {
        // https://api.jquery.com/jquery.post/
        $.post("/action/stop");
    });

    $("#buttonPlay").click(function () {
        // https://api.jquery.com/jquery.post/
        $.ajax({
            url: "/action/play",
            type: "POST",
            data: JSON.stringify({delayMs: parseInt($("#inputPlayDelay").val())}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        })
    });

    $("#generationDataCanvas").click(function (event) {
        let canvas = document.getElementById("generationDataCanvas"),
            canvasLeft = canvas.offsetLeft + canvas.clientLeft,
            canvasTop = canvas.offsetTop + canvas.clientTop,
            clickX = event.pageX - canvasLeft,
            clickY = event.pageY - canvasTop,
            nextXPos = 0,
            nextYPos = 0,
            cellSize = Math.trunc(canvasSize / matrixSize) - cellSpaceSize;

        for (let x = 0; x < matrixSize; x++) {
            for (let y = 0; y < matrixSize; y++) {

                if (clickY > nextYPos && clickY < nextYPos + cellSize && clickX > nextXPos && clickX < nextXPos + cellSize) {
                    $.ajax({
                        url: "/action/clickCell",
                        type: "POST",
                        data: JSON.stringify({x: x, y: y}),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json"
                    })
                }

                nextXPos += cellSize + cellSpaceSize;
            }
            nextXPos = 0;
            nextYPos += cellSize + cellSpaceSize;
        }

    });

});

window.addEventListener('load', () => {
    // init UI elements
    alertBox = $("#alertBox");

    buttonConnect = $("#buttonConnect");
    buttonDisconnect = $("#buttonDisconnect");
    buttonConnecting = $("#buttonConnecting");

    // initiate dialog and button as disconnected
    setDisconnected();

    // https://api.jquery.com/jquery.post/
    $.post("/action/onLoad");

});

function drawCellsFromData(generationData, previewDisplay) {
    let canvas;

    if (previewDisplay) {
        canvas = document.querySelector("#generationDataPreview");
    } else {
        canvas = document.querySelector("#generationDataCanvas");
    }

    const context = canvas.getContext("2d");

    // Set the generation size considering the min and max matrix size
    const matrixMinSize = 5;
    const matrixMaxSize = 63;
    matrixSize = generationData.length;
    if (matrixSize < matrixMinSize) {
        matrixSize = matrixMinSize;
    } else if (matrixSize > matrixMaxSize) {
        matrixSize = matrixMaxSize
    }

    canvas.width = canvasSize;
    canvas.height = canvasSize;

    // Set the cell size considering the min and max values
    const cellMinSize = 8;
    const cellMaxSize = 512;
    let cellSize = Math.trunc(canvasSize / matrixSize) - cellSpaceSize;
    if (cellSize < cellMinSize) {
        cellSize = cellMinSize;
    } else if (cellSize > cellMaxSize) {
        cellSize = cellMaxSize
    }

    // Fill the canvas with data
    let nextXPos = 0;
    let nextYPos = 0;
    for (let y = 0; y < matrixSize; y++) {
        for (let x = 0; x < matrixSize; x++) {
            if (generationData[y][x]) {
                context.fillStyle = 'blue';
            } else {
                context.fillStyle = 'lightgray';
            }
            context.fillRect(nextXPos, nextYPos, cellSize, cellSize);
            nextXPos += cellSize + cellSpaceSize;
        }

        nextXPos = 0;
        nextYPos += cellSize + cellSpaceSize;
    }
}


// Tab pages

$(function () {

    $("#tabButtonStartConditions").click(function () {
        $.post("action/initPreviewDisplay");
        document.getElementById("tabButtonStartConditions").className = "btn btn-outline-primary";
        document.getElementById("tabButtonStatistics").className = "btn btn-outline-secondary";
        document.getElementById("tabButtonRules").className = "btn btn-outline-secondary";

        document.getElementById("tabContent").innerHTML =
            "                <canvas id=\"generationDataPreview\"></canvas>\n" +
            "                <div class=\"buttonLine\">\n" +
            "                    <button id=\"startConditionsSwap\" type=\"submit\" class=\"btn btn-outline-success\">Swap</button>\n" +
            "                    <button id=\"startConditionsClear\" type=\"reset\" class=\"btn btn-outline-warning\">Clear</button>\n" +
            "                </div>\n" +
            "                <div class=\"tabSectionSeperator\"></div>\n" +
            "                <div class=\"buttonLine\">\n" +
            "                    <button id=\"startConditionsPreview\" type=\"button\" class=\"btn btn-outline-primary\">Preview from Storage</button>\n" +
            "                    <button id=\"startConditionsSave\" type=\"submit\" class=\"btn btn-outline-success\">Save from Preview</button>\n" +
            "                </div>\n" +
            "                <div class=\"buttonLine\">\n" +
            "                    <input id=\"startConditionsStorageNumberInput\" type=\"number\" placeholder=\"0\" aria-label=\"The number of the storage where the generation data which should be loaded is stored\">\n" +
            "                </div>\n" +
            "            </div>";


        $("#startConditionsSwap").click(function () {
            // https://api.jquery.com/jquery.post/
            $.post("/action/startConditionsSwap");
        });

        $("#startConditionsClear").click(function () {
            // https://api.jquery.com/jquery.post/
            $.post("/action/startConditionsClear");
        });

        $("#startConditionsPreview").click(function () {
            // https://api.jquery.com/jquery.post/
            $.ajax({
                url: "/action/startConditionsPreview",
                type: "POST",
                data: JSON.stringify({storageId: parseInt($("#startConditionsStorageNumberInput").val())}),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            })
        });

        $("#startConditionsSave").click(function () {
            // https://api.jquery.com/jquery.post/
            $.ajax({
                url: "/action/startConditionsSave",
                type: "POST",
                data: JSON.stringify({storageId: parseInt($("#startConditionsStorageNumberInput").val())}),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            })
        });

    });

    $("#tabButtonStatistics").click(function () {
        $.post("action/initStatistics");
        document.getElementById("tabButtonStartConditions").className = "btn btn-outline-secondary";
        document.getElementById("tabButtonStatistics").className = "btn btn-outline-primary";
        document.getElementById("tabButtonRules").className = "btn btn-outline-secondary";

        document.getElementById("tabContent").innerHTML =
            "                <div class=\"statisticsLine\">\n" +
            "                    <h2>Generation</h2>\n" +
            "                    <br/>\n" +
            "                    <h1 id=\"generationStatistics\">1</h1>\n" +
            "                </div>\n" +
            "                <div class=\"statisticsLine\">\n" +
            "                    <h2>Alive</h2>\n" +
            "                    <br/>\n" +
            "                    <h1 id=\"aliveCellsStatistics\">0</h1>\n" +
            "                </div>";
    });

    $("#tabButtonRules").click(function () {
        $.post("action/loadRules");
        document.getElementById("tabButtonStartConditions").className = "btn btn-outline-secondary";
        document.getElementById("tabButtonStatistics").className = "btn btn-outline-secondary";
        document.getElementById("tabButtonRules").className = "btn btn-outline-primary";

        document.getElementById("tabContent").innerHTML =
            "                <h5>Game Rules</h5>\n" +
            "                <div class=\"rule\">\n" +
            "                    <h6>Rule 1</h6>\n" +
            "                    <select id=\"rule1Enabled\" class=\"enabled\">\n" +
            "                        <option>enabled</option>\n" +
            "                        <option>disabled</option>\n" +
            "                    </select>\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <p class=\"color-keyword\">if </p>\n" +
            "                    <p class=\"color-parenthesis\">(</p>\n" +
            "                    <p class=\"color-staticField\">alive</p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule1NowAlive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-operatorSign\">&& </p>\n" +
            "                    <p class=\"color-staticField\">livingNeighbourCount </p>\n" +
            "                    <select id=\"rule1Operator\" class=\"operator color-operatorSign\">\n" +
            "                        <option>=</option>\n" +
            "                        <option>></option>\n" +
            "                        <option><</option>\n" +
            "                        <option>>=</option>\n" +
            "                        <option><=</option>\n" +
            "                        <option>!=</option>\n" +
            "                    </select>\n" +
            "                    <select id=\"rule1Number\" class=\"number color-number\">\n" +
            "                        <option>0</option>\n" +
            "                        <option>1</option>\n" +
            "                        <option>2</option>\n" +
            "                        <option>3</option>\n" +
            "                        <option>4</option>\n" +
            "                        <option>5</option>\n" +
            "                        <option>6</option>\n" +
            "                        <option>7</option>\n" +
            "                        <option>8</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-parenthesis\">) </p>\n" +
            "                    <p class=\"color-staticField\">alive </p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule1Alive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "                <div class=\"rule\">\n" +
            "                    <h6>Rule 2</h6>\n" +
            "                    <select id=\"rule2Enabled\" class=\"enabled\">\n" +
            "                        <option>enabled</option>\n" +
            "                        <option>disabled</option>\n" +
            "                    </select>\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <p class=\"color-keyword\">if </p>\n" +
            "                    <p class=\"color-parenthesis\">(</p>\n" +
            "                    <p class=\"color-staticField\">alive</p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule2NowAlive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-operatorSign\">&& </p>\n" +
            "                    <p class=\"color-staticField\">livingNeighbourCount </p>\n" +
            "                    <select id=\"rule2Operator\" class=\"operator color-operatorSign\">\n" +
            "                        <option>=</option>\n" +
            "                        <option>></option>\n" +
            "                        <option><</option>\n" +
            "                        <option>>=</option>\n" +
            "                        <option><=</option>\n" +
            "                        <option>!=</option>\n" +
            "                    </select>\n" +
            "                    <select id=\"rule2Number\" class=\"number color-number\">\n" +
            "                        <option>0</option>\n" +
            "                        <option>1</option>\n" +
            "                        <option>2</option>\n" +
            "                        <option>3</option>\n" +
            "                        <option>4</option>\n" +
            "                        <option>5</option>\n" +
            "                        <option>6</option>\n" +
            "                        <option>7</option>\n" +
            "                        <option>8</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-parenthesis\">) </p>\n" +
            "                    <p class=\"color-staticField\">alive </p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule2Alive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "                <div class=\"rule\">\n" +
            "                    <h6>Rule 3</h6>\n" +
            "                    <select id=\"rule3Enabled\" class=\"enabled\">\n" +
            "                        <option>enabled</option>\n" +
            "                        <option>disabled</option>\n" +
            "                    </select>\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <p class=\"color-keyword\">if </p>\n" +
            "                    <p class=\"color-parenthesis\">(</p>\n" +
            "                    <p class=\"color-staticField\">alive</p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule3NowAlive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-operatorSign\">&& </p>\n" +
            "                    <p class=\"color-staticField\">livingNeighbourCount </p>\n" +
            "                    <select id=\"rule3Operator\" class=\"operator color-operatorSign\">\n" +
            "                        <option>=</option>\n" +
            "                        <option>></option>\n" +
            "                        <option><</option>\n" +
            "                        <option>>=</option>\n" +
            "                        <option><=</option>\n" +
            "                        <option>!=</option>\n" +
            "                    </select>\n" +
            "                    <select id=\"rule3Number\" class=\"number color-number\">\n" +
            "                        <option>0</option>\n" +
            "                        <option>1</option>\n" +
            "                        <option>2</option>\n" +
            "                        <option>3</option>\n" +
            "                        <option>4</option>\n" +
            "                        <option>5</option>\n" +
            "                        <option>6</option>\n" +
            "                        <option>7</option>\n" +
            "                        <option>8</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-parenthesis\">) </p>\n" +
            "                    <p class=\"color-staticField\">alive </p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule3Alive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "                <div class=\"rule\">\n" +
            "                    <h6>Rule 4</h6>\n" +
            "                    <select id=\"rule4Enabled\" class=\"enabled\">\n" +
            "                        <option>enabled</option>\n" +
            "                        <option>disabled</option>\n" +
            "                    </select>\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <p class=\"color-keyword\">if </p>\n" +
            "                    <p class=\"color-parenthesis\">(</p>\n" +
            "                    <p class=\"color-staticField\">alive</p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule4NowAlive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-operatorSign\">&& </p>\n" +
            "                    <p class=\"color-staticField\">livingNeighbourCount </p>\n" +
            "                    <select id=\"rule4Operator\" class=\"operator color-operatorSign\">\n" +
            "                        <option>=</option>\n" +
            "                        <option>></option>\n" +
            "                        <option><</option>\n" +
            "                        <option>>=</option>\n" +
            "                        <option><=</option>\n" +
            "                        <option>!=</option>\n" +
            "                    </select>\n" +
            "                    <select id=\"rule4Number\" class=\"number color-number\">\n" +
            "                        <option>0</option>\n" +
            "                        <option>1</option>\n" +
            "                        <option>2</option>\n" +
            "                        <option>3</option>\n" +
            "                        <option>4</option>\n" +
            "                        <option>5</option>\n" +
            "                        <option>6</option>\n" +
            "                        <option>7</option>\n" +
            "                        <option>8</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-parenthesis\">) </p>\n" +
            "                    <p class=\"color-staticField\">alive </p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule4Alive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "                <div class=\"rule\">\n" +
            "                    <h6>Rule 5</h6>\n" +
            "                    <select id=\"rule5Enabled\" class=\"enabled\">\n" +
            "                        <option>enabled</option>\n" +
            "                        <option>disabled</option>\n" +
            "                    </select>\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <p class=\"color-keyword\">if </p>\n" +
            "                    <p class=\"color-parenthesis\">(</p>\n" +
            "                    <p class=\"color-staticField\">alive</p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule5NowAlive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-operatorSign\">&& </p>\n" +
            "                    <p class=\"color-staticField\">livingNeighbourCount </p>\n" +
            "                    <select id=\"rule5Operator\" class=\"operator color-operatorSign\">\n" +
            "                        <option>=</option>\n" +
            "                        <option>></option>\n" +
            "                        <option><</option>\n" +
            "                        <option>>=</option>\n" +
            "                        <option><=</option>\n" +
            "                        <option>!=</option>\n" +
            "                    </select>\n" +
            "                    <select id=\"rule5Number\" class=\"number color-number\">\n" +
            "                        <option>0</option>\n" +
            "                        <option>1</option>\n" +
            "                        <option>2</option>\n" +
            "                        <option>3</option>\n" +
            "                        <option>4</option>\n" +
            "                        <option>5</option>\n" +
            "                        <option>6</option>\n" +
            "                        <option>7</option>\n" +
            "                        <option>8</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-parenthesis\">) </p>\n" +
            "                    <p class=\"color-staticField\">alive </p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule5Alive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "                <div class=\"rule\">\n" +
            "                    <h6>Rule 6</h6>\n" +
            "                    <select id=\"rule6Enabled\" class=\"enabled\">\n" +
            "                        <option>enabled</option>\n" +
            "                        <option>disabled</option>\n" +
            "                    </select>\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <p class=\"color-keyword\">if </p>\n" +
            "                    <p class=\"color-parenthesis\">(</p>\n" +
            "                    <p class=\"color-staticField\">alive</p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule6NowAlive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-operatorSign\">&& </p>\n" +
            "                    <p class=\"color-staticField\">livingNeighbourCount </p>\n" +
            "                    <select id=\"rule6Operator\" class=\"operator color-operatorSign\">\n" +
            "                        <option>=</option>\n" +
            "                        <option>></option>\n" +
            "                        <option><</option>\n" +
            "                        <option>>=</option>\n" +
            "                        <option><=</option>\n" +
            "                        <option>!=</option>\n" +
            "                    </select>\n" +
            "                    <select id=\"rule6Number\" class=\"number color-number\">\n" +
            "                        <option>0</option>\n" +
            "                        <option>1</option>\n" +
            "                        <option>2</option>\n" +
            "                        <option>3</option>\n" +
            "                        <option>4</option>\n" +
            "                        <option>5</option>\n" +
            "                        <option>6</option>\n" +
            "                        <option>7</option>\n" +
            "                        <option>8</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-parenthesis\">) </p>\n" +
            "                    <p class=\"color-staticField\">alive </p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule6Alive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "                <div class=\"rule\">\n" +
            "                    <h6>Rule 7</h6>\n" +
            "                    <select id=\"rule7Enabled\" class=\"enabled\">\n" +
            "                        <option>enabled</option>\n" +
            "                        <option>disabled</option>\n" +
            "                    </select>\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <p class=\"color-keyword\">if </p>\n" +
            "                    <p class=\"color-parenthesis\">(</p>\n" +
            "                    <p class=\"color-staticField\">alive</p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule7NowAlive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-operatorSign\">&& </p>\n" +
            "                    <p class=\"color-staticField\">livingNeighbourCount </p>\n" +
            "                    <select id=\"rule7Operator\" class=\"operator color-operatorSign\">\n" +
            "                        <option>=</option>\n" +
            "                        <option>></option>\n" +
            "                        <option><</option>\n" +
            "                        <option>>=</option>\n" +
            "                        <option><=</option>\n" +
            "                        <option>!=</option>\n" +
            "                    </select>\n" +
            "                    <select id=\"rule7Number\" class=\"number color-number\">\n" +
            "                        <option>0</option>\n" +
            "                        <option>1</option>\n" +
            "                        <option>2</option>\n" +
            "                        <option>3</option>\n" +
            "                        <option>4</option>\n" +
            "                        <option>5</option>\n" +
            "                        <option>6</option>\n" +
            "                        <option>7</option>\n" +
            "                        <option>8</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-parenthesis\">) </p>\n" +
            "                    <p class=\"color-staticField\">alive </p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule7Alive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "                <div class=\"rule\">\n" +
            "                    <h6>Rule 8</h6>\n" +
            "                    <select id=\"rule8Enabled\" class=\"enabled\">\n" +
            "                        <option>enabled</option>\n" +
            "                        <option>disabled</option>\n" +
            "                    </select>\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <p class=\"color-keyword\">if </p>\n" +
            "                    <p class=\"color-parenthesis\">(</p>\n" +
            "                    <p class=\"color-staticField\">alive</p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule8NowAlive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-operatorSign\">&& </p>\n" +
            "                    <p class=\"color-staticField\">livingNeighbourCount </p>\n" +
            "                    <select id=\"rule8Operator\" class=\"operator color-operatorSign\">\n" +
            "                        <option>=</option>\n" +
            "                        <option>></option>\n" +
            "                        <option><</option>\n" +
            "                        <option>>=</option>\n" +
            "                        <option><=</option>\n" +
            "                        <option>!=</option>\n" +
            "                    </select>\n" +
            "                    <select id=\"rule8Number\" class=\"number color-number\">\n" +
            "                        <option>0</option>\n" +
            "                        <option>1</option>\n" +
            "                        <option>2</option>\n" +
            "                        <option>3</option>\n" +
            "                        <option>4</option>\n" +
            "                        <option>5</option>\n" +
            "                        <option>6</option>\n" +
            "                        <option>7</option>\n" +
            "                        <option>8</option>\n" +
            "                    </select>\n" +
            "                    <p class=\"color-parenthesis\">) </p>\n" +
            "                    <p class=\"color-staticField\">alive </p>\n" +
            "                    <p class=\"color-operatorSign\">= </p>\n" +
            "                    <select id=\"rule8Alive\" class=\"alive color-keyword\">\n" +
            "                        <option>true</option>\n" +
            "                        <option>false</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "                <div class=\"tabSectionSeperator\"></div>\n" +
            "                <h5>Configuration</h5>\n" +
            "                <div id=\"configuration\" class=\"rule\">\n" +
            "                    <h6>Matrix size</h6>\n" +
            "                    <input id=\"matrixSize\" type=\"number\" min=\"0\" aria-label=\"The size of the display matrix\" value=\"32\">\n" +
            "                    <div class=\"verticalSeperator\"></div>\n" +
            "                    <input id=\"infiniteDisplay\" type=\"checkbox\" aria-label=\"If the display should be infinite\" checked>\n" +
            "                    <h6>Infinite display</h6>\n" +
            "                </div>\n" +
            "                <div class=\"tabSectionSeperator\"></div>\n" +
            "                <button id=\"saveRules\" type=\"submit\" class=\"btn btn-outline-success\">Save Settings</button>";

        $("#saveRules").click(function () {
            matrixSize = parseInt(document.getElementById("matrixSize").value);
            // https://api.jquery.com/jquery.post/
            $.ajax({
                url: "/action/saveRules",
                type: "POST",
                data: JSON.stringify({
                    gameRules: [
                        {
                            ruleId: 1,
                            enabled: document.getElementById("rule1Enabled").value === "enabled",
                            nowAlive: document.getElementById("rule1NowAlive").value === "true",
                            operator: document.getElementById("rule1Operator").value,
                            number: parseInt(document.getElementById("rule1Number").value),
                            alive: document.getElementById("rule1Alive").value === "true"
                        },
                        {
                            ruleId: 2,
                            enabled: document.getElementById("rule2Enabled").value === "enabled",
                            nowAlive: document.getElementById("rule2NowAlive").value === "true",
                            operator: document.getElementById("rule2Operator").value,
                            number: parseInt(document.getElementById("rule2Number").value),
                            alive: document.getElementById("rule2Alive").value === "true"
                        },
                        {
                            ruleId: 3,
                            enabled: document.getElementById("rule3Enabled").value === "enabled",
                            nowAlive: document.getElementById("rule3NowAlive").value === "true",
                            operator: document.getElementById("rule3Operator").value,
                            number: parseInt(document.getElementById("rule3Number").value),
                            alive: document.getElementById("rule3Alive").value === "true"
                        },
                        {
                            ruleId: 4,
                            enabled: document.getElementById("rule4Enabled").value === "enabled",
                            nowAlive: document.getElementById("rule4NowAlive").value === "true",
                            operator: document.getElementById("rule4Operator").value,
                            number: parseInt(document.getElementById("rule4Number").value),
                            alive: document.getElementById("rule4Alive").value === "true"
                        },
                        {
                            ruleId: 5,
                            enabled: document.getElementById("rule5Enabled").value === "enabled",
                            nowAlive: document.getElementById("rule5NowAlive").value === "true",
                            operator: document.getElementById("rule5Operator").value,
                            number: parseInt(document.getElementById("rule5Number").value),
                            alive: document.getElementById("rule5Alive").value === "true"
                        },
                        {
                            ruleId: 6,
                            enabled: document.getElementById("rule6Enabled").value === "enabled",
                            nowAlive: document.getElementById("rule6NowAlive").value === "true",
                            operator: document.getElementById("rule6Operator").value,
                            number: parseInt(document.getElementById("rule6Number").value),
                            alive: document.getElementById("rule6Alive").value === "true"
                        },
                        {
                            ruleId: 7,
                            enabled: document.getElementById("rule7Enabled").value === "enabled",
                            nowAlive: document.getElementById("rule7NowAlive").value === "true",
                            operator: document.getElementById("rule7Operator").value,
                            number: parseInt(document.getElementById("rule7Number").value),
                            alive: document.getElementById("rule7Alive").value === "true"
                        },
                        {
                            ruleId: 8,
                            enabled: document.getElementById("rule8Enabled").value === "enabled",
                            nowAlive: document.getElementById("rule8NowAlive").value === "true",
                            operator: document.getElementById("rule8Operator").value,
                            number: parseInt(document.getElementById("rule8Number").value),
                            alive: document.getElementById("rule8Alive").value === "true"
                        }
                    ],
                    matrixSize: matrixSize,
                    infiniteDisplay: document.getElementById("infiniteDisplay").checked
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            });
        });

    });

});

function updateStatistics(generation, aliveCells) {
    const generationStatistics = document.getElementById("generationStatistics"),
        aliveCellsStatistics = document.getElementById("aliveCellsStatistics");
    if (generationStatistics == null || aliveCellsStatistics == null) {
        return;
    }

    if (generation != null) {
        document.getElementById("generationStatistics").innerHTML = generation;
    }
    if (aliveCells != null) {
        document.getElementById("aliveCellsStatistics").innerHTML = aliveCells;
    }

}

function updateRules(rulesData) {
    const gameRules = rulesData.gameRules;

    document.getElementById("rule1Enabled").value = gameRules[0].enabled ? "enabled" : "disabled";
    document.getElementById("rule1NowAlive").value = gameRules[0].nowAlive ? "true" : "false";
    document.getElementById("rule1Operator").value = gameRules[0].operator;
    document.getElementById("rule1Number").value = gameRules[0].number;
    document.getElementById("rule1Alive").value = gameRules[0].alive ? "true" : "false";

    document.getElementById("rule2Enabled").value = gameRules[1].enabled ? "enabled" : "disabled";
    document.getElementById("rule2NowAlive").value = gameRules[1].nowAlive ? "true" : "false";
    document.getElementById("rule2Operator").value = gameRules[1].operator;
    document.getElementById("rule2Number").value = gameRules[1].number;
    document.getElementById("rule2Alive").value = gameRules[1].alive ? "true" : "false";

    document.getElementById("rule3Enabled").value = gameRules[2].enabled ? "enabled" : "disabled";
    document.getElementById("rule3NowAlive").value = gameRules[2].nowAlive ? "true" : "false";
    document.getElementById("rule3Operator").value = gameRules[2].operator;
    document.getElementById("rule3Number").value = gameRules[2].number;
    document.getElementById("rule3Alive").value = gameRules[2].alive ? "true" : "false";

    document.getElementById("rule4Enabled").value = gameRules[3].enabled ? "enabled" : "disabled";
    document.getElementById("rule4NowAlive").value = gameRules[3].nowAlive ? "true" : "false";
    document.getElementById("rule4Operator").value = gameRules[3].operator;
    document.getElementById("rule4Number").value = gameRules[3].number;
    document.getElementById("rule4Alive").value = gameRules[3].alive ? "true" : "false";

    document.getElementById("rule5Enabled").value = gameRules[4].enabled ? "enabled" : "disabled";
    document.getElementById("rule5NowAlive").value = gameRules[4].nowAlive ? "true" : "false";
    document.getElementById("rule5Operator").value = gameRules[4].operator;
    document.getElementById("rule5Number").value = gameRules[4].number;
    document.getElementById("rule5Alive").value = gameRules[4].alive ? "true" : "false";

    document.getElementById("rule6Enabled").value = gameRules[5].enabled ? "enabled" : "disabled";
    document.getElementById("rule6NowAlive").value = gameRules[5].nowAlive ? "true" : "false";
    document.getElementById("rule6Operator").value = gameRules[5].operator;
    document.getElementById("rule6Number").value = gameRules[5].number;
    document.getElementById("rule6Alive").value = gameRules[5].alive ? "true" : "false";

    document.getElementById("rule7Enabled").value = gameRules[6].enabled ? "enabled" : "disabled";
    document.getElementById("rule7NowAlive").value = gameRules[6].nowAlive ? "true" : "false";
    document.getElementById("rule7Operator").value = gameRules[6].operator;
    document.getElementById("rule7Number").value = gameRules[6].number;
    document.getElementById("rule7Alive").value = gameRules[6].alive ? "true" : "false";

    document.getElementById("rule8Enabled").value = gameRules[7].enabled ? "enabled" : "disabled";
    document.getElementById("rule8NowAlive").value = gameRules[7].nowAlive ? "true" : "false";
    document.getElementById("rule8Operator").value = gameRules[7].operator;
    document.getElementById("rule8Number").value = gameRules[7].number;
    document.getElementById("rule8Alive").value = gameRules[7].alive ? "true" : "false";

    document.getElementById("matrixSize").value = rulesData.matrixSize;
    document.getElementById("infiniteDisplay").checked = rulesData.infiniteDisplay;

}