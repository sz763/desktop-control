let mouseMoveArea = document.getElementById("mouse-move-area");
let leftMouseBtn = document.getElementById("left-mouse-btn");
let rightMouseBtn = document.getElementById("right-mouse-btn");
let keyboardArea = document.getElementById("keyboard-area");
let backspaceBtn = document.getElementById("back-space");
var stompClient = null;
var lastClick = Date.now();


let data = {
    currentX: 0,
    currentY: 0,
    newX: 0,
    newY: 0,
    lastSentX: 0,
    lastSentY: 0,
    interval: {},
    isMousePressed: false
}

let handleMouseDown = (e) => {
    let clickTime = Date.now();
    if (clickTime - lastClick < 500) {
        handleLeftClick();
    }
    lastClick = clickTime;

    if (e.touches) {
        let touch = e.touches[0];
        data.currentX = touch.clientX;
        data.currentY = touch.clientY;
    } else {
        data.currentX = e.layerX;
        data.currentY = e.layerY;
    }
    data.isMousePressed = true;
    data.interval = setInterval(sendMousePoses, 5)
}
let handleMouseUp = () => {
    data.isMousePressed = false;
    clearInterval(data.interval);
}
let handleMouseMove = (e) => {
    if (!data.isMousePressed) {
        return;
    }
    if (e.changedTouches) {
        let touch = e.changedTouches[0];
        data.newX = touch.clientX - data.currentX
        data.newY = touch.clientY - data.currentY
    } else {
        data.newX = e.layerX - data.currentX
        data.newY = e.layerY - data.currentY
    }
}
let handleMouseClick = (e) => {
    console.log(e)
}
let handleRightClick = () => {
    handleClickButton("RIGHT")
}
let handleLeftClick = () => {
    handleClickButton("LEFT")
}
let sendMousePoses = () => {
    if (data.lastSentX == data.newX && data.lastSentY == data.newY) {
        return;
    }
    sendRequest("/mouse/move", {
        x: data.newX,
        y: data.newY
    });
    data.lastSentX = data.newX;
    data.lastSentY = data.newY;
}
let handleClickButton = (mouseButtonId) => {
    sendRequest("/mouse/click", {
        mbtn: mouseButtonId
    });
}
let sendRequest = (url, body) => {
    // httpRequest(url, body);
    websocketRequest(url, body);
}
let handleKeyboardEntry = (e) => {
    if (e.keyCode == 13) {
        sendRequest("/keyboard/text", {
            txt: keyboardArea.value
        });
    }
}
let handleBackspace = (e) => {
    sendRequest("/keyboard/backspace", {});
}

mouseMoveArea.addEventListener("mousedown", handleMouseDown);
mouseMoveArea.addEventListener("mouseup", handleMouseUp);
mouseMoveArea.addEventListener("mousemove", handleMouseMove);
mouseMoveArea.addEventListener("click", handleMouseClick);
mouseMoveArea.addEventListener("touchstart", handleMouseDown, false);
mouseMoveArea.addEventListener("touchend", handleMouseUp, false);
mouseMoveArea.addEventListener("touchcancel", handleMouseUp, false);
mouseMoveArea.addEventListener("touchmove", handleMouseMove, false);

leftMouseBtn.addEventListener("click", handleLeftClick);
rightMouseBtn.addEventListener("click", handleRightClick);
keyboardArea.addEventListener("keyup", handleKeyboardEntry);
backspaceBtn.addEventListener("click", handleBackspace);


let wsConnect = () => {
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
    });
}
wsConnect();

let httpRequest = (url, body) => {
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
    }).then((rsp) => {
    });
}
let websocketRequest = (url, body) => {
    stompClient.send('/events' + url, {}, JSON.stringify(body));
}

