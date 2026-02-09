let stompClient = null;

// UI Elements
let alertBox = null;

let buttonConnect = null;
let buttonDisconnect = null;
let buttonConnecting = null;

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

            stompClient.subscribe('/generation', function (generationData) {
                scheduleDraw(JSON.parse(generationData.body).generationData);
            });
        },
        function (frame) { // errorCallback
            setDisconnected();

            alertBox.removeClass();
            alertBox.addClass("alert alert-danger");
            alertBox.text("Connection failed!");
        }
    );
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
    buttonConnect.click(function() { connect(); });
    buttonDisconnect.click(function() { disconnect(); });

    $("#buttonInit").click(function() {
        // https://api.jquery.com/jquery.post/
        $.post("/action/init");
    });

    $("#buttonNext").click(function() {
        // https://api.jquery.com/jquery.post/
        $.post("/action/next");
    });

    $("#buttonStop").click(function() {
        // https://api.jquery.com/jquery.post/
        $.post("/action/stop");
    });

    $("#buttonPlay").click(function() {
        // https://api.jquery.com/jquery.post/
        $.ajax({
            url: "/action/play",
            type: "POST",
            data: JSON.stringify({delayMs: parseInt($("#inputPlayDelay").val())}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        })
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
});

const CANVAS_SIZE = 700;
const MATRIX_MIN_SIZE = 5;
const MATRIX_MAX_SIZE = 256;
const CELL_SPACE_SIZE = 3;
const CELL_MIN_SIZE = 8;
const CELL_MAX_SIZE = 64;

let generationCanvas = null;
let generationContext = null;
let cachedMatrixSize = 0;
let cachedCellSize = 0;
let cachedXPositions = [];
let cachedYPositions = [];
let pendingGenerationData = null;
let drawScheduled = false;

function scheduleDraw(generationData) {
    pendingGenerationData = generationData;
    if (drawScheduled) {
        return;
    }
    drawScheduled = true;
    window.requestAnimationFrame(() => {
        drawScheduled = false;
        if (pendingGenerationData) {
            drawCellsFromData(pendingGenerationData);
            pendingGenerationData = null;
        }
    });
}

function drawCellsFromData(generationData) {
    if (!generationCanvas) {
        generationCanvas = document.querySelector("#generationDataCanvas");
        generationContext = generationCanvas.getContext("2d");
    }

    // Set the generation size considering the min and max matrix size
    let matrixSize = generationData.length;
    if (matrixSize < MATRIX_MIN_SIZE) {
        matrixSize = MATRIX_MIN_SIZE;
    }
    else if (matrixSize > MATRIX_MAX_SIZE) {
        matrixSize = MATRIX_MAX_SIZE
    }

    // Set the cell size considering the min and max values
    let cellSize = Math.trunc(CANVAS_SIZE / matrixSize) - CELL_SPACE_SIZE;
    if (cellSize < CELL_MIN_SIZE) {
        cellSize = CELL_MIN_SIZE;
    }
    else if (cellSize > CELL_MAX_SIZE) {
        cellSize = CELL_MAX_SIZE
    }

    if (generationCanvas.width !== CANVAS_SIZE || generationCanvas.height !== CANVAS_SIZE) {
        generationCanvas.width = CANVAS_SIZE;
        generationCanvas.height = CANVAS_SIZE;
    }

    if (matrixSize !== cachedMatrixSize || cellSize !== cachedCellSize) {
        cachedMatrixSize = matrixSize;
        cachedCellSize = cellSize;
        cachedXPositions = new Array(matrixSize);
        cachedYPositions = new Array(matrixSize);
        let pos = 0;
        for (let i = 0; i < matrixSize; i++) {
            cachedXPositions[i] = pos;
            cachedYPositions[i] = pos;
            pos += cellSize + CELL_SPACE_SIZE;
        }
    }

    // Clear to keep visible gaps, then batch dead and live cells into two fills
    generationContext.clearRect(0, 0, generationCanvas.width, generationCanvas.height);
    generationContext.beginPath();
    for (let y = 0; y < matrixSize; y++) {
        const row = generationData[y];
        const yPos = cachedYPositions[y];
        for (let x = 0; x < matrixSize; x++) {
            if (!row[x]) {
                generationContext.rect(cachedXPositions[x], yPos, cellSize, cellSize);
            }
        }
    }
    generationContext.fillStyle = 'lightgray';
    generationContext.fill();
    generationContext.beginPath();
    for (let y = 0; y < matrixSize; y++) {
        const row = generationData[y];
        const yPos = cachedYPositions[y];
        for (let x = 0; x < matrixSize; x++) {
            if (row[x]) {
                generationContext.rect(cachedXPositions[x], yPos, cellSize, cellSize);
            }
        }
    }
    generationContext.fillStyle = 'blue';
    generationContext.fill();
}
