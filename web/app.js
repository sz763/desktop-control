let data = {
    currentX : 0,
    currentY : 0,
    newX : 0,
    newY : 0,
    lastSentX : 0,
    lastSentY : 0,
    interval : {},
    isMousePressed : false
}
let handleMouseDown = (e) => {
    data.currentX = e.layerX;
    data.currentY = e.layerY;
    data.isMousePressed = true;
    data.interval = setInterval(sendMousePoses, 100)
}
let handleMouseUp = () => {
    data.isMousePressed = false;
    clearInterval(data.interval);
}
let handleMouseMove = (e) => {
    if (!data.isMousePressed) {
        return;
    }
    data.newX = e.layerX - data.currentX
    data.newY = e.layerY - data.currentY
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
    sendPostRequest("/mouse/move", {
        x: data.newX,
        y: data.newY
    });
    data.lastSentX = data.newX;
    data.lastSentY = data.newY;
}
let handleClickButton = (mouseButtonId) => {
    sendPostRequest("/mouse/click", {
        mouseButton: mouseButtonId
    });
}
let sendPostRequest = (url, body) => {
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
    }).then((rsp)=> {

    });
}
let handleKeyPress = (e) => {
    sendPostRequest("/keyboard", {
        key: e.key
    });
}
let clearArea = (e) => {
    e.target.value = '';
}


let mouseMoveArea = document.getElementById("mouse-move-area");
mouseMoveArea.addEventListener("mousedown", handleMouseDown);
mouseMoveArea.addEventListener("mouseup", handleMouseUp);
mouseMoveArea.addEventListener("mousemove", handleMouseMove);
mouseMoveArea.addEventListener("click", handleMouseClick);

let leftMouseBtn = document.getElementById("left-mouse-btn");
leftMouseBtn.addEventListener("click", handleLeftClick);

let rightMouseBtn = document.getElementById("right-mouse-btn");
rightMouseBtn.addEventListener("click", handleRightClick);

let keyboardArea = document.getElementById("keyboard-area")
keyboardArea.addEventListener("keypress", handleKeyPress)
keyboardArea.addEventListener("keyup", clearArea)