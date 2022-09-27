let stompClient = null;

function setConnected() {
    $("#buttonConnect").addClass("d-none");
    $("#buttonDisconnect").removeClass("d-none");
    $("#buttonConnecting").addClass("d-none");

    $("#alertBox").removeClass();
    $("#alertBox").addClass("alert alert-success");
    $("#alertBox").text("Connected :)");
}

function setDisconnected() {
    $("#buttonConnect").removeClass("d-none");
    $("#buttonDisconnect").addClass("d-none");
    $("#buttonConnecting").addClass("d-none");

    $("#alertBox").removeClass();
    $("#alertBox").addClass("alert alert-warning");
    $("#alertBox").text("Server not connected!");
}

function setConnectionPending() {
    $("#buttonConnect").addClass("d-none");
    $("#buttonDisconnect").addClass("d-none");
    $("#buttonConnecting").removeClass("d-none");
}

function connect() {
    setConnectionPending();

    let socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect(
        {}, // headers
        function (frame) { // connectCallback
            setConnected();
            console.log('Connected: ' + frame);

            stompClient.subscribe('/generation', function (generationData) {
                drawCellsFromData(JSON.parse(generationData.body).generationData);
            });
        },
        function (frame) { // errorCallback
            setDisconnected();
            console.log('Connection Failed: ' + frame);

            $("#alertBox").removeClass();
            $("#alertBox").addClass("alert alert-danger");
            $("#alertBox").text("Connection failed!");
        }
    );
}

function disconnect() {
    setDisconnected();

    if (stompClient !== null) {
        stompClient.disconnect();
    }

    console.log("Disconnected");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#buttonConnect").click(function() { connect(); });
    $("#buttonDisconnect").click(function() { disconnect(); });

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
    setDisconnected();
});

function drawCellsFromData(generationData) {
    const canvas = document.querySelector("#generationDataCanvas");
    const context = canvas.getContext("2d");
    const canvasSize = 700;

    // Set the generation size considering the min and max matrix size
    const matrixMinSize = 5;
    const matrixMaxSIze = 256;
    let matrixSize = generationData.length;
    if (matrixSize < matrixMinSize) {
        matrixSize = matrixMinSize;
    }
    else if (matrixSize > matrixMaxSIze) {
        matrixSize = matrixMaxSIze
    }

    canvas.width = canvasSize;
    canvas.height = canvasSize;

    // Set the cell size considering the min and max values
    const cellSpaceSize = 3;
    const cellMinSize = 8;
    const cellMaxSize = 64;
    let cellSize = Math.trunc(canvasSize / matrixSize) - cellSpaceSize;
    if (cellSize < cellMinSize) {
        cellSize = cellMinSize;
    }
    else if (cellSize > cellMaxSize) {
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
