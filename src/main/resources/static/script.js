

function sendMessage(){
    let message = document.getElementById("user-input").value
    console.log("This is your message: ", message)
    addToLogUser(message)
    messageReq(message)


}

function addToLogBot(message){
    
    const chatBox = document.getElementById("chat-box")
    const newDiv = document.createElement("div")

    const userText = document.createTextNode("BOT --> " + message)
    newDiv.setAttribute("id", "user-message")
    newDiv.appendChild(userText)
    chatBox.appendChild(newDiv)

}

function addToLogUser(message){

    const chatBox = document.getElementById("chat-box")
    const newDiv = document.createElement("div")

    const userText = document.createTextNode("USER --> " + message)
    newDiv.setAttribute("id", "bot-message")
    newDiv.appendChild(userText)
    chatBox.appendChild(newDiv)

}

function messageReq(message){
    fetch('http://localhost:8080/api/chat/message', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: message
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Could not fetch resource");
        }
        return response.text()  
    })
    .then(data => {
        console.log(data)
        addToLogBot(data)  // Display Claude's response
    })
    .catch(error => console.error(error))
    
}
