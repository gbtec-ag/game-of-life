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
    const matrixMaxSize = 113;
    matrixSize = generationData.length;
    if (matrixSize < matrixMinSize) {
        matrixSize = matrixMinSize;
    } else if (matrixSize > matrixMaxSize) {
        matrixSize = matrixMaxSize
    }

    canvas.width = canvasSize;
    canvas.height = canvasSize;

    // Set the cell size considering the min and max values
    const cellMinSize = 4;
    const cellMaxSize = 28;
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