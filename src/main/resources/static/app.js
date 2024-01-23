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
    $.post("/action/onConnect");
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

            stompClient.subscribe('/display', function (displayData) {
                drawCellsFromData(JSON.parse(displayData.body).displayData);
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
    buttonConnect.click(function () {
        connect();
    });
    buttonDisconnect.click(function () {
        disconnect();
    });

    $("#buttonStop").click(function () {
        // https://api.jquery.com/jquery.post/
        $.post("/action/stop");
    });

    $("#buttonPlay").click(function () {
        // https://api.jquery.com/jquery.post/
        $.post("/action/play");
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

function drawCellsFromData(displayData) {
    const canvas = document.querySelector("#generationDataCanvas");
    const context = canvas.getContext("2d");
    const canvasSize = 700;

    // Set the generation size considering the min and max matrix size
    const matrixMinSize = 5;
    const matrixMaxSize = 256;
    let matrixSize = displayData.length;
    if (matrixSize < matrixMinSize) {
        matrixSize = matrixMinSize;
    } else if (matrixSize > matrixMaxSize) {
        matrixSize = matrixMaxSize
    }

    canvas.width = canvasSize;
    canvas.height = canvasSize;

    // Set the cell size considering the min and max values
    const cellSpaceSize = 1;
    const cellMinSize = 8;
    const cellMaxSize = 64;
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

            const cell = displayData[y][x],
                type = cell.type,
                orientation = cell.orientation;

            if (type === "SNAKE_HEAD") {
                context.drawImage(getImage(type, orientation), 0, 0, 40, 40, nextXPos, nextYPos, cellSize, cellSize);
                context.fillStyle = "rgba(255, 255, 255, 0)";
            } else if (type === "SNAKE_BODY") {
                context.drawImage(getImage(type, orientation), 0, 0, 40, 40, nextXPos, nextYPos, cellSize, cellSize);
                context.fillStyle = "rgba(255, 255, 255, 0)";
            } else if (type === "SNAKE_TAIL") {
                context.drawImage(getImage(type, orientation), 0, 0, 40, 40, nextXPos, nextYPos, cellSize, cellSize);
                context.fillStyle = "rgba(255, 255, 255, 0)";
            } else if (type === "FOOD") {
                context.drawImage(getImage(type, orientation), 0, 0, 40, 40, nextXPos, nextYPos, cellSize, cellSize);
                context.fillStyle = "rgba(255, 255, 255, 0)";
            } else if (type === "WALL") {
                context.fillStyle = "blue";
            } else if (type === "EMPTY") {
                context.fillStyle = "lightgrey";
            }

            context.fillRect(nextXPos, nextYPos, cellSize, cellSize);
            nextXPos += cellSize + cellSpaceSize;
        }

        nextXPos = 0;
        nextYPos += cellSize + cellSpaceSize;
    }
}

function getImage(type, orientation) {
    if (type === "SNAKE_BODY") {
        return document.getElementById(type + "(" + orientation + ")");
    } else if (type === "SNAKE_HEAD" || type === "SNAKE_TAIL") {
        return document.getElementById(type + "(" + orientation.split("_")[1] + ")");
    } else if (type === "FOOD") {
        return document.getElementById(type);
    }
}

document.onkeydown = function (e) {
    let orientation;
    switch (e.key) {
        case "ArrowUp":
            orientation = "UP";
            break;
        case "ArrowDown":
            orientation = "DOWN";
            break;
        case "ArrowLeft":
            orientation = "LEFT";
            break;
        case "ArrowRight":
            orientation = "RIGHT";
            break;
    }

    if (orientation === undefined) {
        return;
    }

    $.ajax({
        url: "/action/changeOrientation",
        type: "POST",
        data: JSON.stringify({orientation: orientation}),
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

}